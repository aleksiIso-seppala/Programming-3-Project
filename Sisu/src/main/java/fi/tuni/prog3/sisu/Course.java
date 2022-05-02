
package fi.tuni.prog3.sisu;

/**
 * Luokka, joka säilyttää kurssin ominaisuudet.
 * @author Lauri Kalliojärvi
 */

public class Course {
    
    /**
     * Kurssin laajuus opintopisteinä.
     */
    private int studyPoints;
    
    /**
     * Kurssin nimi.
     */
    private String name;
    
    /**
     * Kurssin tunniste.
     */
    private String id;
    
    /**
     * Rakennin, joka ottaa arvot opintopisteille, nimelle sekä tunnisteelle
     * parametreinä.
     * @param studyPoints opintopisteet
     * @param name kurssin nimi
     * @param id kurssin tunniste
     */
    public Course(int studyPoints,
            String name,
            String id ) {
        
        this.studyPoints = studyPoints;
        this.name = name;
        this.id = id;
        
    }
    
    /**
     * Palauttaa kurssin laajuuden opintopisteinä.
     * @return opintopisteet
     */
    public int getStudyPoints() {
        return this.studyPoints;
    }
    
    /**
     * Palauttaa kurssin nimen.
     * @return nimi
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Palauttaa kurssin tunnisteen.
     * @return tunniste
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Palauttaa Course-olion tilan nimen muodossa.
     * @return kurssin nimi
     */
    @Override
    public String toString() {
        return this.name;
    }
}
