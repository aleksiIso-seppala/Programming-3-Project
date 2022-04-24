
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 *
 * @author Lauri Kalliojärvi
 */
public class Degree {

   private ArrayList<Module> studyFields;
   private ArrayList<Course> courses;
   private int studyPoints;
   private String name;
   private String id;
   
   public Degree(ArrayList<Module> studyFields,
           ArrayList<Course> courses,
           int studyPoints,
           String name,
           String id ) {
           
       this.studyFields = studyFields;
       this.courses = courses;
       this.studyPoints = studyPoints;
       this.name = name;
       this.id = id;
       
   }
   
   public ArrayList<Module> getStudyFields() {
       return this.studyFields;
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
   
}
