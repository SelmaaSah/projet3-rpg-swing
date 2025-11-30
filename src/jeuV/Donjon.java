package jeuV;
import java.util.ArrayList;

public class Donjon {

    private Destructible[][] map; 
    private int playerX;
    private int playerY;
    private int height;
    private int width;
	private int exitY;
	private int exitX;

    public Donjon(int width, int height) {
        this.height = height;
        this.width = width;
        this.map = new Destructible[height][width];
        
        this.playerX = 0;
        this.playerY = 0;
        
        this.exitX = width - 1;
        this.exitY = height - 1;
        
        
        initializeMap();
        
    }

    private void initializeMap() {
        this.map[1][2] = new Monster();
        this.map[3][1] = new Obstacle();
        this.map[2][2] = new Obstacle();

        this.map[2][3] = new Monster();
        
    }

    public void displayMap() {
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[y].length; x++) {
                
                if (y == playerY && x == playerX) {
                    System.out.print("[P]");
                }
                else if (this.map[y][x] instanceof Monster) {
                    System.out.print("[M]");
                }
                else if (this.map[y][x] instanceof Obstacle) {
                    System.out.print("[O]");
                }
                else if (y == exitY && x == exitX) {
                    System.out.print("[EXIT]"); 
                }
                else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    public Destructible movePlayer(String direction) {
        int newX = playerX;
        int newY = playerY;

        switch (direction) {
            case "haut": newY--; break;
            case "bas": newY++; break;
            case "gauche": newX--; break;
            case "droite": newX++; break;
            default:
                System.out.println("Direction inconnue...");
                return null;
        }

	        if (newX < 0 || newX >= this.width || newY < 0 || newY >= this.height) {
	            System.out.println("Vous ne pouvez pas sortir du donjon !");
	            return null;
	        }
        
        if (this.map[newY][newX] != null) {
            System.out.println("Le chemin est bloqué par quelque chose !");
            return this.map[newY][newX];
        }

        this.playerX = newX;
        this.playerY = newY;
        return null; 
    }
    
    public void clearMapTile(Destructible target) {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (this.map[y][x] == target) { 
                    this.map[y][x] = null;
                    System.out.println("Le passage est libéré !");
                    return;
                }
            }
        }
    }

	public boolean playerexit() {
		return (playerX == exitX && playerY == exitY);
	}
	
	public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getPlayerY() {
        return this.playerY;
    }
    public int getPlayerX() {
        return this.playerX;
    }


    
    public Destructible getElement(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y< height) {
            return this.map[y][x];
        }
        return null;
    }
    
    public boolean isExit(int x, int y) {
        return (x == this.exitX && y == this.exitY);
    }

	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
}