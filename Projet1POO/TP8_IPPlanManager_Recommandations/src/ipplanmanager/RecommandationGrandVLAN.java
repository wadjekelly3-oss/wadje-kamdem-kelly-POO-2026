package ipplanmanager;

public class RecommandationGrandVLAN implements RegleRecommandation {
    
    public Recommandation analyser(VLAN vlan) {
        ResultatVLSM reseau = vlan.getReseauAssocie();
        if (reseau != null) {
            int capacite = reseau.getCapacite();
            if (capacite > 200) {
                return new Recommandation(
                    "VLAN de grande taille",
                    "MOYENNE",
                    "Le VLAN " + vlan.getNom() + " possède une grande capacité. Il faut surveiller les broadcasts."
                );
            }
        }
        return null;
    }
}