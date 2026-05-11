package ipplanmanager.console;

import ipplanmanager.model.BesoinReseau;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleService {
    private Scanner scanner;
    
    public ConsoleService() {
        scanner = new Scanner(System.in);
    }
    
    public String saisirTexte(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    
    public int saisirEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valeur invalide, entrez un nombre entier.");
            }
        }
    }
    
    public ArrayList<BesoinReseau> saisirBesoins() {
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        int nb = saisirEntier("Nombre de besoins reseau a saisir : ");
        for (int i = 0; i < nb; i++) {
            System.out.println("\nSaisie du besoin " + (i + 1));
            String nom = saisirTexte("Nom du service ou departement : ");
            int hotes = saisirEntier("Nombre d'hotes demandes : ");
            besoins.add(new BesoinReseau(nom, hotes));
        }
        return besoins;
    }
    
    public void afficherMenu() {
        System.out.println("\n===== MENU IPPLAN-MANAGER =====");
        System.out.println("1. Saisir les besoins et generer un plan complet");
        System.out.println("2. Charger les besoins depuis un fichier CSV");
        System.out.println("3. Quitter");
        System.out.print("Choix : ");
    }
    
    public int lireChoix() {
        return saisirEntier("");
    }
}