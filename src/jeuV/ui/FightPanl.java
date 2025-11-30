package jeuV.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jeuV.Player;
import jeuV.Monster;
import jeuV.Weapon;

public class FightPanl extends JDialog {

    private Player player;
    private Monster monster;
    private boolean monsterDied = false; 

    // Composants graphiques
    private JComboBox<String> weaponSelector; 
    private JTextArea combatLog; 
    private JLabel monsterHPLabel; 

    public FightPanl(JFrame parent, Player player, Monster monster) {
        super(parent, "COMBAT !", true); 
        this.player = player;
        this.monster = monster;

        this.setSize(400, 300);
        this.setLocationRelativeTo(parent);
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(255, 100, 100)); // 
        monsterHPLabel = new JLabel("Monstre : " + monster.getPtsDeVie() + " PV");
        monsterHPLabel.setForeground(Color.WHITE);
        monsterHPLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(monsterHPLabel);
        this.add(topPanel, BorderLayout.NORTH);

        combatLog = new JTextArea();
        combatLog.setEditable(false);
        combatLog.setText("Un monstre apparaît ! Préparez-vous !\n");
        this.add(new JScrollPane(combatLog), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));

        JPanel weaponPanel = new JPanel();
        weaponPanel.add(new JLabel("Choisir arme : "));
        
        weaponSelector = new JComboBox<>();
     
        if (player.getInventorySize() == 0) {
            weaponSelector.addItem("Mains nues (Pas d'arme)");
        } else {
            for (int i = 0; i < player.getInventorySize(); i++) {
                Weapon w = player.getWeapon(i);
              
                String weaponName = w.getClass().getSimpleName(); 
                weaponSelector.addItem(weaponName + " (Dmg: " + w.getDamage() + ")");
            }
        }
        weaponPanel.add(weaponSelector);
        
        JPanel buttonPanel = new JPanel();
        JButton attackButton = new JButton("ATTAQUER ⚔️");
        JButton fleeButton = new JButton("FUIR 🏃");

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                faireAttaque();
            }
        });

        fleeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combatLog.append("Vous fuyez\n");
                dispose(); 
            }
        });

        buttonPanel.add(attackButton);
        buttonPanel.add(fleeButton);

        bottomPanel.add(weaponPanel);
        bottomPanel.add(buttonPanel);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void faireAttaque() {
        if (player.getInventorySize() == 0) {
            combatLog.append("Vous n'avez pas d'arme ! Vous ne faites rien.\n");
            return;
        }

        int index = weaponSelector.getSelectedIndex();
        Weapon armeChoisie = player.getWeapon(index);

        System.out.println(armeChoisie.asciiArt());
        
        // attacker
        armeChoisie.attack(monster);
        
        combatLog.append("Vous frappez avec " + armeChoisie.getClass().getSimpleName() + " !\n");
        combatLog.append("Le monstre perd des PV...\n");
        
        monsterHPLabel.setText("Monstre : " + monster.getPtsDeVie() + " PV");

        if (monster.getPtsDeVie() <= 0) {
            monsterDied = true;
            JOptionPane.showMessageDialog(this, "VICTOIRE ! Le monstre est vaincu !");
            dispose(); 
        }
    }

    public boolean isMonsterDead() {
        return monsterDied;
    }
}