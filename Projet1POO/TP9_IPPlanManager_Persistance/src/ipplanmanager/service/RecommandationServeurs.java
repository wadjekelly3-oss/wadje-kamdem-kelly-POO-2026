package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public class RecommandationServeurs implements RegleRecommandation {
    
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom();
        String nomMajuscule = nom.toUpperCase();
        if (nomMajuscule.contains("SERVEUR")) {
            return new Recommandation(
                "Protection du VLAN Serveurs",
                "ELEVEE",
                "Le VLAN " + nom + " doit etre protege par des ACL."
            );
        }
        return null;
    }
}