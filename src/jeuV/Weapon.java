package jeuV;

public abstract class Weapon {

    protected double damage;
    protected int price; 

    public Weapon(double initialDamage, int price) { 
        this.damage = initialDamage;
        this.price = price; 
    }

    public double getDamage() {
        return this.damage;
    }
    
    public int getPrice() {
        return this.price;
    }

    public abstract String asciiArt();
    
    public abstract void attack(Destructible target);
}