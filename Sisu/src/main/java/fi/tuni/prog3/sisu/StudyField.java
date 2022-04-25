
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 *
 * @author Lauri Kalliojärvi
 */

public class StudyField {

    private ArrayList<Course> courses;
    private Degree degree;
    private String name;
    private String id;
    
    public StudyField(ArrayList<Course> courses, String name, String id ) {
        this.courses = courses;
        this.name = name;
        this.id = id;
    }
    
    public ArrayList<Course> getCourses() {
        return this.courses;
    }
    
    public Degree getDegree() {
        return this.degree;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setDegree(Degree degreeToSet) {
        this.degree = degreeToSet;
    }
    
}
