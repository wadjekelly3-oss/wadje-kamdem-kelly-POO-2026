package ipplanmanager;

public class AdresseIP {

    private String valeur;

    public AdresseIP(String valeur) {
        setValeur(valeur);
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            System.out.println("Erreur : adresseIP invalide.");
            this.valeur = "0.0.0.0";
        } else {
            this.valeur = valeur;
        }
    }

    public void afficher() {
        System.out.println("Adresse IP : " + valeur);
    }

    // Méthode ajoutée pour la section 14
    public boolean estAdresseLocale() {
        return valeur != null && valeur.startsWith("192.");
    }
}