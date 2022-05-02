package fi.tuni.prog3.sisu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import javafx.scene.control.TreeItem;

/**
 * Luokka, joka sisältää Sisu-ohjelman toiminnallisuuden.
 * @author Lauri Puoskari
 */
public class Sisu {
    /**
     * Opiskelija, jonka opintotietoja tarkastellaan ja muokataan.
     */
    private Student activeStudent;
    /**
     * Lista kaikista StudentData.json -tiedostoon tallennetuista opiskelijoista.
     */
    private ArrayList<Student> savedStudents;
    /**
     * Map kaikista tutkinto-ohjelmista.
     */
    private TreeMap<String, Degree> degrees;
    /**
     * Valittu tutkinto-ohjelma.
     */
    private Degree selectedDegree;
    /**
     * Totuusarvo siitä, onko opiskelijan tietojen tallennus onnistunut.
     */
    private boolean isSuccessfullySaved;
    /**
     * Totuusarvo siitä, löytyikö annettuja tietoja vastaavaa opiskelijaa
     * StudentData.json -tiedostosta.
     */
    private boolean isStudentFound;
    /**
     * Map kaikista tutkinto-ohjelmaan sisältyvistä kursseista.
     */
    private TreeMap<String,Course> coursesInCompletionsView;
    
    /**
     * Sisu-luokan rakentaja.
     * @param studentName Opiskelijan nimi.
     * @param studentNr Opiskelijan opiskelijanumero.
     * @param years Taulukko opiskelijan aloitus- ja valmistumisvuosista. Jos
     * taulukossa on vain yksi alkio, kyseessä on aloitusvuosi.
     * @throws IOException 
     */
    public Sisu(String studentName, String studentNr, String... years) throws IOException {
        switch (years.length) {
            case 0:
                activeStudent = new Student(studentName, studentNr);
                break;
            case 1:
                activeStudent = new Student(studentName, studentNr, years[0]);
                break;
            case 2:
                activeStudent = new Student(studentName, studentNr,
                                            years[0], years[1]);
                break;
            default:
                break;
        }
        this.savedStudents = JSONHandler.readAllStudentData();
        this.isStudentFound = false;
        int studentIndex = 0;
        for (Student savedStudent : this.savedStudents){
            if(savedStudent.getStudentNr().equals(this.activeStudent.getStudentNr())){
                this.isStudentFound = true;
                studentIndex = this.savedStudents.indexOf(savedStudent);
                System.out.println("Student is found");
            }
        }
        if (this.isStudentFound) {
            Student savedStudent = this.savedStudents.get(studentIndex);
            if (savedStudent.getActiveDegree() != null) {
                this.activeStudent.setActiveDegree(savedStudent.getActiveDegree());
                this.selectedDegree = savedStudent.getActiveDegree();
            }
            if (savedStudent.getActiveStudyField() != null) {
                this.activeStudent.setActiveStudyField(savedStudent.
                                                        getActiveStudyField());
                this.selectedDegree.setSelectedField(
                                savedStudent.getActiveStudyField().getName());
            }
            this.activeStudent.setCompletions(savedStudent.getCompletions());
            this.savedStudents.remove(savedStudent);
        }
        this.savedStudents.add(this.activeStudent);
        this.degrees = JSONHandler.readDegrees();
        this.coursesInCompletionsView = new TreeMap<>();
    }
    
    /**
     * Palauttaa opiskelijan, jonka opintotietoja tarkastellaan.
     * @return Kyseinen opiskelija.
     */
    public Student getActiveStudent() {
        return this.activeStudent;
    }
    
    /**
     * Palauttaa listan kaikkien tutkinto-ohjelmien nimistä.
     * @return lista kaikkien tutkinto-ohjelmien nimistä.
     */
    public ArrayList<String> getDegrees() {
        return new ArrayList<>(this.degrees.keySet());
    }
    
    /**
     * Tallentaa opiskelijan tiedot StudentData.json -tiedostoon.
     * @throws IOException 
     */
    public void saveStudentData() throws IOException {
        
        try{
            JSONHandler.writeAllStudentData(this.savedStudents);
            this.isSuccessfullySaved = true;
        } catch(IOException ex){
            this.isSuccessfullySaved = false;
        }
    }
    
    /**
     * Asettaa tutkinto-ohjelman valituksi.
     * @param selectedDegreeStr Valittu tutkinto-ohjelma.
     */
    public void setSelectedDegree(String selectedDegreeStr) {
        this.selectedDegree = this.degrees.get(selectedDegreeStr);
    }
    
    /**
     * Palauttaa valitun tutkinto-ohjelman.
     * @return Valittu tutkinto-ohjelma.
     */
    public Degree getSelectedDegree() {
        return this.selectedDegree;
    }
    
    /**
     * Lukee valitun tutkinto-ohjelman sisällön ja tallentaa sen Degree-luokkaan.
     * @throws IOException 
     */
    public void initModules() throws IOException {
        JSONHandler.readDegree(this.selectedDegree);
    }
    
    /**
     * Palauttaa listan valitun tutkinto-ohjelman alimoduulien nimistä.
     * @return Lista valitun tutkinto-ohjelman alimoduulien nimistä.
     */
    public ArrayList<String> getModules() {
        return new ArrayList<>(this.selectedDegree.getStudyFields().keySet());
    }
    
    /**
     * Palauttaa listan valitun tutkinto-ohjelman opintosuuntien nimistä.
     * @return lista valitun tutkinto-ohjelman opintosuuntien nimistä.
     */
    public ArrayList<String> getStudyFields() {
        return new ArrayList<>(this.selectedDegree.getStudyFields().keySet());
    }
    
    /**
     * Lukee moduulin sisällön ja palauttaa sen TreeIteminä.
     * @param module Luettava moduuli.
     * @return Luettu moduuli TreeIteminä.
     * @throws IOException 
     */
    public TreeItem<String> getModuleContent(Module module) throws IOException {
        TreeItem<String> moduleTi = new TreeItem<>(module.getName());
        for (var subModule : module.getModules().entrySet()) {
            moduleTi.getChildren().add(getModuleContent(subModule.getValue()));
        }
        for (String course : module.getCourses().keySet()) {
            TreeItem<String> courseTi = new TreeItem<>(course);
            moduleTi.getChildren().add(courseTi);
            this.addToCoursesInCompletionsView(module.getCourses().get(course));
            this.getSelectedDegree().addToAllSubCourses(module.getCourses().get(course));
        }
        return moduleTi;
    }
    
    /**
     * Palauttaa totuusarvon siitä, onnistuiko opiskelijan tietojen tallennus.
     * @return Totuusarvo opiskelijan tietojen tallentamisen onnistumisesta.
     */
    public boolean getIsSuccessfullySaved() {
        return this.isSuccessfullySaved;
    }
    
    /**
     * Palauttaa listan valitun tutkinto-ohjelman kaikkien kurssien nimistä.
     * @return Lista kaikkien tutkinto-ohjelmaan sisältyvien kurssien nimistä.
     */
    public TreeMap<String,Course> getCoursesInCompletionsView() {
        return this.coursesInCompletionsView;
    }
    
    /**
     * Lisää annetun kurssin valitun tutkinto-ohjelman kaikkiin kursseihin.
     * @param course Lisättävä kurssi.
     */
    public void addToCoursesInCompletionsView(Course course) {
        this.coursesInCompletionsView.put(course.getName(), course);
    }
    
    /**
     * Tyhjentää listan valitun tutkinto-ohjelman kaikista kursseista.
     */
    public void clearCoursesInCompletionsView() {
        this.coursesInCompletionsView.clear();
    }
    
    /**
     * Palauttaa totuusarvon, onko annettuja opiskelijan tietoja vastaavaa
     * opiskelijaa löytynyt StudentData.json -tiedostosta.
     * @return Totuusarvo opiskelijan löytymisestä.
     */
    public boolean getIsStudentFound() {
        return this.isStudentFound;
    }
}