
package fi.tuni.prog3.sisu;

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
    private int minCredits;
    private int accCredits;
    
    
    public Module(TreeMap<String,Course> courses,
            String name,
            String id, 
            int credits) {
        
        this.minCredits = credits;
        this.courses = courses;
        this.name = name;
        this.id = id;
        this.modules = new TreeMap<>();
        this.degree = null;
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
    
    public int getMinCredits() {
        return this.minCredits;
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
        return this.name + " " + this.accCredits + "/" + this.minCredits;
    }

    public TreeMap<String,Module> getModules() {
        return this.modules;
    }
    
    public int getAccCredits() {
        return this.accCredits;
    }
    
    public void setAccCredits(int credits) {
        this.accCredits = credits;
    }
}
