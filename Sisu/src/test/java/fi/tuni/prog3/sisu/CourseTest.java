
package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
/**
 *
 * @author Lauri Kalliojärvi
 */
@Disabled("Disabled")
public class CourseTest {
    
    Course testCourse;
    int studyPoints = 5;
    String courseName = "Kurssi 1";
    String courseId = "AAA-001";
    @BeforeAll
    void setUpClass(TestInfo testInfo) throws Exception {
        this.testCourse = new Course(studyPoints, courseName, courseId);
    }
    @BeforeEach
    void showTestInfo(TestInfo testInfo) throws Exception {
        System.out.println(testInfo.getDisplayName());
    }
    @Test
    @DisplayName("Testing Course.getStudyPoints()")
    public void testGetStudyPoints() {
        int expResult = studyPoints;
        int result = this.testCourse.getStudyPoints();
        assertEquals(expResult, result);
    }
    @Test
    @DisplayName("Testing Course.getName()")
    public void testGetName() {
        String expResult = courseName;
        String result = testCourse.getName();
        assertEquals(expResult, result);
    }
    @Test
    @DisplayName("Testing Course.getId()")
    public void testGetId() {
        String expResult = courseId;
        String result = testCourse.getId();
        assertEquals(expResult, result);
    }
    @Test
    @DisplayName("Testing Course.toString()")
    public void testToString() {
        String expResult = courseName;
        String result = testCourse.toString();
        assertEquals(expResult, result);
    }
    @Test
    @DisplayName("Testing.Course.compareTo")
    public void testCompareTo() {
        Course compare = new Course(5,"Kurssi 1","AAA-001");
        int expResult = 0;
        int result = testCourse.compareTo(compare);
        assertEquals(expResult, result);
    }
    
}
