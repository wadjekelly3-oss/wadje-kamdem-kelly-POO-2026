package ipplanmanager;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP1 =====");
        System.out.println("Découverte des premières classes du projet");
        System.out.println();
        
        // Création des adresses IP
        AdresseIP ipRouteur = new AdresseIP("192.168.1.1");
        AdresseIP ipServeur = new AdresseIP("192.168.1.10");
        AdresseIP ipClient = new AdresseIP("192.168.1.50");
        AdresseIP ipSwitch = new AdresseIP("192.168.1.254");
        AdresseIP ipPointAcces = new AdresseIP("192.168.2.1");
        AdresseIP ipClient2 = new AdresseIP("192.168.2.100");
        
        // Création des interfaces réseau
        InterfaceReseau interfaceRouteur = new InterfaceReseau("eth0", ipRouteur);
        InterfaceReseau interfaceServeur = new InterfaceReseau("eth0", ipServeur);
        InterfaceReseau interfaceClient = new InterfaceReseau("wlan0", ipClient);
        InterfaceReseau interfaceSwitch = new InterfaceReseau("GigabitEthernet0/1", ipSwitch);
        InterfaceReseau interfacePointAcces = new InterfaceReseau("wlan0", ipPointAcces);
        InterfaceReseau interfaceClient2 = new InterfaceReseau("eth0", ipClient2);
        
        // Interface inactive (supplémentaire)
        InterfaceReseau interfaceInactive = new InterfaceReseau("eth1", new AdresseIP("192.168.1.100"));
        // L'interface reste inactive (pas d'appel à activer())
        
        // Interface sans adresse IP (supplémentaire)
        InterfaceReseau interfaceSansIP = new InterfaceReseau("eth1", null);
        
        // Activation des interfaces
        interfaceRouteur.activer();
        interfaceServeur.activer();
        interfaceSwitch.activer();
        interfacePointAcces.activer();
        interfaceClient2.activer();
        
        // Création des équipements
        Equipement routeur = new Equipement("R1_EDGE", "Routeur", interfaceRouteur);
        Equipement serveur = new Equipement("SRV_DNS", "Serveur", interfaceServeur);
        Equipement client = new Equipement("PC_ADMIN", "Poste client", interfaceClient);
        Equipement switchEquipement = new Equipement("SW_CORE", "Switch", interfaceSwitch);
        Equipement pointAccesWifi = new Equipement("AP_HALL", "Point d'accès WiFi", interfacePointAcces);
        Equipement client2 = new Equipement("PC_COMPTA", "Poste client", interfaceClient2);
        
        // Création des réseaux
        ReseauIP reseauPrincipal = new ReseauIP("192.168.1.0", 24, "Réseau principal du laboratoire IRT");
        ReseauIP reseauSecondaire = new ReseauIP("192.168.2.0", 24, "Réseau WiFi invités");
        
        // Affichage des réseaux
        System.out.println("--- Réseaux créés ---");
        System.out.println();
        reseauPrincipal.afficher();
        System.out.println();
        reseauSecondaire.afficher();
        System.out.println();
        
        // Affichage des équipements
        System.out.println("--- Équipements créés ---");
        System.out.println();
        routeur.afficher();
        System.out.println();
        serveur.afficher();
        System.out.println();
        client.afficher();
        System.out.println();
        switchEquipement.afficher();
        System.out.println();
        pointAccesWifi.afficher();
        System.out.println();
        client2.afficher();
        System.out.println();
        
        // Affichage des interfaces supplémentaires
        System.out.println("--- Interfaces supplémentaires ---");
        System.out.println();
        System.out.println("Interface inactive :");
        interfaceInactive.afficher();
        System.out.println();
        System.out.println("Interface sans adresse IP :");
        interfaceSansIP.afficher();
    }
}