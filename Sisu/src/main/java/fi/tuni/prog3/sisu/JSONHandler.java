/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.util.ArrayList;
import com.google.gson.*;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.nio.file.Files;
/**
 *
 * @author Aleksi Iso-Seppälä
 */
public class JSONHandler {
    
    private final static String STUDENT_FILE = "StudentData.json";
    
    /**
     * Käy läpi sisun tutkinto-ohjelmat.
     * @return Treemap, joka sisältää kaikki tutkinto-ohjelmat.
     * @throws MalformedURLException new URL ei toimi (virheellinen osoite)
     * @throws IOException virheellinen new URL ei toimi (virheellinen osoite)
     */    
    public static TreeMap<String, Degree> readDegrees() throws MalformedURLException, IOException{
        
        TreeMap<String, Degree> degrees = new TreeMap<>();

        String sUrl = "https://sis-tuni.funidata.fi/kori/api/module-search?"
                + "curriculumPeriodId=uta-lvv-2021&universityId=tuni-university"
                + "-root-id&moduleType=DegreeProgramme&limit=1000";
        
        URL url = new URL(sUrl);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));

        Gson gson = new Gson();
        JsonObject response = gson.fromJson(in,JsonObject.class);
        JsonElement rootElement = response.get("searchResults");
        JsonArray degreesJson = rootElement.getAsJsonArray();
        
        for(var object : degreesJson){
            JsonObject degree = (JsonObject) object;
            String groupId = degree.get("groupId").getAsString();
            String name = degree.get("name").getAsString();
            
            var creditsO = degree.getAsJsonObject("credits");
            int credits = creditsO.get("min").getAsInt();            
            
            Degree degreeClass = new Degree(credits, name, groupId);
            degrees.put(name, degreeClass);
        }

        return degrees;
    }
    
    /**
     * lukee halutun tutkinto-ohjelman alaiset moduulit ja kurssit
     * @param selectedDegree valittu tutkinto-ohjelma
     * @throws MalformedURLException new URL ei toimi (virheellinen osoite)
     * @throws IOException new URL ei toimi (virheellinen osoite)
     */
    public static void readDegree(Degree selectedDegree) throws MalformedURLException, IOException{
        
        String groupId = selectedDegree.getId();
        String mUrl = "https://sis-tuni.funidata.fi/kori/api/modules/"
        + "by-group-id?groupId=" + groupId + 
        "&universityId=tuni-university-root-id";
        
        URL url = new URL(mUrl);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(url.openStream()));
        Gson gson = new Gson();
        JsonArray response = gson.fromJson(input,JsonArray.class);        
        
        for(var object : response){
            
            JsonObject degree = (JsonObject) object;
            
            JsonObject rules = degree.getAsJsonObject("rule");
            
            boolean hasStudyFields = true;
            
            while(rules.get("type").getAsString().equals("CreditsRule")){
                if(rules.get("credits") != null){
                    hasStudyFields = false;
                }
                rules = rules.getAsJsonObject("rule");
            }
           
            var innerRule = rules.getAsJsonArray("rules");
            OUTER:
            while(true){
                for(var rule : innerRule){
                    JsonObject object1 = rule.getAsJsonObject();
                    if(object1.get("type").getAsString().equals("CompositeRule")){
                        innerRule = object1.getAsJsonArray("rules");
                        break;
                    }
                    else if(object1.get("type").getAsString().equals("ModuleRule")){
                        break OUTER;
                    }
                }               
            }

            int counter = 0;
            for(var rule : innerRule){
                JsonObject object1 = rule.getAsJsonObject();
                if(object1.get("type").getAsString().equals("ModuleRule")){
                    String moduleId = object1.get("moduleGroupId").getAsString();
                    Module module = readModule(moduleId);
                    if(hasStudyFields){
                        selectedDegree.addStudyField(module);                     
                    }
                    else{
                        selectedDegree.addModule(module);
                    }
 
                }
                else{
                    TreeMap<String,Course> courses = new TreeMap<>();
                    Module studyField = new Module(courses,selectedDegree.getName(),selectedDegree.getId(),selectedDegree.getStudyPoints());
                    readArray(innerRule,studyField);
                    if(hasStudyFields){
                        selectedDegree.addStudyField(studyField);                     
                    }
                    else{
                        selectedDegree.addModule(studyField);
                    }
                }
            }
        }
        
        return;
    } 
    
    /**
     * lukee modulin ja sen alimoduulit
     * @param groupId moduulin groupId, jolla se haetaan Sisusta.
     * @return palauttaa Moduulin luokkana.
     * @throws MalformedURLException new URL ei toimi (virheellinen osoite)
     * @throws IOException new URL ei toimi (virheellinen osoite)
     */
    public static Module readModule(String groupId) throws MalformedURLException, IOException{
        
        String sUrl = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="
                + groupId + "&universityId=tuni-university-root-id";
        URL url = new URL(sUrl);
        BufferedReader input = new BufferedReader (
                new InputStreamReader(url.openStream()));
        Gson gson = new Gson(); 
        JsonArray response = gson.fromJson(input, JsonArray.class);
        
        for (var object : response){
            
            JsonObject moduleO = (JsonObject) object;
            var rules = moduleO.getAsJsonObject("rule");
            
            int credits;
            var creditsO = moduleO.getAsJsonObject("targetCredits");
            if(creditsO != null){
                credits = creditsO.get("min").getAsInt();               
            }
            else{
                credits = -1;
            }
            String name = null;
            var nameO = moduleO.getAsJsonObject("name");
            if(nameO.get("fi") == null){
                name = nameO.get("en").getAsString();
            }
            else{
                name = nameO.get("fi").getAsString();
            }

            
            TreeMap<String,Course> courses = new TreeMap<>();
            Module module = new Module(courses, name, groupId, credits);
            
            while(rules.get("type").getAsString().equals("CreditsRule")){
                rules = rules.getAsJsonObject("rule");
            }
            
            var innerArray = rules.getAsJsonArray("rules");
            readArray(innerArray, module);
            return module;
        }
        return null;
    }
    
    /**
     * Rekursiivinen funktio, joka käy läpi moduulin "kerroksia" eli listoja,
     * kunnes löydetään moduulin alimoduulit tai kurssit
     * @param array käsiteltävä lista.
     * @param module Moduuli, jonka alla käsiteltävä lista on
     * @throws IOException new URL ei toimi (virheellinen osoite)
     */
    public static void readArray(JsonArray array, Module module) throws IOException{
                
        for(JsonElement element : array){
            if(element.isJsonArray()){
                readArray(element.getAsJsonArray(), module);
                continue;
            }
            
            JsonObject object = element.getAsJsonObject();
            switch (object.get("type").getAsString()) {
                case "ModuleRule":
                    String moduleId = object.get("moduleGroupId").getAsString();
                    Module innerModule =  readModule(moduleId);
                    module.addModule(innerModule);
                    break;
                case "CourseUnitRule":
                    String courseId = object.get("courseUnitGroupId").getAsString();
                    Course course = readCourse(courseId);
                    module.addCourse(course);
                    break;
                case "CompositeRule":
                    JsonArray innerArray = object.getAsJsonArray("rules");
                    readArray(innerArray,module);
                    break;
                case "AnyCourseUnitRule":
                    break;
                case "AnyModuleRule":
                    break;
                case "CreditsRule":
                    while(object.get("type").getAsString().equals("CreditsRule")){
                        object = object.getAsJsonObject("rule");
                    }
                    innerArray = object.getAsJsonArray("rules");
                    readArray(innerArray,module);
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * Käy läpi kurssin tiedot Sisusta, ja luo sen pohjalta Course-olion.
     * @param courseId Kurssin Id, jonka avulla tiedot haetaan
     * @return Luotu kurssi-olio.
     * @throws MalformedURLException new URL ei toimi (virheellinen osoite)
     * @throws IOException new URL ei toimi (virheellinen osoite)
     */
    public static Course readCourse(String courseId) throws MalformedURLException, IOException{
        
        String sUrl = "https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId="
                + courseId + "&universityId=tuni-university-root-id";
        URL url = new URL(sUrl);
        BufferedReader input = new BufferedReader (
                new InputStreamReader(url.openStream()));
        Gson gson = new Gson();
        JsonArray response = gson.fromJson(input, JsonArray.class);
        
        for(var data : response){
            JsonObject courseO = (JsonObject) data;
            
            var creditsO = courseO.getAsJsonObject("credits");
            int credits = creditsO.get("min").getAsInt();
            
            var nameO = courseO.getAsJsonObject("name");
            String name;          
            
            if(nameO.get("fi") == null){
                name = nameO.get("en").getAsString();
            }
            else{
                name = nameO.get("fi").getAsString();
            }
            
            Course course = new Course(credits, name, courseId);
            
            return course;
        }
        return null;
    }
    
    /**
     * Kirjoittaa kaikki tallennetut opiskelijat JSON-tiedostoon opiskelijatietojen
     * tallennusta varten.
     * @param students Lista tallennettavista opiskelijoista
     * @throws IOException Tiedoston avaaminen ei onnistu
     */
    public static void writeAllStudentData(ArrayList<Student> students) throws IOException {
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer  = new FileWriter(STUDENT_FILE);
        JsonArray userData = new JsonArray();
        for(var student : students){
            JsonObject user = new JsonObject();
            user.addProperty("studentName",student.getName());
            user.addProperty("studentNr",student.getStudentNr());
            user.addProperty("startingYear",student.getStartingYear());
            user.addProperty("finishingYear",student.getFinishingYear());
            if(student.getActiveDegree() != null){
                user.addProperty("activeDegree",student.getActiveDegree().getId());  
            }

            if(student.getActiveStudyField() != null){
                user.addProperty("activeStudyField",student.getActiveStudyField().getId());  
            }
            JsonArray completions = new JsonArray();

            for(var completion : student.getCompletions().entrySet()){
                    completions.add(completion.getValue().getId());
            }
            user.add("completions", completions);
            userData.add(user);
        }
        
        gson.toJson(userData, writer);
        writer.close();
        
    }
    
    /**
     * Lukee tallennetut opiskelijat JSON-tiedostosta. Tallentaa luetut opiskelijat olioina listaan.
     * @return Palauttaa listan opiskelija-olioista.
     * @throws IOException tiedoston avaaminen ei onnistu
     */
    public static ArrayList<Student> readAllStudentData(TreeMap<String, Degree> degrees) throws IOException {
        
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(STUDENT_FILE));
        JsonArray response = gson.fromJson(reader, JsonArray.class);
            
        
        ArrayList<Student> students = new ArrayList<>();
        
        if(response == null){
            return students;
        }
        
        for(var data : response){
            JsonObject element = (JsonObject) data;
            String name = element.get("studentName").getAsString();
            String studentNumber  = element.get("studentNr").getAsString();
            
            
            String startingYear = null;
            String finishingYear = null;
            if(element.get("startingYear") != null){
                startingYear = element.get("startingYear").getAsString();
                if(element.get("finishingYear") != null){
                    finishingYear = element.get("finishingYear").getAsString();
                }
            }
            
            Student student = new Student(name,studentNumber,startingYear,finishingYear);            
            
            
            if(element.get("activeDegree") != null){
                String activeDegree = element.get("activeDegree").getAsString();
                Degree selectedDegree = null;
                for(var degree : degrees.entrySet()){
                    if(degree.getValue().getId().equals(activeDegree)){
                        selectedDegree = degree.getValue();
                        break;
                    }
                }
                readDegree(selectedDegree);
                student.setActiveDegree(selectedDegree);
                
                if(element.get("activeStudyField") != null){
                    String activeStudyField = element.get("activeStudyField").getAsString();
                    Module selectedStudyField = null;
                    for(var studyfield : selectedDegree.getStudyFields().entrySet()){
                        if(studyfield.getValue().getId().equals(activeStudyField)){
                            selectedStudyField = studyfield.getValue();
                            break;
                        }
                    }                 
                    student.setActiveStudyField(selectedStudyField);
                } 
            }
            
           

            
            JsonArray completions = element.getAsJsonArray("completions");
            for(var course : completions){
                String courseId = course.getAsString();
                Course courseClass = readCourse(courseId);
                if (courseClass != null) {
                    student.completeCourse(courseClass);
                }
            }
            students.add(student);
        }

        return students;
        
    }   
}

