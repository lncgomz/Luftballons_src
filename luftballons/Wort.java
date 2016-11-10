package luftballons;

/**
 *
 * @author LeoncioGomez
 */
public class Wort {
    private String de;
    private String es;
    private int klasse;
    private int kategorie;
    
    /**
     * Método constructor de la clase Wort (Palabra)
     * @param de Palabra en alemán
     * @param es Palabra en español
     * @param klasse Clase de palabra (Sust Masc, Adj, Adv, etc)
     * @param kategorie Categoría de palabra (Verbo, Deporte, Números, etc)
     */
    public Wort(String de, String es, int klasse, int kategorie){
        this.de = de;
        this.es = es;
        this.klasse = klasse;
        this.kategorie = kategorie;
    }

    /**
     * @return the de
     */
    public String getDe() {
        return de;
    }

    /**
     * @param de the de to set
     */
    public void setDe(String de) {
        this.de = de;
    }

    /**
     * @return the es
     */
    public String getEs() {
        return es;
    }

    /**
     * @param es the es to set
     */
    public void setEs(String es) {
        this.es = es;
    }

    /**
     * @return the klasse
     */
    public int getKlasse() {
        return klasse;
    }

    /**
     * @param klasse the klasse to set
     */
    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }
}
