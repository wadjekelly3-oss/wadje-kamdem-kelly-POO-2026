package ipplanmanager.model;

public class BesoinReseau {
    private String nom;
    private int nombreHotes;
    
    public BesoinReseau(String nom, int nombreHotes) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "besoin_inconnu";
        } else {
            this.nom = nom;
        }
        if (nombreHotes <= 0) {
            this.nombreHotes = 1;
        } else {
            this.nombreHotes = nombreHotes;
        }
    }
    
    public String getNom() { return nom; }
    public int getNombreHotes() { return nombreHotes; }
}