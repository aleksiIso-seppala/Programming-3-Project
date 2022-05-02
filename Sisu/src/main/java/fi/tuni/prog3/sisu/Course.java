
package fi.tuni.prog3.sisu;

/**
 *
 * @author Lauri Kalliojärvi
 */

public class Course implements Comparable<Course>{

    private int studyPoints;
    private String name;
    private String id;
    
    public Course(int studyPoints,
            String name,
            String id ) {
        
        this.studyPoints = studyPoints;
        this.name = name;
        this.id = id;
        
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

    @Override
    public int compareTo(Course o) {
        return name.compareTo(o.getName());
    }
}
