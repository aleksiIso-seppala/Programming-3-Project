
package fi.tuni.prog3.sisu;

import java.util.TreeMap;

/**
 * Luokka, joka säilyttää moduulin ominaisuudet. 
 * @author Lauri Kalliojärvi
 */
public class Module {
    
    /**
     * TreeMap, joka sisältää moduulin sisältämät kurssit.
     * Kurssien nimet avaimina ja Course-oliot arvoina.
     */
    private TreeMap<String,Course> courses;
    
    /**
     * TreeMap, joka sisältää moduulin alimoduulit.
     * Moduulien nimet avaimina ja Module-oliot arvoina.
     */
    private TreeMap<String,Module> modules;
    
    /**
     * Moduulin tutkinto-ohjelma Degree-oliona.
     */
    private Degree degree;
    
    /**
     * Moduulin nimi.
     */
    private String name;
    
    /**
     * Moduulin tunniste.
     */
    private String id;
    
    /**
     * Moduulin opintopisteiden vähimmäismäärä.
     */
    private int minCredits;
    
    /**
     * Moduulista suoritettujen opintopisteiden määrä.
     */
    private int accCredits;
    
    /**
     * Luokan rakennin, joka ottaa kurssit, nimen, tunnisteen
     * sekä opintopisteiden määrän parametreinä.
     * @param courses TreeMap kursseista. Avaimina kurssien nimet
     * ja arvoina Course-oliot
     * @param name moduulin nimi
     * @param id moduulin id
     * @param credits opintopisteet
     */
    public Module(TreeMap<String,Course> courses,
            String name,
            String id, 
            int credits) {
        
        this.minCredits = credits;
        this.courses = courses;
        this.name = name;
        this.id = id;
        this.modules = new TreeMap<>();
        this.degree = null;
    }
    
    /**
     * Palauttaa moduulin sisältämät kurssit.
     * @return TreeMap kursseista. Kurssien nimet avaimina
     * ja Course-oliot arvoina.
     */
    public TreeMap<String,Course> getCourses() {
        return this.courses;
    }
    
    /**
     * Palauttaa moduulin sisältämän tutkinto-ohjelman Degree-oliona.
     * @return moduulin alainen Degree-olio
     */
    public Degree getDegree() {
        return this.degree;
    }
    
    /**
     * Palauttaa moduulin nimen.
     * @return moduulin nimi
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Palauttaa moduulin tunnisteen.
     * @return moduulin tunniste
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Palauttaa moduulin opintopisteiden vähimmäismäärän.
     * @return opintopisteet
     */
    public int getMinCredits() {
        return this.minCredits;
    }
    
    /**
     * Asettaa annetun tukinto-ohjelman moduulin alaiseksi.
     * @param degreeToSet asetettava Degree-olio
     */
    public void setDegree(Degree degreeToSet) {
        this.degree = degreeToSet;
    }
    
    /**
     * Lisää annetun moduulin alimoduuliksi lisäämällä sen TreeMappiin,
     * joka pitää kirjaa alimoduuleista.
     * @param module asetettava Module-olio
     */
    public void addModule(Module module){
        this.modules.put(module.getName(),module);
    }
    
    /**
     * Lisää annetun kurssin moduulin kursseista kirjaa pitävään TreeMappiin.
     * @param course lisättävä Course-olio
     */
    public void addCourse(Course course){
        this.courses.put(course.getName(),course);
    }
    
    /**
     * Tulostaa moduulin tilan.
     * @return moduulin tila muodossa: nimi suoritetut pisteet/vähimmäispisteet
     */
    @Override
    public String toString() {
        return this.name + " " + this.accCredits + "/" + this.minCredits;
    }
    
    /**
     * Hakee moduulin sisältämät alimoduulit TreeMapissa.
     * Avaimina moduulien nimet ja arvoina Module-oliot.
     * @return alimoduulit
     */
    public TreeMap<String,Module> getModules() {
        return this.modules;
    }
    
    /**
     * Hakee moduuliin tallennetut suoritettujen opintopisteiden määrän.
     * @return suoritetut opintopisteet
     */
    public int getAccCredits() {
        return this.accCredits;
    }
    
    /**
     * Asettaa suoritetut opintopisteet moduuliin.
     * @param credits suoritetut opintopisteet
     */
    public void setAccCredits(int credits) {
        this.accCredits = credits;
    }
}
