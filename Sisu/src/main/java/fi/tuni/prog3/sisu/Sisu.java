package fi.tuni.prog3.sisu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Lauri Puoskari
 */

public class Sisu {
    private Student activeStudent;
    private ArrayList<Student> savedStudents;
    private TreeMap<String, Degree> degrees;
    private Degree selectedDegree;
    //private TreeMap<String, Module> modules;
    

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
        for ( Student savedStudent : this.savedStudents){
            if(savedStudent.getStudentNr().equals(this.activeStudent.getStudentNr())){
                this.activeStudent.setActiveDegree(savedStudent.getActiveDegree());
                this.activeStudent.setActiveStudyField(savedStudent.getActiveStudyField());
                this.activeStudent.setCompletions(savedStudent.getCompletions());
                this.savedStudents.remove(savedStudent);
                this.savedStudents.add(this.activeStudent);
            }
        }

        // TODO: Haetaan tiedot tutkinto-ohjelmista, luodaan ne ja talletetaan
        //       this.degrees
        this.degrees = JSONHandler.readDegrees();
    }
    
    public Student getActiveStudent() {
        return this.activeStudent;
    }
    
    public ArrayList<String> getDegrees() {
        return new ArrayList<>(this.degrees.keySet());
    }
    
    public boolean saveStudentData() throws IOException {
        
        try{
            JSONHandler.writeAllStudentData(this.savedStudents);
            return true;
        } catch(IOException ex){
            return false;
        }
    }
    
    public void setSelectedDegree(String selectedDegreeStr) {
        this.selectedDegree = this.degrees.get(selectedDegreeStr);
    }
    
    public Degree getSelectedDegree() {
        return this.selectedDegree;
    }
    
    public void setModules() throws IOException {
        JSONHandler.readDegree(this.selectedDegree);
    }
    
    public ArrayList<String> getModules() {
        return new ArrayList<>(this.selectedDegree.getStudyFields().keySet());
    }
    
    public ArrayList<String> getStudyFields() {
        return new ArrayList<>(this.selectedDegree.getStudyFields().keySet());
    }
    
    public TreeItem<String> getModuleContent(Module module) throws IOException {
//        for (var m : this.selectedDegree.getStudyFields().keySet()) {
//            System.out.println(m);
//        }
//        Module module = JSONHandler.readModule(
//                this.selectedDegree.getStudyFields().get(moduleStr).getId());
        TreeItem<String> moduleTi = new TreeItem<>(module.getName());
        for (var subModule : module.getModules().entrySet()) {
            moduleTi.getChildren().add(getModuleContent(subModule.getValue()));
        }
        for (String course : module.getCourses().keySet()) {
            TreeItem<String> courseTi = new TreeItem<>(course.toString());
            moduleTi.getChildren().add(courseTi);
        }
        return moduleTi;
    }

//    //Tämä metodi on vain ohjelman testaamista varten kunnes API saadaan käyttöön.
//    public void initForTests() {
//        /*Course course1 = new Course(5, new ArrayList<Degree>(), "Kurssi 1", "AAA-001");
//        Course course2 = new Course(5, new ArrayList<Degree>(), "Kurssi 2", "BBB-002");
//        Course course3 = new Course(5, new ArrayList<Degree>(), "Kurssi 3", "CCC-003");
//        Course course4 = new Course(5, new ArrayList<Degree>(), "Kurssi 4", "DDD-004");
//        Course course5 = new Course(5, new ArrayList<Degree>(), "Kurssi 5", "EEE-005");
//        Course course6 = new Course(5, new ArrayList<Degree>(), "Kurssi 6", "FFF-006");
//        Course course7 = new Course(5, new ArrayList<Degree>(), "Kurssi 7", "GGG-007");
//        Course course8 = new Course(5, new ArrayList<Degree>(), "Kurssi 8", "HHH-008");
//        Course course9 = new Course(5, new ArrayList<Degree>(), "Kurssi 9", "III-009");
//        
//        ArrayList<Course> courses1_3 = new ArrayList<>();
//        courses1_3.add(course1);
//        courses1_3.add(course2);
//        courses1_3.add(course3);
//        
//        ArrayList<Course> courses4_6 = new ArrayList<>();
//        courses1_3.add(course4);
//        courses1_3.add(course5);
//        courses1_3.add(course6);
//        
//        ArrayList<Course> courses7_9 = new ArrayList<>();
//        courses1_3.add(course7);
//        courses1_3.add(course8);
//        courses1_3.add(course9);*/
//    }
//    
}