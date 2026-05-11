package ipplanmanager;

import java.util.ArrayList;

public class InfrastructureReseau {
    private String nom;
    private ArrayList<Equipement> equipements = new ArrayList<>();
    private ArrayList<SousReseau> sousReseaux = new ArrayList<>();
    public InfrastructureReseau(String nom) { this.nom = nom; }
    public void ajouterEquipement(Equipement e) { equipements.add(e); }
    public void ajouterSousReseau(SousReseau s) { sousReseaux.add(s); }
}