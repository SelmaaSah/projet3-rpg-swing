package jeuV;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenue dans le Donjon !");
        
        System.out.println("Quel est votre nom, aventurier ou aventirière ?");
        String playerName = scanner.nextLine();
        
        Player player = new Player(playerName);
        WeaponStore store = new WeaponStore();
        Donjon donjon = new Donjon(5, 4);
        
        
        // player.addWeapon(new Axe());
        
        boolean gameRunning = true;
        
        while (gameRunning) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Voir mon inventaire");
            System.out.println("2. Aller au magasin d'armes");
            System.out.println("3. Afficher la carte");
            System.out.println("4. Explorer le donjon (Se déplacer)");
            System.out.println("5. Quitter le jeu");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    player.showInventory();
                    break;
                case "2":
                    System.out.println("--- Mode Magasin ---");
                    boolean shop = true;
                    
                    while (shop) {
                        System.out.println("Argent : " + player.getSilver() + " pièces");
                        	
                        store.displayWeapons();
                        System.out.println("Que voulez-vous acheter ? (Entrez le numéro, ou 'menu' pour partir)");
                        
                        String buyChoice = scanner.nextLine();
                        
                        if (buyChoice.equals("menu")) {
                            shop = false;
                        } else {
                            try {
                                int itemIndex = Integer.parseInt(buyChoice) - 1; 
                                
                                Weapon item = store.getWeaponFromStock(itemIndex);
                                
                                if (item == null) {
                                    System.out.println("Article invalide.");
                                } else if (player.getSilver() < item.getPrice()) {
                                    System.out.println("Pas assez d'argent !");
                                } else {
                                    player.spendSilver(item.getPrice());
                                    player.addWeapon(item);
                                    System.out.println("Achat réussi !");
                                }
                                
                            } catch (Exception e) {
                                System.out.println("Faut entrer un nombre ou 'menu' !");
                            }
                        }
                    }
                    System.out.println("---- Retour au menu principal ---");
                    break;
                case "3":
                    donjon.displayMap();
                    break;
                case "4": 
                    System.out.println("--- Mode Exploration ---");
                    boolean exploring = true;
                    
                    donjon.displayMap();
                    
                    while (exploring) {
                        System.out.println("Ou aller ? (haut, bas, gauche, droite... ou 'menu' pour revenir)");
                        String direction = scanner.nextLine();
                        
                        if (direction.equals("menu")) {
                            exploring = false;
                        } else {
                            Destructible encounter = donjon.movePlayer(direction);
                            if (encounter != null) {
                                startCombat(player, encounter, donjon, scanner);
                            }
                            if (exploring) {
                                donjon.displayMap();
                            }
                            if (donjon.playerexit()) {
                                System.out.println("\n***********************************");
                                System.out.println("FÉLICITATIONS ! Vous avez trouvé la sortie !");
                                System.out.println("***********************************");
                                
                                exploring = false; 
                                gameRunning = false;
                            }
                        }
                        
                    }
                    System.out.println("---- Retour au menu principal ---");
                    break;
                case "5":
                    System.out.println("Merci d'avoir joué À bientôt !");
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
        
        scanner.close();
    }
    
    
    private static void startCombat(Player player, Destructible enemy, Donjon donjon, Scanner scanner) {
        
        System.out.println("\n--- ! COMBAT ! ---\n");
        
        if (enemy instanceof Monster) {
            System.out.println("Un MONSTRE vous bloque !");
            ((Monster) enemy).grogner();
        } else if (enemy instanceof Obstacle) {
            System.out.println("Un OBSTACLE vous bloque ! ,");
            ((Obstacle) enemy).bloquerPassage();
        }

        boolean combatRunning = true;
        while (combatRunning) {
            
            System.out.println("Que faire ?");
            System.out.println("1. Attaquer");
            System.out.println("2. Fuir");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    player.showInventory();
                    if (player.getInventorySize() == 0) {
                        System.out.println("Vous n'avez pas d'arme ! Fuyez c'est un conseil :D !");
                        break;
                    }
                    
                    System.out.println("Choisir une arme (entrez le numéro):");
                    
                    int weaponChoice = 0;
                    try {
                        weaponChoice = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Faut entrer un nombre !");
                        break;
                    }

                    Weapon chosenWeapon = player.getWeapon(weaponChoice - 1);
                    
                    if (chosenWeapon == null) {
                        System.out.println("Choix d'arme invalide.");
                    } else {
                        chosenWeapon.attack(enemy);
                        
                        if (enemy.getPtsDeVie() <= 0) {
                            System.out.println("Vous avez gagné !");
                            donjon.clearMapTile(enemy);
                            combatRunning = false;
                        }
                    }
                    break;
                
                case "2":
                    System.out.println("Vous fuyez le combat. :o");
                    combatRunning = false;
                    break;
                    
                default:
                    System.out.println("Choix de combat invalide");
                    break;
            }
        }
        
        System.out.println("--- Retour à l'exploration ---");
    }
    
}