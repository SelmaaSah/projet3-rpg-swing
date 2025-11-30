package jeuV;

public class Axe extends Weapon {

	 private static final double AXE_DAMAGE = 10.0;
	   static final double MONSTER_DAMAGE_RATIO = 0.8;
	  static final double OBSTACLE_DAMAGE_RATIO = 1.2;
	  private static final int AXE_PRICE = 20; 

	public String asciiArt() {
		
		return 
				" _,-,\r\n"
				+ "T_  |\r\n"
				+ "||`-'\r\n"
				+ "||\r\n"
				+ "||\r\n"
				+ "~~";
	}
	



    
    public Axe() {
        super(AXE_DAMAGE, AXE_PRICE); 
    }


    public void attack(Monster m) {
        System.out.println("La Hache frappe un Monstre ");
        m.recevoirDegats((int) (this.damage * MONSTER_DAMAGE_RATIO));
    }

    public void attack(Obstacle o) {
        System.out.println("La Hache s'abat sur un Obstacle !");
        o.recevoirDegats((int) (this.damage* OBSTACLE_DAMAGE_RATIO));
    }

    @Override
    public void attack(Destructible target) {
        System.out.println("La Hache frappe une cible Destructible !");
        target.recevoirDegats((int) this.damage);
    }
}