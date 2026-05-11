package ipplanmanager.service;

import ipplanmanager.console.ConsoleService;
import ipplanmanager.exception.AdresseIPInvalideException;
import ipplanmanager.exception.ChevauchementReseauException;
import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.repository.BesoinRepository;
import ipplanmanager.repository.FichierPlanRepository;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicationIPPlanManager {
    private ConsoleService console;
    private MoteurVLSM moteurVLSM;
    private GestionnaireVLAN gestionnaireVLAN;
    private ValidateurPlanAdressage validateur;
    private MoteurRecommandation moteurRecommandation;
    private FichierPlanRepository fichierRepository;
    private RapportService rapportService;
    private BesoinRepository besoinRepository;
    
    public ApplicationIPPlanManager() {
        console = new ConsoleService();
        moteurVLSM = new MoteurVLSM();
        gestionnaireVLAN = new GestionnaireVLAN();
        validateur = new ValidateurPlanAdressage();
        moteurRecommandation = new MoteurRecommandation();
        fichierRepository = new FichierPlanRepository();
        rapportService = new RapportService();
        besoinRepository = new BesoinRepository();
        
        moteurRecommandation.ajouterRegle(new RecommandationWifiInvite());
        moteurRecommandation.ajouterRegle(new RecommandationServeurs());
        moteurRecommandation.ajouterRegle(new RecommandationGrandVLAN());
    }
    
    public void demarrer() {
        boolean continuer = true;
        while (continuer) {
            console.afficherMenu();
            int choix = console.lireChoix();
            
            if (choix == 1) {
                executerGenerationComplete();
            } else if (choix == 2) {
                executerChargementCSV();
            } else if (choix == 3) {
                System.out.println("Merci d'avoir utilise IPPlan-Manager !");
                continuer = false;
            } else {
                System.out.println("Choix invalide.");
            }
        }
    }
    
    private void executerGenerationComplete() {
        try {
            String nomProjet = console.saisirTexte("\nNom du projet reseau : ");
            String adresseDepart = console.saisirTexte("Adresse reseau de depart : ");
            
            CalculateurReseau.verifierAdresseIP(adresseDepart);
            
            ArrayList<BesoinReseau> besoins = console.saisirBesoins();
            ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan(adresseDepart, besoins);
            
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            
            genererVLANs(resultats);
            
            ArrayList<VLAN> vlans = gestionnaireVLAN.getVlans();
            ArrayList<Recommandation> recos = moteurRecommandation.analyserVLANs(vlans);
            
            afficherResultats(resultats, vlans, recos);
            sauvegarderResultats(nomProjet, besoins, resultats, vlans, recos);
            
            System.out.println("\nTraitement termine avec succes !");
            
        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur d'adresse IP : " + e.getMessage());
        } catch (ChevauchementReseauException e) {
            System.out.println("Erreur de chevauchement : " + e.getMessage());
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur de fichier : " + e.getMessage());
        }
    }
    
    private void executerChargementCSV() {
        try {
            String chemin = console.saisirTexte("\nChemin du fichier CSV : ");
            String nomProjet = console.saisirTexte("Nom du projet : ");
            String adresseDepart = console.saisirTexte("Adresse reseau de depart : ");
            
            CalculateurReseau.verifierAdresseIP(adresseDepart);
            
            ArrayList<BesoinReseau> besoins = besoinRepository.chargerBesoins(chemin);
            System.out.println("Besoins charges : " + besoins.size());
            
            ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan(adresseDepart, besoins);
            
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            
            genererVLANs(resultats);
            
            ArrayList<VLAN> vlans = gestionnaireVLAN.getVlans();
            ArrayList<Recommandation> recos = moteurRecommandation.analyserVLANs(vlans);
            
            afficherResultats(resultats, vlans, recos);
            sauvegarderResultats(nomProjet, besoins, resultats, vlans, recos);
            
            System.out.println("\nTraitement termine avec succes !");
            
        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur d'adresse IP : " + e.getMessage());
        } catch (ChevauchementReseauException e) {
            System.out.println("Erreur de chevauchement : " + e.getMessage());
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur de fichier : " + e.getMessage());
        }
    }
    
    private void genererVLANs(ArrayList<ResultatVLSM> resultats) throws ConflitVLANException {
        int numeroVLAN = 10;
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r = resultats.get(i);
            VLAN vlan = new VLAN(numeroVLAN, r.getNomBesoin(), r, "VLAN " + r.getNomBesoin());
            gestionnaireVLAN.ajouterVLAN(vlan);
            numeroVLAN = numeroVLAN + 10;
        }
    }
    
    private void afficherResultats(ArrayList<ResultatVLSM> resultats, ArrayList<VLAN> vlans,
                                    ArrayList<Recommandation> recos) {
        System.out.println("\n===== PLAN D'ADRESSAGE PROPOSE =====");
        for (int i = 0; i < resultats.size(); i++) {
            resultats.get(i).afficher();
        }
        
        System.out.println("\n===== VLANS GENERES =====");
        for (int i = 0; i < vlans.size(); i++) {
            vlans.get(i).afficher();
        }
        
        System.out.println("\n===== RECOMMANDATIONS =====");
        if (recos.isEmpty()) {
            System.out.println("Aucune recommandation.");
        } else {
            for (int i = 0; i < recos.size(); i++) {
                recos.get(i).afficher();
            }
        }
    }
    
    private void sauvegarderResultats(String nomProjet, ArrayList<BesoinReseau> besoins,
                                       ArrayList<ResultatVLSM> resultats, ArrayList<VLAN> vlans,
                                       ArrayList<Recommandation> recos) throws IOException {
        String nomFichier = nomProjet.replace(" ", "_");
        String dossier = "exports/";
        
        fichierRepository.sauvegarderPlanCSV(resultats, dossier + nomFichier + "_plan.csv");
        fichierRepository.sauvegarderVLANsCSV(vlans, dossier + nomFichier + "_vlans.csv");
        fichierRepository.sauvegarderRecommandations(recos, dossier + nomFichier + "_recommandations.txt");
        rapportService.genererRapportComplet(nomProjet, besoins, resultats, vlans, recos,
            dossier + nomFichier + "_rapport.txt");
        
        System.out.println("\nFichiers sauvegardes dans exports/ :");
        System.out.println("  - " + nomFichier + "_plan.csv");
        System.out.println("  - " + nomFichier + "_vlans.csv");
        System.out.println("  - " + nomFichier + "_recommandations.txt");
        System.out.println("  - " + nomFichier + "_rapport.txt");
    }
}