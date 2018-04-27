package org.poc.slotgame;

/*import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

*//**
 * @author tp250177
 *
 *//*
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
//		new test().testAudioAMR();
		PrinterJob pj = PrinterJob.getPrinterJob();
		PrintReciept pr = new PrintReciept();
		pj.setPrintable(pr, pr.getPageFormat(pj));
		
		try {
			pj.print();
		} catch (PrinterException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			pj.cancel();
		}
		
		SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd/yy hh:mm:ss a");
		System.out.println(sdf.format(new Date()));
		 JLabel label = new JLabel("Label Text");

		    final String propertyName = "text";
		    label.setTransferHandler(new TransferHandler(propertyName));

		    label.addMouseListener(new MouseAdapter() {
		      public void mousePressed(MouseEvent evt) {
		        JComponent comp = (JComponent) evt.getSource();
		        TransferHandler th = comp.getTransferHandler();

		        th.exportAsDrag(comp, evt, TransferHandler.COPY);
		      }
		    });
		    
		    JFrame f = new JFrame("Drag and Drop");
			f.getContentPane().add(label);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.pack();
			f.setLocationRelativeTo(null);
			f.setVisible(true);
	}

}
*/


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

/**
 * Make some buttons for a memory game.
 * @author John B. Matthews
 */
public class Buttons extends JPanel
    implements ActionListener, ItemListener {

    private static final int MAX = Game.Brainiac.max();
    private static final int RATE = 1000 / 8; // ~8 Hz
    private static final Random random = new Random();
    private static final DecimalFormat pf = new DecimalFormat("0%");
    private final Timer timer = new Timer(RATE, this);
    private final JPanel buttonPanel = new JPanel();
    private final JButton start = new JButton();
    private final JLabel status = new JLabel();
    private final JComboBox gameCombo = new JComboBox();
    private final JComboBox setCombo = new JComboBox();
    private final List<GameButton> buttons = new ArrayList<GameButton>(MAX);
    private final List<GameButton> selected = new ArrayList<GameButton>();
    private Game game = Game.Easy;
    private GlyphSet set = GlyphSet.Symbols;
    private int tries;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                if (System.getProperty("os.name").startsWith("Mac OS X")) {
                    System.setProperty("apple.awt.graphics.UseQuartz", "true");
                }
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new Buttons());
                f.setTitle("Memory Game");
                f.pack();
                f.setVisible(true);
            }
        });
    }

    /** Construct a memory game using buttons. */
    public Buttons() {
        this.setLayout(new BorderLayout());
        buttonPanel.setPreferredSize(new Dimension(800, 600));
        resetGame();
        this.add(buttonPanel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        start.setText("Start");
        start.addActionListener(this);
        panel.add(start);

        status.setText("Click Start for a new game.");
        panel.add(status);

        for (Game g : Game.values()) {
            gameCombo.addItem(g);
        }
        gameCombo.setActionCommand("Game");
        gameCombo.setSelectedItem(game);
        gameCombo.addActionListener(this);
        panel.add(gameCombo);

        for (GlyphSet g : GlyphSet.values()) {
            setCombo.addItem(g);
        }
        setCombo.setActionCommand("Set");
        setCombo.setSelectedItem(set);
        setCombo.addActionListener(this);
        panel.add(setCombo);

        this.add(panel, BorderLayout.SOUTH);
        timer.start();
    }

    /** Reset the game. */
    private void resetGame() {
        set.shuffle();
        buttons.clear();
        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(game.rows(), game.cols()));
        for (int index = 0; index < game.max() / 2; index++) {
            buttons.add(newButton(index));
            buttons.add(newButton(index));
        }
        Collections.shuffle(buttons, random);
        for (GameButton gb : buttons) {
            buttonPanel.add(gb);
        }
        buttonPanel.validate();
        tries = 0;
    }

    /** Convenience method for making buttons in pairs. */
    private GameButton newButton(int index) {
        GameButton gb = new GameButton(index, game, set);
        gb.addItemListener(this);
        gb.addActionListener(this);
        return gb;
    }

    /** Handle ActionEvents. */
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String cmd = e.getActionCommand();
        if ("Start".equals(cmd)) {
            timer.stop();
            resetGame();
            updateStatus();
        }
        if ("Game".equals(cmd)) {
            timer.stop();
            game = (Game) gameCombo.getSelectedItem();
            resetGame();
            timer.start();
        }
        if ("Set".equals(cmd)) {
            timer.stop();
            set = (GlyphSet) setCombo.getSelectedItem();
            resetGame();
            timer.start();
        }
        if (src == timer) {
            int index = random.nextInt(game.max());
            GameButton gb = buttons.get(index);
            gb.setSelected(!gb.isSelected());
        }
        if (!timer.isRunning() && src instanceof GameButton) {
            checkMatch((GameButton) src);
        }
    }

    /** Handle matches; preclude more than two tiles at once. */
    private void checkMatch(GameButton gb) {
        if (selected.size() == 0) {         // anything yet?
            selected.add(gb);
        } else if (selected.size() == 1) {  // one selected?
            GameButton s0 = selected.get(0);
            if (gb == s0) {                 // same one again?
                selected.remove(s0);
                tries--;
            } else if (gb.match(s0)) {      // matching glyphs?
                retirePair(gb, s0);
            } else {
                selected.add(gb);
            }
        } else if (selected.size() > 1) {   // more than one?
            if (selected.contains(gb)) {    // same one again?
                selected.remove(selected.indexOf(gb));
                tries--;
            } else {
                GameButton s0 = selected.get(0);
                GameButton s1 = selected.get(1);
                if (gb.match(s0)) {         // matching glyphs?
                    retirePair(gb, s0);
                } else if (gb.match(s1)) {  // matching glyphs?
                    retirePair(gb, s1);
                } else {
                    selected.add(gb);
                    selected.remove(0).setSelected(false);
                    selected.remove(0).setSelected(false);
                }
            }
        }
        tries++;
        updateStatus();
        checkWon();
    }

    /** Remove a and b from play. */
    private void retirePair(GameButton a, GameButton b) {
        a.setSelected(true);
        a.setEnabled(false);
        selected.remove(a);
        b.setSelected(true);
        b.setEnabled(false);
        selected.remove(b);
    }

    /** If game over, enable all tiles and resume animation. */
    private void checkWon() {
        if (score() == game.max()) {
            for (GameButton gb : buttons) {
                gb.setEnabled(true);
            }
            timer.start();
        }
    }

    /** Update status label. */
    private void updateStatus() {
        int score = score();
        double percent = tries == 0 ? 0 : (double) score / tries;
        StringBuilder sb = new StringBuilder();
        sb.append("Matched ");
        sb.append(score);
        sb.append(" of ");
        sb.append(game.max());
        sb.append(" in ");
        sb.append(tries);
        sb.append(" tries (");
        sb.append(pf.format(percent));
        sb.append(").");
        status.setText(sb.toString());
    }

    /** Calculate score by counting retired tiles. */
    private int score() {
        int count = 0;
        for (GameButton gb : buttons) {
            if (!gb.isEnabled()) {
                count++;
            }
        }
        return count;
    }

    /** Handle ItemEvents. */
    public void itemStateChanged(ItemEvent e) {
        GameButton gb = (GameButton) e.getItem();
        gb.setState();
    }
}

/**
 * GameButton extends JToggleButton for the tiles of a memory game.
 * The selected state indicates whether to show or hide a glyph.
 * The enabled state indicates that a pair has been matched.
 *  
 * @author John B. Matthews
 */
class GameButton extends JToggleButton {

    private static final Color[] colors = {
        Color.red, Color.green.darker(), Color.blue,
        Color.cyan.darker(), Color.magenta, Color.yellow,};
    private static final String hidden = "?";
    private final String text;
    private final Color color;

    /** Construct a game button with the given glyph index. */
    public GameButton(int index, Game game, GlyphSet set) {
        super();
        this.text = set.getGlyph(index);
        this.color = colors[index % colors.length];
        this.setFont(game.font());
        this.setEnabled(true);
        this.setText(hidden);
    }

    /** Update  the button's appearance to match its state. */
    public void setState() {
        if (this.isSelected() || !this.isEnabled()) {
            this.setForeground(color);
            this.setText(text);
            this.setToolTipText(this.toString());
        } else {
            this.setForeground(Color.black);
            this.setText(hidden);
            this.setToolTipText(null);
        }
    }

    /** Get this buton's glyph. */
    public String getGlyph() {
        return text;
    }

    /** Return true if this button's glyph matches gb. */
    public boolean match(GameButton gb) {
        return this.text.equals(gb.getGlyph());
    }

    @Override
    public String toString() {
        return "\\u" + Integer.toHexString(text.codePointAt(0));
    }
}

/**
 * Assemble named lists of related glyphs in a given font family.
 * @author John B. Matthews
 */
enum GlyphSet {

    ASCII(0x0021, 0x007E), Greek(0x0370, 0x03FF), Letters(0x2100, 0x214F),
    Operators(0x2200, 0x22FF), Miscellany(0x2300, 0x23FF), Borders(0x2500, 0x257F),
    Symbols(0x2600, 0x26FF), Dingbats(0x2700, 0x27BF), Arrows(0x2900, 0x297F);
    public static final String FAMILY = Font.SERIF;
    private static final Font font = new Font(FAMILY, Font.PLAIN, 12);
    private static final Random random = new Random();
    private final List<String> list = new ArrayList<String>(256);
    private final int first, last;

    /** Construct a list of displayable code points. */
    private GlyphSet(int first, int last) {
        this.first = first;
        this.last = last;
    }

    private void init() {
        for (int i = first; i <= last; i++) {
            if (font.canDisplay(i)) {
                StringBuilder sb = new StringBuilder();
                sb.appendCodePoint(i);
                list.add(sb.toString());
            }
        }
        shuffle();
    }

    /** Return a String containing the glyph at index i. */
    public String getGlyph(int index) {
        if (list.isEmpty()) {
            init(); // lazy

        }
        if (index < list.size()) {
            return list.get(index);
            
        } else {
            return "\uFFFD";
            
        }
    }

    /** Shuffle the list of glyphs. */
    public void shuffle() {
        Collections.shuffle(list, random);
    }
}

/**
 * Enumerate the features of a memory game.
 * @author John B. Matthews
 */
enum Game {

    Easy(3, 4, 112), Average(4, 5, 96), Hard(5, 6, 80), Brainiac(6, 7, 64);
    private int rows;
    private int cols;
    private Font font;
    private String name;

    /** Construct a game defined by rows, columns, typeface and name. */
    private Game(int rows, int cols, int size) {
        this.rows = rows;
        this.cols = cols;
        this.font = new Font(GlyphSet.FAMILY, Font.BOLD, size);
        this.name = this.name() + ": " + cols + "\u00d7" + rows;
    }

    /** Return the number of rows in this game. */
    public int rows() {
        return this.rows;
    }

    /** Return the number of columns in this game. */
    public int cols() {
        return this.cols;
    }

    /** Return the number tiles in this game. */
    public int max() {
        return this.rows * this.cols;
    }

    /** Return the typeface for this game. */
    public Font font() {
        return this.font;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
