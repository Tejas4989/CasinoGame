package org.poc.slotgame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

public class PrintReciept implements Printable {
	public static SimpleDateFormat SDF =  new SimpleDateFormat("MM/dd/yy hh:mm:ss a");
	private int credits;
	
	/**
	 * Getter of credits
	 * @return
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Setter of credits
	 * @param credits
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

	public PageFormat getPageFormat(PrinterJob pj)
	 {
	     
	     PageFormat pf = pj.defaultPage();
	     Paper paper = pf.getPaper();    

	     double middleHeight =15.0;  
	     double headerHeight = 2.0;                  
	     double footerHeight = 2.0;                  
	     double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
	     double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
	     paper.setSize(width, height);
	     paper.setImageableArea(                    
	         0,
	         10,
	         width,            
	         height - convert_CM_To_PPI(1)
	     );   //define boarder size    after that print area width is about 180 points
	             
	     pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
	     pf.setPaper(paper);    

	     return pf;
	 }
	 
	protected static double convert_CM_To_PPI(double cm) {
		return toPPI(cm * 0.393600787);
	}

	protected static double toPPI(double inch) {
		return inch * 72d;
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		int result = NO_SUCH_PAGE;
		
		if(pageIndex == 0){
			Graphics2D g2d = (Graphics2D)graphics;
			graphics.translate((int) (pageFormat.getImageableX()), (int) (pageFormat.getImageableY()));
			//Temp imageIcon
			ImageIcon printImage = new ImageIcon(new ImageIcon(getClass().getResource(SlotGameGUI.IMAGE_PATH+"sample_qr.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			System.out.println(printImage.getIconHeight() + " width :  "+ printImage.getIconWidth());
	        try{
	            /*Draw Header*/
	            int y=20;
	            int yShift = 10;
	            int headerRectHeight=15;
	                
	             g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
	            g2d.drawString("           	    DEMO!                ", 12, y);y+=yShift;
	            g2d.drawString("=====================================",12,y);y+=yShift;
	            g2d.drawString("           THANKS FOR PLAYING        ",12,y);y+=yShift;
	            g2d.drawString(" *** VALID ON SALE OF ISSUE ONLY! ***",12,y);y+=yShift;
	            g2d.drawString("=====================================",12,y);y+=headerRectHeight;
	            g2d.drawString("           	    DEMO!                ",12,y);y+=yShift;
	            g2d.drawString("  SKL402.50PEN                 BANK1 ",12,y);y+=yShift;
	            g2d.drawString("                                     ",12,y);y+=yShift;
	            g2d.drawString("=====================================",10,y);y+=yShift;
	            g2d.drawString("         !!!!!! DEMO !!!!!!          ",12,y);y+=yShift;
	            g2d.drawString("           	    DEMO!                ",12,y);y+=yShift;
	            g2d.drawString("         * TICKET IS VOID *          ",12,y);y+=yShift;
	            g2d.drawString("         !!!!!! DEMO !!!!!!          ",12,y);y+=yShift;
	            g2d.drawString("=====================================",10,y);y+=headerRectHeight;
	            g2d.drawString(" *** *** *** *** *** *** *** *** *** ",12,y);y+=yShift;
	            g2d.drawString(" *** *** *** *** *** *** *** *** *** ",12,y);y+=yShift;
	            g2d.drawString("           	    DEMO!                ",12,y);y+=yShift;
	            g2d.drawString(" *** *** *** *** *** *** *** *** *** ",12,y);y+=yShift;
	            g2d.drawString(" *** *** *** *** *** *** *** *** *** ",12,y);y+=yShift;
	            g2d.drawString("       "+SDF.format(new Date())+"    ",12,y);y+=yShift;
	            g2d.drawString("           	    DEMO!                ",12,y);y+=yShift;
	            g2d.drawString(" TICKET---------------------------#1 ",12,y);y+=yShift;
	            g2d.drawString("=====================================",10,y);y+=headerRectHeight;
	            g2d.drawString("              PRIZE VALUE            ",12,y);y+=yShift;
	            g2d.drawString("****************$"+getCredits()+"**************",12,y);y+=yShift;
	            g2d.drawImage(printImage.getImage(), 85, y, null);y+=60;
	            g2d.drawString("           	    DEMO!                ",12,y);y+=yShift;
	            g2d.drawString("=====================================",12,y);y+=yShift;
	            g2d.drawString("VOID IF MUTILATED.....VAL # 840799550",12,y);y+=yShift;
	            g2d.drawString("--------- 402.50216721:139658 -------",12,y);y+=yShift;
	            g2d.drawString("           	    DEMO!                ",12,y);y+=yShift;
	            g2d.drawString("  (c) 2009-2018 ALL RIGHTS RESERVED  ",12,y);y+=yShift;
	            g2d.drawString("=====================================",12,y);y+=yShift;

	    }
	    catch(Exception r){
//	    	r.printStackTrace();
	    	System.out.println("User Cancelled the receipt printing");
	    }

	              result = PAGE_EXISTS;    
		}
		return result;
	}

}
