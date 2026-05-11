package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;
import java.util.ArrayList;

public class MoteurRecommandation {
    private ArrayList<RegleRecommandation> regles;
    
    public MoteurRecommandation() {
        regles = new ArrayList<>();
    }
    
    public void ajouterRegle(RegleRecommandation r) {
        regles.add(r);
    }
    
    public ArrayList<Recommandation> analyserVLANs(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> recos = new ArrayList<>();
        for (int i = 0; i < vlans.size(); i++) {
            for (int j = 0; j < regles.size(); j++) {
                Recommandation r = regles.get(j).analyser(vlans.get(i));
                if (r != null) {
                    recos.add(r);
                }
            }
        }
        return recos;
    }
}