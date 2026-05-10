package ipplanmanager;

public class ResultatVLSM {
    private String nomBesoin, adresseReseau, masqueDecimal;
    private int cidr, capacite;
    private String premiereAdresseUtilisable, derniereAdresseUtilisable;
    
    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr,
                        String masqueDecimal, int capacite,
                        String premiere, String derniere) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;
        this.premiereAdresseUtilisable = premiere;
        this.derniereAdresseUtilisable = derniere;
    }
    
    public String getNomBesoin() { return nomBesoin; }
    public String getAdresseReseau() { return adresseReseau; }
    public int getCidr() { return cidr; }
    public String getMasqueDecimal() { return masqueDecimal; }
    public int getCapacite() { return capacite; }
    public String getPremiereAdresseUtilisable() { return premiereAdresseUtilisable; }
    public String getDerniereAdresseUtilisable() { return derniereAdresseUtilisable; }
    
    public void afficher() {
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr
            + " | Masque : " + masqueDecimal
            + " | Plage : " + premiereAdresseUtilisable + " - " + derniereAdresseUtilisable
            + " | Capacité : " + capacite + " hôtes");
    }
}