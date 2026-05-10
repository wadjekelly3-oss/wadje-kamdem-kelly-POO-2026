package ipplanmanager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP5 - Moteur VLSM =====");
        System.out.println();
        
        // Scénario 1 : Besoins standards
        System.out.println("========== SCÉNARIO 1 : Besoins standards ==========");
        System.out.println();
        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("TECHNIQUE", 120));
        besoins1.add(new BesoinReseau("WIFI", 80));
        besoins1.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins1.add(new BesoinReseau("SERVEURS", 20));
        besoins1.add(new BesoinReseau("DIRECTION", 10));
        
        System.out.println("Besoins exprimés par l'utilisateur :");
        for (BesoinReseau b : besoins1) {
            b.afficher();
        }
        
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats1 = moteur.genererPlan("192.168.1.0", besoins1);
        
        System.out.println();
        System.out.println("Plan d'adressage proposé :");
        for (ResultatVLSM r : resultats1) {
            r.afficher();
        }
        
        // Scénario 2 : Petits besoins
        System.out.println();
        System.out.println("========== SCÉNARIO 2 : Petits besoins ==========");
        System.out.println();
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("LABO", 5));
        besoins2.add(new BesoinReseau("ADMIN", 10));
        besoins2.add(new BesoinReseau("COM", 15));
        
        System.out.println("Besoins exprimés par l'utilisateur :");
        for (BesoinReseau b : besoins2) {
            b.afficher();
        }
        
        ArrayList<ResultatVLSM> resultats2 = moteur.genererPlan("10.0.0.0", besoins2);
        
        System.out.println();
        System.out.println("Plan d'adressage proposé :");
        for (ResultatVLSM r : resultats2) {
            r.afficher();
        }
        
        // Scénario 3 : Gros besoins
        System.out.println();
        System.out.println("========== SCÉNARIO 3 : Gros besoins ==========");
        System.out.println();
        ArrayList<BesoinReseau> besoins3 = new ArrayList<>();
        besoins3.add(new BesoinReseau("SIEGE", 500));
        besoins3.add(new BesoinReseau("DATACENTER", 200));
        besoins3.add(new BesoinReseau("R&D", 100));
        besoins3.add(new BesoinReseau("SUPPORT", 60));
        besoins3.add(new BesoinReseau("STAGIAIRES", 25));
        
        System.out.println("Besoins exprimés par l'utilisateur :");
        for (BesoinReseau b : besoins3) {
            b.afficher();
        }
        
        ArrayList<ResultatVLSM> resultats3 = moteur.genererPlan("172.16.0.0", besoins3);
        
        System.out.println();
        System.out.println("Plan d'adressage proposé :");
        for (ResultatVLSM r : resultats3) {
            r.afficher();
        }
    }
}