
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 *
 * @author Lauri Kalliojärvi
 */

public class Course {

    private int studyPoints;
    private ArrayList<Degree> degrees;
    private String name;
    private String id;
    
    public Course(int studyPoints,
            String name,
            String id ) {
        
        this.degrees = degrees;
        this.name = name;
        this.id = id;
        
    }
    
    public int getStudyPoints() {
        return this.studyPoints;
    }
    
    public ArrayList<Degree> getDegrees() {
        return this.degrees;
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
