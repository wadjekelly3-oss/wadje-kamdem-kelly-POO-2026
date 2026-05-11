package ipplanmanager;

public class BesoinReseau {
    private String nom;
    private int nombreHotes;
    public BesoinReseau(String nom, int nombreHotes) { setNom(nom); setNombreHotes(nombreHotes); }
    public String getNom() { return nom; }
    public void setNom(String n) { this.nom = (n==null||n.isEmpty())?"besoin_inconnu":n; }
    public int getNombreHotes() { return nombreHotes; }
    public void setNombreHotes(int nh) { this.nombreHotes = (nh<=0)?1:nh; }
    public void afficher() { System.out.println("Besoin : " + nom + " | Hôtes : " + nombreHotes); }
}