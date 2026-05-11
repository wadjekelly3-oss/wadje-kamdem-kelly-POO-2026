package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public class RecommandationWifiInvite implements RegleRecommandation {
    
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom();
        String nomMajuscule = nom.toUpperCase();
        if (nomMajuscule.contains("WIFI")) {
            return new Recommandation(
                "Isolation du WiFi",
                "ELEVEE",
                "Le VLAN " + nom + " doit etre isole des VLANs internes."
            );
        }
        return null;
    }
}