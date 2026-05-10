package ipplanmanager;

public class CalculateurReseau {
    
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) {
            return 0;
        }
        int bitsHotes = 32 - cidr;
        return (int) Math.pow(2, bitsHotes) - 2;
    }
    
    public static String obtenirClasseReseau(String adresseIP) {
        String[] parts = adresseIP.split("\\.");
        int premierOctet = Integer.parseInt(parts[0]);
        
        if (premierOctet >= 1 && premierOctet <= 126) {
            return "Classe A";
        }
        if (premierOctet >= 128 && premierOctet <= 191) {
            return "Classe B";
        }
        if (premierOctet >= 192 && premierOctet <= 223) {
            return "Classe C";
        }
        return "Classe inconnue";
    }
    
    public static String obtenirMasqueDecimal(int cidr) {
        switch (cidr) {
            case 8: return "255.0.0.0";
            case 16: return "255.255.0.0";
            case 24: return "255.255.255.0";
            case 25: return "255.255.255.128";
            case 26: return "255.255.255.192";
            case 27: return "255.255.255.224";
            case 28: return "255.255.255.240";
            default: return "Masque non disponible";
        }
    }
    
    public static boolean estReseauPrive(String adresseIP) {
        String[] parts = adresseIP.split("\\.");
        int premierOctet = Integer.parseInt(parts[0]);
        int deuxiemeOctet = Integer.parseInt(parts[1]);
        
        if (premierOctet == 10) {
            return true;
        }
        if (premierOctet == 172 && deuxiemeOctet >= 16 && deuxiemeOctet <= 31) {
            return true;
        }
        if (premierOctet == 192 && deuxiemeOctet == 168) {
            return true;
        }
        return false;
    }
}