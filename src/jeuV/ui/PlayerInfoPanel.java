package jeuV.ui;

import jeuV.Player;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class PlayerInfoPanel extends JPanel {

    private Player player;
    private JLabel nameLabel;
    private JLabel silverLabel;
    private JLabel inventoryLabel;
    

    public PlayerInfoPanel(Player player) {
        this.player = player;
        
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(200, 0)); 
        this.setBackground(new Color(255, 230, 240)); 
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        nameLabel = new JLabel("Aventurier: " + player.getName());
        silverLabel = new JLabel("Argent: " + player.getSilver() + " pièces");
        inventoryLabel = new JLabel("Inventaire: " + player.getInventorySize() + " armes");
        
        infoPanel.add(nameLabel);
        infoPanel.add(silverLabel);
        infoPanel.add(inventoryLabel);
        
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(new JLabel("--- Espace Inventaire Détaillé ---"), BorderLayout.CENTER);
        
        this.validate();
    }
    
    public void updateInfo() {
        nameLabel.setText("Aventurier: " + player.getName());
        silverLabel.setText("Argent: " + player.getSilver() + " pièces");
        inventoryLabel.setText("Inventaire: " + player.getInventorySize() + " armes");
    }
}