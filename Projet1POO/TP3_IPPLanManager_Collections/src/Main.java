package ipplanmanager;

public class Main {
    public static void main(String[] args) {
        
        // Création de l'infrastructure
        InfrastructureReseau infrastructure = new InfrastructureReseau("Infrastructure YFY");
        
        // Création des réseaux
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Réseau administration");
        ReseauIP reseauTech = new ReseauIP("192.168.2.0", 24, "Réseau technique");
        ReseauIP reseauWifi = new ReseauIP("192.168.3.0", 24, "Réseau WiFi invités");
        
        // Création des sous-réseaux
        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        SousReseau tech = new SousReseau("TECH", reseauTech);
        SousReseau wifi = new SousReseau("WIFI", reseauWifi);
        
        // Ajout des sous-réseaux à l'infrastructure
        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);
        
        // Création des adresses IP
        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.1");
        AdresseIP ip3 = new AdresseIP("192.168.1.10");
        AdresseIP ip4 = new AdresseIP("192.168.2.1");
        AdresseIP ip5 = new AdresseIP("192.168.2.10");
        AdresseIP ip6 = new AdresseIP("192.168.3.1");
        AdresseIP ip7 = new AdresseIP("192.168.3.10");
        AdresseIP ip8 = new AdresseIP("192.168.1.100");
        AdresseIP ip9 = new AdresseIP("192.168.2.100");
        AdresseIP ip10 = new AdresseIP("192.168.3.100");
        
        // Création des interfaces
        InterfaceReseau eth0 = new InterfaceReseau("eth0", ip1);
        InterfaceReseau eth1 = new InterfaceReseau("eth1", ip2);
        InterfaceReseau eth2 = new InterfaceReseau("eth2", ip3);
        InterfaceReseau g0_0 = new InterfaceReseau("GigabitEthernet0/0", ip4);
        InterfaceReseau g0_1 = new InterfaceReseau("GigabitEthernet0/1", ip5);
        InterfaceReseau eth0_serv = new InterfaceReseau("eth0", ip6);
        InterfaceReseau eth1_serv = new InterfaceReseau("eth1", ip7);
        InterfaceReseau wlan0 = new InterfaceReseau("wlan0", ip8);
        InterfaceReseau eth0_client1 = new InterfaceReseau("eth0", ip9);
        InterfaceReseau eth0_client2 = new InterfaceReseau("eth0", ip10);
        
        // Activation des interfaces
        eth0.activer();
        eth1.activer();
        eth2.activer();
        g0_0.activer();
        g0_1.activer();
        eth0_serv.activer();
        eth1_serv.activer();
        wlan0.activer();
        eth0_client1.activer();
        // eth0_client2 reste inactive (pas de .activer())
        
        // Création des équipements
        Equipement routeur = new Equipement("R1_EDGE", "Routeur");
        routeur.ajouterInterface(eth0);
        routeur.ajouterInterface(eth1);
        routeur.ajouterInterface(eth2);
        infrastructure.ajouterEquipement(routeur);
        
        Equipement switchEquip = new Equipement("SW_CORE", "Switch");
        switchEquip.ajouterInterface(g0_0);
        switchEquip.ajouterInterface(g0_1);
        infrastructure.ajouterEquipement(switchEquip);
        
        Equipement serveur = new Equipement("SRV_WEB", "Serveur");
        serveur.ajouterInterface(eth0_serv);
        serveur.ajouterInterface(eth1_serv);
        infrastructure.ajouterEquipement(serveur);
        
        Equipement pointAcces = new Equipement("AP_HALL", "Point d'accès WiFi");
        pointAcces.ajouterInterface(wlan0);
        infrastructure.ajouterEquipement(pointAcces);
        
        Equipement client1 = new Equipement("PC_COMPTA", "Poste client");
        client1.ajouterInterface(eth0_client1);
        infrastructure.ajouterEquipement(client1);
        
        Equipement client2 = new Equipement("PC_STAGIAIRE", "Poste client");
        client2.ajouterInterface(eth0_client2);
        infrastructure.ajouterEquipement(client2);
        
        // Affichage complet de l'infrastructure
        infrastructure.afficher();
        
        // Test de recherche d'équipement
        System.out.println();
        System.out.println("===== Test de recherche =====");
        System.out.println();
        System.out.println("Recherche de 'R1_EDGE' :");
        infrastructure.rechercherEquipement("R1_EDGE");
        System.out.println();
        System.out.println("Recherche de 'ROUTEUR_INCONNU' :");
        infrastructure.rechercherEquipement("ROUTEUR_INCONNU");
    }
}