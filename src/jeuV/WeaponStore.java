package jeuV;


import java.util.ArrayList;

public class WeaponStore {

    private ArrayList<Weapon> stock = new ArrayList<>();

    public WeaponStore() {
        stock.add(new Axe());
        stock.add(new Hammer());
        stock.add(new Axe());
        
        //System.out.println("Magasin créé");
    }

    public void displayWeapons() {
        System.out.println("--- Bienvenue dans notre magasin ! ---");
        
        for (int i = 0; i < this.stock.size(); i++) {
            Weapon w = this.stock.get(i);
            System.out.println("--- Article #" + (i + 1) + " ---");
            System.out.println(w.asciiArt());
            System.out.println("Dégâts : " + w.getDamage());
            System.out.println("Prix : " + w.getPrice() + " pièces"); 
        }
    }
    
    
    public Weapon getWeaponFromStock(int index) {
        if (index >= 0 && index < this.stock.size()) {
            Weapon w = this.stock.get(index);
            if (w instanceof Axe) {
                return new Axe(); 
            } 
            else
            	
            	if (w instanceof Hammer) {
                return new Hammer(); 
            	}
            	else {
            		return new Bow(); 

            	}
            	
        }
        return null;    }
}