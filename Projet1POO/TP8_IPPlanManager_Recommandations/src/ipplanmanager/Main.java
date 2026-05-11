package ipplanmanager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP8 - Recommandations =====");
        System.out.println();
        
        // Créer les besoins
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ETUDIANTS", 500));
        besoins.add(new BesoinReseau("WIFI_INVITES", 200));
        besoins.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoins.add(new BesoinReseau("LABORATOIRES", 60));
        besoins.add(new BesoinReseau("SERVEURS", 30));
        
        System.out.println("Besoins exprimes :");
        for (int i = 0; i < besoins.size(); i++) {
            besoins.get(i).afficher();
        }
        System.out.println();
        
        // Générer le plan VLSM
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("10.10.0.0", besoins);
        
        // Créer les VLANs
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        int idVLAN = 10;
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r = resultats.get(i);
            VLAN vlan = new VLAN(idVLAN, r.getNomBesoin(), r, "VLAN " + r.getNomBesoin());
            try {
                gestionnaire.ajouterVLAN(vlan);
            } catch (ConflitVLANException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
            idVLAN = idVLAN + 10;
        }
        
        System.out.println("Plan VLAN genere :");
        System.out.println();
        gestionnaire.afficherTousLesVLANs();
        
        // Appliquer les règles
        MoteurRecommandation moteurReco = new MoteurRecommandation();
        moteurReco.ajouterRegle(new RecommandationWifiInvite());
        moteurReco.ajouterRegle(new RecommandationServeurs());
        moteurReco.ajouterRegle(new RecommandationGrandVLAN());
        moteurReco.ajouterRegle(new RecommandationAdministration());
        
        ArrayList<Recommandation> recos = moteurReco.analyserVLANs(gestionnaire.getVlans());
        
        System.out.println("Recommandations proposees :");
        System.out.println();
        moteurReco.afficherRecommandations(recos);
    }
}