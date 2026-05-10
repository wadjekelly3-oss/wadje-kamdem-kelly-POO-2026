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
        int taille = calculerTailleBloc(cidr);
        return convertirEntierEnIp(ip + taille - 2);
    }
    
    public static String obtenirClasseReseau(String adresseIP) {
        int premier = Integer.parseInt(adresseIP.split("\\.")[0]);
        if (premier >= 1 && premier <= 126) return "Classe A";
        if (premier >= 128 && premier <= 191) return "Classe B";
        if (premier >= 192 && premier <= 223) return "Classe C";
        return "Classe inconnue";
    }
    
    public static boolean estReseauPrive(String adresseIP) {
        String[] p = adresseIP.split("\\.");
        int o1 = Integer.parseInt(p[0]);
        int o2 = Integer.parseInt(p[1]);
        if (o1 == 10) return true;
        if (o1 == 172 && o2 >= 16 && o2 <= 31) return true;
        if (o1 == 192 && o2 == 168) return true;
        return false;
    }
}