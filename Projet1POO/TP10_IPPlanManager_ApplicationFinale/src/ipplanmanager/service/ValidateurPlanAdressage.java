package ipplanmanager.service;

import ipplanmanager.exception.AdresseIPInvalideException;
import ipplanmanager.exception.ChevauchementReseauException;
import ipplanmanager.model.ResultatVLSM;
import java.util.ArrayList;

public class ValidateurPlanAdressage {
    
    public void verifierChevauchements(ArrayList<ResultatVLSM> r) throws ChevauchementReseauException {
        for (int i = 0; i < r.size(); i++) {
            for (int j = i + 1; j < r.size(); j++) {
                if (CalculateurReseau.reseauxSeChevauchent(
                    r.get(i).getAdresseReseau(), r.get(i).getCidr(),
                    r.get(j).getAdresseReseau(), r.get(j).getCidr())) {
                    throw new ChevauchementReseauException("Chevauchement entre "
                        + r.get(i).getNomBesoin() + " et " + r.get(j).getNomBesoin());
                }
            }
        }
    }
    
    public void verifierAdresses(ArrayList<ResultatVLSM> r) throws AdresseIPInvalideException {
        for (int i = 0; i < r.size(); i++) {
            CalculateurReseau.verifierAdresseIP(r.get(i).getAdresseReseau());
        }
    }
    
    public void afficherValidationReussie() {
        System.out.println("Validation terminee : aucun conflit critique detecte.");
    }
}