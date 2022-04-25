
package fi.tuni.prog3.sisu;

import java.util.TreeMap;

/**
 *
 * @author Lauri Kalliojärvi
 * 
 * Methods getStartingYear() and getFinishingYear return null if not set.
 */

public class Student {
    private String studentName;
    private String studentNr;
    private String startingYear = null;
    private String finishingYear = null;
    private TreeMap<Course, Integer> completions;
    private Degree activeDegree;
    private StudyField activeStudyField;
    
    public Student(String studentName, String studentNr) {
        this.studentName = studentName;
        this.studentNr = studentNr; 
    }
    
    public Student(String studentName, String studentNr, String startingYear) {
        this.studentName = studentName;
        this.studentNr = studentNr;
        this.startingYear = startingYear;
    }
    public Student(String studentName, 
            String studentNr, 
            String startingYear,
            String finishingYear) {
        this.studentName = studentName;
        this.studentNr = studentNr;
        this.startingYear = startingYear;
        this.finishingYear = finishingYear;
    }
    
    public String getName() {
        return this.studentName;
    }
    
    public String getStudentNr() {
        return this.studentNr;
    }
        
    public String getStartingYear() {
        return this.startingYear;
    }
    
    public String getFinishingYear() {
        return this.finishingYear;
    }
    
    public TreeMap<Course, Integer> getCompletions() {
        return this.completions;
    }
       
    public StudyField getActiveStudyField() {
        return this.activeStudyField;
    }

    public Degree getActiveDegree() {
        return this.activeDegree;
    }
 
    public void setCompletions(TreeMap<Course, Integer> newCompletions) {
        this.completions = newCompletions;
    }
    
    public void setActiveDegree(Degree newActiveDegree) {
        this.activeDegree = newActiveDegree;
    }
    
    public void setActiveStudyField(StudyField newActiveStudyField) {
        this.activeStudyField = newActiveStudyField;
    }
}
