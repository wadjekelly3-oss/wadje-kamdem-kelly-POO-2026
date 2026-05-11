package ipplanmanager;

public class SousReseau {
    private String nom;
    private ReseauIP reseau;
    public SousReseau(String nom, ReseauIP reseau) { setNom(nom); this.reseau = reseau; }
    public String getNom() { return nom; }
    public void setNom(String n) { this.nom = (n==null||n.isEmpty())?"Sous-reseau_inconnu":n; }
}