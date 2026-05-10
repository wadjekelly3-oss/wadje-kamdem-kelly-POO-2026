package ipplanmanager;

import java.util.ArrayList;

public class GestionnaireVLAN {
    private ArrayList<VLAN> vlans;
    
    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }
    
    public void ajouterVLAN(VLAN vlan) {
        vlans.add(vlan);
    }
    
    public void afficherTousLesVLANs() {
        for (VLAN vlan : vlans) {
            vlan.afficher();
            System.out.println();
        }
    }
    
    public VLAN rechercherVLAN(int id) {
        for (VLAN vlan : vlans) {
            if (vlan.getId() == id) return vlan;
        }
        return null;
    }
    
    public int obtenirNombreVLANs() {
        return vlans.size();
    }
    
    // Travail supplémentaire
    public void afficherVLANsCritiques() {
        System.out.println("===== VLANs CRITIQUES (capacité > 100 hôtes) =====");
        boolean trouve = false;
        for (VLAN vlan : vlans) {
            if (vlan.getReseauAssocie() != null && vlan.getReseauAssocie().getCapacite() > 100) {
                System.out.println("VLAN critique : VLAN " + vlan.getId() + " - " 
                    + vlan.getNom() + " - " + vlan.getReseauAssocie().getCapacite() + " hôtes");
                trouve = true;
            }
        }
        if (!trouve) System.out.println("Aucun VLAN critique détecté.");
    }
    
    public VLAN obtenirPlusGrandVLAN() {
        VLAN plusGrand = null;
        int max = 0;
        for (VLAN vlan : vlans) {
            if (vlan.getReseauAssocie() != null && vlan.getReseauAssocie().getCapacite() > max) {
                max = vlan.getReseauAssocie().getCapacite();
                plusGrand = vlan;
            }
        }
        return plusGrand;
    }
    
    public ArrayList<VLAN> getVlans() { return vlans; }
}