package ipplanmanager;

public class RecommandationServeurs implements RegleRecommandation {
    
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom();
        String nomMajuscule = nom.toUpperCase();
        if (nomMajuscule.contains("SERVEUR")) {
            return new Recommandation(
                "Protection du VLAN Serveurs",
                "ÉLEVÉE",
                "Le VLAN " + nom + " doit être protégé par des ACL et surveillé en priorité."
            );
        }
        return null;
    }
}