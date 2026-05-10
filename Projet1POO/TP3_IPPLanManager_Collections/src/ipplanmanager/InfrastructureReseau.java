package ipplanmanager;

import java.util.ArrayList;

public class InfrastructureReseau {
    private String nom;
    private ArrayList<Equipement> equipements;
    private ArrayList<SousReseau> sousReseaux;
    
    public InfrastructureReseau(String nom) {
        this.nom = nom;
        equipements = new ArrayList<>();
        sousReseaux = new ArrayList<>();
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void ajouterEquipement(Equipement equipement) {
        equipements.add(equipement);
    }
    
    public void ajouterSousReseau(SousReseau sousReseau) {
        sousReseaux.add(sousReseau);
    }
    
    public void afficherEquipements() {
        if (equipements.isEmpty()) {
            System.out.println("Aucun équipement dans l'infrastructure.");
        } else {
            for (Equipement equipement : equipements) {
                equipement.afficher();
                System.out.println();
            }
        }
    }
    
    public void afficherSousReseaux() {
        if (sousReseaux.isEmpty()) {
            System.out.println("Aucun sous-réseau dans l'infrastructure.");
        } else {
            for (SousReseau sousReseau : sousReseaux) {
                sousReseau.afficher();
                System.out.println();
            }
        }
    }
    
    public void rechercherEquipement(String nom) {
        boolean trouve = false;
        for (Equipement equipement : equipements) {
            if (equipement.getNom().equalsIgnoreCase(nom)) {
                System.out.println("=== Équipement trouvé ===");
                equipement.afficher();
                trouve = true;
                break;
            }
        }
        if (!trouve) {
            System.out.println("Équipement introuvable.");
        }
    }
    
    public void afficher() {
        System.out.println("===== Infrastructure : " + nom + " =====");
        System.out.println();
        System.out.println("--- Sous-réseaux ---");
        afficherSousReseaux();
        System.out.println("--- Équipements ---");
        afficherEquipements();
    }
}