
package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Lauri Kalliojärvi
 */
public class StudyFieldTest {
    
    public StudyFieldTest() {
    }
    
    @BeforeEach
    void showTestInfo(TestInfo testInfo) throws Exception {
        
        System.out.println(testInfo.getDisplayName());
    }
 
    @Test
    @DisplayName("Testing initializing a new StudyField and getting it's attributes")
    public void testClassInitializing() {
        Course course1 = new Course(5,"Kurssi-1","001");
        Course course2 = new Course(3,"Kurssi-2","002");
        ArrayList<Course> testList = new ArrayList<>();
        testList.add(course1);
        testList.add(course2);
        String name = "A1";
        String id = "111";
        StudyField testStudyField = new StudyField(testList,name,id);
        assertEquals(testStudyField.getName(),name);
        assertEquals(testStudyField.getId(), id);
        assertEquals(testStudyField.getCourses(), testList);
        
    }

    @Test
    @DisplayName("Testing getDegree and setDegree")
    public void testSetandGetDegree() {
        ArrayList<Course> testList = new ArrayList<>();
        String name = "A1";
        String id = "111";
        StudyField testStudyField = new StudyField(testList,name,id);
        Degree testDegree = new Degree(180,"Degree-1","1-1-1");
        testStudyField.setDegree(testDegree);
        String expResult = testDegree.getId();
        assertEquals(expResult, testStudyField.getDegree().getId());
    }
    
    @Test
    @DisplayName("Testing toString")
    public void testToString() {
        ArrayList<Course> testList = new ArrayList<>();
        String name = "A1";
        String id = "111";
        StudyField testStudyField = new StudyField(testList,name,id);
        String expResult = name;
        assertEquals(expResult, testStudyField.toString());
    }
}
