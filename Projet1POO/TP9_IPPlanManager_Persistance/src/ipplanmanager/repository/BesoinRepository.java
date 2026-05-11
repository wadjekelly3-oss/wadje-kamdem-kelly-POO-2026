package ipplanmanager.repository;

import ipplanmanager.model.BesoinReseau;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BesoinRepository {
    
    public ArrayList<BesoinReseau> chargerBesoins(String cheminFichier) throws IOException {
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        
        BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
        
        String ligne = reader.readLine();
        
        while ((ligne = reader.readLine()) != null) {
            String[] colonnes = ligne.split(";");
            if (colonnes.length == 2) {
                String nom = colonnes[0];
                int hotes = Integer.parseInt(colonnes[1]);
                besoins.add(new BesoinReseau(nom, hotes));
            }
        }
        
        reader.close();
        return besoins;
    }
}