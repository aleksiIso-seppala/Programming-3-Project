
package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Lauri Kalliojärvi
 */
public class Degree {
   private TreeMap<String, Module> studyFields;
   private TreeMap<String, Module> modules;
   private int studyPoints;
   private String name;
   private String id;
   private Module selectedField;
   
   public Degree(
           int studyPoints,
           String name,
           String id ) {  
       this.studyFields = new TreeMap<>();
       this.modules = new TreeMap<>();
       this.studyPoints = studyPoints;
       this.name = name;
       this.id = id;  
   }
   
   public TreeMap<String, Module> getStudyFields() {
       return this.studyFields;
   }
   
   public void addModule(Module module){
       modules.put(module.getName(),module);
   }
   
   public TreeMap<String, Module> getModules() {
       return this.modules;
   }

   public void addStudyField(Module module) {
       studyFields.put(module.getName(), module);
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

    public void setSelectedField(String selectedFieldStr) {
        this.selectedField = this.studyFields.get(selectedFieldStr);
    }
    
    public Module getSelectedField() {
        return this.selectedField;
    }
}
