package ipplanmanager;

import java.util.ArrayList;

public class Equipement {
    private String nom, type;
    private ArrayList<InterfaceReseau> interfaces;
    
    public Equipement(String nom, String type) {
        setNom(nom); setType(type); interfaces = new ArrayList<>();
    }
    public String getNom() { return nom; }
    public void setNom(String n) { this.nom = (n==null||n.isEmpty())?"equipement_inconnu":n; }
    public String getType() { return type; }
    public void setType(String t) { this.type = (t==null||t.isEmpty())?"type_inconnu":t; }
    public void ajouterInterface(InterfaceReseau i) { interfaces.add(i); }
}