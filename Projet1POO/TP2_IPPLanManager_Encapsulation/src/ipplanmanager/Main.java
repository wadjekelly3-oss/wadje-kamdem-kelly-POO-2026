package ipplanmanager;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== TP2 : Encapsulation =====\n");

        // ========== 1. Création d'adresses IP (valides et invalides) ==========
        System.out.println("--- Création des adresses IP ---");
        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        AdresseIP ip2 = new AdresseIP("");
        AdresseIP ip3 = new AdresseIP(null);
        AdresseIP ip4 = new AdresseIP("10.0.0.1");
        AdresseIP ip5 = new AdresseIP("172.16.0.254");
        ip1.afficher();
        ip2.afficher();
        ip3.afficher();
        ip4.afficher();
        ip5.afficher();

        // ========== 2. Création d'interfaces ==========
        System.out.println("\n--- Création des interfaces ---");
        InterfaceReseau interface1 = new InterfaceReseau("eth0", ip1);
        InterfaceReseau interface2 = new InterfaceReseau("", ip2);
        InterfaceReseau interface3 = new InterfaceReseau("wlan0", ip4);
        InterfaceReseau interface4 = new InterfaceReseau("eth1", ip5);

        interface1.activer();
        interface3.activer();

        interface1.afficher();
        System.out.println("---");
        interface2.afficher();
        System.out.println("---");
        interface3.afficher();
        System.out.println("---");
        interface4.afficher();

        // ========== 3. Création de plusieurs équipements ==========
        System.out.println("\n--- Création des équipements ---");

        // Équipements valides
        Equipement routeur = new Equipement("R1-EDGE", "Router", interface1);
        Equipement switch1 = new Equipement("SW-CORE", "Switch", interface3);
        Equipement firewall = new Equipement("FW-MAIN", "Firewall", interface4);

        // Équipements avec cas invalides
        Equipement serveur = new Equipement("", "Server", interface2);
        Equipement pcInconnu = new Equipement(null, "", null);
        Equipement apWifi = new Equipement("AP-Lobby", "", interface4);

        // Affichage de tous les équipements
        System.out.println("--- Équipements valides ---");
        routeur.afficher();
        System.out.println("-------------------");
        switch1.afficher();
        System.out.println("-------------------");
        firewall.afficher();

        System.out.println("\n--- Équipements avec erreurs ---");
        serveur.afficher();
        System.out.println("-------------------");
        pcInconnu.afficher();
        System.out.println("-------------------");
        apWifi.afficher();

        // ========== 4. Modification avec les setters ==========
        System.out.println("\n--- Modification avec les setters ---");

        // Modifier une adresse IP
        System.out.println("Changement d'IP de ip1 : 192.168.1.1 → 192.168.100.1");
        ip1.setValeur("192.168.100.1");
        ip1.afficher();

        // Tenter une modification invalide
        System.out.println("Tentative de mettre une IP vide sur ip1 :");
        ip1.setValeur("");
        ip1.afficher();

        // Changer le nom d'un équipement
        System.out.println("\nCorrection du nom du serveur :");
        serveur.setNom("SRV-WEB-01");
        serveur.afficher();

        // Changer le type
        System.out.println("\nCorrection du type de l'AP :");
        apWifi.setType("Point d'accès WiFi");
        apWifi.afficher();

        // Activer/Désactiver une interface
        System.out.println("\nActivation de l'interface eth1 :");
        interface4.activer();
        interface4.afficher();

        System.out.println("\nDésactivation de l'interface wlan0 :");
        interface3.desactiver();
        interface3.afficher();

        // Changer l'interface d'un équipement
        System.out.println("\nChangement d'interface du switch :");
        switch1.setInterfacePrincipale(interface1);
        switch1.afficher();

        // ========== 5. Création de réseaux (test ReseauIP) ==========
        System.out.println("\n--- Test des réseaux IP ---");
        ReseauIP reseau1 = new ReseauIP("192.168.1.0", 24, "Réseau local");
        ReseauIP reseau2 = new ReseauIP("10.0.0.0", 8, "Réseau interne");
        ReseauIP reseau3 = new ReseauIP("", -5, "");
        ReseauIP reseau4 = new ReseauIP("172.16.0.0", 45, "Réseau DMZ");

        reseau1.afficher();
        reseau2.afficher();
        reseau3.afficher();
        reseau4.afficher();
        // ========== 6. Test de la méthode estAdresseLocale() ==========
        System.out.println("\n--- Test estAdresseLocale() ---");
        AdresseIP ipLocale = new AdresseIP("192.168.1.1");
        AdresseIP ipPublique = new AdresseIP("8.8.8.8");
        System.out.println("192.168.1.1 est locale ? " + ipLocale.estAdresseLocale());
        System.out.println("8.8.8.8 est locale ? " + ipPublique.estAdresseLocale());

        
        System.out.println("\n===== Fin des tests =====");
    }
}