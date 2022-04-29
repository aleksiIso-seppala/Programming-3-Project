
package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lauri Kalliojärvi
 */
public class StudentTest {
    
    Student testStudent1;
    Student testStudent2;
    String studentNr = "001";
    String studentName = "Matti";
    String startingYear = "2020";
    String finishingYear = "2020";
    Course testCourse1;
    Course testCourse2;
    StudyField testStudyField;
    Degree testDegree;
    ArrayList<Course> testCourses;
    
    public StudentTest() {
    }
    
    @BeforeAll
    public void setUpClass() {
        Student testStudent1 = new Student(studentName, studentNr);
        Student testStudent2 = new Student(studentName, studentNr,
                startingYear, finishingYear);
        Course testCourse1 = new Course(5,"AAA","001");
        Course testCourse2 = new Course(5,"BBB","002");
        Degree testDegree = new Degree(180,"A","1");
    }
    
    @BeforeEach
    void showTestInfo(TestInfo testInfo) throws Exception {
        System.out.println(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Testing Student.testGetName()")
    public void testGetName() {
        String expResult = studentName;
        String result = testStudent1.getName();
        assertEquals(expResult, result);
    }

    @DisplayName("Testing Student.getStudentNr()")
    public void testGetStudentNr() {
        String expResult = studentNr;
        String result = testStudent1.getStudentNr();
        assertEquals(expResult, result);
    }
    
    @ParameterizedTest
    @MethodSource("yearProvider")
    @DisplayName("Testing Student.getStartingYear")
    public void testGetStartingYear(String startYear) {
        String expResult = startYear;
        if(startYear.equalsIgnoreCase(null)){
            String result = testStudent1.getStartingYear();
            assertEquals(expResult, result);
        } else {
            String result = testStudent2.getStartingYear();
            assertEquals(expResult, result);
        }
    }

    @ParameterizedTest
    @MethodSource("yearProvider")
    @DisplayName("Testing Student.getFinishingYear")
    public void testGetFinishingYear(String finishYear) {
        String expResult = finishYear;
        if(finishYear.equalsIgnoreCase(null)){
            String result = testStudent1.getFinishingYear();
            assertEquals(expResult, result);
        } else {
            String result = testStudent2.getFinishingYear();
            assertEquals(expResult, result);
        }
    }

    @Test
    @DisplayName("Testing Student.getCompletions()")
    public void testGetCompletions() {
        Student instance = null;
        TreeMap<Course, Integer> expResult = null;
        TreeMap<Course, Integer> result = instance.getCompletions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @DisplayName("Testing Student.getActiveStudyField()")
    public void testGetActiveStudyField() {
        System.out.println("getActiveStudyField");
        Student instance = null;
        StudyField expResult = null;
        StudyField result = instance.getActiveStudyField();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @DisplayName("Testing Student.getActiveDegree()")
    public void testGetActiveDegree() {
        System.out.println("getActiveDegree");
        Student instance = null;
        Degree expResult = null;
        Degree result = instance.getActiveDegree();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @DisplayName("Testing Student.setCompletions()")
    public void testSetCompletions() {
        System.out.println("setCompletions");
        TreeMap<Course, Integer> newCompletions = null;
        Student instance = null;
        instance.setCompletions(newCompletions);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @DisplayName("Testing Student.setActiveDegree()")
    public void testSetActiveDegree() {
        System.out.println("setActiveDegree");
        Degree newActiveDegree = null;
        Student instance = null;
        instance.setActiveDegree(newActiveDegree);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @DisplayName("Testing Student.setActiveStudyField()")
    public void testSetActiveStudyField() {
        System.out.println("setActiveStudyField");
        StudyField newActiveStudyField = null;
        Student instance = null;
        instance.setActiveStudyField(newActiveStudyField);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @DisplayName("Testing Student.completeCourse()")
    public void testCompleteCourse() {
        System.out.println("completeCourse");
        Course completedCourse = null;
        Student instance = null;
        instance.completeCourse(completedCourse);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @DisplayName("Testing Student.removeCourse()")
    public void testRemoveCourse() {
        System.out.println("removeCourse");
        Course removedCourse = null;
        Student instance = null;
        instance.removeCourse(removedCourse);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @DisplayName("Testing Student.toString()")
    public void testToString() {
        System.out.println("toString");
        Student instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    public static Stream<String> yearProvider() {
        return Stream.of(null, "2020");
    }
}
