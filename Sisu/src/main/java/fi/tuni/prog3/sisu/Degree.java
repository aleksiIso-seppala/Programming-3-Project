
package fi.tuni.prog3.sisu;

import java.util.TreeMap;

/**
 * Luokka, joka säilyttää tutkinto-ohjelman ominaisuudet.
 * @author Lauri Kalliojärvi
 */
public class Degree {
    /**
     * TreeMap opintosuunnista.
     * TreeMapin avaimina opintosuuntien nimet.
     */
   private TreeMap<String, Module> studyFields;
   
   /**
     * TreeMap moduuleista.
     * Avaimina moduulien nimet.
     */
   private TreeMap<String, Module> modules;
   
   /**
     * Tutkinto-ohjelman laajuus opintopisteissä.
     */
   private int studyPoints;
   
   /**
     * Tutkinto-ohjelman nimi.
     */
   private String name;
   
   /**
     * Tutkinto-ohjelman tunniste.
     */
   private String id;
   
   /**
     * Oppilaan valitsema opintosuunta.
     */
   private Module selectedField;
   
   /**
     * Rakennin, joka ottaa arvot opintopisteille, nimelle 
     * sekä tunnisteelle parametreina.
     * @param studyPoints opintopisteet
     * @param name nimi
     * @param id tunniste
     */
   public Degree(
           int studyPoints,
           String name,
           String id ) {  
       this.studyFields = new TreeMap<>();
       this.modules = new TreeMap<>();
       this.studyPoints = studyPoints;
       this.name = name;
       this.id = id;  
   }
   
   /**
     * Palauttaa tutkinto-ohjelman opintosuunat.
     * @return TreeMap opintosuunnista, opintosuuntien nimet avaimina 
     * ja opintosuunta-oliot arvoina.
     */
   public TreeMap<String, Module> getStudyFields() {
       return this.studyFields;
   }
   
   /**
     * Lisää moduulin moduulista kirjaapitävään TreeMappiin.
     * @param module lisättävä moduuli.
     */
   public void addModule(Module module){
       modules.put(module.getName(),module);
   }
   
   /**
     * Palauttaa TreeMapin tutkinto-ohjelman moduuleista.
     * @return moduulit, moduulien nimet avaimina ja moduuli-oliot arvoina.
     */
   public TreeMap<String, Module> getModules() {
       return this.modules;
   }
   
   /**
     * Lisää annetun moduulin Tutkinto-ohjelmaan,
     * lisäämällä sen moduuleista listaa pitävään TreeMappiin.
     * @param module moduuli-olio.
     */
   public void addStudyField(Module module) {
       studyFields.put(module.getName(), module);
   }
   
   /**
     * Palauttaa tutkinto-ohjelman laajuuden opintopisteinä.
     * @return opintopisteiden määrä.
     */
   public int getStudyPoints() {
       return this.studyPoints;
   }
   
   /**
     * Palauttaa tutkinto-ohjelman nimen.
     * @return tutkinto-ohjelman nimi.
     */
   public String getName() {
       return this.name;
   }
   
   /**
     * Palauttaa tutkinto-ohjelman tunnisteen.
     * @return tunniste.
     */
   public String getId() {
       return this.id;
   }
   
   /**
     * Palauttaa tutkinto-ohjelman tilan nimen muodossa.
     * @return nimi
     */
   @Override
    public String toString() {
        return this.name;
    }
    
    /**
     * Asettaa annetun opintosuunnan tutkinto-ohjelman alaiseksi,
     * lisäämällä opintosuunnan TreeMappiin, joka pitää listaa opintosuunnista.
     * @param selectedFieldStr valittu opintosuunta.
     */
    public void setSelectedField(String selectedFieldStr) {
        this.selectedField = this.studyFields.get(selectedFieldStr);
    }
    
    /**
     * Palauttaa valitun opintosuunnan.
     * @return valittu opintosuunta.
     */
    public Module getSelectedField() {
        return this.selectedField;
    }
}
