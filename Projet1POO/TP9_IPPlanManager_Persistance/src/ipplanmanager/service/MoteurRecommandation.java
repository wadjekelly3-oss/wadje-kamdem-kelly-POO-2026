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
            VLAN v = vlans.get(i);
            for (int j = 0; j < regles.size(); j++) {
                RegleRecommandation regle = regles.get(j);
                Recommandation r = regle.analyser(v);
                if (r != null) {
                    recos.add(r);
                }
            }
        }
        return recos;
    }
}