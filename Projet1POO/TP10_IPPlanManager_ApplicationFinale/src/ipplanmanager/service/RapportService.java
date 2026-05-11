package ipplanmanager.service;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RapportService {
    
    public void genererRapportComplet(String nomProjet, ArrayList<BesoinReseau> besoins,
                                       ArrayList<ResultatVLSM> resultats, ArrayList<VLAN> vlans,
                                       ArrayList<Recommandation> recommandations,
                                       String cheminFichier) throws IOException {
        FileWriter w = new FileWriter(cheminFichier);
        
        w.write("======== RAPPORT TECHNIQUE IPPLAN-MANAGER ========\n");
        w.write("Projet : " + nomProjet + "\n\n");
        
        w.write("1. Besoins exprimes\n");
        for (int i = 0; i < besoins.size(); i++) {
            w.write("- " + besoins.get(i).getNom() + " : " + besoins.get(i).getNombreHotes() + " hôtes\n");
        }
        
        w.write("\n2. Plan d'adressage propose\n");
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r = resultats.get(i);
            w.write("- " + r.getNomBesoin() + " : " + r.getAdresseReseau() + "/" + r.getCidr()
                + " | Demandes : " + r.getHotesDemandes() + " | Capacite : " + r.getCapacite()
                + " | Marge : " + r.getMarge() + "\n");
        }
        
        w.write("\n3. VLANs proposes\n");
        for (int i = 0; i < vlans.size(); i++) {
            VLAN v = vlans.get(i);
            w.write("- VLAN " + v.getId() + " | " + v.getNom());
            if (v.getReseauAssocie() != null) {
                w.write(" | " + v.getReseauAssocie().getAdresseReseau() + "/" + v.getReseauAssocie().getCidr());
            }
            w.write("\n");
        }
        
        w.write("\n4. Recommandations\n");
        if (recommandations.isEmpty()) {
            w.write("Aucune.\n");
        } else {
            for (int i = 0; i < recommandations.size(); i++) {
                w.write("- [" + recommandations.get(i).getPriorite() + "] " + recommandations.get(i).getTitre() + "\n");
            }
        }
        
        w.close();
    }
}