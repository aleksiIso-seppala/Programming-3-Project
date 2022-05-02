
package fi.tuni.prog3.sisu;

import java.util.TreeMap;

/**
 * Luokka, joka sisältää opiskelijan ominaisuudet.
 * @author Lauri Kalliojärvi
 */

public class Student {
    
    /**
     * Opiskelijan nimi.
     */
    private String studentName;
    
    /**
     * Opiskelijan opiskelijanumero.
     */
    private String studentNr;
    
    /**
     * Opiskelijan aloitusvuosi.
     */
    private String startingYear = null;
    
    /**
     * Opiskelijan valmistumisvuosi.
     */
    private String finishingYear = null;
    
    /**
     * TreeMap, joka sisältää opiskelijan suoritteet (suoritetut kurssit).
     * Avaimina Course-oliot ja arvoina opintopistemäärät.
     */
    private TreeMap<Course, Integer> completions;
    
    /**
     * Opiskelijan valitsema tutkinto-ohjelma.
     */
    private Degree activeDegree = null;
    
    /**
     * Opiskelijan valitsema opintosuunta.
     */
    private Module activeStudyField = null;
    
    /**
     * Rakennin, joka ottaa parametreina opiskelijan nimen ja opiskelijanumeron.
     * @param studentName opiskelijan nimi
     * @param studentNr opiskelijanumero
     */
    public Student(String studentName, String studentNr) {
        this.studentName = studentName;
        this.studentNr = studentNr;
        this.completions = new TreeMap<>();
    }
    
    /**
     * Toinen rakennin, joka lisäksi ottaa kolmanneksi parametriksi aloitusvuoden.
     * @param studentName opiskelijan nimi
     * @param studentNr opiskelijanumero
     * @param startingYear aloitusvuosi
     */
    public Student(String studentName, String studentNr, String startingYear) {
        this.studentName = studentName;
        this.studentNr = studentNr;
        this.startingYear = startingYear;
        this.completions = new TreeMap<>();
    }
    
  /**
     * Kolmas rakennin, joka vielä ottaa neljänneksi parametriksi valmistumisvuoden.
     * @param studentName opiskelijan nimi
     * @param studentNr opiskelijanumero
     * @param startingYear aloitusvuosi
     * @param finishingYear valmistumisvuosi
     */
    public Student(String studentName, 
            String studentNr, 
            String startingYear,
            String finishingYear) {
        this.studentName = studentName;
        this.studentNr = studentNr;
        this.startingYear = startingYear;
        this.finishingYear = finishingYear;
        this.completions = new TreeMap<>();
    }
    
    /**
     * Palauttaa opiskelijan nimen.
     * @return nimi
     */
    public String getName() {
        return this.studentName;
    }
    
    /**
     * Palauttaa opiskelijan opiskelijanumeron.
     * @return String opiskelijanumero
     */
    public String getStudentNr() {
        return this.studentNr;
    }
    
    /**
     * Palauttaa opiskelijan aloitusvuoden.
     * @return aloitusvuosi
     */
    public String getStartingYear() {
        return this.startingYear;
    }
    
    /**
     * Palauttaa opiskelijan valmistumisvuoden.
     * @return valmistumisvuosi
     */
    public String getFinishingYear() {
        return this.finishingYear;
    }
    
    /**
     * Palauttaa opiskelijan suoritukset TreeMapissa.
     * Mapin avaimina Course-oliot ja arvoina opintopisteet.
     * @return opiskelijan suorittamat kurssit. 
     */
    public TreeMap<Course, Integer> getCompletions() {
        return this.completions;
    }
    
    /**
     * Palauttaa opiskelijan valitseman opintosuunnan Module-oliona.
     * @return valittu opintosuunta
     */
    public Module getActiveStudyField() {
        return this.activeStudyField;
    }
    
    /**
     * Palauttaa opiskelija valitseman tutkinto-ohjelman.
     * @return tutkinto-ohjelma Degree-oliona
     */
    public Degree getActiveDegree() {
        return this.activeDegree;
    }
    
    /**
     * Asettaa parametrina annetut suoritteet opiskelijan suoritteiksi.
     * @param newCompletions uudet suoritetut kurssit.
     * Avaimina Course-oliot ja arvoina kurssien opintopisteet.
     */
    public void setCompletions(TreeMap<Course, Integer> newCompletions) {
        this.completions = newCompletions;
    }
    
    /**
     * Asettaa parametrina annetun tutkinto-ohjelman opiskelijan 
     * aktiivisiksi opinnoiksi. Eli opiskelijan valitsemaksi tutkinto-ohjelmaksi.
     * @param newActiveDegree uusi tutkinto-ohjelma Degree-oliona
     */
    public void setActiveDegree(Degree newActiveDegree) {
        this.activeDegree = newActiveDegree;
    }
    
    /**
     * Asettaa parametrina annetun opintosuunnan opiskelijan aktiiviseksi 
     * opintosuunnaksi. 
     * @param newActiveStudyField uusi valittu opintosuunta
     */
    public void setActiveStudyField(Module newActiveStudyField) {
        this.activeStudyField = newActiveStudyField;
    }
    
    /**
     * Asettaa annetun kurssin suoritetuksi
     * muiden suoritettujen kurssien joukkoon completions-TreeMappiin.
     * @param completedCourse suoritettu(lisättävä) kurssi
     */
    public void completeCourse(Course completedCourse) {
        completions.put(completedCourse,completedCourse.getStudyPoints());
    }
    
    /**
     * Poistaa parametrina annetun kurssin opiskelin suorituksista.
     * @param removedCourse poistettava kurssi Course-oliona
     */
    public void removeCourse(Course removedCourse) {
        this.completions.remove(removedCourse);
    }
    
    /**
     * Palauttaa opiskelijan tilan nimen muodossa.
     * @return opiskelijan nimi
     */
    @Override
    public String toString() {
        return this.studentName;
    }
    
    /**
     * Poistaa kaikki opiskelijan suoritukset,
     * suorituksia kirjaapitävästä TreeMapista.
     */
    public void clearCompletions() {
        this.completions.clear();
    }
}
