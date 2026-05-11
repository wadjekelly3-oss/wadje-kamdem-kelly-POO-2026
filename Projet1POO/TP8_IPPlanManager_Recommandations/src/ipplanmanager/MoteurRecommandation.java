package ipplanmanager;

import java.util.ArrayList;

public class MoteurRecommandation {
    private ArrayList<RegleRecommandation> regles;
    
    public MoteurRecommandation() {
        regles = new ArrayList<>();
    }
    
    public void ajouterRegle(RegleRecommandation regle) {
        regles.add(regle);
    }
    
    public ArrayList<Recommandation> analyserVLANs(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> resultats = new ArrayList<>();
        for (int i = 0; i < vlans.size(); i++) {
            VLAN vlan = vlans.get(i);
            for (int j = 0; j < regles.size(); j++) {
                RegleRecommandation regle = regles.get(j);
                Recommandation r = regle.analyser(vlan);
                if (r != null) {
                    resultats.add(r);
                }
            }
        }
        return resultats;
    }
    
    public void afficherRecommandations(ArrayList<Recommandation> liste) {
        if (liste.isEmpty()) {
            System.out.println("Aucune recommandation particulière.");
            return;
        }
        for (int i = 0; i < liste.size(); i++) {
            liste.get(i).afficher();
        }
    }
}