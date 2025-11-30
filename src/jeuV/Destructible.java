package jeuV;

public abstract class Destructible {

    protected int ptsDeVie = 30;

    public int getPtsDeVie() {
        return ptsDeVie;
    }

    public void setPtsDeVie(int ptsDeVie) {
        this.ptsDeVie = ptsDeVie;
    }
    
    public void recevoirDegats(int degats) {
        this.ptsDeVie = this.ptsDeVie - degats;
        System.out.println("Le monstre perds " + degats + " point de vies. Il lui reste " + this.ptsDeVie + " points de vies.");
    }
}
