package ipplanmanager;

public class CalculateurReseau {
    
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        int bitsHotes = 32 - cidr;
        if (bitsHotes == 0) return 1;
        return (int) Math.pow(2, bitsHotes) - 2;
    }
    
    public static int calculerCidrPourHotes(int nombreHotes) {
        for (int cidr = 32; cidr >= 0; cidr--) {
            if (calculerNombreHotes(cidr) >= nombreHotes) return cidr;
        }
        return -1;
    }
    
    public static String obtenirMasqueDecimal(int cidr) {
        int masque = 0xffffffff << (32 - cidr);
        int o1 = (masque >>> 24) & 255;
        int o2 = (masque >>> 16) & 255;
        int o3 = (masque >>> 8) & 255;
        int o4 = masque & 255;
        return o1 + "." + o2 + "." + o3 + "." + o4;
    }
    
    public static int convertirIPEnEntier(String ip) {
        String[] parts = ip.split("\\.");
        int resultat = 0;
        for (int i = 0; i < 4; i++) {
            resultat = resultat * 256 + Integer.parseInt(parts[i]);
        }
        return resultat;
    }
    
    public static String convertirEntierEnIp(int valeur) {
        int o1 = (valeur >>> 24) & 255;
        int o2 = (valeur >>> 16) & 255;
        int o3 = (valeur >>> 8) & 255;
        int o4 = valeur & 255;
        return o1 + "." + o2 + "." + o3 + "." + o4;
    }
    
    public static int calculerTailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }
    
    public static String calculerPremiereAdresseUtilisable(String adresseReseau) {
        return convertirEntierEnIp(convertirIPEnEntier(adresseReseau) + 1);
    }
    
    public static String calculerDerniereAdresseUtilisable(String adresseReseau, int cidr) {
        int ip = convertirIPEnEntier(adresseReseau);
        return convertirEntierEnIp(ip + calculerTailleBloc(cidr) - 2);
    }
    
    // ========== SECTION 10 : NOUVELLES MÉTHODES ==========
    
    public static boolean estAdresseIPValide(String ip) {
        if (ip == null || ip.isEmpty()) return false;
        String[] parties = ip.split("\\.");
        if (parties.length != 4) return false;
        for (String partie : parties) {
            try {
                int valeur = Integer.parseInt(partie);
                if (valeur < 0 || valeur > 255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    
    public static void verifierAdresseIP(String ip) throws AdresseIPInvalideException {
        if (!estAdresseIPValide(ip)) {
            throw new AdresseIPInvalideException("Adresse IP invalide : " + ip);
        }
    }
    
    public static int calculerAdresseFin(String adresseReseau, int cidr) {
        int debut = convertirIPEnEntier(adresseReseau);
        int tailleBloc = calculerTailleBloc(cidr);
        return debut + tailleBloc - 1;
    }
    
    public static boolean reseauxSeChevauchent(String adresse1, int cidr1,
                                                String adresse2, int cidr2) {
        int debut1 = convertirIPEnEntier(adresse1);
        int fin1 = calculerAdresseFin(adresse1, cidr1);
        int debut2 = convertirIPEnEntier(adresse2);
        int fin2 = calculerAdresseFin(adresse2, cidr2);
        return debut1 <= fin2 && debut2 <= fin1;
    }
}