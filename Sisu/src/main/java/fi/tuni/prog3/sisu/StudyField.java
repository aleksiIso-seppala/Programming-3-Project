
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 * Luokka, joka sisältää opintosuunnan ominaisuudet.
 * @author Lauri Kalliojärvi
 */
public class StudyField {
    
    /**
     * Opintosuunnan sisältämät kurssit ArraListissä.
     */
    private ArrayList<Course> courses;
    
    /**
     * Opintosuunnan tutkinto-ohjelma.
     */
    private Degree degree;
    
    /**
     * Opintosuunnan nimi.
     */
    private String name;
    
    /**
     * Opintosuunnan tunniste.
     */
    private String id;
    
    /**
     * Rakennin, joka tallentaa parametreinä annetut kurssit, nimen
     * sekä tunnisteen.
     * @param courses ArraList kursseista
     * @param name nimi
     * @param id tunniste
     */
    public StudyField(ArrayList<Course> courses, String name, String id ) {
        this.courses = courses;
        this.name = name;
        this.id = id;
    }
    
    /**
     * Palauttaa opintosuunnan kurssit ArrayListissä.
     * @return ArrayList Course-olioista
     */
    public ArrayList<Course> getCourses() {
        return this.courses;
    }
    
    /**
     * Palauttaa opintosuunnan tutkinto-ohjelman.
     * @return Degree-olio
     */
    public Degree getDegree() {
        return this.degree;
    }
    
    /**
     * palauttaa opintosuunnan nimen
     * @return nimi
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * palauttaa opintosuunnan tunnisteen.
     * @return tunniste
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Asettaa parametrinä annetun tutkinto-ohjelman opintosuunnalle.
     * @param degreeToSet tutkinto-ohjelma
     * 
     */
    public void setDegree(Degree degreeToSet) {
        this.degree = degreeToSet;
    }
    
    /**
     * Palauttaa opintosuunnan tilan nimen muodossa.
     * @return opintosuunnan nimi
     */
    @Override
    public String toString() {
        return this.name;
    }
    
}
