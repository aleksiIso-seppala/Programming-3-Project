
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 *
 * @author Lauri Kalliojärvi
 */

public class Module {

    private ArrayList<Course> courses;
    private Degree degree;
    private String name;
    private String id;
    
    public Module(ArrayList<Course> courses,
            Degree degree,
            String name,
            String id ) {
        
        this.courses = courses;
        this.degree = degree;
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
