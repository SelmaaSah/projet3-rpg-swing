package jeuV.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane; 
import jeuV.Donjon;
import jeuV.Destructible;
import jeuV.Monster;
import jeuV.Obstacle;
import jeuV.Player;

public class KeyControlleur extends KeyAdapter {

    private Donjon donjon;
    private Player player; 
    private MapPanel mapPanel;

    public KeyControlleur(Donjon donjon, MapPanel mapPanel, Player player) {
        this.donjon = donjon;
        this.mapPanel = mapPanel;
        this.player = player; 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        String direction = null;

        switch (keyCode) {
            case KeyEvent.VK_UP: case KeyEvent.VK_Z: direction = "haut"; break;
            case KeyEvent.VK_DOWN: case KeyEvent.VK_S: direction = "bas"; break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_Q: direction = "gauche"; break;
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_D: direction = "droite"; break;
        }

        if (direction != null) {
            
            Destructible rencontre = donjon.movePlayer(direction);
            
            mapPanel.redrawMap();
            
            // --- GESTION DES RENCONTRES ---
            if (rencontre != null) {
                
                // CAS 1 : OBSTACLE
                if (rencontre instanceof Obstacle) {
                    JOptionPane.showMessageDialog(mapPanel, 
                        "Le chemin est bloqué par des débris !", 
                        "Bloqué", 
                        JOptionPane.WARNING_MESSAGE);
                }
                
                // CAS 2 : MONSTRE (C'est ici qu'on lance le combat)
                else if (rencontre instanceof Monster) {
                    
                    Monster leMonstre = (Monster) rencontre;
                    
                    javax.swing.JFrame topFrame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(mapPanel);
                    
                    FightPanl combat = new FightPanl(topFrame, this.player, leMonstre);
                    
                    combat.setVisible(true); // Bloque le jeu jusqu'à la fin du combat
                    
                    if (combat.isMonsterDead()) {
                        donjon.clearMapTile(leMonstre);
                        System.out.println("Le monstre a disparu du donjon.");
                        mapPanel.redrawMap(); // On enlève le rond rouge
                    }
                }
            }
            
            // --- GESTION VICTOIRE ---
            if (donjon.playerexit()) {
                JOptionPane.showMessageDialog(mapPanel, 
                    "FÉLICITATIONS !\nVous avez trouvé la sortie !", 
                    "Victoire", 
                    JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
    }
}