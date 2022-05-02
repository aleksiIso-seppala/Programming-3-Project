
package fi.tuni.prog3.sisu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Testiluokka, joka testaa Course-luokan toiminnallisuuden.
 * @author Lauri Kalliojärvi
 */
public class CourseTest {
    
    /**
     * Testiluokan rakentaja.
     */
    public CourseTest() {
    }
    
    /**
     * Kertoo suoritettavan testin nimen ja testattavat metodit.
     */
    @BeforeEach
    void showTestInfo(TestInfo testInfo) throws Exception {
        System.out.println(testInfo.getDisplayName());
    }
    
    /**
     * Testi, joka testaa uuden Student-luokan luomisen sekä 
     * get-metodit.
     */
    @Test
    @DisplayName("Testing class-initialization and get-methods")
    public void testInit() {
        String name = "Kurssi-1";
        String id = "AAA-1";
        int studyPoints = 5;
        Course testCourse = new Course(studyPoints, name, id);
        assertEquals(name, testCourse.getName());
        assertEquals(id, testCourse.getId());
        assertEquals(studyPoints, testCourse.getStudyPoints());
    }
    
    /**
     * Testi, joka testaa Student-luokan tilan tulostumisen.
     */
    @Test
    @DisplayName("Testing toString")
    public void testToString() {
        String name = "Kurssi-1";
        String id = "AAA-1";
        int studyPoints = 5;
        Course testCourse = new Course(studyPoints, name, id);
        assertEquals(name, testCourse.toString());
    }
}
