package ipplanmanager;

import java.util.ArrayList;

public class Equipement {
    private String nom, type;
    private ArrayList<InterfaceReseau> interfaces;
    
    public Equipement(String nom, String type) {
        setNom(nom);
        setType(type);
        interfaces = new ArrayList<>();
    }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = (nom == null || nom.isEmpty()) ? "equipement_inconnu" : nom; }
    public String getType() { return type; }
    public void setType(String type) { this.type = (type == null || type.isEmpty()) ? "type_inconnu" : type; }
    public void ajouterInterface(InterfaceReseau i) { interfaces.add(i); }
    
    public void afficher() {
        System.out.println("Nom : " + nom + " | Type : " + type + " | Interfaces : " + interfaces.size());
        for (InterfaceReseau i : interfaces) { i.afficher(); System.out.println(); }
    }
}