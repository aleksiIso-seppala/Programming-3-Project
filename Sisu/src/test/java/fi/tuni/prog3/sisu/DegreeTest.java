
package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lauri Kalliojärvi
 */
public class DegreeTest {
    
    public DegreeTest() {
    }
    
    @BeforeEach
    void showTestInfo(TestInfo testInfo) throws Exception {
        
        System.out.println(testInfo.getDisplayName());
    }

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

    @ParameterizedTest
    @MethodSource("fieldProvider")
    @DisplayName("Testing setSelectedField and getSelectedField")
    public void testSetAndGetSelectedField(List<Module> fields) {
        int points = 180;
        String name = "Degree-A";
        String id = "1111";
        Degree testDegree = new Degree(points, name, id);
        testDegree.addStudyField(fields.get(0));
        testDegree.addStudyField(fields.get(1));
        Module expSelected = fields.get(0);
        testDegree.setSelectedField(expSelected.getName());
        Module resultSelected = testDegree.getSelectedField();
        assertEquals(expSelected.getName(),resultSelected.getName());
        
        
        
    }
    public static Stream<List<Module>> fieldProvider() {
        TreeMap<String,Course> emptyCourses = new TreeMap<>();
        Module testField1 = new Module(emptyCourses,"studyfield-1","1",0);
        Module testField2 = new Module(emptyCourses,"studyfield-2","2",0);
        Module testField3 = new Module(emptyCourses,"studyfield-3","3",0);
        Module testField4 = new Module(emptyCourses,"studyfield-4","4",0);
        return Stream.of(List.of(testField1, testField2),
                List.of(testField3, testField4)
        );
    }
}
