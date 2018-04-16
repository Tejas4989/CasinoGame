package org.poc.slotgame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


Java Swing tutorial
Drag and drop icons

Author: Jan Bodnar
Website: http://zetcode.com
 

public class IconDnD extends JFrame {


    public IconDnD() {

        initUI();
    }

    private void initUI() {

        ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/Chip-25.png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/Chip-50.png"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/images/Chip-100.png"));

        JLabel label1 = new JLabel(icon1, JLabel.CENTER);
        JLabel label2 = new JLabel(icon2, JLabel.CENTER);
        JLabel label3 = new JLabel(icon3, JLabel.CENTER);

        MouseListener listener = new DragMouseAdapter();
        label1.addMouseListener(listener);
        label2.addMouseListener(listener);
        label3.addMouseListener(listener);

        JButton button = new JButton(icon2);
        button.setFocusable(false);

        label1.setTransferHandler(new TransferHandler("icon"));
        label2.setTransferHandler(new TransferHandler("icon"));
        label3.setTransferHandler(new TransferHandler("icon"));
        button.setTransferHandler(new TransferHandler("icon"));

        createLayout(label1, label2, label3, button);

        setTitle("Icon Drag & Drop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private class DragMouseAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {

            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
        
        public void mouseReleased(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }
    
    private class DropMouseAdapter extends MouseAdapter {

        public void mouseReleased(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[0])
                        .addGap(30)
                        .addComponent(arg[1])
                        .addGap(30)
                        .addComponent(arg[2])
                )
                .addComponent(arg[3], GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1])
                        .addComponent(arg[2]))
                .addGap(30)
                .addComponent(arg[3])
        );

        pack();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            IconDnD ex = new IconDnD();
            ex.setVisible(true);
        });
    }
}*/

@SuppressWarnings("serial")
public class TestMouseRelease extends JPanel {
   private String[] panelNames = { "Panel A", "Panel B" };

   public TestMouseRelease() {
      setLayout(new GridLayout(1, 0));
      MouseAdapter mAdapter = new MyMouseAdapter();

      addMouseListener(mAdapter);
      addMouseMotionListener(mAdapter);

      for (String panelName : panelNames) {
         JPanel panel = new JPanel();
         panel.setName(panelName);
         // panel.addMouseListener(mAdapter);
         // panel.addMouseMotionListener(mAdapter);
         panel.setBorder(BorderFactory.createTitledBorder(panelName));
         panel.add(Box.createRigidArea(new Dimension(300, 300)));
         add(panel);
      }
   }

   private class MyMouseAdapter extends MouseAdapter {
      @Override
      public void mousePressed(MouseEvent e) {
         displayInfo(e, "mousePressed");
      }

      @Override
      public void mouseReleased(MouseEvent e) {
         displayInfo(e, "mouseReleased");
      }

      @Override
      public void mouseDragged(MouseEvent e) {
         displayInfo(e, "mouseDragged");
      }

      private void displayInfo(MouseEvent e, String info) {
         JComponent comp = (JComponent) e.getSource();
         Component childComp = comp.getComponentAt(e.getPoint());
         if (childComp != null) {
            String name = childComp.getName();
            System.out.println(name + ": " + info);
         }
      }
   }

   private static void createAndShowGui() {
      JFrame frame = new JFrame("TestMouseRelease");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new TestMouseRelease());
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}