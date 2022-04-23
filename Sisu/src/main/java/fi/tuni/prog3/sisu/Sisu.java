package fi.tuni.prog3.sisu;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Lauri Puoskari
 */

public class Sisu {
    private Student activeStudent;
    private ArrayList<Degree> degrees;

    public Sisu(String studentName, String studentNr, String... years) {
        switch (years.length) {
            case 0:
                //Luodaan uusi Student-olio ilman vuositietoja.
                //activeStudent = Student(studentName, studentNr);
                break; 
            case 1:
                //Luodaan uusi Student-olio ainoastaan aloitusvuositiedolla.
                //activeStudent = Student(studentName, studentNr, years[0]);
                break;
            case 2:
                //Luodaan uusi Student-olio ainoastaan molemmilla vuositiedoilla.
                //activeStudent = Student(studentName, studentNr,
                //                            years[0], years[1]);
                break;
            default:
                break;
        }
        
        // TODO: Etsitään tiedostosta luotua student-oliota vastaavat tiedot,
        //       jos ei löydy, niin luodaan uudet tiedot.
        
        // TODO: Haetaan tiedot tutkinto-ohjelmista, luodaan ne ja talletetaan
        //       this.degrees
    }
    
    public Student getActiveStudent() {
        return this.activeStudent;
    }
    
    public ArrayList<Degree> getDegrees() {
        return this.degrees;
    }
    
    public boolean saveStudentData() /*throws FileNotFoundException*/ {
        
        // TODO: Tallenetaan opiskelijan opintotiedot oikeaan tiedostoon. Jos
        //       tiedosto löytyy ja tallennus onnistuu palautetaan true, jos ei
        //       niin false.
        
//        try {
//            return true;
//        }
//        catch(FileNotFoundException e) {
//            return false;
//        }

        // Placeholder-koodi kunnes metodi on toteutettu.
        return true;
    }

    //Tämä metodi on vain ohjelman testaamista varten kunnes API saadaan käyttöön.
    public void initForTests() {
        /*Course course1 = new Course(5, new ArrayList<Degree>(), "Kurssi 1", "AAA-001");
        Course course2 = new Course(5, new ArrayList<Degree>(), "Kurssi 2", "BBB-002");
        Course course3 = new Course(5, new ArrayList<Degree>(), "Kurssi 3", "CCC-003");
        Course course4 = new Course(5, new ArrayList<Degree>(), "Kurssi 4", "DDD-004");
        Course course5 = new Course(5, new ArrayList<Degree>(), "Kurssi 5", "EEE-005");
        Course course6 = new Course(5, new ArrayList<Degree>(), "Kurssi 6", "FFF-006");
        Course course7 = new Course(5, new ArrayList<Degree>(), "Kurssi 7", "GGG-007");
        Course course8 = new Course(5, new ArrayList<Degree>(), "Kurssi 8", "HHH-008");
        Course course9 = new Course(5, new ArrayList<Degree>(), "Kurssi 9", "III-009");
        
        ArrayList<Course> courses1_3 = new ArrayList<>();
        courses1_3.add(course1);
        courses1_3.add(course2);
        courses1_3.add(course3);
        
        ArrayList<Course> courses4_6 = new ArrayList<>();
        courses1_3.add(course4);
        courses1_3.add(course5);
        courses1_3.add(course6);
        
        ArrayList<Course> courses7_9 = new ArrayList<>();
        courses1_3.add(course7);
        courses1_3.add(course8);
        courses1_3.add(course9);*/
    }
}