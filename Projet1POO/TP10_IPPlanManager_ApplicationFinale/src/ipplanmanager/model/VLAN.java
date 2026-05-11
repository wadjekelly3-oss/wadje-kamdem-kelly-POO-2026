package ipplanmanager.model;

public class VLAN {
    private int id;
    private String nom;
    private ResultatVLSM reseauAssocie;
    private String description;
    
    public VLAN(int id, String nom, ResultatVLSM reseauAssocie, String description) {
        if (id <= 0) {
            this.id = 1;
        } else {
            this.id = id;
        }
        if (nom == null || nom.isEmpty()) {
            this.nom = "VLAN_INCONNU";
        } else {
            this.nom = nom;
        }
        this.reseauAssocie = reseauAssocie;
        if (description == null || description.isEmpty()) {
            this.description = "Aucune description";
        } else {
            this.description = description;
        }
    }
    
    public int getId() { return id; }
    public String getNom() { return nom; }
    public ResultatVLSM getReseauAssocie() { return reseauAssocie; }
    public String getDescription() { return description; }
    
    public void afficher() {
        System.out.println("VLAN " + id + " | " + nom);
        if (reseauAssocie != null) {
            reseauAssocie.afficher();
        }
    }
}