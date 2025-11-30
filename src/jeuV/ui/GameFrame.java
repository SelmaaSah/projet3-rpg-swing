package jeuV.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import jeuV.Player;
import jeuV.WeaponStore;
import jeuV.Donjon;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    
    private static final int LARGEUR = 800; 
    private static final int HAUTEUR = 600; 

    private Player gamePlayer;
    private WeaponStore gameStore;
    private Donjon gameDonjon;
    

    private JPanel mainGamePanel; 

    public GameFrame() {
        super("Mon RPG du Donjon"); 
        
        
        this.getContentPane().setBackground(new Color(255, 192, 203)); 
                this.setSize(LARGEUR, HAUTEUR); 
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null); 
        this.setLayout(new BorderLayout()); 

        MenuStartDialogue startDialog = new MenuStartDialogue(this);
        startDialog.setVisible(true); 
    }

    public void startGame(Player player, WeaponStore store) {
        this.gamePlayer = player;
        this.gameStore = store;
        this.gameDonjon = new Donjon(5, 4); // initialise le Donjon
        
        this.getContentPane().removeAll(); 
        
        JPanel mainGamePanel = new JPanel(new BorderLayout());
        mainGamePanel.setBackground(Color.BLACK); // fond
        PlayerInfoPanel infoPanel = new PlayerInfoPanel(this.gamePlayer);
        mainGamePanel.add(infoPanel, BorderLayout.WEST);
        
        MapPanel mapPanel = new MapPanel(this.gameDonjon, this.gamePlayer);
        mainGamePanel.add(mapPanel, BorderLayout.CENTER);
                this.add(mainGamePanel, BorderLayout.CENTER);
        
        this.validate(); 
        this.repaint();
    }
}