package ipplanmanager;

import java.util.ArrayList;

public class GestionnaireVLAN {
    private ArrayList<VLAN> vlans;
    
    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }
    
    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException {
        if (vlan == null) {
            return;
        }
        for (int i = 0; i < vlans.size(); i++) {
            VLAN v = vlans.get(i);
            if (v.getId() == vlan.getId()) {
                throw new ConflitVLANException("Conflit VLAN : l'identifiant " + vlan.getId() + " est déjà utilisé.");
            }
        }
        vlans.add(vlan);
    }
    
    public ArrayList<VLAN> getVlans() {
        return vlans;
    }
    
    public void afficherTousLesVLANs() {
        for (int i = 0; i < vlans.size(); i++) {
            VLAN v = vlans.get(i);
            v.afficher();
            System.out.println();
        }
    }
}