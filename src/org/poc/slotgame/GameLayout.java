package org.poc.slotgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GameLayout {

    public static void main(String[] args) {
        new GameLayout();
    }

    public GameLayout() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(4,4,4,4);
                
                // insert pnlreels jpanel here.
                gbc.gridx = 1;
//                gbc.weightx = 0;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                frame.add(createPane(Color.RED), gbc);
                gbc.gridy++;
                frame.add(createPane(Color.GREEN), gbc);
                gbc.gridy++;
                frame.add(createPane(Color.BLUE), gbc);
                
                //Add spin/ play button here
                gbc.gridx = 0;
//                gbc.weightx = 0;
                gbc.gridy = 0;
                gbc.gridheight = 3;
                gbc.fill = GridBagConstraints.VERTICAL;
                frame.add(createPane(Color.YELLOW), gbc);               
                
                // add one row for bet radio button
                gbc.gridx = 0;
                gbc.gridy = 3;
//                gbc.weightx=0.5;
                gbc.gridwidth=2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                frame.add(createPane(Color.CYAN), gbc);
                
                // add separator line
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(4,4,4,4);
                //add credit, won, lost and balance 
                gbc.gridx = 0;
                gbc.gridy = 4; // 5 the row
//                gbc.weightx=1;
//                gbc.weighty=1;
                gbc.gridwidth=2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                frame.add(createPane(Color.ORANGE), gbc);
                
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(4,4,4,4);
                
              //add Cashout, Buy Credit, sound off/on button 
                gbc.gridx = 0;
                gbc.gridy = 5; // 5 the row
                gbc.weightx=2;
                gbc.weighty=2;
                gbc.gridwidth=2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                frame.add(createPane(Color.MAGENTA), gbc);
                

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public JPanel createPane(Color color) {
        JPanel pane = new JPanel(){ 
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(200, 100);
            }
        };
        pane.setBackground(color);
        return pane;
    }

}