package ipplanmanager;

public class InterfaceReseau {
    private String nom;
    private AdresseIP adresseIP;
    private boolean active;
    
    public InterfaceReseau(String nom, AdresseIP adresseIP) {
        setNom(nom);
        this.adresseIP = adresseIP;
        this.active = false;
    }
    
    public String getNom() { return nom; }
    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "interface_inconnue" : nom;
    }
    public AdresseIP getAdresseIP() { return adresseIP; }
    public boolean isActive() { return active; }
    public void activer() { active = true; }
    public void desactiver() { active = false; }
    
    public void afficher() {
        System.out.println("Interface : " + nom);
        if (adresseIP != null) adresseIP.afficher();
        else System.out.println("Adresse IP : non configurée");
        System.out.println("État : " + (active ? "active" : "inactive"));
    }
}