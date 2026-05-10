package ipplanmanager;

public class Main {
    public static void main(String[] args) {
        
        InfrastructureReseau infrastructure = new InfrastructureReseau("Infrastructure YFY");
        
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Réseau Administration");
        ReseauIP reseauTech = new ReseauIP("172.16.0.0", 16, "Réseau Technique");
        ReseauIP reseauWifi = new ReseauIP("10.0.0.0", 8, "Réseau WiFi");
        ReseauIP reseauDMZ = new ReseauIP("192.168.100.0", 28, "Réseau DMZ");
        ReseauIP reseauLabo = new ReseauIP("10.10.10.0", 27, "Réseau Laboratoire");
        ReseauIP reseauPublic = new ReseauIP("8.8.8.0", 24, "Réseau Public DNS");
        
        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        SousReseau tech = new SousReseau("TECH", reseauTech);
        SousReseau wifi = new SousReseau("WIFI", reseauWifi);
        SousReseau dmz = new SousReseau("DMZ", reseauDMZ);
        SousReseau labo = new SousReseau("LABO", reseauLabo);
        SousReseau pub = new SousReseau("PUBLIC", reseauPublic);
        
        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);
        infrastructure.ajouterSousReseau(dmz);
        infrastructure.ajouterSousReseau(labo);
        infrastructure.ajouterSousReseau(pub);
        
        infrastructure.afficher();
        
        System.out.println();
        System.out.println("===== Tests de calculs réseau =====");
        System.out.println();
        System.out.println("Nombre d'hôtes pour /24 : " + CalculateurReseau.calculerNombreHotes(24));
        System.out.println("Nombre d'hôtes pour /16 : " + CalculateurReseau.calculerNombreHotes(16));
        System.out.println("Nombre d'hôtes pour /8 : " + CalculateurReseau.calculerNombreHotes(8));
        System.out.println("Nombre d'hôtes pour /28 : " + CalculateurReseau.calculerNombreHotes(28));
        System.out.println("Nombre d'hôtes pour /27 : " + CalculateurReseau.calculerNombreHotes(27));
        System.out.println();
        System.out.println("Classe de 192.168.1.0 : " + CalculateurReseau.obtenirClasseReseau("192.168.1.0"));
        System.out.println("Classe de 172.16.0.0 : " + CalculateurReseau.obtenirClasseReseau("172.16.0.0"));
        System.out.println("Classe de 10.0.0.0 : " + CalculateurReseau.obtenirClasseReseau("10.0.0.0"));
        System.out.println("Classe de 8.8.8.0 : " + CalculateurReseau.obtenirClasseReseau("8.8.8.0"));
        System.out.println();
        System.out.println("192.168.1.0 est privé ? " + CalculateurReseau.estReseauPrive("192.168.1.0"));
        System.out.println("172.16.0.0 est privé ? " + CalculateurReseau.estReseauPrive("172.16.0.0"));
        System.out.println("10.0.0.0 est privé ? " + CalculateurReseau.estReseauPrive("10.0.0.0"));
        System.out.println("8.8.8.0 est privé ? " + CalculateurReseau.estReseauPrive("8.8.8.0"));
    }
}