package jeuV;

public class Bow extends Weapon {

    private static final double BOW_DAMAGE = 12.0;
    private static final int BOW_PRICE = 15;

    public Bow() {
        super(BOW_DAMAGE, BOW_PRICE);
    }

    @Override
    public String asciiArt() {
        return ">>>>>>>_____________________\\`-._\r\n"
        		+ ">>>>>>>                     /.-'";
    }

    @Override
    public void attack(Destructible target) {
        System.out.println("La fleche frappe une cible destructible !");
        target.recevoirDegats((int) this.damage);
    }
}