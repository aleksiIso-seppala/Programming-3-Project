
package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
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
    
    public StudentTest() {
    }
    
    @BeforeEach
    void showTestInfo(TestInfo testInfo) throws Exception {
        
        System.out.println(testInfo.getDisplayName());
    }
    @Test
    @DisplayName("Testing testGetName")
    public void testGetName() {
        
        Student testStudent1 = new Student("Matti", "001");
        String expResult = "Matti";
        String result = testStudent1.getName();
        assertEquals(expResult, result);
    }
    @Test
    @DisplayName("Testing getStudentNr")
    public void testGetStudentNr() {
       
        Student testStudent1 = new Student("Matti", "001");
        String expResult = "001";
        String result = testStudent1.getStudentNr();
        assertEquals(expResult, result);
    }
    @ParameterizedTest
    @MethodSource("yearProvider")
    @DisplayName("Testing getStartingYear")
    public void testGetStartingYear(String startYear) {
        
        String expResult = startYear;
        if(startYear.equalsIgnoreCase(null)){
            Student testStudent1 = new Student("Matti", "001");
            String result = testStudent1.getStartingYear();
            assertEquals(expResult, result);
        } else {
            Student testStudent2 = new Student("Matti", "001",
                "2020", "2025");
            String result = testStudent2.getStartingYear();
            assertEquals(expResult, result);
        }
    }
    @ParameterizedTest
    @MethodSource("yearProvider")
    @DisplayName("Testing getFinishingYear")
    public void testGetFinishingYear(String finishYear) {
        
        String expResult = finishYear;
        if(finishYear.equalsIgnoreCase(null)){
            Student testStudent1 = new Student("Matti", "001");
            String result = testStudent1.getFinishingYear();
            assertEquals(expResult, result);
        } else {
            Student testStudent2 = new Student("Matti", "001",
                "2020", "2025");
            String result = testStudent2.getFinishingYear();
            assertEquals(expResult, result);
        }
    }
    @Test
    @DisplayName("Testing getCompletions and setCompletions")
    public void testSetANdGetCompletions() {
        
        Student testStudent1 = new Student("Matti", "001");
        Course testCourse1 = new Course(5,"AAA","001");
        Course testCourse2 = new Course(5,"BBB","002");
        TreeMap<Course, Integer> testCompletions = new TreeMap<>();
        testCompletions.put(testCourse1, testCourse1.getStudyPoints());
        testCompletions.put(testCourse2, testCourse2.getStudyPoints());
        testStudent1.setCompletions(testCompletions);
        
        TreeMap<Course, Integer> expMap = testCompletions;
        TreeMap<Course, Integer> resultMap = testStudent1.getCompletions();
        boolean expResult = true;
        boolean result = resultMap.equals(expMap);
        assertEquals(expResult, result);
    }
    @Test
    @DisplayName("Testing getActiveStudyField and setActiveStudyField")
    public void testSetAndGetActiveStudyField() {
        
        Student testStudent1 = new Student("Matti", "001");
        ArrayList<Course> testCourseList = new ArrayList<>();
        StudyField testStudyField = new StudyField(testCourseList,"A","1");
        testStudent1.setActiveStudyField(testStudyField);
        String expResult = testStudyField.getId();
        String result = testStudent1.getActiveStudyField().getId();
        assertEquals(expResult, result);
    }
    @Test
    @DisplayName("Testing getActiveDegree and setActiveDegree")
    public void testSetAndGetActiveDegree() {
        
        Student testStudent1 = new Student("Matti", "001");
        Degree testDegree = new Degree(180,"A Degree","Degree-1");
        testStudent1.setActiveDegree(testDegree);
        String expResult = testDegree.getId();
        String result = testStudent1.getActiveDegree().getId();
        assertEquals(expResult, result);
    }

    @ParameterizedTest
    @MethodSource("courseProvider")
    @DisplayName("Testing completeCourse")
    public void testCompleteCourse(Course course) {
        
        Student testStudent1 = new Student("Matti", "001");
        boolean expResult = true;
        testStudent1.completeCourse(course);
        TreeMap completions = testStudent1.getCompletions();
        boolean result = completions.containsKey(course);
        assertEquals(expResult,result);
        //????
        
    }
    @ParameterizedTest
    @MethodSource("courseProvider")
    @DisplayName("Testing removeCourse")
    public void testRemoveCourse(Course course) {
        
        Student testStudent1 = new Student("Matti", "1");
        TreeMap<Course, Integer> toComplete = new TreeMap<>();
        toComplete.put(course,course.getStudyPoints());
        testStudent1.setCompletions(toComplete);
        testStudent1.removeCourse(course);
        boolean expResult = false;
        boolean result = testStudent1.getCompletions().containsKey(course);
        assertEquals(expResult,result);
        
    }
    @Test
    @DisplayName("Testing toString")
    public void testToString() {
        Course c = new Course(5, "AAA", "001");
        String expResult = "AAA";
        String result = c.toString();
        assertEquals(expResult, result);
    }
    public static Stream<String> yearProvider() {
        return Stream.of(null, "2020");
    }
    
    public static Stream<Course> courseProvider() {
        Course course1 = new Course(5,"AAA","001");
        Course course2 = new Course(3,"BBB","002");
        Course course3 = new Course(0,"CCC","003");
        return Stream.of(course1, course2, course3);
    }
}
