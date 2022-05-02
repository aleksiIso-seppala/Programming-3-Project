
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
    private Degree activeDegree = null;
    private Module activeStudyField = null;
    
    public Student(String studentName, String studentNr) {
        this.studentName = studentName;
        this.studentNr = studentNr;
        this.completions = new TreeMap<>();
    }
    
    public Student(String studentName, String studentNr, String startingYear) {
        this.studentName = studentName;
        this.studentNr = studentNr;
        this.startingYear = startingYear;
        this.completions = new TreeMap<>();
    }
    public Student(String studentName, 
            String studentNr, 
            String startingYear,
            String finishingYear) {
        this.studentName = studentName;
        this.studentNr = studentNr;
        this.startingYear = startingYear;
        this.finishingYear = finishingYear;
        this.completions = new TreeMap<>();
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
       
    public Module getActiveStudyField() {
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
    
    public void setActiveStudyField(Module newActiveStudyField) {
        this.activeStudyField = newActiveStudyField;
    }
    
    public void completeCourse(Course completedCourse) {
        completions.put(completedCourse,completedCourse.getStudyPoints());
    }
    
    public void removeCourse(Course removedCourse) {
        this.completions.remove(removedCourse);
    }
    
    @Override
    public String toString() {
        return this.studentName;
    }
    
    public void clearCompletions() {
        this.completions.clear();
    }
}
