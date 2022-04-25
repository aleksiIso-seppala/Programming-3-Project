
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 *
 * @author Lauri Kalliojärvi
 */
public class Degree {
   private ArrayList<StudyField> studyFields;
   private ArrayList<Module> modules;
   private ArrayList<Course> courses;
   private int studyPoints;
   private String name;
   private String id;
   
   public Degree(ArrayList<StudyField> studyFields,
           ArrayList<Module> modules,
           ArrayList<Course> courses,
           int studyPoints,
           String name,
           String id ) {  
       this.studyFields = studyFields;
       this.modules = modules;
       this.courses = courses;
       this.studyPoints = studyPoints;
       this.name = name;
       this.id = id;  
   }
   
   public ArrayList<StudyField> getStudyFields() {
       return this.studyFields;
   }
   
   public ArrayList<Module> getModules() {
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
