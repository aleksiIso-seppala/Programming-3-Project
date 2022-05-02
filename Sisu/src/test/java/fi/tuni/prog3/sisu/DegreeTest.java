
package fi.tuni.prog3.sisu;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testiluokka, joka testaa Degree-luokan toiminnallisuuden.
 * @author Lauri Kalliojärvi
 */
public class DegreeTest {
    
    /**
     * Testiluokan rakentaja.
     */
    public DegreeTest() {
    }
    
    /**
     * Kertoo suoritettavan testin nimen ja testattavat metodit
     */
    @BeforeEach
    void showTestInfo(TestInfo testInfo) throws Exception {
        System.out.println(testInfo.getDisplayName());
    }
    
    /**
     * Testi, joka testaa uuden Degree-luokan luomisen sekä 
     * attribuuttien tallentumisen.
     */
    @Test
    @DisplayName("Testing initialization and get-methods")
    public void testInitandGetters() {
        int points = 180;
        String name = "Degree-A";
        String id = "1111";
        Degree testDegree = new Degree(points, name, id);
        assertEquals(testDegree.getName(), name);
        assertEquals(testDegree.getId(), id);
        assertEquals(testDegree.getStudyPoints(), points);
    }
    
    /**
     * Testi, joka testaa Degree-luokan moduulien ja opintosuuntien tallentumisen
     * sekä tulostamisen.
     */
    @Test
    @DisplayName("Testing addModule, getModules, addStudyField and getStudyFields")
    public void testModulesAndStudyFields() {
        int points = 180;
        String name = "Degree-A";
        String id = "1111";
        Degree testDegree = new Degree(points, name, id);
        TreeMap<String, Course> emptyCourseMap = new TreeMap<>();
        String name1 = "Module-1";
        String name2 = "Module-2";
        String id1 = "1";
        String id2 = "2";
        int credits1 = 120;
        int credits2 = 180;
        Module module1 = new Module(emptyCourseMap, name1, id1, credits1);
        Module module2 = new Module(emptyCourseMap, name2, id2, credits2);
        Module testField1 = new Module(emptyCourseMap, name1, id1, credits1);
        Module testField2 = new Module(emptyCourseMap, name2, id2, credits2);
        
        TreeMap<String, Module> expModules = new TreeMap<>();
        expModules.put(name1, module1);
        expModules.put(name2, module2);
        testDegree.addModule(module1);
        testDegree.addModule(module2);
        
        TreeMap<String, Module> expStudyFields = new TreeMap<>();
        expStudyFields.put(name1, testField1);
        expStudyFields.put(name2, testField2);
        testDegree.addStudyField(testField1);
        testDegree.addStudyField(testField2);
        
        TreeMap<String, Module> resultModules = testDegree.getModules();
        boolean expModuleResult = true;
        boolean moduleResult = resultModules.equals(expModules);
        assertEquals(expModuleResult, moduleResult);
        
        TreeMap<String, Module> resultStudyFields = testDegree.getStudyFields();
        boolean expStudyFieldResult = true;
        boolean studyFieldResult = resultStudyFields.equals(expStudyFields);
        assertEquals(expStudyFieldResult, studyFieldResult); 
    }
    
    /**
     * Testi, joka testaa Degree-luokan tilan tulostumisen.
     */
    @Test
    @DisplayName("Testing toString")
    public void testToString() {
        int points = 180;
        String name = "Degree-A";
        String id = "1111";
        Degree testDegree = new Degree(points, name, id);
        String expResult = name;
        assertEquals(expResult, testDegree.toString());
    }
    
    
    /**
     * Testi, joka testaa valitun opintosuunnan tallentamisen sekä tulostamisen.
     * @Param fields opintosuunnat
     */
    @Test
    @DisplayName("Testing setSelectedField and getSelectedField")
    public void testSetAndGetSelectedField() {
        int points = 180;
        String name = "Degree-A";
        String id = "1111";
        Degree testDegree = new Degree(points, name, id);
        TreeMap<String,Course> emptyCourses = new TreeMap<>();
        Module testField = new Module(emptyCourses,"studyfield-1","1",0);
        testDegree.addStudyField(testField);
        testDegree.addStudyField(testField);
        Module expSelected = testField;
        testDegree.setSelectedField(expSelected.getName());
        Module resultSelected = testDegree.getSelectedField();
        assertEquals(expSelected.getName(),resultSelected.getName());    
    }

}
