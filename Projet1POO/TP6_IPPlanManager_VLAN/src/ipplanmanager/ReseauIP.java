package ipplanmanager;

public class ReseauIP {
    private String adresseReseau;
    private int masqueCidr;
    private String description;
    
    public ReseauIP(String adresseReseau, int masqueCidr, String description) {
        setAdresseReseau(adresseReseau);
        setMasqueCidr(masqueCidr);
        setDescription(description);
    }
    
    public String getAdresseReseau() { return adresseReseau; }
    public void setAdresseReseau(String adresseReseau) {
        this.adresseReseau = (adresseReseau == null || adresseReseau.isEmpty()) ? "0.0.0.0" : adresseReseau;
    }
    public int getMasqueCidr() { return masqueCidr; }
    public void setMasqueCidr(int masqueCidr) {
        this.masqueCidr = (masqueCidr < 0 || masqueCidr > 32) ? 24 : masqueCidr;
    }
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = (description == null || description.isEmpty()) ? "Pas de description" : description;
    }
    
    public void afficher() {
        System.out.println("Réseau : " + adresseReseau + "/" + masqueCidr);
        System.out.println("Description : " + description);
    }
}