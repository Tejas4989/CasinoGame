package org.poc.slotgame;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	
	public void testAudioAMR(){
		JFrame f = new JFrame("Audio Test");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      f.setSize(300, 200);
	      f.setVisible(true);
	   
	      try {
	         // Open an audio input stream.
	         URL url = this.getClass().getResource("/images/sample.wav");
//	         File audioFile = new File(this.getClass().getResource("/images/sample.wav").getPath());
	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	         // Get a sound clip resource.
	         Clip clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioIn);
	         clip.start();
	         clip.loop(Clip.LOOP_CONTINUOUSLY);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
//		new test().testAnimatedGIF();
		new test().testAudioAMR();
		Map<String, String> map = new HashMap<String, String>();
	}

}
