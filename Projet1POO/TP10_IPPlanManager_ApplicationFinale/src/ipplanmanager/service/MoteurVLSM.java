package ipplanmanager.service;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.ResultatVLSM;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MoteurVLSM {
    
    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart, ArrayList<BesoinReseau> besoins) {
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();
        
        Collections.sort(besoins, new Comparator<BesoinReseau>() {
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });
        
        int adrCourante = CalculateurReseau.convertirIPEnEntier(adresseDepart);
        
        for (int i = 0; i < besoins.size(); i++) {
            BesoinReseau b = besoins.get(i);
            int cidr = CalculateurReseau.calculerCidrPourHotes(b.getNombreHotes());
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            String adrReseau = CalculateurReseau.convertirEntierEnIp(adrCourante);
            
            ResultatVLSM r = new ResultatVLSM(b.getNom(), adrReseau, cidr, masque, capacite, b.getNombreHotes());
            resultats.add(r);
            adrCourante = adrCourante + CalculateurReseau.calculerTailleBloc(cidr);
        }
        return resultats;
    }
}