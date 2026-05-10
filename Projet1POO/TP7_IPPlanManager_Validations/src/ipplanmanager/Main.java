package ipplanmanager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP7 - Validations avancées =====");
        System.out.println();
        
        // ===== SCÉNARIO 1 : Plan normal + validation =====
        System.out.println("========== SCÉNARIO 1 : Plan VLSM normal ==========");
        System.out.println();
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("SERVEURS", 20));
        
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0", besoins);
        
        System.out.println("Plan généré :");
        for (ResultatVLSM r : resultats) {
            r.afficher();
        }
        
        ValidateurPlanAdressage validateur = new ValidateurPlanAdressage();
        try {
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            validateur.afficherValidationReussie();
        } catch (AdresseIPInvalideException | ChevauchementReseauException e) {
            System.out.println("Erreur de validation : " + e.getMessage());
        }
        
        // ===== SCÉNARIO 2 : Test conflit VLAN =====
        System.out.println();
        System.out.println("========== SCÉNARIO 2 : Test de conflit VLAN ==========");
        System.out.println();
        
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        try {
            VLAN vlan10 = new VLAN(10, "ADMINISTRATION", resultats.get(0), "VLAN Administration");
            VLAN vlan20 = new VLAN(20, "TECHNIQUE", resultats.get(1), "VLAN Technique");
            VLAN vlan10Erreur = new VLAN(10, "WIFI", resultats.get(2), "VLAN WiFi avec ID déjà utilisé");
            
            gestionnaire.ajouterVLAN(vlan10);
            System.out.println("VLAN 10 ajouté avec succès.");
            gestionnaire.ajouterVLAN(vlan20);
            System.out.println("VLAN 20 ajouté avec succès.");
            gestionnaire.ajouterVLAN(vlan10Erreur);
            System.out.println("VLAN 10 (doublon) ajouté.");
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }
        
        // ===== SCÉNARIO 3 : Adresse IP invalide (Travail demandé §17) =====
        System.out.println();
        System.out.println("========== SCÉNARIO 3 : Adresse IP de départ invalide ==========");
        System.out.println();
        
        try {
            CalculateurReseau.verifierAdresseIP("192.168.300.0");
            System.out.println("Adresse valide.");
        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur détectée : " + e.getMessage());
        }
        
        // ===== SCÉNARIO 4 : Test chevauchement manuel (Travail demandé §17) =====
        System.out.println();
        System.out.println("========== SCÉNARIO 4 : Test de chevauchement manuel ==========");
        System.out.println();
        
        ArrayList<ResultatVLSM> reseauxChevauchants = new ArrayList<>();
        reseauxChevauchants.add(new ResultatVLSM("RESEAU_A", "192.168.1.0", 25,
            "255.255.255.128", 126, "192.168.1.1", "192.168.1.126"));
        reseauxChevauchants.add(new ResultatVLSM("RESEAU_B", "192.168.1.64", 26,
            "255.255.255.192", 62, "192.168.1.65", "192.168.1.126"));
        
        System.out.println("Réseaux créés manuellement :");
        for (ResultatVLSM r : reseauxChevauchants) {
            r.afficher();
        }
        
        ValidateurPlanAdressage validateur2 = new ValidateurPlanAdressage();
        try {
            validateur2.verifierChevauchements(reseauxChevauchants);
            System.out.println("Aucun chevauchement détecté.");
        } catch (ChevauchementReseauException e) {
            System.out.println("Erreur de chevauchement : " + e.getMessage());
        }
    }
}