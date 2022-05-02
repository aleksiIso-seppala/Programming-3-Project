
package fi.tuni.prog3.sisu;

import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testiluokka, joka testaa Student-luokan toiminnallisuuden.
 * @author Lauri Kalliojärvi
 */
public class StudentTest {
    
    /**
     * Testiluokan rakentaja.
     */
    public StudentTest() {
    }
    
    /**
     * Kertoo suoritettavan testin nimen ja testattavat metodit.
     */
    @BeforeEach
    void showTestInfo(TestInfo testInfo) throws Exception {
        
        System.out.println(testInfo.getDisplayName());
    }
    
    /**
     * Testi, joka testaa Student-luokan luomisen tunnistetiedoilla.
     */
    @Test
    @DisplayName("Testing class-initialization and getName and getStudentNr")
    public void testInit() {
        
        String name = "Matti";
        String nr = "001";
        Student testStudent = new Student(name, nr);
        assertEquals(name, testStudent.getName());
        assertEquals(nr, testStudent.getStudentNr());
    }
    
    /**
     * Testi, joka testaa Student-luokan luomisen aloitusvuodella.
     * Aloitusvuosi testataan myös luomalla olio ilman aloitusvuotta,
     * jolloin aloitusvuodeksi pitäisi tulla null.
     */
    @Test
    @DisplayName("Testing getStartingYear")
    public void testGetStartingYear() {
        Student testStudent1 = new Student("Matti", "001");
        String year1 = "2020";
        Student testStudent2 = new Student("Maija", "002", "2020");
        assertEquals(null, testStudent1.getStartingYear());
        assertEquals(year1, testStudent2.getStartingYear());
    }
    
    /**
     * Testi, joka testaa Student-luokan luomisen valmistumisvuodella.
     * Valmistumisvuosi testataan myös luomalla olio ilman valmistumisvuotta,
     * jolloin valmistumisvuodeksi pitäisi tulla null.
     */
    @Test
    @DisplayName("Testing getFinishingYear")
    public void testGetFinishingYear() {

            Student testStudent1 = new Student("Matti", "001");
            String year1 = "2025";
            String year2 = "2030";
            Student testStudent2 = new Student("Matti", "001", year1, year2);
            assertEquals(null, testStudent1.getFinishingYear());
            assertEquals(year2, testStudent2.getFinishingYear());
    }
    
    /**
     * Testi, joka testaa Student-luokan suoritetietojen tallentamisen
     * sekä hakemisen.
     */
    @Test
    @DisplayName("Testing getCompletions and setCompletions")
    public void testSetANdGetCompletions() {
        
        Student testStudent1 = new Student("Matti", "001");
        Course testCourse1 = new Course(5,"AAA","001");
        Course testCourse2 = new Course(5,"BBB","002");
        TreeMap<String, Course> testCompletions = new TreeMap<>();
        testCompletions.put(testCourse1.getName(), testCourse1);
        testCompletions.put(testCourse2.getName(), testCourse2);
        testStudent1.setCompletions(testCompletions);
        
        TreeMap<String, Course> expMap = testCompletions;
        TreeMap<String, Course> resultMap = testStudent1.getCompletions();
        boolean expResult = true;
        boolean result = resultMap.equals(expMap);
        assertEquals(expResult, result);
    }
    
    /**
     * Testi, joka testaa Student-luokan opintosuunnan tallentamisen
     * sekä hakemisen.
     */
    @Test
    @DisplayName("Testing getActiveStudyField and setActiveStudyField")
    public void testSetAndGetActiveStudyField() {
        
        Student testStudent1 = new Student("Matti", "001");
        TreeMap<String,Course> testCourseMap = new TreeMap<>();
        Module testStudyField = new Module(testCourseMap,"A","1",180);
        testStudent1.setActiveStudyField(testStudyField);
        String expResult = testStudyField.getId();
        String result = testStudent1.getActiveStudyField().getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Testi, joka testaa Student-luokan tutkinto-ohjelman tallentamisen
     * sekä hakemisen.
     */
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
    
    /**
     * Testi, joka testaa Student-luokan metodin completeCourse,
     * jota kutsutaan kun kurssi on suoritettu
     */
    @Test
    @DisplayName("Testing completeCourse")
    public void testCompleteCourse() {
        
        Student testStudent1 = new Student("Matti", "001");
        Course course1 = new Course(5,"AAA","001");
        testStudent1.completeCourse(course1);
        TreeMap completions = testStudent1.getCompletions();
        boolean expResult = true;
        boolean result = completions.containsKey(course1.getName());
        assertEquals(expResult,result);  
    }
    
    /**
     * Testi, joka testaa Student-luokan kurssin poistamisen suoritetiedoista.
     */
    @Test
    @DisplayName("Testing removeCourse")
    public void testRemoveCourse() {
         
        Student testStudent1 = new Student("Matti", "1");
        Course course1 = new Course(5,"AAA","001");
        TreeMap<String, Course> toComplete = new TreeMap<>();
        toComplete.put(course1.getName(),course1);
        testStudent1.setCompletions(toComplete);
        testStudent1.removeCourse(course1.getName());
        boolean expResult = false;
        boolean result = testStudent1
                .getCompletions()
                .containsKey(course1.getName());
        assertEquals(expResult,result);
        
    }
    
    /**
     * Testi, joka testaa Student-luokan tilanteen tulostamisen.
     */
    @Test
    @DisplayName("Testing toString")
    public void testToString() {
        Course c = new Course(5, "AAA", "001");
        String expResult = "AAA 5op";
        String result = c.toString();
        assertEquals(expResult, result);
    }
}
