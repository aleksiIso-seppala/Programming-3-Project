
package fi.tuni.prog3.sisu;

import java.util.TreeMap;

/**
 *
 * @author Lauri Kalliojärvi
 * 
 * Methods getStartingYear() and getFinishingYear return null if not set.
 */

public class Student {
    private String name;
    private String studentNr;
    private String startingYear = null;
    private String finishingYear = null;
    private TreeMap<Course, Integer> completions;
    private Degree activeDegree;
    private StudyField activeStudyField;
    
    public Student(String name,
            String studentNr,
            TreeMap<Course, Integer> completions,
            Degree activeDegree,
            StudyField activeStudyField ) {
        
        this.name = name;
        this.studentNr = studentNr;
        this.completions = completions;
        this.activeDegree = activeDegree;
        this.activeStudyField = activeStudyField;
        
    }
    
    public Student(String name,
            String studentNr,
            TreeMap<Course, Integer> completions,
            Degree activeDegree,
            StudyField activeStudyField,
            String startingYear,
            String finishingYear ) {
        
        this.name = name;
        this.studentNr = studentNr;
        this.completions = completions;
        this.activeDegree = activeDegree;
        this.activeStudyField = activeStudyField;
        this.startingYear = startingYear;
        this.finishingYear = finishingYear;
        
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getStudentNr() {
        return this.studentNr;
    }
    
    public TreeMap<Course, Integer> getCompletions() {
        return this.completions;
    
    }
    public Degree getActiveDegree() {
        return this.activeDegree;
    }
    
    public StudyField getActiveStudyField() {
        return this.activeStudyField;
    }
    
    public String getStartingYear() {
        return this.startingYear;
    }
    
    public String getFinishingYear() {
        return this.finishingYear;
    }
     
}
