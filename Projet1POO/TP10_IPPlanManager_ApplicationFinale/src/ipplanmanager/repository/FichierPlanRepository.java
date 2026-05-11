package ipplanmanager.repository;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FichierPlanRepository {
    
    public void sauvegarderPlanCSV(ArrayList<ResultatVLSM> resultats, String chemin) throws IOException {
        FileWriter w = new FileWriter(chemin);
        w.write("Nom;AdresseReseau;CIDR;Masque;HotesDemandes;Capacite;Marge\n");
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r = resultats.get(i);
            w.write(r.getNomBesoin() + ";" + r.getAdresseReseau() + ";" + r.getCidr() + ";"
                + r.getMasqueDecimal() + ";" + r.getHotesDemandes() + ";" + r.getCapacite() + ";"
                + r.getMarge() + "\n");
        }
        w.close();
    }
    
    public void sauvegarderVLANsCSV(ArrayList<VLAN> vlans, String chemin) throws IOException {
        FileWriter w = new FileWriter(chemin);
        w.write("ID;Nom;AdresseReseau;CIDR;Capacite\n");
        for (int i = 0; i < vlans.size(); i++) {
            VLAN v = vlans.get(i);
            if (v.getReseauAssocie() != null) {
                w.write(v.getId() + ";" + v.getNom() + ";" + v.getReseauAssocie().getAdresseReseau()
                    + ";" + v.getReseauAssocie().getCidr() + ";" + v.getReseauAssocie().getCapacite() + "\n");
            }
        }
        w.close();
    }
    
    public void sauvegarderRecommandations(ArrayList<Recommandation> recos, String chemin) throws IOException {
        FileWriter w = new FileWriter(chemin);
        w.write("===== RECOMMANDATIONS IPPLAN-MANAGER =====\n\n");
        if (recos.isEmpty()) {
            w.write("Aucune.\n");
        } else {
            for (int i = 0; i < recos.size(); i++) {
                w.write("[" + recos.get(i).getPriorite() + "] " + recos.get(i).getTitre()
                    + " : " + recos.get(i).getMessage() + "\n");
            }
        }
        w.close();
    }
}