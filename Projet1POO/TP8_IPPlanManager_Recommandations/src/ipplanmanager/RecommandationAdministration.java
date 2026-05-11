package ipplanmanager;

public class RecommandationAdministration implements RegleRecommandation {
    
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom();
        String nomMajuscule = nom.toUpperCase();
        if (nomMajuscule.contains("ADMIN")) {
            return new Recommandation(
                "Accès restreint Administration",
                "ÉLEVÉE",
                "Le VLAN " + nom + " doit être limité aux administrateurs réseau."
            );
        }
        return null;
    }
}