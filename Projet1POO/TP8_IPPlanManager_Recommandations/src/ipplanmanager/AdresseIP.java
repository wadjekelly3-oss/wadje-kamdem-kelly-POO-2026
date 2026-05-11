package ipplanmanager;

public class AdresseIP {
    private String valeur;
    
    public AdresseIP(String valeur) { setValeur(valeur); }
    public String getValeur() { return valeur; }
    public void setValeur(String valeur) {
        this.valeur = (valeur == null || valeur.isEmpty()) ? "0.0.0.0" : valeur;
    }
    public void afficher() { System.out.println("Adresse IP : " + valeur); }
}