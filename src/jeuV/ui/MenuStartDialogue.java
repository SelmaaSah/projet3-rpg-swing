package jeuV.ui;

import jeuV.Player;
import jeuV.WeaponStore;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Pour gérer l'événement du bouton START

public class MenuStartDialogue extends JDialog implements ActionListener {
    
    private JTextField nameField;
    private WeaponStore store;
    private Player player; // ref du jouueur
    private GameFrame parentFrame; 
    private static final int LARGEUR = 500;
    private static final int HAUTEUR = 400;

    public MenuStartDialogue(GameFrame parentFrame) {
        super(parentFrame, "Configuration du Personnage", true); 
        this.parentFrame = parentFrame;
        this.store = new WeaponStore();
        
        this.setSize(LARGEUR, HAUTEUR);
        this.setLocationRelativeTo(parentFrame); // Centre sur la fenêtre principale [cite: 1328]
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        setupLayout();
    }
    
    private void setupLayout() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(240, 240, 255)); 
        
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        namePanel.add(new JLabel("Nom de l'aventurier :"));
        nameField = new JTextField(15);
        namePanel.add(nameField);
        
        contentPanel.add(namePanel, BorderLayout.NORTH);
        
       
        JLabel storeLabel = new JLabel("Visualisez le magasin avant de commencer (achat non implémenté) :");
        
        javax.swing.JTextArea storeArea = new javax.swing.JTextArea(5, 30);
        storeArea.setEditable(false);
        // On ne peut pas appeler la méthode displayWeapons() car elle fait des System.out.println
        storeArea.setText("--- Magasin d'Armes ---\n");
        storeArea.append("Hache (10 dmg)\n");
        storeArea.append("Marteau (20 dmg)\n");
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(storeLabel, BorderLayout.NORTH);
        centerPanel.add(storeArea, BorderLayout.CENTER);
        
        contentPanel.add(centerPanel, BorderLayout.CENTER);
        
        JButton startButton = new JButton("DÉMARRER L'EXPLORATION (START)"); // [cite: 1383]
        startButton.addActionListener(this); 
        contentPanel.add(startButton, BorderLayout.SOUTH);
        
        this.add(contentPanel); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String playerName = nameField.getText().trim();
        
        if (playerName.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
            "Veuillez entrer un nom pour votre personnage",  "Erreur de saisie !",  javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            this.player = new Player(playerName);
                        this.dispose(); 
            
            parentFrame.startGame(this.player, this.store);
        }
    }
    
    public Player getCreatedPlayer() {
        return this.player; 
    }
}