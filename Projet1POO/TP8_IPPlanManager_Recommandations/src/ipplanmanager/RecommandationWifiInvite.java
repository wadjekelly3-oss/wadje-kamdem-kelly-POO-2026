package ipplanmanager;

public class RecommandationWifiInvite implements RegleRecommandation {
    
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom();
        String nomMajuscule = nom.toUpperCase();
        if (nomMajuscule.contains("WIFI")) {
            return new Recommandation(
                "Isolation du WiFi",
                "ÉLEVÉE",
                "Le VLAN " + nom + " doit être isolé des VLANs internes sensibles."
            );
        }
        return null;
    }
}