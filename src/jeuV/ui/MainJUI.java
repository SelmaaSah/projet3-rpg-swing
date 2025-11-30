package jeuV.ui;

import javax.swing.SwingUtilities;

import java.lang.Runnable; 


public class MainJUI {

    public static void main(String[] args) {
        
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame window = new GameFrame();
                window.setVisible(true);
            }
        });
        
    }
}