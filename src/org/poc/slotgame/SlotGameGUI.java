package org.poc.slotgame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


public class SlotGameGUI {
	     
	    private JButton btnCash, btnSpin, btnPrintReceipt;
	    private JCheckBox cbAlwaysWin, cbSuperJackpot;
	    private JRadioButton rb1X, rb2X, rb3X, rb4X;
	    private JFrame frmFrame;
	    private JLabel lblCredits, lblLost, lblMatchThree, lblMatchTwo, lblMoney, lblReel1, lblReel2, lblReel3, lblReel4, lblReel5, lblReel6, lblReel7, lblReel8, lblReel9, lblStatus, lblWon;
	    private JPanel pnl1Reels, pnl2Reels, pnl3Reels, pnlReel1, pnlReel2, pnlReel3, pnlReel4, pnlReel5, pnlReel6, pnlReel7, pnlReel8, pnlReel9;
	    private JProgressBar prgbarCheatUnlocker;
	    private JSeparator sepCheats, sepStats, sepStats2, sepStatus;
	    private JToggleButton tgglSound;
	    private int credits = 100, boughtCredits = 100, bet = 15, matchThree, matchTwo, win, lost;
	    private double payout = 25.0, creditBuyout = 10.0, funds;
	    private int reel1 = 1, reel2 = 2, reel3 = 3, reel4 = 4, reel5 = 5, reel6 = 6, reel7 = 7, reel8 = 8, reel9 = 9; // starting values of the reels.
	    private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
	    private Map<String, String> replacementIconMap = new HashMap<String, String>();
	    private DecimalFormat df = new DecimalFormat("0.00");
	    private Clip audioClip = null;
	    private ImageIcon bonusIcon = new ImageIcon(getClass().getResource("/images/Trollface.png"));
	     
	    public SlotGameGUI(int credits, int boughtCredits, int bet, double payout, double creditBuyout, int reel1, int reel2, int reel3,int reel4, int reel5, int reel6,int reel7, int reel8, int reel9) {
	        this.credits=credits;
	        this.boughtCredits=boughtCredits;
	        this.bet=bet;
	        this.payout=payout;
	        this.creditBuyout=creditBuyout;
	        this.reel1=reel1;
	        this.reel2=reel2;
	        this.reel3=reel3;
	        this.reel4=reel4;
	        this.reel5=reel5;
	        this.reel6=reel6;
	        this.reel7=reel7;
	        this.reel8=reel8;
	        this.reel9=reel9;
	        createForm();
	        loadImages();
	        addFields();
	        addButtons();
	        layoutFrame();
	        layoutReels();
	        layoutOther();
	    }
	     
	    public SlotGameGUI() {
	        createForm();
	        loadImages();
	        loadReplacementIconMap();
	        addFields();
	        addButtons();
	        layoutFrame();
	        layoutReels();
	        layoutOther();
	        loadAudio();
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
	     
	    /** Creates the JFrame and Panels. */
	    private void createForm() {
	         
	        frmFrame = new JFrame();
	        frmFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        frmFrame.setTitle("Welcome To Casino");
	        frmFrame.setResizable(false);
	        frmFrame.setVisible(true);
	         
	        pnl1Reels = new JPanel();
	        pnl1Reels.setBorder(BorderFactory.createEtchedBorder());
	        
	        pnl2Reels = new JPanel();
	        pnl2Reels.setBorder(BorderFactory.createEtchedBorder());
	        
	        pnl3Reels = new JPanel();
	        pnl3Reels.setBorder(BorderFactory.createEtchedBorder());
	        
	         // first row
	        pnlReel1 = new JPanel();
	        pnlReel1.setBackground(new Color(255, 215, 0));
	        pnlReel1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
	        pnlReel2 = new JPanel();
	        pnlReel2.setBackground(new Color(255, 216, 0));
	        pnlReel2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
	        pnlReel3 = new JPanel();
	        pnlReel3.setBackground(new java.awt.Color(255, 215, 0));
	        pnlReel3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
	        
	        // Second Row
	        pnlReel4 = new JPanel();
	        pnlReel4.setBackground(new Color(255, 215, 0));
	        pnlReel4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
	        pnlReel5 = new JPanel();
	        pnlReel5.setBackground(new Color(255, 216, 0));
	        pnlReel5.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
	        pnlReel6 = new JPanel();
	        pnlReel6.setBackground(new java.awt.Color(255, 215, 0));
	        pnlReel6.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
	        
	        // third row
	        pnlReel7 = new JPanel();
	        pnlReel7.setBackground(new Color(255, 215, 0));
	        pnlReel7.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
	        pnlReel8 = new JPanel();
	        pnlReel8.setBackground(new Color(255, 216, 0));
	        pnlReel8.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
	        pnlReel9 = new JPanel();
	        pnlReel9.setBackground(new java.awt.Color(255, 215, 0));
	        pnlReel9.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
	         
	    }
	     
	    /** Adds labels to the form. */
	    private void addFields() {
	         
	        lblReel1 = new JLabel();
	        lblReel2 = new JLabel();
	        lblReel3 = new JLabel();
	        lblReel4 = new JLabel();
	        lblReel5 = new JLabel();
	        lblReel6 = new JLabel();
	        lblReel7 = new JLabel();
	        lblReel8 = new JLabel();
	        lblReel9 = new JLabel();
	         
	        sepStats = new JSeparator();
	        lblMatchTwo = new JLabel();
	        lblMatchTwo.setText("Matched Two: ");
	        lblMatchThree = new JLabel();
	        lblMatchThree.setText("Matched Three: ");
	        lblWon = new JLabel();
	        lblWon.setText("Won: ");
	         
	        sepStats2 = new JSeparator();
	        sepStats2.setOrientation(SwingConstants.VERTICAL);
	        lblCredits = new JLabel();
	        lblCredits.setText("Credits: "+credits);
	        lblMoney = new JLabel();
	        lblMoney.setText("Money: $"+df.format(funds));
	        lblLost = new JLabel();
	        lblLost.setText("Lost: ");
	         
	        sepStatus = new JSeparator();
	        lblStatus = new JLabel();
	        lblStatus.setBackground(new Color(255, 255, 255));
	        lblStatus.setFont(new Font("Arial", 1, 14));
	        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
	        lblStatus.setText("Welcome to Casnio InnovatiQ!!! �2018");
	         
	        sepCheats = new JSeparator();
	        prgbarCheatUnlocker = new JProgressBar();
	        prgbarCheatUnlocker.setToolTipText("Fill the bar to unlock the cheat menu.");
	         
	        lblReel1.setIcon(images.get(reel1));
	        lblReel2.setIcon(images.get(reel2));
	        lblReel3.setIcon(images.get(reel3));
	        lblReel4.setIcon(images.get(reel4));
	        lblReel5.setIcon(images.get(reel5));
	        lblReel6.setIcon(images.get(reel6));
	        lblReel7.setIcon(images.get(reel7));
	        lblReel8.setIcon(images.get(reel8));
	        lblReel9.setIcon(images.get(reel9));
	         
	    }
	     
	    /** Adds buttons to the form. */
	    private void addButtons() {
	         
	        btnSpin = new JButton();
	        btnSpin.setBackground(new Color(50, 255, 50));
	        btnSpin.setText("Spin");
	        btnSpin.setToolTipText("Click to spin the reels!");
	        btnSpin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	        btnSpin.setInheritsPopupMenu(true);
	        btnSpin.setMaximumSize(new Dimension(200, 50));
	        btnSpin.setMinimumSize(new Dimension(200, 50));
	        btnSpin.addActionListener(new SpinHandler());
	         
	        btnCash = new JButton();
	        btnCash.setBackground(new Color(255, 0, 0));
	        btnCash.setText("Buy Credits");
	        btnCash.setToolTipText("$"+df.format(bet)+" converts to "+boughtCredits+" credits.");
	        btnCash.setHorizontalTextPosition(SwingConstants.CENTER);
	        btnCash.addActionListener(new BuyCreditsHandler());
	        
	        btnPrintReceipt = new JButton();
	        btnPrintReceipt.setBackground(new Color(255,255,255));
	        btnPrintReceipt.setText("Cash Out");
	        btnPrintReceipt.setToolTipText("Click to print cash out reciept.");
	        btnPrintReceipt.setHorizontalTextPosition(SwingConstants.CENTER);
	        btnPrintReceipt.addActionListener(new PrintRecieptHandler());
	         
	        tgglSound = new JToggleButton();
	        tgglSound.setSelected(true);
	        tgglSound.setText("Sound OFF");
	        tgglSound.addActionListener(new SoundHandler());
	         
	        cbAlwaysWin = new JCheckBox();
	        cbAlwaysWin.setText("Always Win Mode");
	        cbAlwaysWin.setEnabled(false);
	        cbAlwaysWin.addActionListener(new AlwaysWinHandler());
	         
	        cbSuperJackpot = new JCheckBox();
	        cbSuperJackpot.setText("Super Jackpot");
	        cbSuperJackpot.setEnabled(false);
	        cbSuperJackpot.addActionListener(new SuperPrizeHandler());
	        
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
	         
	    }
	     
	    /** Lays out the frame. */
	    private void layoutFrame() {
	         
	        GroupLayout frameLayout = new GroupLayout(frmFrame.getContentPane());
	        frmFrame.getContentPane().setLayout(frameLayout);
	        frameLayout.setHorizontalGroup(
	        frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGap(0, 600, Short.MAX_VALUE)
	        );
	        frameLayout.setVerticalGroup(
	        frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGap(0, 500, Short.MAX_VALUE)
	        );
	    }
	     
	    /** Lays out the panels and reels. */
	    private void layoutReels() {
	         
	        GroupLayout pnlReelsLayout = new GroupLayout(pnl1Reels);
	        pnl1Reels.setLayout(pnlReelsLayout);
	        pnlReelsLayout.setHorizontalGroup(
	        pnlReelsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReelsLayout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(pnlReel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addGap(18, 18, 18)
	        .addComponent(pnlReel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addGap(18, 18, 18)
	        .addComponent(pnlReel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnlReelsLayout.setVerticalGroup(
	        pnlReelsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReelsLayout.createSequentialGroup()
	        .addContainerGap()
	        .addGroup(pnlReelsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
	        .addComponent(pnlReel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(pnlReel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(pnlReel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        
	        // Second row
	        GroupLayout pnl2ReelLayout = new GroupLayout(pnl2Reels);
	        pnl2Reels.setLayout(pnl2ReelLayout);
	        pnl2ReelLayout.setHorizontalGroup(
	        pnl2ReelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnl2ReelLayout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(pnlReel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addGap(18, 18, 18)
	        .addComponent(pnlReel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addGap(18, 18, 18)
	        .addComponent(pnlReel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnl2ReelLayout.setVerticalGroup(
	        pnl2ReelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnl2ReelLayout.createSequentialGroup()
	        .addContainerGap()
	        .addGroup(pnl2ReelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
	        .addComponent(pnlReel5, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(pnlReel4, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(pnlReel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	    	        
	        // Third row
	        GroupLayout pnl3ReelLayout = new GroupLayout(pnl3Reels);
	        pnl3Reels.setLayout(pnl3ReelLayout);
	        pnl3ReelLayout.setHorizontalGroup(
	        pnl3ReelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnl3ReelLayout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(pnlReel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addGap(18, 18, 18)
	        .addComponent(pnlReel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addGap(18, 18, 18)
	        .addComponent(pnlReel9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnl3ReelLayout.setVerticalGroup(
	        pnl3ReelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnl3ReelLayout.createSequentialGroup()
	        .addContainerGap()
	        .addGroup(pnl3ReelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
	        .addComponent(pnlReel8, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(pnlReel7, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(pnlReel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        
	        // For First row
	        GroupLayout pnlReel1Layout = new GroupLayout(pnlReel1);
	        pnlReel1.setLayout(pnlReel1Layout);
	        pnlReel1Layout.setHorizontalGroup(
	        pnlReel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel1Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel1)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnlReel1Layout.setVerticalGroup(
	        pnlReel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel1Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel1)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	         
	        GroupLayout pnlReel2Layout = new GroupLayout(pnlReel2);
	        pnlReel2.setLayout(pnlReel2Layout);
	        pnlReel2Layout.setHorizontalGroup(
	        pnlReel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel2Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel2)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnlReel2Layout.setVerticalGroup(
	        pnlReel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel2Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel2)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	         
	        GroupLayout pnlReel3Layout = new GroupLayout(pnlReel3);
	        pnlReel3.setLayout(pnlReel3Layout);
	        pnlReel3Layout.setHorizontalGroup(
	        pnlReel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel3Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel3)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnlReel3Layout.setVerticalGroup(
	        pnlReel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel3Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel3)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        
	        //For Second Row
	        GroupLayout pnlReel4Layout = new GroupLayout(pnlReel4);
	        pnlReel4.setLayout(pnlReel4Layout);
	        pnlReel4Layout.setHorizontalGroup(
	        pnlReel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel4Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel4)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnlReel4Layout.setVerticalGroup(
	        pnlReel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel4Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel4)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	         
	        GroupLayout pnlReel5Layout = new GroupLayout(pnlReel5);
	        pnlReel5.setLayout(pnlReel5Layout);
	        pnlReel5Layout.setHorizontalGroup(
	        pnlReel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel5Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel5)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnlReel5Layout.setVerticalGroup(
	        pnlReel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel5Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel5)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	         
	        GroupLayout pnlReel6Layout = new GroupLayout(pnlReel6);
	        pnlReel6.setLayout(pnlReel6Layout);
	        pnlReel6Layout.setHorizontalGroup(
	        pnlReel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel6Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel6)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnlReel6Layout.setVerticalGroup(
	        pnlReel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel6Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel6)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        
	        // For Third row
	        GroupLayout pnlReel7Layout = new GroupLayout(pnlReel7);
	        pnlReel7.setLayout(pnlReel7Layout);
	        pnlReel7Layout.setHorizontalGroup(
	        pnlReel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel7Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel7)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnlReel7Layout.setVerticalGroup(
	        pnlReel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel7Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel7)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	         
	        GroupLayout pnlReel8Layout = new GroupLayout(pnlReel8);
	        pnlReel8.setLayout(pnlReel8Layout);
	        pnlReel8Layout.setHorizontalGroup(
	        pnlReel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel8Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel8)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnlReel8Layout.setVerticalGroup(
	        pnlReel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel8Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel8)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	         
	        GroupLayout pnlReel9Layout = new GroupLayout(pnlReel9);
	        pnlReel9.setLayout(pnlReel9Layout);
	        pnlReel9Layout.setHorizontalGroup(
	        pnlReel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel9Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel9)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pnlReel9Layout.setVerticalGroup(
	        pnlReel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(pnlReel9Layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(lblReel9)
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	         
	    }
	     
	    /** lays out the remaining labels, check boxes, progress bars, etc. */
	    private void layoutOther() {
	         
	        GroupLayout layout = new GroupLayout(frmFrame.getContentPane());
	        frmFrame.getContentPane().setLayout(layout);
	         
	        layout.setHorizontalGroup(
	        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	        .addContainerGap()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	        .addComponent(sepCheats)
	        .addComponent(prgbarCheatUnlocker, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
	        .addGap(0, 0, Short.MAX_VALUE))
	        .addGroup(layout.createSequentialGroup()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	        .addGroup(layout.createSequentialGroup()
	        .addComponent(cbAlwaysWin)
	        .addGap(18, 18, 18)
	        .addComponent(cbSuperJackpot)
	        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(btnPrintReceipt,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(tgglSound))
	        .addGroup(layout.createSequentialGroup()
	        .addComponent(rb1X)
	        .addGap(18, 18, 18)
	        .addComponent(rb2X)
	        .addGap(18, 18, 18)
	        .addComponent(rb3X)
	        .addGap(18, 18, 18)
	        .addComponent(rb4X))
	        .addComponent(btnSpin, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(pnl1Reels, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(pnl2Reels, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(pnl3Reels, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(sepStats, GroupLayout.Alignment.TRAILING)
	        .addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addGroup(layout.createSequentialGroup()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
	        .addComponent(lblMatchTwo, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(lblWon, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(lblMatchThree, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
	        .addPreferredGap(ComponentPlacement.UNRELATED)
	        .addComponent(sepStats2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.UNRELATED)
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	        .addComponent(lblLost, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(lblCredits, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(lblMoney, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
	        .addGap(0, 0, Short.MAX_VALUE)))
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	        .addComponent(btnCash)
	        .addComponent(sepStatus, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)))
	        .addContainerGap())))
	        );
	         
	        layout.setVerticalGroup(
	        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(pnl1Reels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addComponent(pnl2Reels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addComponent(pnl3Reels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.RELATED)
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	        .addComponent(rb1X)
	        .addComponent(rb2X)
	        .addComponent(rb3X)
	        .addComponent(rb4X))
	        .addContainerGap()
	        .addComponent(btnSpin, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.UNRELATED)
	        .addComponent(sepStats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.UNRELATED)
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	        .addComponent(lblWon, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.RELATED)
	        .addComponent(lblMatchTwo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.RELATED)
	        .addComponent(lblMatchThree, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
	        .addComponent(sepStats2)
	        .addGroup(layout.createSequentialGroup()
	        .addComponent(lblLost, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.RELATED)
	        .addComponent(lblCredits, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.RELATED)
	        .addComponent(lblMoney, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        .addComponent(btnCash, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        .addPreferredGap(ComponentPlacement.UNRELATED)
	        .addComponent(sepStatus, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.UNRELATED)
	        .addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.UNRELATED)
	        .addComponent(sepCheats, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.RELATED)
	        .addComponent(prgbarCheatUnlocker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addPreferredGap(ComponentPlacement.UNRELATED)
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	        .addComponent(cbAlwaysWin)
	        .addComponent(cbSuperJackpot)
	        .addComponent(btnPrintReceipt)
	        .addComponent(tgglSound))
	        .addContainerGap())
	        );
	         
	        frmFrame.pack();
	         
	    }
	     
	    /** Performs action when Buy Credits button is clicked. */
	    class BuyCreditsHandler implements ActionListener {
	        public void actionPerformed(ActionEvent event) {
	            buyCredits();
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
	     
	    /** if the player has enough funds credits are added. */
	    public void buyCredits() {
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
	     
	    /** if user has enough funds to buy credits changes buttons colour to alert user. */
	    public void buyCreditsCheck() {
	        if (funds < bet) {
	            btnCash.setBackground(new java.awt.Color(255, 0, 0));
	            } else {
	            btnCash.setBackground(new java.awt.Color(50, 255, 50));
	        }
	    }
	     
	    /** Performs action when Spin button is clicked. */
	    class SpinHandler implements ActionListener {
	        public void actionPerformed(ActionEvent event) {
	            if (funds < creditBuyout && credits < bet) {
	                lblStatus.setText("<html><a href='http://www.innovatiqtechnology.com/'>InnovatiQ</a></html>");
	                } else if ((credits - bet) >= 0) {
	                pnlReel1.setBackground(new java.awt.Color(255, 215, 0));
	                pnlReel2.setBackground(new java.awt.Color(255, 215, 0));
	                pnlReel3.setBackground(new java.awt.Color(255, 215, 0));
	                pnlReel4.setBackground(new java.awt.Color(255, 215, 0));
	                pnlReel5.setBackground(new java.awt.Color(255, 215, 0));
	                pnlReel6.setBackground(new java.awt.Color(255, 215, 0));
	                pnlReel7.setBackground(new java.awt.Color(255, 215, 0));
	                pnlReel8.setBackground(new java.awt.Color(255, 215, 0));
	                pnlReel9.setBackground(new java.awt.Color(255, 215, 0));
	                genReelNumbers();
	                matchCheck();
	                findRowMatches();
	                findColumnwMatches();
	                } else {
	                lblStatus.setText("Bet is "+bet+" credits, purchase more with $!");
	            }
	            buyCreditsCheck();
	        }
	    }
	     
	    /** Generates the 3 reel numbers. */
	    public void genReelNumbers() {
	        Random rand = new Random();
	        if (cbAlwaysWin.isSelected() == true) { // If the Always win cheat mode is enabled.
	            int winType = rand.nextInt(4); // generates number between 0-3 to determine the type of win
	            reel1 = rand.nextInt(images.size());
	            if (winType == 0) { // winType = 0 - Reels 1, 2 and 3 will all match.
	                reel2 = reel1;
	                reel3 = reel1;
	                } else if (winType == 1) { // winType = 1 - Reels 1 and 2 will match.
	                reel2 = reel1;
	                } else if (winType == 2) { // winType = 2 - Reels 1 and 3 will match.
	                reel3 = reel1;
	                } else {    // winType = 3 - Reels 2 and 3 will match.
	                if (reel1 >= 0 ) {
	                    reel2 = reel1 + 1;
	                    reel3 = reel1 + 1;
	                    } if (reel1 == images.size()-1) {
	                    reel2 = reel1 - 1;
	                    reel3 = reel1 - 1;
	                }
	            }
	            } else { // If the Always win cheat mode is disabled play a normal game.
	            reel1 = rand.nextInt(images.size());
	            reel2 = rand.nextInt(images.size());
	            reel3 = rand.nextInt(images.size());
	            reel4 = rand.nextInt(images.size());
	            reel5 = rand.nextInt(images.size());
	            reel6 = rand.nextInt(images.size());
	            reel7 = rand.nextInt(images.size());
	            reel8 = rand.nextInt(images.size());
	            reel9 = rand.nextInt(images.size());
	        }
	        setReelIcon(reel1, reel2, reel3, reel4, reel5, reel6, reel7, reel8, reel9); // Set the reel image
	    }
	     
	    /** Sets the reels icon based on loaded image in images ArrayList. */
	    public void setReelIcon(int ico1, int ico2, int ico3, int ico4, int ico5, int ico6, int ico7, int ico8, int ico9) {
	        lblReel1.setIcon(images.get(ico1)); // icon = the ArrayList index = random reel number
	        lblReel2.setIcon(images.get(ico2));
	        lblReel3.setIcon(images.get(ico3));
	        lblReel4.setIcon(images.get(ico4));
	        lblReel5.setIcon(images.get(ico5));
	        lblReel6.setIcon(images.get(ico6));
	        lblReel7.setIcon(images.get(ico7));
	        lblReel8.setIcon(images.get(ico8));
	        lblReel9.setIcon(images.get(ico9));
	    }
	     
	    /** Checks for number matches and adjusts score depending on result. */
	    public void matchCheck() {
	        if (reel1 == reel2 && reel2 == reel3) {
	            lblStatus.setText("You matched THREE symbols ("+images.get(reel1).getDescription().split(",")[0]+")! +$"+df.format(getPrize(payout))+"!");
	            lblMatchThree.setText("Matched Three: "+matchThree());
	            pnlReel1.setBackground(new java.awt.Color(255, 0, 0)); // Highlights matched icons.
	            pnlReel2.setBackground(new java.awt.Color(255, 0, 0));
	            pnlReel3.setBackground(new java.awt.Color(255, 0, 0));
	         } else if (reel1 == reel2 || reel1 == reel3) {
	            lblStatus.setText("You matched TWO symbols ("+images.get(reel1).getDescription().split(",")[0]+")! +$"+df.format(getPrize(payout))+"!");
	            lblMatchTwo.setText("Matched Two: "+matchTwo());
	            if (reel1 == reel2) {
	                pnlReel1.setBackground(new java.awt.Color(255, 0, 0)); // Highlights matched icons.
	                pnlReel2.setBackground(new java.awt.Color(255, 0, 0));
	                } else if (reel1 == reel3){
	                pnlReel1.setBackground(new java.awt.Color(255, 0, 0)); // Highlights matched icons.
	                pnlReel3.setBackground(new java.awt.Color(255, 0, 0));
	            }
	         } else if (reel2 == reel3) {
	            lblStatus.setText("You matched TWO symbols ("+images.get(reel2).getDescription().split(",")[0]+")! +$"+df.format(getPrize(payout))+"!");
	            lblMatchTwo.setText("Matched Two: "+matchTwo());
	            pnlReel2.setBackground(new java.awt.Color(255, 0, 0)); // Highlights matched icons.
	            pnlReel3.setBackground(new java.awt.Color(255, 0, 0));
	         } else {
	            lblStatus.setText("Sorry, you didn't match any symbols. -"+bet+" credits!");
	            lblLost.setText("Lost: "+lose());
	        }
	        lblCredits.setText("Credits: "+(credits -= bet)); // deduct bet amount from available credits.
	        lblMoney.setText("Money: $"+df.format((funds += getPrize(payout)))); // If there is a win add amount to cash pot.
	        lblWon.setText("Wins: "+win()); // increment win amount.
	    }
	    
	    /**
	     *  Find row matches
	     */
	    public void findRowMatches(){
			if (reel1 == reel2 && reel1 == reel3 && reel2 == reel3) {
				// change one of the reel icon and set to other icon
				// then based on pair position, set the bonus icon to replacement place.
				System.out.println("All first rows reel are matched.");
			}else if(reel1 == reel2 || reel1 == reel3 || reel2 == reel3){
	    		if(reel1 == reel2){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel1 and reel2 icons are match");
	    			lblReel6.setIcon(bonusIcon);
	    		}else if(reel1 == reel3){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel1 and reel3 icons are match");
	    			lblReel5.setIcon(bonusIcon);
	    		}if(reel2 == reel3){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel2 and reel3 icons are match");
	    			lblReel4.setIcon(bonusIcon);
	    		}
	    	}
			// second row match
			if (reel4 == reel5 && reel4 == reel6 && reel5 == reel6) {
				// change one of the reel icon and set to other icon
				// then based on pair position, set the bonus icon to replacement place.
				System.out.println("All second rows reel are matched.");
			}else if(reel4 == reel5 || reel4 == reel6 || reel5 == reel6){
	    		if(reel4 == reel5){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel4 and reel5 icons are match");
	    			lblReel9.setIcon(bonusIcon);
	    		}else if(reel4 == reel6){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel4 and reel6 icons are match");
	    			lblReel2.setIcon(bonusIcon);
	    		}if(reel5 == reel6){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel5 and reel6 icons are match");
	    			lblReel7.setIcon(bonusIcon);
	    		}
	    	}
			
			// Third row
			if (reel7 == reel8 && reel7 == reel9 && reel8 == reel9) {
				// change one of the reel icon and set to other icon
				// then based on pair position, set the bonus icon to replacement place.
				System.out.println("All first rows reel are matched.");
//				lblReel6.setIcon(bonusIcon);
			}else if(reel7 == reel8 || reel7 == reel9 || reel8 == reel9){
	    		if(reel7 == reel8){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel7 and reel8 icons are match");
	    			lblReel6.setIcon(bonusIcon);
	    		}else if(reel7 == reel9){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel7 and reel9 icons are match");
	    			lblReel5.setIcon(bonusIcon);
	    		}if(reel8 == reel9){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel8 and reel9 icons are match");
	    			lblReel4.setIcon(bonusIcon);
	    		}
	    	}
	    }
	    
	    /**
	     *  Find column matches
	     */
	    public void findColumnwMatches(){
			if (reel1 == reel4 && reel1 == reel7 && reel4 == reel7) {
				// change one of the reel icon and set to other icon
				// then based on pair position, set the bonus icon to replacement place.
				System.out.println("All first columns reel are matched.");
			}else if(reel1 == reel4 || reel1 == reel7 || reel4 == reel7){
	    		if(reel1 == reel4){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel1 and reel4 icons are match");
	    			lblReel8.setIcon(bonusIcon);
	    		}else if(reel1 == reel7){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel1 and reel7 icons are match");
	    			lblReel5.setIcon(bonusIcon);
	    		}if(reel4 == reel7){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel4 and reel7 icons are match");
	    			lblReel2.setIcon(bonusIcon);
	    		}
	    	}
			// second row match
			if (reel2 == reel5 && reel2 == reel8 && reel5 == reel8) {
				// change one of the reel icon and set to other icon
				// then based on pair position, set the bonus icon to replacement place.
				System.out.println("All second columns reel are matched.");
			}else if(reel2 == reel5 || reel2 == reel8 || reel5 == reel8){
	    		if(reel2 == reel5){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel2 and reel5 icons are match");
	    			lblReel7.setIcon(bonusIcon);
	    		}else if(reel2 == reel8){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel2 and reel8 icons are match");
	    			lblReel6.setIcon(bonusIcon);
	    		}if(reel5 == reel8){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel5 and reel8 icons are match");
	    			lblReel1.setIcon(bonusIcon);
	    		}
	    	}
			
			// Third row
			if (reel3 == reel6 && reel3 == reel9 && reel6 == reel9) {
				// change one of the reel icon and set to other icon
				// then based on pair position, set the bonus icon to replacement place.
				System.out.println("All first columns reel are matched.");
			}else if(reel3 == reel6 || reel3 == reel9 || reel6 == reel9){
	    		if(reel3 == reel6){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel3 and reel6 icons are match");
	    			lblReel8.setIcon(bonusIcon);
	    		}else if(reel3 == reel9){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel3 and reel9 icons are match");
	    			lblReel5.setIcon(bonusIcon);
	    		}if(reel6 == reel9){
	    			// Get the replacement place for this pair and set the bonus icon.
	    			System.out.println("reel6 and reel9 icons are match");
	    			lblReel2.setIcon(bonusIcon);
	    		}
	    	}
	    }
	     
	    /** sets progress bar equal to the current win count. if bar is full it unlocks cheat menu */
	    public void prgBarCheck() {
	        if (prgbarCheatUnlocker.getValue() <= 99) {
	            prgbarCheatUnlocker.setValue(win);
	            } else if (prgbarCheatUnlocker.getValue() == 100) { // after 100 wins unlock the cheats.
	            prgbarCheatUnlocker.setValue(100);
	            lblStatus.setText("100 wins! Congratulations you've unlocked the cheat menu!");
	            cbSuperJackpot.setEnabled(true);
	            cbAlwaysWin.setEnabled(true);
	        }
	    }
	     
	    /** calculates prize to be awarded for win based on number of matches and cheats. */
	    public double getPrize(double prize) {
	        if (reel1 == reel2 && reel2 == reel3) {
	            if (cbSuperJackpot.isSelected() == true) {
	                prize *= 100; // if cheating and all are matched return the full pay out x100.
	                } else {
	                prize = payout; // if all are matched return the full pay out.
	            }
	            } else if (reel1 == reel2 || reel1 == reel3 || reel2 == reel3) {
	            if (cbSuperJackpot.isSelected() == true) {
	                prize *= 50; // if cheating and two are matched return the pay out x50.
	                } else {
	                prize = payout / 5; // if two are matched return 1/5th of the pay out.
	            }
	            } else {
	            prize = 0; // If no win return no prize.
	        }
	        return prize;
	    }
	     
	    /** Performs action when Super Jack pot check box is clicked. */
	    class SuperPrizeHandler implements ActionListener{
	        public void actionPerformed(ActionEvent e) {
	            if (cbSuperJackpot.isSelected() == true) {
	                lblStatus.setText("Super Prize mode ENABLED! The $ won is now x100!");
	            }
	            if (cbSuperJackpot.isSelected() == false) {
	                lblStatus.setText("Super Prize mode DISABLED! :'(");
	            }
	        }
	    }
	    
	    /** Performs action when bet check box is clicked. */
	    class BetHandler implements ActionListener{
	        public void actionPerformed(ActionEvent e) {
	        	JRadioButton rb = (JRadioButton)e.getSource();
	            if(rb == rb1X){
	            	System.out.println("bet 1 is applied");
	            }else if(rb == rb2X){
	            	System.out.println("Bet 2 is applied");
	            }else if(rb == rb3X){
	            	System.out.println("Bet 3 is applied");
	            }else {
	            	System.out.println("Bet 4 is applied");
	            }
	        }
	    }
	     
	    /** Performs action when Troll face check box is clicked. */
	    class AlwaysWinHandler implements ActionListener{
	        public void actionPerformed(ActionEvent e) {
	            if (cbAlwaysWin.isSelected() == true) {
	                lblStatus.setText("Always Win mode ENABLED! 7-7-7's here we come!");
	            }
	            if (cbAlwaysWin.isSelected() == false) {
	                lblStatus.setText("Always Win mode DISABLED! :'(");
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
	    
	    /** Loads replacementIcon place into the icon replacementMap.
		    */
		    public void loadReplacementIconMap() {
		        replacementIconMap.put("1-2", "6");
		        replacementIconMap.put("2-3", "4");
		        replacementIconMap.put("1-3", "5");
		        replacementIconMap.put("1-4", "8");
		        replacementIconMap.put("1-7", "5");
		        replacementIconMap.put("4-7", "2");
		        
		        replacementIconMap.put("4-5", "9");
		        replacementIconMap.put("4-6", "8");
		        replacementIconMap.put("5-6", "7");
		        replacementIconMap.put("2-5", "6");
		        replacementIconMap.put("2-8", "7");
		        replacementIconMap.put("5-8", "3");
		        
		        replacementIconMap.put("7-8", "6");
		        replacementIconMap.put("7-9", "5");
		        replacementIconMap.put("8-9", "4");
		        replacementIconMap.put("3-6", "8");
		        replacementIconMap.put("3-9", "5");
		        replacementIconMap.put("6-9", "2");
		    }
	     
	    /** Loads ImageIcons into the images ArrayList.
	    *    The difficulty is determined by the number of images present in the ArrayList:
	    *    �    Add images here to make game more difficult.
	    *    �    Remove images here to make game easier.
	    */
	    public void loadImages() {
	        images.add(createImageIcon("/images/Banana.png", "Banana,10"));
	        images.add(createImageIcon("/images/Bar.png", "Bar,20"));
	        images.add(createImageIcon("/images/Bell.png", "Bell,30"));
	        images.add(createImageIcon("/images/Cherry.png", "Cherry,40"));
	        images.add(createImageIcon("/images/Clover.png", "Clover,50"));
	        images.add(createImageIcon("/images/Diamond.png", "Diamond,60"));
	        images.add(createImageIcon("/images/Plum.png", "Plum,70"));
	        images.add(createImageIcon("/images/Seven.png", "Seven,80"));
	        images.add(createImageIcon("/images/Watermelon.png", "Watermelon,90"));
	        images.add(createImageIcon("/images/animated.gif", "Animated,100"));
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
	     
	    /** Increments matchThree by 1 and returns value. */
	    public int matchThree() {
	        matchThree++;
	        return matchThree;
	    }
	     
	    /** Increments matchTwo by 1 and returns value. */
	    public int matchTwo() {
	        matchTwo++;
	        return matchTwo;
	    }
	     
	    /** Increments lost by 1 and returns value. */
	    public int lose() {
	        lost++;
	        return lost;
	    }
	     
	    /** Increments win by 1, increases progress bar and returns value. */
	    public int win() {
	        win = matchThree + matchTwo;
	        prgBarCheck(); // Increments the progress bar to unlock cheat menu.
	        return win;
	    }
	     
	    public static void main(String args[]) {
	         
	        try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	            } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(SlotGameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	            } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(SlotGameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	            } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(SlotGameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(SlotGameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
	         
	        java.awt.EventQueue.invokeLater(new Runnable() {
	             
	            public void run() {
	                new SlotGameGUI();
	            }
	        });
	         
	    }
	     
}
