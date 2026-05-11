package ipplanmanager.service;

import ipplanmanager.exception.AdresseIPInvalideException;

public class CalculateurReseau {
    
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        int bits = 32 - cidr;
        if (bits == 0) return 1;
        return (int) Math.pow(2, bits) - 2;
    }
    
    public static int calculerCidrPourHotes(int nb) {
        for (int c = 32; c >= 0; c--) {
            if (calculerNombreHotes(c) >= nb) return c;
        }
        return -1;
    }
    
    public static String obtenirMasqueDecimal(int cidr) {
        int m = 0xffffffff << (32 - cidr);
        int o1 = (m >>> 24) & 255;
        int o2 = (m >>> 16) & 255;
        int o3 = (m >>> 8) & 255;
        int o4 = m & 255;
        return o1 + "." + o2 + "." + o3 + "." + o4;
    }
    
    public static int convertirIPEnEntier(String ip) {
        String[] p = ip.split("\\.");
        int r = 0;
        for (int i = 0; i < 4; i++) {
            r = r * 256 + Integer.parseInt(p[i]);
        }
        return r;
    }
    
    public static String convertirEntierEnIp(int v) {
        return ((v >>> 24) & 255) + "." + ((v >>> 16) & 255) + "." + ((v >>> 8) & 255) + "." + (v & 255);
    }
    
    public static int calculerTailleBloc(int c) {
        return (int) Math.pow(2, 32 - c);
    }
    
    public static boolean estAdresseIPValide(String ip) {
        if (ip == null || ip.isEmpty()) return false;
        String[] p = ip.split("\\.");
        if (p.length != 4) return false;
        for (String s : p) {
            try {
                int v = Integer.parseInt(s);
                if (v < 0 || v > 255) return false;
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
    
    public static int calculerAdresseFin(String a, int c) {
        return convertirIPEnEntier(a) + calculerTailleBloc(c) - 1;
    }
    
    public static boolean reseauxSeChevauchent(String a1, int c1, String a2, int c2) {
        int d1 = convertirIPEnEntier(a1);
        int f1 = calculerAdresseFin(a1, c1);
        int d2 = convertirIPEnEntier(a2);
        int f2 = calculerAdresseFin(a2, c2);
        return d1 <= f2 && d2 <= f1;
    }
}