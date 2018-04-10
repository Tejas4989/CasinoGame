package org.poc.slotgame;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class test {
	
	public void testAnimatedGIF(){
		URL url = getClass().getResource("/images/animated.gif");
		Icon icon = new ImageIcon(url);
		JLabel label = new JLabel(icon);
		
		JFrame f = new JFrame("Animation");
		f.getContentPane().add(label);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		new test().testAnimatedGIF();
	}

}
