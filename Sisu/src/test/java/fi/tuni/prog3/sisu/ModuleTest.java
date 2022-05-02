
package fi.tuni.prog3.sisu;

import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testiluokka, joka testaa Module-luokan toiminnallisuuden
 * @author Lauri Kalliojärvi
 */
public class ModuleTest {
    
    /**
     * Testiluokan rakentaja.
     */
    public ModuleTest() {
    }
    
    /**
     * Kertoo suoritettavan testin nimen ja testattavat metodit
     */
    @BeforeEach
    void showTestInfo(TestInfo testInfo) throws Exception {
        
        System.out.println(testInfo.getDisplayName());
    }
    
    /**
     * Testi, joka testaa Module-luokan luomisen sekä attribuuttien tallentumisen
     */
    @Test
    @DisplayName("Testing class-initialization and getName, getID and getMincredits")
    public void testInit() {
        TreeMap<String, Course> emptyCourses = new TreeMap<>();
        String name = "Module-1";
        String id = "111";
        int credits = 180;
        Module testModule = new Module(emptyCourses, name, id, credits);
        assertEquals(name, testModule.getName());
        assertEquals(id, testModule.getId());
        assertEquals(credits, testModule.getMinCredits());
    }
    
    /**
     * Testi, joka testaa Module-luokan kurssitietojen lisäämisen sekä poistamisen.
     */
    @Test
    @DisplayName("testing adding and getting courses")
    public void testAddAndGetCourses() {
        TreeMap<String, Course> testCourses = new TreeMap<>();
        String name = "Module-1";
        String id = "111";
        int credits = 180;
        Module testModule = new Module(testCourses, name, id, credits);
        Course course1 = new Course(5, "Course-1", "111");
        testCourses.put(course1.getName(), course1);
        testModule.addCourse(course1);
        boolean expResult = true;
        boolean result = testCourses.equals(testModule.getCourses());
        assertEquals(expResult, result);
    }
    
    /**
     * Testi, joka testaa Module-luokan tutkinto-ohjelman tallentamisen 
     * sekä lukemisen.
     */
    @Test
    @DisplayName("Testing settting and getting degree")
    public void testSetAndGetDegree() {
        TreeMap<String, Course> emptyCourses = new TreeMap<>();
        String name = "Module-1";
        String id = "111";
        int credits = 180;
        Module testModule = new Module(emptyCourses, name, id, credits);
        Degree testDegree = new Degree(180,"Degree-1", "111");
        testModule.setDegree(testDegree);
        boolean expResult = true;
        boolean result = testDegree.equals(testModule.getDegree());
        assertEquals(expResult, result);
    }
    
    /**
     * Testi, joka testaa Module-luokan alimoduulien tallentamisen
     * sekä lukemisen.
     */
    @Test
    @DisplayName("Testing adding and getting studyfield")
    public void testAddAndGetSubModules() {
        TreeMap<String, Course> emptyCourses = new TreeMap<>();
        String name = "Module-1";
        String id = "111";
        int credits = 180;
        Module testModule = new Module(emptyCourses, name, id, credits);
        Module subModule = new Module(emptyCourses, "submodule-1", "1.1.1", 100);
        TreeMap<String, Module> testMap = new TreeMap<>();
        testMap.put(subModule.getName(), subModule);
        testModule.addModule(subModule);
        boolean expResult = true;
        boolean result = testMap.equals(testModule.getModules());
        assertEquals(expResult, result);
    }
    
    /**
     * Testi, joka testaa Module-luokan opintopisteiden lisäämisen
     * sekä lukemisen.
     */
    @Test
    @DisplayName("Testing setting and getting accCredits")
    public void testSetAndGetAccCredits() {
        TreeMap<String, Course> emptyCourses = new TreeMap<>();
        String name = "Module-1";
        String id = "111";
        int credits = 180;
        Module testModule = new Module(emptyCourses, name, id, credits);
        int accCredits = 120;
        testModule.setAccCredits(accCredits);
        assertEquals(accCredits, testModule.getAccCredits());
        
    }
    
    /**
     * Testi, joka testaa Module-luokan tilan tulostamisen.
     */
    @Test
    @DisplayName("Testing toString-method")
    public void testToString() {
        TreeMap<String, Course> emptyCourses = new TreeMap<>();
        String name = "Module-1";
        String id = "111";
        int credits = 180;
        Module testModule = new Module(emptyCourses, name, id, credits);
        testModule.setAccCredits(10);
        String expResult = name + " " + credits + "op";
        assertEquals(expResult, testModule.toString());
    }
}
