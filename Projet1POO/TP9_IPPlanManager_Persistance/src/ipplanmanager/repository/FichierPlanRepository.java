package ipplanmanager.repository;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FichierPlanRepository {
    
    public void sauvegarderPlanCSV(ArrayList<ResultatVLSM> resultats, String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("Nom;AdresseReseau;CIDR;Capacite\n");
        
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r = resultats.get(i);
            writer.write(r.getNomBesoin() + ";"
                + r.getAdresseReseau() + ";"
                + r.getCidr() + ";"
                + r.getCapacite() + "\n");
        }
        
        writer.close();
    }
    
    public void sauvegarderVLANsCSV(ArrayList<VLAN> vlans, String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("ID;Nom;AdresseReseau;CIDR;Capacite\n");
        
        for (int i = 0; i < vlans.size(); i++) {
            VLAN v = vlans.get(i);
            if (v.getReseauAssocie() != null) {
                writer.write(v.getId() + ";"
                    + v.getNom() + ";"
                    + v.getReseauAssocie().getAdresseReseau() + ";"
                    + v.getReseauAssocie().getCidr() + ";"
                    + v.getReseauAssocie().getCapacite() + "\n");
            }
        }
        
        writer.close();
    }
    
    public void sauvegarderRecommandations(ArrayList<Recommandation> recommandations, String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("===== RECOMMANDATIONS IPPLAN-MANAGER =====\n\n");
        
        if (recommandations.isEmpty()) {
            writer.write("Aucune recommandation particuliere.\n");
        } else {
            for (int i = 0; i < recommandations.size(); i++) {
                Recommandation r = recommandations.get(i);
                writer.write("[ " + r.getPriorite() + " ] " + r.getTitre() + " : " + r.getMessage() + "\n");
            }
        }
        
        writer.close();
    }
}