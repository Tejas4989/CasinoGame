package org.poc.slotgame;
/**
    Copyright (C) 2012 whatatux
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

class Gui extends JFrame {
	private DecimalFormat df = new DecimalFormat("0.00");
	protected static final String IMAGE_PATH = "/images/Final_images/";
	private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
	GridBagLayout gbl = new GridBagLayout();
	private JRadioButton rb1X, rb2X, rb3X, rb4X;
	private int credits = 100, boughtCredits = 10, bet = 1, matchThree, matchTwo, win, lost;
	private JButton btnCash, btnSpin, btnPrintReceipt;
	private JToggleButton tgglSound;
	private Clip audioClip = null;
	private JLabel lblCredits, lblLost, lblMatchThree, lblMatchTwo, lblMoney, lblStatus, lblWon;
	private double payout = 25.0, creditBuyout = 10.0, funds;
	private ReelIconLabel lblReel1, lblReel2, lblReel3, lblReel4, lblReel5, lblReel6, lblReel7, lblReel8, lblReel9;
	private int reel1 = 1, reel2 = 2, reel3 = 3, reel4 = 4, reel5 = 5, reel6 = 6, reel7 = 7, reel8 = 8, reel9 = 9; // starting values of the reels.
	private JPanel reelPanel;
	private List<JLabel> matchedReelNumbers = null;
	public boolean isMatchedCheck = false;
	 
	
	
	Gui() {
		// other data loading stuff
		loadImages();
		loadAudio();
		initializeLabels();
		
		
		// layout stuff
		setLayout(gbl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);

		//instantiates Border panels.
		BorderPanel pnlA = new BorderPanel("Icon Info");
		BorderPanel pnlB = new BorderPanel("Reels");
		BorderPanel pnlC = new BorderPanel("Bet");
		BorderPanel pnlD = new BorderPanel("Spin");
		BorderPanel pnlE = new BorderPanel("Match Won/Lose");
		BorderPanel pnlF = new BorderPanel("Credit Calculations");
		BorderPanel pnlH = new BorderPanel("Wining/Losing Information");
		BorderPanel pnlG = new BorderPanel("Other Buttons");

		//adding all panels to main contentPane.
		add(pnlA);
		add(pnlB);
		add(pnlC);
		add(pnlD);
		add(pnlE);
		add(pnlF);
		add(pnlH);
		add(pnlG);
		

		//set constraints of each panel.
		makeConstraints(gbl, pnlA, 1, 1, 0, 0, 0.75, 4.0);
		makeConstraints(gbl, pnlB, 2, 1, 1, 0, 2.25, 4.0);
		makeConstraints(gbl, pnlC, 1, 1, 0, 1, 1.5, 0.2);
		makeConstraints(gbl, pnlD, 2, 1, 1, 1, 1.5, 0.2); 
		makeConstraints(gbl, pnlE, 1, 1, 0, 2, 0.75, 0.5);
		makeConstraints(gbl, pnlF, 1, 1, 1, 2, 1.25, 0.5);
		makeConstraints(gbl, pnlH, 1, 1, 2, 2, 1, 0.5);
		makeConstraints(gbl, pnlG, 3, 1, 0, 3, 0.3, 0.3);
		
		// add Icon and label in pnlA
		addIconAndPoints(pnlA);
		// add Reel Panel in pnlB
		addReels(pnlB);
		//add Reel Panel in pnlC
		addBetButtons(pnlC);
		//add Reel Panel in pnlD
		addSpinButton(pnlD);
		//add Reel Panel in pnlE
		addWonLoseInformation(pnlE);
		//add Reel Panel in pnlF
		addCreditInformation(pnlF);
		//add Reel Panel in pnlG
		addOtherButtons(pnlG);
		//add wining losing information
		addWiningLosingInformation(pnlH);
		
	}
	
	 /**
	  * Add Winning and losing information label
	 * @param pnlH
	 */
	private void addWiningLosingInformation(BorderPanel pnlH) {
		pnlH.add(lblStatus);
	}

	/**
	 * @param pnlB
	 */
	private void addReels(BorderPanel pnlB) {
		reelPanel = new JPanel();
		reelPanel.setBackground(new java.awt.Color(255, 215, 0));
		reelPanel.setBorder(BorderFactory.createMatteBorder(12, 12, 12, 12, Color.DARK_GRAY));
        GridBagLayout gb = new GridBagLayout();
        reelPanel.setLayout(gb);
        //adding reel label to panel
        reelPanel.add(lblReel1);
        lblReel1.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 8, Color.DARK_GRAY));
        reelPanel.add(lblReel2);
        lblReel2.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 8, Color.DARK_GRAY));
        reelPanel.add(lblReel3);
        lblReel3.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 0, Color.DARK_GRAY));
        reelPanel.add(lblReel4);
        lblReel4.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 8, Color.DARK_GRAY));
        reelPanel.add(lblReel5);
        lblReel5.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 8, Color.DARK_GRAY));
        reelPanel.add(lblReel6);
        lblReel6.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 0, Color.DARK_GRAY));
        reelPanel.add(lblReel7);
        lblReel7.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 8, Color.DARK_GRAY));
        reelPanel.add(lblReel8);
        lblReel8.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 8, Color.DARK_GRAY));
        reelPanel.add(lblReel9);
        
        int y = 0;
        int x = 0;
		makeConstraints(gb, lblReel1, 1, 1, x++, y, 0.5, 0.5);
		makeConstraints(gb, lblReel2, 1, 1, x++, y, 0.5, 0.5);
		makeConstraints(gb, lblReel3, 1, 1, x, y++, 0.5, 0.5);
		x = 0;
		makeConstraints(gb, lblReel4, 1, 1, x++, y, 0.5, 0.5);
		makeConstraints(gb, lblReel5, 1, 1, x++, y, 0.5, 0.5);
		makeConstraints(gb, lblReel6, 1, 1, x, y++, 0.5, 0.5);
		x = 0;
		makeConstraints(gb, lblReel7, 1, 1, x++, y, 0.5, 0.5);
		makeConstraints(gb, lblReel8, 1, 1, x++, y, 0.5, 0.5);
		makeConstraints(gb, lblReel9, 1, 1, x, y++, 0.5, 0.5);
		
        pnlB.add(reelPanel);		
	}

	/**
	 * @param pnlC
	 */
	private void addBetButtons(BorderPanel pnlC) {
		 rb1X = new JRadioButton("1X", true);
	        rb1X.addActionListener(new BetHandler());
	        
	        rb2X = new JRadioButton("2x", false);
	        rb2X.addActionListener(new BetHandler());
	        
	        rb3X = new JRadioButton("3x", false);
	        rb3X.addActionListener(new BetHandler());
	        
	        rb4X = new JRadioButton("4x", false);
	        rb4X.addActionListener(new BetHandler());
	        
	        ButtonGroup bg = new ButtonGroup();
	        bg.add(rb1X);
	        bg.add(rb2X);
	        bg.add(rb3X);
	        bg.add(rb4X);
	        
	        pnlC.add(rb1X);
	        pnlC.add(rb2X);
	        pnlC.add(rb3X);
	        pnlC.add(rb4X);
	}

	/**
	 * @param pnlD
	 */
	private void addSpinButton(BorderPanel pnlD) {
		btnSpin = new JButton();
		btnSpin.setBackground(new Color(50, 255, 50));
		btnSpin.setText("Spin");
		btnSpin.setToolTipText("Click to spin the reels!");
		btnSpin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		btnSpin.setInheritsPopupMenu(true);
		btnSpin.setPreferredSize(new Dimension(350, 30));
		btnSpin.setVerticalAlignment(SwingConstants.CENTER);
		btnSpin.addActionListener(new SpinHandler());	
		
		pnlD.add(btnSpin);
	}

	/**
	 * @param pnlE
	 */
	private void addWonLoseInformation(BorderPanel pnlE) {
		JPanel wonLosePanel = new JPanel();
		GridBagLayout gb = new GridBagLayout();
		wonLosePanel.setLayout(gb);
		int y = 0;
		int x = 0;

		makeConstraints(gb, lblWon, 1, 1, x, y++, 0.3, 0.3);
		makeConstraints(gb, lblMatchTwo, 1, 1, x, y++, 0.3, 0.3);
		makeConstraints(gb, lblMatchThree, 1, 1, x, y, 0.3, 0.3);
		wonLosePanel.add(lblWon);
		wonLosePanel.add(lblMatchTwo);
		wonLosePanel.add(lblMatchThree);
		pnlE.add(wonLosePanel);
		
	}

	/**
	 * @param pnlF
	 */
	private void addCreditInformation(BorderPanel pnlF) {
		JPanel creditInfoPanel = new JPanel();
		GridBagLayout gb = new GridBagLayout();
		creditInfoPanel.setLayout(gb);	
		int y=0;
		makeConstraints(gb, lblLost, 1, 1, 0, y++, 0.3, 0.3);
		makeConstraints(gb, lblCredits, 1, 1, 0, y++, 0.3, 0.3);
		makeConstraints(gb, lblMoney, 1, 1, 0, y, 0.3, 0.3);
		creditInfoPanel.add(lblLost);
		creditInfoPanel.add(lblCredits);
		creditInfoPanel.add(lblMoney);
		pnlF.add(creditInfoPanel);
		
	}

	/**
	 * @param pnlG
	 */
	private void addOtherButtons(BorderPanel pnlG) {
		  btnCash = new JButton();
	        btnCash.setBackground(new Color(255, 0, 0));
	        btnCash.setText("Buy Credits");
	        btnCash.setToolTipText("$"+df.format(bet)+" converts to "+boughtCredits+" credits.");
	        btnCash.setHorizontalTextPosition(SwingConstants.LEFT);
	        btnCash.addActionListener(new BuyCreditsHandler());
	        
	        pnlG.add(btnCash);
	        
	        btnPrintReceipt = new JButton();
	        btnPrintReceipt.setBackground(new Color(255,255,255));
	        btnPrintReceipt.setText("Cash Out");
	        btnPrintReceipt.setToolTipText("Click to print cash out reciept.");
	        btnPrintReceipt.setHorizontalTextPosition(SwingConstants.CENTER);
	        btnPrintReceipt.addActionListener(new PrintRecieptHandler());
	        
	        pnlG.add(btnPrintReceipt);
	         
	        tgglSound = new JToggleButton();
	        tgglSound.setSelected(true);
	        tgglSound.setText("Sound OFF");
	        btnPrintReceipt.setHorizontalTextPosition(SwingConstants.RIGHT);
	        tgglSound.addActionListener(new SoundHandler());
	        
	        pnlG.add(tgglSound);
	}

	/** Loads ImageIcons into the images ArrayList.
	    *    The difficulty is determined by the number of images present in the ArrayList:
	    *    •    Add images here to make game more difficult.
	    *    •    Remove images here to make game easier.
	    */
	    public void loadImages() {
	        images.add(createImageIcon(IMAGE_PATH+"Apple.png", "Apple,10"));
	        images.add(createImageIcon(IMAGE_PATH+"Avocado.png", "Avocado,20"));
	        images.add(createImageIcon(IMAGE_PATH+"Banana.png", "Banana,30"));
	        images.add(createImageIcon(IMAGE_PATH+"Cherry.png", "Cherry,40"));
	        images.add(createImageIcon(IMAGE_PATH+"Coconut.png", "Coconut,50"));
	        images.add(createImageIcon(IMAGE_PATH+"Dragon.png", "Dragon,60"));
	        images.add(createImageIcon(IMAGE_PATH+"Kiwi.png", "Kiwi,70"));
	        images.add(createImageIcon(IMAGE_PATH+"Mango.png", "Mango,80"));
	        images.add(createImageIcon(IMAGE_PATH+"Melon.png", "Melon,90"));
	        images.add(createImageIcon(IMAGE_PATH+"Orange.png", "Orange,40"));
	        images.add(createImageIcon(IMAGE_PATH+"Pineaple.png", "Pineaple,50"));
	        images.add(createImageIcon(IMAGE_PATH+"Pomes.png", "Pomes,60"));
	        images.add(createImageIcon(IMAGE_PATH+"Pomogranets.png", "Pomogranets,70"));
	        images.add(createImageIcon(IMAGE_PATH+"Strawberry.png", "Strawberry,80"));
	        images.add(createImageIcon(IMAGE_PATH+"Watermelon.png", "Watermelon,90"));
	        images.add(createImageIcon(IMAGE_PATH+"animated.gif", "Animated,100"));
	       /* //convert to icons array
	        icons = new ImageIcon[images.size()];
	        int i=0;
	        for (ImageIcon imageIcon : images) {
				icons[i++] = imageIcon;
			}*/
	    }
	    
	    /**
	     * Load the audio file.
	     */
	    private void loadAudio(){
	    	try {
		         // Open an audio input stream.
		         URL url = this.getClass().getResource("/images/sample.wav");
//		         File audioFile = new File(this.getClass().getResource("/images/sample.wav").getPath());
		         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
		         // Get a sound clip resource.
		         this.audioClip = AudioSystem.getClip();
		         // Open audio clip and load samples from the audio input stream.
		         this.audioClip.open(audioIn);
		      } catch (UnsupportedAudioFileException ex) {
		         ex.printStackTrace();
		      } catch (IOException ioEx) {
		         ioEx.printStackTrace();
		      } catch (LineUnavailableException luEx) {
		         luEx.printStackTrace();
		      }
	    }
	    
	    /** Create a new ImageIcon, unless the URL is not found. */
	    public ImageIcon createImageIcon(String path, String description) {
	        java.net.URL imgURL = getClass().getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL, description);
	            } else {
	            System.err.println("Couldn't find file: " + path);
	            return null;
	        }
	    }
	    
		public void initializeLabels() {
			lblStatus = new JLabel();
			lblStatus.setBackground(new Color(255, 255, 255));
			lblStatus.setFont(new Font("Arial", 1, 14));
			lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
			lblStatus.setText("Welcome to Casnio InnovatiQ!!! ©2018");
			
			lblMatchTwo = new JLabel();
	        lblMatchTwo.setText("Matched Two: ");
	        lblMatchTwo.setHorizontalAlignment(SwingConstants.LEFT);
	        lblMatchThree = new JLabel();
	        lblMatchThree.setText("Matched Three: ");
	        lblMatchThree.setHorizontalAlignment(SwingConstants.LEFT);
	        lblWon = new JLabel();
	        lblWon.setText("Won: ");
	        lblWon.setHorizontalAlignment(SwingConstants.LEFT);
	        
	        lblCredits = new JLabel();
	        lblCredits.setText("Credits: "+credits);
	        lblCredits.setHorizontalAlignment(SwingConstants.LEFT);
	        lblMoney = new JLabel();
	        lblMoney.setText("Money: $"+df.format(funds));
	        lblMoney.setHorizontalAlignment(SwingConstants.LEFT);
	        lblLost = new JLabel();
	        lblLost.setText("Lost: ");
	        lblLost.setHorizontalAlignment(SwingConstants.LEFT);
	        
	        // Reel labels
	        lblReel1 = new ReelIconLabel(images.get(reel1), this);
	        lblReel2 = new ReelIconLabel(images.get(reel2), this);
	        lblReel3 = new ReelIconLabel(images.get(reel3), this);
	        lblReel4 = new ReelIconLabel(images.get(reel4), this);
	        lblReel5 = new ReelIconLabel(images.get(reel5), this);
	        lblReel6 = new ReelIconLabel(images.get(reel6), this);
	        lblReel7 = new ReelIconLabel(images.get(reel7), this);
	        lblReel8 = new ReelIconLabel(images.get(reel8), this);
	        lblReel9 = new ReelIconLabel(images.get(reel9), this);
	        
	        lblStatus = new JLabel();
	        lblStatus.setBackground(new Color(255, 255, 255));
	        lblStatus.setFont(new Font("Arial", 1, 14));
	        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
	        lblStatus.setText("Welcome to Casnio InnovatiQ!!! ©2018");
			
		}
	
	/**
	 * Adding Vertical scrollable icon and points bar
	 * @param comp
	 */
	public void addIconAndPoints(JComponent comp){
	        JPanel iconPanel = new JPanel();
	        iconPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
	        GridBagLayout gb = new GridBagLayout();
	        iconPanel.setLayout(gb);
	        int y = 0;
	        for (ImageIcon icon : images) {
	        	int x=0;
	        	JLabel iconLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT), icon.getDescription()));
	        	JLabel iconDescription = new JLabel(icon.getDescription());
	        	
	        	makeConstraints(gb, iconLabel, 1, 1, x++, y, 0.5, 4.0);
	        	makeConstraints(gb, iconDescription, 1, 1, x++, y, 0.5, 4.0);
	        	iconPanel.add(iconLabel);
	        	iconPanel.add(iconDescription);
	    		y++;
			}
	        JScrollPane scrollPane = new JScrollPane(iconPanel);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	        scrollPane.setBounds(10, 10, 10, 10);
	        comp.add(scrollPane);
	}
	
	

	/**
	  * Generate constraints for Swing components
	  * @param gbl     a gridbaglayout that used to embed Swing component
	  * @param comp    a Swing component intended to be embedded in gbl
	  * @param w       desired component width
	  * @param h       desired component height
	  * @param x       desired location in x-axis
	  * @param y       desired location in y-axis
	  * @param weightx desired weight in terms of x-axis
	  * @param weighty desired weight in terms of y-axis
	  */
	public void makeConstraints(GridBagLayout gbl, JComponent comp, int w, int h, int x, int y,
			double weightx, double weighty) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.anchor = GridBagConstraints.WEST;
//		constraints.insets = new Insets(1, 1, 1, 1);
		gbl.setConstraints(comp, constraints);
	}

	//Main method
	public static void main (String [] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Gui().setVisible(true);
			}
		});
	}
	
	// Action handlers
	/** Performs action when bet check box is clicked. */
    class BetHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	JRadioButton rb = (JRadioButton)e.getSource();
            if(rb == rb1X){
            	bet = 1;
            	System.out.println("bet 1 is applied");
            }else if(rb == rb2X){
            	System.out.println("Bet 2 is applied");
            	bet = 5;
            }else if(rb == rb3X){
            	System.out.println("Bet 3 is applied");
            	bet = 10;
            }else {
            	System.out.println("Bet 4 is applied");
            	bet = 20;
            }
        }
    }
    
    /** Performs action when Spin button is clicked. */
    class SpinHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
			if (funds < creditBuyout && credits < bet) {
				lblStatus.setText("<html><a href='http://www.innovatiqtechnology.com/'>InnovatiQ</a></html>");
			} else if ((credits - bet) >= 0) {
				isMatchedCheck = false; // to enable the matchCheck
				reelPanel.setBackground(new java.awt.Color(255, 215, 0));
				genReelNumbers();
				matchedReelNumbers = new ArrayList<JLabel>();
				if (findRowMatches() || findColumnwMatches()) {
					System.out.println("Found expected jackpot" + matchedReelNumbers);
				}else {
					lblStatus.setText("Sorry, you didn't match any symbols. -" + bet + " credits!");
					lblLost.setText("Lost: " + lose());
					lblCredits.setText("Credits: " + (credits -= bet)); // deduct bet amount from available credits.
				}
			} else {
				lblStatus.setText("Bet is " + bet + " credits, purchase more with $!");
			}
			buyCreditsCheck();
		}
    }
    
    /** if user has enough funds to buy credits changes buttons colour to alert user. */
    public void buyCreditsCheck() {
        if (funds < bet) {
            btnCash.setBackground(new java.awt.Color(255, 0, 0));
            } else {
            btnCash.setBackground(new java.awt.Color(50, 255, 50));
        }
    }
    
    /**
     *  Find row matches
     */
    public boolean findRowMatches(){
		if (reel1 == reel2 && reel1 == reel3 && reel2 == reel3) {
			// change one of the reel icon and set to other icon
			// then based on pair position, set the bonus icon to replacement place.
			System.out.println("All first rows reel are matched.");
			return false;
		}else if(reel1 == reel2 || reel1 == reel3 || reel2 == reel3){
			matchedReelNumbers.add(lblReel1);
			matchedReelNumbers.add(lblReel2);
			matchedReelNumbers.add(lblReel3);
    		if(reel1 == reel2){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel1 and reel2 icons are match");
    			// get the matched Icon's half cutting image
    			String reelImageIconDescription = images.get(reel1).getDescription();
    			lblReel6.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}else if(reel1 == reel3){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel1 and reel3 icons are match");
    			String reelImageIconDescription = images.get(reel1).getDescription();
    			lblReel5.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}if(reel2 == reel3){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel2 and reel3 icons are match");
    			String reelImageIconDescription = images.get(reel3).getDescription();
    			lblReel4.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}
    	}
		// second row match
		if (reel4 == reel5 && reel4 == reel6 && reel5 == reel6) {
			// change one of the reel icon and set to other icon
			// then based on pair position, set the bonus icon to replacement place.
			System.out.println("All second rows reel are matched.");
			return false;
		}else if(reel4 == reel5 || reel4 == reel6 || reel5 == reel6){
			matchedReelNumbers.add(lblReel4);
			matchedReelNumbers.add(lblReel5);
			matchedReelNumbers.add(lblReel6);
    		if(reel4 == reel5){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel4 and reel5 icons are match");
    			String reelImageIconDescription = images.get(reel4).getDescription();
    			lblReel9.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}else if(reel4 == reel6){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel4 and reel6 icons are match");
    			String reelImageIconDescription = images.get(reel4).getDescription();
    			lblReel2.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}if(reel5 == reel6){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel5 and reel6 icons are match");
    			String reelImageIconDescription = images.get(reel5).getDescription();
    			lblReel7.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}
    	}
		
		// Third row
		if (reel7 == reel8 && reel7 == reel9 && reel8 == reel9) {
			// change one of the reel icon and set to other icon
			// then based on pair position, set the bonus icon to replacement place.
			System.out.println("All first rows reel are matched.");
			return false;
//			lblReel6.setIcon(bonusIcon);
		}else if(reel7 == reel8 || reel7 == reel9 || reel8 == reel9){
			matchedReelNumbers.add(lblReel7);
			matchedReelNumbers.add(lblReel8);
			matchedReelNumbers.add(lblReel9);
    		if(reel7 == reel8){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel7 and reel8 icons are match");
    			String reelImageIconDescription = images.get(reel7).getDescription();
    			lblReel6.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}else if(reel7 == reel9){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel7 and reel9 icons are match");
    			String reelImageIconDescription = images.get(reel7).getDescription();
    			lblReel5.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}if(reel8 == reel9){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel8 and reel9 icons are match");
    			String reelImageIconDescription = images.get(reel8).getDescription();
    			lblReel4.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}
    	}
		return false;
    }
    
    /**
     *  Find column matches
     */
    public boolean findColumnwMatches(){
		if (reel1 == reel4 && reel1 == reel7 && reel4 == reel7) {
			// change one of the reel icon and set to other icon
			// then based on pair position, set the bonus icon to replacement place.
			System.out.println("All first columns reel are matched.");
			return false;
		}else if(reel1 == reel4 || reel1 == reel7 || reel4 == reel7){
			matchedReelNumbers.add(lblReel1);
			matchedReelNumbers.add(lblReel4);
			matchedReelNumbers.add(lblReel7);
    		if(reel1 == reel4){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel1 and reel4 icons are match");
    			String reelImageIconDescription = images.get(reel1).getDescription();
    			lblReel8.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}else if(reel1 == reel7){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel1 and reel7 icons are match");
    			String reelImageIconDescription = images.get(reel1).getDescription();
    			lblReel5.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}if(reel4 == reel7){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel4 and reel7 icons are match");
    			String reelImageIconDescription = images.get(reel4).getDescription();
    			lblReel4.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}
    	}
		// second row match
		if (reel2 == reel5 && reel2 == reel8 && reel5 == reel8) {
			// change one of the reel icon and set to other icon
			// then based on pair position, set the bonus icon to replacement place.
			System.out.println("All second columns reel are matched.");
		}else if(reel2 == reel5 || reel2 == reel8 || reel5 == reel8){
			matchedReelNumbers.add(lblReel2);
			matchedReelNumbers.add(lblReel5);
			matchedReelNumbers.add(lblReel8);
    		if(reel2 == reel5){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel2 and reel5 icons are match");
    			String reelImageIconDescription = images.get(reel2).getDescription();
    			lblReel7.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}else if(reel2 == reel8){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel2 and reel8 icons are match");
    			String reelImageIconDescription = images.get(reel2).getDescription();
    			lblReel6.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}if(reel5 == reel8){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel5 and reel8 icons are match");
    			String reelImageIconDescription = images.get(reel5).getDescription();
    			lblReel1.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}
    	}
		
		// Third row
		if (reel3 == reel6 && reel3 == reel9 && reel6 == reel9) {
			// change one of the reel icon and set to other icon
			// then based on pair position, set the bonus icon to replacement place.
			System.out.println("All first columns reel are matched.");
		}else if(reel3 == reel6 || reel3 == reel9 || reel6 == reel9){
			matchedReelNumbers.add(lblReel3);
			matchedReelNumbers.add(lblReel6);
			matchedReelNumbers.add(lblReel9);
    		if(reel3 == reel6){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel3 and reel6 icons are match");
    			String reelImageIconDescription = images.get(reel3).getDescription();
    			lblReel8.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}else if(reel3 == reel9){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel3 and reel9 icons are match");
    			String reelImageIconDescription = images.get(reel3).getDescription();
    			lblReel5.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}if(reel6 == reel9){
    			// Get the replacement place for this pair and set the bonus icon.
    			System.out.println("reel6 and reel9 icons are match");
    			String reelImageIconDescription = images.get(reel6).getDescription();
    			lblReel2.setIcon(ReelIconLabel.getScaledImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+reelImageIconDescription.split(",")[0]+"_half.png"), reelImageIconDescription)));
    			return true;
    		}
    	}
		return false;
    }
    
    /** Generates the 3 reel numbers. */
    public void genReelNumbers() {
		Random rand = new Random();
		reel1 = rand.nextInt(images.size());
		reel2 = rand.nextInt(images.size());
		reel3 = rand.nextInt(images.size());
		reel4 = rand.nextInt(images.size());
		reel5 = rand.nextInt(images.size());
		reel6 = rand.nextInt(images.size());
		reel7 = rand.nextInt(images.size());
		reel8 = rand.nextInt(images.size());
		reel9 = rand.nextInt(images.size());
		// Set the reel image
		setReelIcon(reel1, reel2, reel3, reel4, reel5, reel6, reel7, reel8, reel9); 
    }
     
    /** Sets the reels icon based on loaded image in images ArrayList. */
    public void setReelIcon(int ico1, int ico2, int ico3, int ico4, int ico5, int ico6, int ico7, int ico8, int ico9) {
    	
        lblReel1.setAnimatedReelIcons(ReelIconLabel.getScaledImageIcon(images.get(ico1)), images); // icon = the ArrayList index = random reel number
        lblReel2.setAnimatedReelIcons(ReelIconLabel.getScaledImageIcon(images.get(ico2)), images);
        lblReel3.setAnimatedReelIcons(ReelIconLabel.getScaledImageIcon(images.get(ico3)), images);
        lblReel4.setAnimatedReelIcons(ReelIconLabel.getScaledImageIcon(images.get(ico4)), images);
        lblReel5.setAnimatedReelIcons(ReelIconLabel.getScaledImageIcon(images.get(ico5)), images);
        lblReel6.setAnimatedReelIcons(ReelIconLabel.getScaledImageIcon(images.get(ico6)), images);
        lblReel7.setAnimatedReelIcons(ReelIconLabel.getScaledImageIcon(images.get(ico7)), images);
        lblReel8.setAnimatedReelIcons(ReelIconLabel.getScaledImageIcon(images.get(ico8)), images);
        lblReel9.setAnimatedReelIcons(ReelIconLabel.getScaledImageIcon(images.get(ico9)), images);
    }
    
    /** Performs action when Buy Credits button is clicked. */
    class BuyCreditsHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	if (funds >= creditBuyout) {
	            funds -= creditBuyout;
	            lblMoney.setText("Money: $"+df.format(funds));
	            credits += boughtCredits;
	            lblCredits.setText("Credits: "+credits);
	            lblStatus.setText("+"+boughtCredits+" credits purchased! -$"+df.format(creditBuyout));
	            } else {
	            lblStatus.setText("Insufficient $ to purchase credits!");
	        }
	        buyCreditsCheck();
        }
    }
    
    /** Performs action when Buy Credits button is clicked. */
    class PrintRecieptHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //print receipt function goes here..
        	PrinterJob pj = PrinterJob.getPrinterJob();
    		PrintReciept pr = new PrintReciept();
    		pr.setCredits(credits);
    		pj.setPrintable(pr, pr.getPageFormat(pj));
    		try {
    			pj.print();
    		} catch (PrinterException e) {
    			// TODO: handle exception
    			e.printStackTrace();
    		}
        }
    }
    
    /** Performs action when sound toggle button is clicked.
	    * NOT IMPLEMENTED
	    */
	    class SoundHandler implements ActionListener{
	        public void actionPerformed(ActionEvent e) {
	            if (tgglSound.isSelected() == false) {
	                tgglSound.setText("Sound ON");
	                lblStatus.setText("Sound effects have been ENABLED!");
	                audioClip.start();
	                audioClip.loop(Clip.LOOP_CONTINUOUSLY);
	                // allowed to play sounds
	                } else {
	                tgglSound.setText("Sound OFF");
	                lblStatus.setText("Sound effects have been DISABLED!");
	                audioClip.stop();
	                // disable sounds
	            }
	        }
	    }
	    
	    /** calculates prize to be awarded for win based on number of matches and cheats. */
	    public double getPrize(double prize) {
	        return prize / 10;
	    }
	    
	    /** Increments matchThree by 1 and returns value. */
	    public int matchThree() {
	        matchThree++;
	        return matchThree;
	    }
	     
	     
	    /** Increments lost by 1 and returns value. */
	    public int lose() {
	        lost++;
	        return lost;
	    }
	     
	    /** Increments win by 1, increases progress bar and returns value. */
	    public int win() {
	        win = matchThree + matchTwo;
//	        prgBarCheck(); // Increments the progress bar to unlock cheat menu.
	        return win;
	    }
	    
	    /** Checks for number matches and adjusts score depending on result. */
	    public void matchCheck() {
	    	if(!isMatchedCheck) {
		    	String[] matchedReel1DescriptionAndPoint = ((ImageIcon)matchedReelNumbers.get(0).getIcon()).getDescription().split(",");
		    	String[] matchedReel2DescriptionAndPoint = ((ImageIcon)matchedReelNumbers.get(1).getIcon()).getDescription().split(",");
		    	String[] matchedReel3DescriptionAndPoint = ((ImageIcon)matchedReelNumbers.get(2).getIcon()).getDescription().split(",");
		    	int matchedReelIconPoint = Integer.parseInt(matchedReel1DescriptionAndPoint[1]);
		    	if(matchedReel1DescriptionAndPoint[0].equalsIgnoreCase(matchedReel2DescriptionAndPoint[0]) && matchedReel1DescriptionAndPoint[0].equalsIgnoreCase(matchedReel3DescriptionAndPoint[0])){
		    		System.out.println("Congrates you won the jackpot of : " + (bet * matchedReelIconPoint));
		    		
		    		lblStatus.setText("You matched THREE symbols ("+matchedReel1DescriptionAndPoint[0]+")! +$"+df.format(getPrize(bet * matchedReelIconPoint))+"!");
		            lblMatchThree.setText("Matched Three: "+matchThree());
		            matchedReelNumbers.get(0).setBackground(new java.awt.Color(255, 0, 0)); // Highlights matched icons.
		            matchedReelNumbers.get(1).setBackground(new java.awt.Color(255, 0, 0));
		            matchedReelNumbers.get(2).setBackground(new java.awt.Color(255, 0, 0));
		            lblMoney.setText("Money: $"+df.format((funds += getPrize(bet * matchedReelIconPoint)))); // If there is a win add amount to cash pot.
		            lblWon.setText("Wins: "+win()); // increment win amount.
		    	} else {
		            lblStatus.setText("Sorry, you didn't match any symbols. -"+bet+" credits!");
		            lblLost.setText("Lost: "+lose());
		        }
		    	isMatchedCheck = true;
		        lblCredits.setText("Credits: "+(credits -= bet)); // deduct bet amount from available credits.
		    }
	    }
}

class BorderPanel extends JPanel {
	BorderPanel(String title) {
		setBorder(BorderFactory.createTitledBorder(title));
	}
}