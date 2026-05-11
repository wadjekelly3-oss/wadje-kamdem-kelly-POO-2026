package ipplanmanager.repository;

import ipplanmanager.model.BesoinReseau;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BesoinRepository {
    
    public ArrayList<BesoinReseau> chargerBesoins(String cheminFichier) throws IOException {
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
        String ligne = reader.readLine();
        while ((ligne = reader.readLine()) != null) {
            String[] cols = ligne.split(";");
            if (cols.length == 2) {
                besoins.add(new BesoinReseau(cols[0], Integer.parseInt(cols[1])));
            }
        }
        reader.close();
        return besoins;
    }
}