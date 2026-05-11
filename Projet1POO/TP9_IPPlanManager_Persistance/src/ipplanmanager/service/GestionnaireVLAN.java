package ipplanmanager.service;

import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.model.VLAN;
import java.util.ArrayList;

public class GestionnaireVLAN {
    private ArrayList<VLAN> vlans;
    
    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }
    
    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException {
        if (vlan == null) return;
        for (int i = 0; i < vlans.size(); i++) {
            VLAN v = vlans.get(i);
            if (v.getId() == vlan.getId()) {
                throw new ConflitVLANException("Conflit VLAN : l'identifiant " + vlan.getId() + " est deja utilise.");
            }
        }
        vlans.add(vlan);
    }
    
    public ArrayList<VLAN> getVlans() {
        return vlans;
    }
}