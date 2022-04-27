
package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Lauri Kalliojärvi
 */
public class Degree {
   private TreeMap<String, StudyField> studyFields;
   private TreeMap<String, Module> modules;
   private ArrayList<Course> courses;
   private int studyPoints;
   private String name;
   private String id;
   
   public Degree(
           int studyPoints,
           String name,
           String id ) {  
       this.studyFields = new TreeMap<>();
       this.modules = new TreeMap<>();
       this.courses = courses;
       this.studyPoints = studyPoints;
       this.name = name;
       this.id = id;  
   }
   
   public TreeMap<String, StudyField> getStudyFields() {
       return this.studyFields;
   }
   
   public void addModule(Module module){
       modules.put(module.getName(),module);
   }
   
   public TreeMap<String, Module> getModules() {
       return this.modules;
   }

   public ArrayList<Course> getCourses() {
       return this.courses;
   }
   
   public int getStudyPoints() {
       return this.studyPoints;
   }
   
   public String getName() {
       return this.name;
   }
   
   public String getId() {
       return this.id;
   }
   
   @Override
    public String toString() {
        return this.name;
    }
}
