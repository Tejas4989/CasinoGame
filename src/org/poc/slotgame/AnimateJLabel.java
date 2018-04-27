package org.poc.slotgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.image.*;
class AnimateJLabel extends JFrame
{
JLabel l;
ImageIcon icon;
Timer t;
int x=0;

 public AnimateJLabel()
 {
  createAndShowGUI();
 }
 
 private void createAndShowGUI()
 {
  // Set frame properties
  setTitle("Animate JLabel");
  setLayout(new GridBagLayout());
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  
  // Create an ImageIcon
  icon=new ImageIcon(getClass().getResource("/images/animated.gif"));  
  
  // Create a JLabel
  l=new JLabel("Java-Demos.blogspot.com");
  
  // Set the text position bottom center relative
  // to the icon so that the icon appears as a
  // a desktop icon
  l.setVerticalTextPosition(SwingConstants.BOTTOM);
  l.setHorizontalTextPosition(SwingConstants.CENTER);
  
  // Set the icon to the JLabel
  l.setIcon(icon);
  
  // Set some font
  l.setFont(new Font("Myriad Pro",Font.PLAIN,28));
  
  // Initially, the label isn't visible
  l.setVisible(false);
  
  // Add the JLabel
  add(l);
   
  // Create a Timer with that executes
  // each 1ms
  t=new Timer(1,new ActionListener(){
  
   public void actionPerformed(ActionEvent ae)
   {
    // First time, make the JLabel visible
    // You don't need to call this again because
    // the label is visible when this is first
    // executed. Calling it every time the timer fires
    // is redundant
    if(x==0) l.setVisible(true);
    
    // Set the location, only the x-co ordinate gets
    // updated (incremented by 1 each time) and the y remains
    // constant here because getHeight() and l.getHeight() both
    // are constant
    l.setLocation(x++-l.getWidth()/2,getHeight()/2-l.getHeight()/2);
    
    // If x equals half of the width of the JFrame
    // i.e. reaches center, then stop the timer
//    if(x==getWidth()/2) t.stop();
   }
  });
  
  // Set some initial delay, optional
  t.setInitialDelay(200);
  
  // Start the timer
  t.start();
  
  // Set size and show the frame
  setSize(500,500);
  setVisible(true);
  setLocationRelativeTo(null);
 }
 
 public static void main(String args[])
 {
  SwingUtilities.invokeLater(new Runnable(){
   public void run()
   {
    new AnimateJLabel();
   }
  });
 }
}


/*public class TestLayout {

    public static void main(String[] args) {
        new TestLayout();
    }

    public TestLayout() {
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
                gbc.gridy =3;
                gbc.weightx=0.5;
                gbc.gridwidth=2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                frame.add(createPane(Color.CYAN), gbc);
                
                // add separator line
                
                //add credit, won, lost and balance 
                gbc.gridx = 0;
                gbc.gridy = 4; // 5 the row
                gbc.weightx=0.2;
                gbc.weighty= 0.1;
                gbc.gridwidth=2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                frame.add(createPane(Color.ORANGE), gbc);
                
              //add Cashout, Buy Credit, sound off/on button 
                gbc.gridx = 0;
                gbc.gridy = 5; // 5 the row
                gbc.weightx=0.2;
                gbc.weighty= 0.2;
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
                return new Dimension(50, 50);
            }

        };
        pane.setBackground(color);
        return pane;
    }

}*/