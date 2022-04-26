
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 *
 * @author Lauri Kalliojärvi
 */

public class Module {

    private ArrayList<Course> courses;
    private ArrayList<Module> modules;
    private Degree degree;
    private String name;
    private String id;
    private int credits;
    
    
    public Module(ArrayList<Course> courses,
            String name,
            String id, 
            int credits) {
        
        this.credits = credits;
        this.courses = courses;
        this.degree = degree;
        this.name = name;
        this.id = id;
        this.modules = new ArrayList<>();
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
    
    public void addModule(Module module){
        this.modules.add(module);
    }
    
    public void addCourse(Course course){
        this.courses.add(course);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
