
package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Lauri Kalliojärvi
 */
public class CourseTest {
    
    public static final int STUDY_POINTS = 5;
    public static final String COURSE1_NAME = "Kurssi 1";
    public static final String COURSE_ID = "AAA-001";
    
    @BeforeEach
    void showTestInfo(TestInfo testInfo) throws Exception {
        System.out.println(testInfo.getDisplayName());
    }
    /*
    @Test
    @DisplayName("Testing getStudyPoints")
    public void testGetStudyPoints() {
        Course c = new Course(STUDY_POINTS, COURSE1_NAME, COURSE_ID);
        int expResult = STUDY_POINTS;
        int result = c.getStudyPoints();
        assertEquals(expResult, result);
    }
*/
    @Test
    @DisplayName("Testing getName")
    public void testGetName() {
        Course c = new Course(STUDY_POINTS, COURSE1_NAME, COURSE_ID);
        String expResult = COURSE1_NAME;
        String result = c.getName();
        assertEquals(expResult, result);
    }
    @Test
    @DisplayName("Testing getId")
    public void testGetId() {
        Course c = new Course(STUDY_POINTS, COURSE1_NAME, COURSE_ID);
        String expResult = COURSE_ID;
        String result = c.getId();
        assertEquals(expResult, result);
    }
    @Test
    @DisplayName("Testing toString")
    public void testToString() {
        Course c = new Course(STUDY_POINTS, COURSE1_NAME, COURSE_ID);
        String expResult = COURSE1_NAME;
        String result = c.toString();
        assertEquals(expResult, result);
    }
    @Test
    @DisplayName("Testing compareTo")
    public void testCompareTo() {
        Course c = new Course(STUDY_POINTS, COURSE1_NAME, COURSE_ID);
        Course compare = new Course(STUDY_POINTS, COURSE1_NAME, COURSE_ID);
        int expResult = 0;
        int result = c.compareTo(compare);
        assertEquals(expResult, result);
    }
    
}
