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
    
    public void ajouterEquipement(Equipement e) { equipements.add(e); }
    public void ajouterSousReseau(SousReseau s) { sousReseaux.add(s); }
    
    public void afficher() {
        System.out.println("===== Infrastructure : " + nom + " =====");
        for (SousReseau s : sousReseaux) { s.afficher(); System.out.println(); }
        for (Equipement e : equipements) { e.afficher(); System.out.println(); }
    }
}