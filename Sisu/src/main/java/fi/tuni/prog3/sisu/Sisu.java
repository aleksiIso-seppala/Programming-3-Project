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
    private boolean isSuccessfullySaved;
    private TreeMap<String,Course> allSubCourses;
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
        boolean isInSavedStudents = false;
        int studentIndex = 0;
        for ( Student savedStudent : this.savedStudents){
            if(savedStudent.getStudentNr().equals(this.activeStudent.getStudentNr())){
                isInSavedStudents = true;
                studentIndex = this.savedStudents.indexOf(savedStudent);
//                this.activeStudent.setActiveDegree(savedStudent.getActiveDegree());
//                this.activeStudent.setActiveStudyField(savedStudent.getActiveStudyField());
//                this.activeStudent.setCompletions(savedStudent.getCompletions());
//                this.savedStudents.remove(savedStudent);
//                this.savedStudents.add(this.activeStudent);
            }
//            else {
//                isInSavedStudents = false;
//                this.savedStudents.add(this.activeStudent);
//            }
        }
        if (isInSavedStudents) {
            Student savedStudent = this.savedStudents.get(studentIndex);
            this.activeStudent.setActiveDegree(savedStudent.getActiveDegree());
            this.activeStudent.setActiveStudyField(savedStudent.getActiveStudyField());
            this.activeStudent.setCompletions(savedStudent.getCompletions());
            this.savedStudents.remove(savedStudent);
        }
        this.savedStudents.add(this.activeStudent);

        // TODO: Haetaan tiedot tutkinto-ohjelmista, luodaan ne ja talletetaan
        //       this.degrees
        this.degrees = JSONHandler.readDegrees();
        
        this.allSubCourses = new TreeMap<>();
    }
    
    public Student getActiveStudent() {
        return this.activeStudent;
    }
    
    public ArrayList<String> getDegrees() {
        return new ArrayList<>(this.degrees.keySet());
    }
    
    public void saveStudentData() throws IOException {
        
        try{
            JSONHandler.writeAllStudentData(this.savedStudents);
            this.isSuccessfullySaved = true;
        } catch(IOException ex){
            this.isSuccessfullySaved = false;
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
            TreeItem<String> courseTi = new TreeItem<>(course);
//            this.selectedDegree.getSelectedField().
//                            addToAllSubCourses(module.getCourses().get(course));
            moduleTi.getChildren().add(courseTi);
            this.addToAllSubCourses(module.getCourses().get(course));
        }
        return moduleTi;
    }
    
    public boolean getIsSuccessfullySaved() {
        return this.isSuccessfullySaved;
    }
    
    public TreeMap<String,Course> getAllSubCourses() {
        return this.allSubCourses;
    }
    
    public void addToAllSubCourses(Course course) {
        this.allSubCourses.put(course.getName(), course);
    }
}