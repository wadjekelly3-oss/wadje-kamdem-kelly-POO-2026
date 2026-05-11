package ipplanmanager.model;

public class ResultatVLSM {
    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private String masqueDecimal;
    private int capacite;
    private int nombreHotesDemandes;
    private String premiereAdresseUtilisable;
    private String derniereAdresseUtilisable;
    
    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr,
                        String masqueDecimal, int capacite,
                        String premiere, String derniere, int nombreHotesDemandes) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;
        this.premiereAdresseUtilisable = premiere;
        this.derniereAdresseUtilisable = derniere;
        this.nombreHotesDemandes = nombreHotesDemandes;
    }
    
    public String getNomBesoin() { return nomBesoin; }
    public String getAdresseReseau() { return adresseReseau; }
    public int getCidr() { return cidr; }
    public String getMasqueDecimal() { return masqueDecimal; }
    public int getCapacite() { return capacite; }
    public int getNombreHotesDemandes() { return nombreHotesDemandes; }
    public String getPremiereAdresseUtilisable() { return premiereAdresseUtilisable; }
    public String getDerniereAdresseUtilisable() { return derniereAdresseUtilisable; }
}