package ipplanmanager.main;

import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.repository.BesoinRepository;
import ipplanmanager.repository.FichierPlanRepository;
import ipplanmanager.service.GestionnaireVLAN;
import ipplanmanager.service.MoteurRecommandation;
import ipplanmanager.service.MoteurVLSM;
import ipplanmanager.service.RapportService;
import ipplanmanager.service.RecommandationWifiInvite;
import ipplanmanager.service.RecommandationServeurs;
import ipplanmanager.service.RecommandationGrandVLAN;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP9 - Persistance =====");
        System.out.println();
        
        String fichierBesoins = "exports/besoins.csv";
        String fichierPlan = "exports/plan_adressage.csv";
        String fichierVlans = "exports/vlans.csv";
        String fichierRecommandations = "exports/recommandations.txt";
        String fichierRapport = "exports/rapport_complet.txt";
        
        BesoinRepository besoinRepository = new BesoinRepository();
        FichierPlanRepository fichierPlanRepository = new FichierPlanRepository();
        RapportService rapportService = new RapportService();
        
        try {
            ArrayList<BesoinReseau> besoins = besoinRepository.chargerBesoins(fichierBesoins);
            
            System.out.println("Besoins charges depuis " + fichierBesoins);
            for (int i = 0; i < besoins.size(); i++) {
                System.out.println("  - " + besoins.get(i).getNom() + " : " + besoins.get(i).getNombreHotes() + " hôtes");
            }
            System.out.println();
            
            MoteurVLSM moteurVLSM = new MoteurVLSM();
            ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan("10.10.0.0", besoins);
            
            GestionnaireVLAN gestionnaireVLAN = new GestionnaireVLAN();
            int numeroVLAN = 10;
            for (int i = 0; i < resultats.size(); i++) {
                ResultatVLSM r = resultats.get(i);
                VLAN vlan = new VLAN(numeroVLAN, r.getNomBesoin(), r, "VLAN " + r.getNomBesoin());
                gestionnaireVLAN.ajouterVLAN(vlan);
                numeroVLAN = numeroVLAN + 10;
            }
            
            MoteurRecommandation moteurRecommandation = new MoteurRecommandation();
            moteurRecommandation.ajouterRegle(new RecommandationWifiInvite());
            moteurRecommandation.ajouterRegle(new RecommandationServeurs());
            moteurRecommandation.ajouterRegle(new RecommandationGrandVLAN());
            
            ArrayList<Recommandation> recommandations = moteurRecommandation.analyserVLANs(gestionnaireVLAN.getVlans());
            
            fichierPlanRepository.sauvegarderPlanCSV(resultats, fichierPlan);
            System.out.println("Fichier sauvegarde : " + fichierPlan);
            
            fichierPlanRepository.sauvegarderVLANsCSV(gestionnaireVLAN.getVlans(), fichierVlans);
            System.out.println("Fichier sauvegarde : " + fichierVlans);
            
            fichierPlanRepository.sauvegarderRecommandations(recommandations, fichierRecommandations);
            System.out.println("Fichier sauvegarde : " + fichierRecommandations);
            
            rapportService.genererRapportComplet(besoins, resultats, gestionnaireVLAN.getVlans(), recommandations, fichierRapport);
            System.out.println("Fichier sauvegarde : " + fichierRapport);
            
            System.out.println();
            System.out.println("Traitement termine avec succes.");
            System.out.println("Tous les fichiers sont dans le dossier exports.");
            
        } catch (IOException e) {
            System.out.println("Erreur de fichier : " + e.getMessage());
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }
    }
}