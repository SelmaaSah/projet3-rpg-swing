package jeuV;
import java.util.ArrayList;


public class Player {

    private String name;
    private int silver;
    private ArrayList<Weapon> inventory;

    public Player(String name) {
        this.name = name;
        this.silver = 50;
        this.inventory = new ArrayList<>();
        System.out.println("Personnage  '" + this.name + "' créé et entre dans le donjon !");
    }

    public void showInventory() {
        System.out.println("--- INVENTAIRE DE " + this.name + " ---");
        System.out.println("Argent : " + this.silver + " pièces"); 
        
        if (this.inventory.isEmpty()) {
            System.out.println("L'inventaire est vide");
        } 
        else {
            System.out.println("Armes :");
            for (int i = 0; i < this.inventory.size(); i++) {
                System.out.println("--- Arme #" + (i + 1) + " ---");
                Weapon w = this.inventory.get(i);
                System.out.println(w.asciiArt());
                System.out.println("Dégâts : " + w.getDamage());
                System.out.println("Valeur : " + w.getPrice() + " pièces");
            }
        }
    }
    
    
    public Weapon getWeapon(int index) {
        if (index >= 0 && index < this.inventory.size()) {
            return this.inventory.get(index);
        }
        return null;
    }
    
    public int getInventorySize() {
        return this.inventory.size();
    }
    
    public int getSilver() {
        return this.silver;
    }
    public void addWeapon(Weapon 
    		w) {
        this.inventory.add(w);
        System.out.println( this.name + " a obtenu une nouvelle arme !");
    }
    
    
    public void spendSilver(int amount) {
        this.silver = this.silver - amount;
    }
    
    public String getName() {
        return this.name;
    }
    

    public void addSilver(int amount) {
        this.silver = this.silver + amount;
        System.out.println("Vous avez trouvé " + amount + " pièces !");
    }
    
    public void addSilver3(int amount) {
        //this.silver = this.silver + amount;
        //this.silver + amount;
    }
}