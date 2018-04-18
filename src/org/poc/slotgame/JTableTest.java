package org.poc.slotgame;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableTest extends JPanel{
	
	public JTableTest()
    {
        Icon aboutIcon = new ImageIcon(getClass().getResource("/images/Gold.png"));
        Icon addIcon = new ImageIcon(getClass().getResource("/images/Coin.png"));
        Icon copyIcon = new ImageIcon(getClass().getResource("/images/Bell.png"));

        String[] columnNames = {"Picture", "Description"};
        Object[][] data =
        {
            {aboutIcon, "About"},
            {addIcon, "Add"},
            {copyIcon, "Copy"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        JTable table = new JTable( model );
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane( table );
        add( scrollPane );
    }
	
	 private static void createAndShowGUI()
	    {
	        JFrame frame = new JFrame("Table Icon");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(new JTableTest());
	        frame.setLocationByPlatform( true );
	        frame.pack();
	        frame.setVisible( true );
	    }

	    public static void main(String[] args)
	    {
	        EventQueue.invokeLater(new Runnable()
	        {
	            public void run()
	            {
	                createAndShowGUI();
	            }
	        });
	    }

}
