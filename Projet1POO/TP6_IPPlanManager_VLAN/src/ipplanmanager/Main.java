package ipplanmanager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP6 - VLANs =====");
        System.out.println();
        
        // ===================== SCÉNARIO A =====================
        System.out.println("========== SCÉNARIO A : Besoins standards ==========");
        System.out.println();
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("SERVEURS", 20));
        
        System.out.println("Besoins exprimés :");
        for (BesoinReseau b : besoins) b.afficher();
        
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0", besoins);
        
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        int idVLAN = 10;
        for (ResultatVLSM r : resultats) {
            gestionnaire.ajouterVLAN(new VLAN(idVLAN, r.getNomBesoin(), r, "VLAN du service " + r.getNomBesoin()));
            idVLAN += 10;
        }
        
        System.out.println();
        System.out.println("===== VLANS GÉNÉRÉS =====");
        gestionnaire.afficherTousLesVLANs();
        
        System.out.println("Nombre total de VLANs : " + gestionnaire.obtenirNombreVLANs());
        System.out.println();
        gestionnaire.afficherVLANsCritiques();
        VLAN plusGrand = gestionnaire.obtenirPlusGrandVLAN();
        if (plusGrand != null) {
            System.out.println("VLAN de plus grande capacité : VLAN " + plusGrand.getId() 
                + " - " + plusGrand.getNom() + " - " + plusGrand.getReseauAssocie().getCapacite() + " hôtes");
        }
        
        System.out.println();
        System.out.println("===== TEST DE RECHERCHE =====");
        VLAN vlan20 = gestionnaire.rechercherVLAN(20);
        if (vlan20 != null) {
            System.out.println("VLAN trouvé :");
            vlan20.afficher();
        }
        
        VLAN vlan99 = gestionnaire.rechercherVLAN(99);
        if (vlan99 == null) System.out.println("VLAN 99 introuvable.");
        
        // ===================== SCÉNARIO B : Université =====================
        System.out.println();
        System.out.println("========== SCÉNARIO B : Université ==========");
        System.out.println();
        
        ArrayList<BesoinReseau> besoinsUniv = new ArrayList<>();
        besoinsUniv.add(new BesoinReseau("ETUDIANTS", 500));
        besoinsUniv.add(new BesoinReseau("WIFI_PUBLIC", 200));
        besoinsUniv.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoinsUniv.add(new BesoinReseau("LABORATOIRES", 60));
        besoinsUniv.add(new BesoinReseau("SERVEURS", 30));
        
        System.out.println("Besoins exprimés :");
        for (BesoinReseau b : besoinsUniv) b.afficher();
        
        ArrayList<ResultatVLSM> resultatsUniv = moteur.genererPlan("10.0.0.0", besoinsUniv);
        
        GestionnaireVLAN gestionnaireUniv = new GestionnaireVLAN();
        int idVLANUniv = 100;
        for (ResultatVLSM r : resultatsUniv) {
            gestionnaireUniv.ajouterVLAN(new VLAN(idVLANUniv, r.getNomBesoin(), r, "VLAN " + r.getNomBesoin()));
            idVLANUniv += 10;
        }
        
        System.out.println();
        System.out.println("===== VLANS GÉNÉRÉS (Université) =====");
        gestionnaireUniv.afficherTousLesVLANs();
        
        System.out.println("Nombre total de VLANs : " + gestionnaireUniv.obtenirNombreVLANs());
        System.out.println();
        gestionnaireUniv.afficherVLANsCritiques();
        VLAN plusGrandUniv = gestionnaireUniv.obtenirPlusGrandVLAN();
        if (plusGrandUniv != null) {
            System.out.println("VLAN de plus grande capacité : VLAN " + plusGrandUniv.getId() 
                + " - " + plusGrandUniv.getNom() + " - " + plusGrandUniv.getReseauAssocie().getCapacite() + " hôtes");
        }
    }
}