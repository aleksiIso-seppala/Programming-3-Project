/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.nio.charset.*;
import java.util.ArrayList;
import com.google.gson.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.nio.file.Files;
/**
 *
 * @author Aleksi Iso-Seppälä
 */
public class JSONHandler {
    
    private final static String STUDENT_FILE = "StudentData.json";

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

            while(rules.get("type").getAsString().equals("CreditsRule")){
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
                    selectedDegree.addStudyField(module);
 
                }
                else{
                    TreeMap<String,Course> courses = new TreeMap<>();
                    Module studyField = new Module(courses,selectedDegree.getName(),selectedDegree.getId(),selectedDegree.getStudyPoints());
                    readArray(innerRule,studyField);
                    selectedDegree.addStudyField(studyField);
                }
            }
        }
        
        return;
    } 
    
//    public static TreeMap<String, Module> readModules(Degree selectedDegree) throws 
//                                        MalformedURLException, IOException {
//        TreeMap<String, Module> modules = new TreeMap<>();
//        
//        String groupId = selectedDegree.getId();
//        
//        String mUrl = "https://sis-tuni.funidata.fi/kori/api/modules/"
//        + "by-group-id?groupId=" + groupId + 
//        "&universityId=tuni-university-root-id";
//        
//        URL url = new URL(mUrl);
//        BufferedReader input = new BufferedReader(
//            new InputStreamReader(url.openStream()));
//        Gson gson = new Gson();
//        JsonArray response = gson.fromJson(input,JsonArray.class);  
//        
//        for(var object : response) {
//            JsonObject degree = (JsonObject) object;
//            if(degree == null){
//                continue;
//            }
//            
//            String name;
//            JsonObject nameObject = degree.getAsJsonObject("name");
//            if(nameObject.get("fi") == null){
//                name = null;
//            }
//            else{
//                name = nameObject.get("fi").toString();               
//            }
//            
//            if (name != null) {
//                if (name.equals(selectedDegree.getName())) {
//                    JsonObject rules = degree.getAsJsonObject("rule");
//                    var innerRule = rules.getAsJsonArray("rules");
//                    if(innerRule == null){
//                        var tempRule = rules.getAsJsonObject("rule");
//                        innerRule = tempRule.getAsJsonArray("rules");
//                    }
//
//                    for(var rule : innerRule){
//                        JsonObject object1 = rule.getAsJsonObject();
//                        if(object1.get("type").getAsString().equals("ModuleRule")){
//                            String moduleId = object1.get("moduleGroupId").getAsString();
//                            Module module = readModule(moduleId);
//                            modules.put(module.getName(), module);
//                        }
//                    }
//                }
//            }
//        }
//        
//        return modules;
//    }
    
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
                user.addProperty("activeDegree",student.getActiveDegree().getName());  
            }

            if(student.getActiveStudyField() != null){
                user.addProperty("activeStudyField",student.getActiveStudyField().getName());  
            }
            JsonArray completions = new JsonArray();

            for(var completion : student.getCompletions().entrySet()){
                    completions.add(completion.getKey().getId());
            }
            user.add("completions", completions);
            userData.add(user);
        }
        
        gson.toJson(userData, writer);
        writer.close();
        
    }
    
    public static ArrayList<Student> readAllStudentData() throws IOException {
        
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(STUDENT_FILE));
        JsonArray response = gson.fromJson(reader, JsonArray.class);
        
        
        ArrayList<Student> students = new ArrayList<>();
        
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
    
    
    public static void main(String args[]) throws IOException {
        var degrees = readDegrees();
        for(var degree : degrees.entrySet()){
            readDegree(degree.getValue());
        }
//        Student st = new Student("Matti","007","2015");
//        Student st2 = new Student("Maija","1");
//        ArrayList<Student> students = new ArrayList<>();
//        Course course1 = new Course(5,"Tietotekniikka","tut-1");
//        Course course2 = new Course(5,"sähkötekniikka","tut-2");
//        Course course3 = new Course(5,"signaalit ja mittaus","tut-3");
//        Course course4 = new Course(5,"ohjemlointi 1","tut-4");        
//        Course course5 = new Course(5,"ohjelmointi 2","tut-5");
//        Course course6 = new Course(5,"tetapk","tut-6");
//        students.add(st);
//        students.add(st2);
//        st.completeCourse(course1);
//        st.completeCourse(course2);
//        st.completeCourse(course3);
//        st2.completeCourse(course4);
//        st2.completeCourse(course5);
//        st2.completeCourse(course6);
//        writeAllStudentData(students);
    }
    
    
}

