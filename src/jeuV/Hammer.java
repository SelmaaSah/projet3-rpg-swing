package jeuV;

public class Hammer extends Weapon {

    private static final double HAMMER_DAMAGE = 20.0;
    private static final int HAMMER_PRICE = 35;

    public Hammer() {
        super(HAMMER_DAMAGE, HAMMER_PRICE);
    }

    @Override
    public String asciiArt() {
        return " ___ \n"
             + "|_  |\n"
             + "|_ _|\n"
             + "  |  \n"
             + "  |  \n"
             + "  |  \n";
    }

    @Override
    public void attack(Destructible target) {
        System.out.println("Le Marteau frappe une cible destructible !");
        target.recevoirDegats((int) this.damage);
    }
}