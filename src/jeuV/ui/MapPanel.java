package jeuV.ui;

import jeuV.Donjon;
import jeuV.Monster;
import jeuV.Obstacle;
import jeuV.Player;
import jeuV.Destructible;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class MapPanel extends JPanel {

    private Donjon donjon;
    private Player player; // On stocke le joueur ici
    
    private final int TILE_SIZE = 50; 

    // MODIFICATION ICI : On demande le Player dans le constructeur
    public MapPanel(Donjon donjon, Player player) {
        this.donjon = donjon;
        this.player = player; // On le sauvegarde
        
        this.setBackground(Color.DARK_GRAY);
        
        int panelW = donjon.getWidth() * TILE_SIZE;
        int panelH = donjon.getHeight() * TILE_SIZE;
        this.setPreferredSize(new Dimension(panelW, panelH));
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        // MODIFICATION ICI : On passe le joueur au contrôleur
        KeyControlleur controller = new KeyControlleur(donjon, this, player);
        this.addKeyListener(controller);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        for (int y = 0; y < donjon.getHeight(); y++) {
            for (int x = 0; x < donjon.getWidth(); x++) {
                
                int drawX = x * TILE_SIZE;
                int drawY = y * TILE_SIZE;
                
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(drawX, drawY, TILE_SIZE, TILE_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(drawX, drawY, TILE_SIZE, TILE_SIZE);
                
                if (x == donjon.getPlayerX() && y == donjon.getPlayerY()) {
                    g.setColor(Color.BLUE);
                    g.fillOval(drawX + 5, drawY + 5, TILE_SIZE - 10, TILE_SIZE - 10);
                    g.setColor(Color.WHITE);
                    g.drawString("P", drawX + 20, drawY + 30);
                } 
                else if (donjon.isExit(x, y)) {
                    g.setColor(Color.GREEN);
                    g.fillRect(drawX + 10, drawY + 10, TILE_SIZE - 20, TILE_SIZE - 20);
                    g.setColor(Color.BLACK);
                    g.drawString("EXIT", drawX + 10, drawY + 30);
                }
                else {
                    Destructible obj = donjon.getElement(x, y);
                    if (obj instanceof Monster) {
                        g.setColor(Color.RED);
                        g.fillOval(drawX + 5, drawY + 5, TILE_SIZE - 10, TILE_SIZE - 10);
                        g.setColor(Color.BLACK);
                        g.drawString("M", drawX + 20, drawY + 30);
                    } 
                    else if (obj instanceof Obstacle) {
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(drawX + 5, drawY + 5, TILE_SIZE - 10, TILE_SIZE - 10);
                    }
                }
            }
        }
    }
    
    public void redrawMap() {
        this.repaint(); 
    }
}