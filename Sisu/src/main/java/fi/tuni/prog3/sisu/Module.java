
package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Lauri Kalliojärvi
 */

public class Module {

    private TreeMap<String,Course> courses;
    private TreeMap<String,Module> modules;
    private Degree degree;
    private String name;
    private String id;
    private int credits;
    
    
    public Module(TreeMap<String,Course> courses,
            String name,
            String id, 
            int credits) {
        
        this.credits = credits;
        this.courses = courses;
        this.degree = degree;
        this.name = name;
        this.id = id;
        this.modules = new TreeMap<>();
    }
    
    public TreeMap<String,Course> getCourses() {
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
        this.modules.put(module.getName(),module);
    }
    
    public void addCourse(Course course){
        this.courses.put(course.getName(),course);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
