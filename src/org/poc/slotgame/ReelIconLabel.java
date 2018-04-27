package org.poc.slotgame;

import java.awt.Color;
import java.awt.Image;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.TransferHandler;



public class ReelIconLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3673161273378158979L;
	private DropTarget dropTarget;
	private int acceptableActions = DnDConstants.ACTION_COPY;
//	private DTListener dtListener;
	private DragSource dragSource;
//	private DGListener dgListener;
//	public DSListener dsListner;
	
	public static ImageIcon getScaledImageIcon(ImageIcon icon){
		if(icon.getDescription().startsWith("Animated"))
			return icon;
		return new ImageIcon(icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH), icon.getDescription());
	}
	
	public ReelIconLabel(ImageIcon icon) {
		// TODO Auto-generated constructor stub
		this.setIcon(getScaledImageIcon(icon));
		this.setTransferHandler(new TransferHandler("icon"));
		
		// set the Drag source and related listeners
		/*this.dragSource = DragSource.getDefaultDragSource();
		this.dgListener = new DGListener();
		this.dsListner = new DSListener();
		// Component action listener
		this.dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY_OR_MOVE, this.dgListener);
		
		// set the dropTarget object
		this.dropTarget = new DropTarget(this, this.acceptableActions,this.dtListener,true);
		this.setDragSource(dragSource);
		this.setDropTarget(dropTarget);*/
	}
	
	public void setBg(Color bg){
		setBackground(bg);
	}
	
	/*public void showBorder(boolean flag){
		this.showBorder(flag);
		if(flag)
			this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	}
	
	public void setDropTarget(DropTarget dt){
		this.dropTarget = dt;
	}
	public DropTarget getDropTarget(){
		return this.dropTarget;
	}
	
	public DragSource getDragSource() {
		return dragSource;
	}

	public void setDragSource(DragSource dragSource) {
		this.dragSource = dragSource;
	}

	*//**
	 * Drag Target Listener
	 * @author tp250177
	 *
	 *//*
	class DTListener implements DropTargetListener{

		@Override
		public void dragEnter(DropTargetDragEvent e) {
		      showBorder(true);
		      e.acceptDrag(ReelIconLabel.this.acceptableActions);
		    }
		@Override
		public void dragOver(DropTargetDragEvent e) {
			e.acceptDrag(ReelIconLabel.this.acceptableActions);
		}

		@Override
		public void dropActionChanged(DropTargetDragEvent e) {
			e.acceptDrag(ReelIconLabel.this.acceptableActions);
		}

		public void dragExit(DropTargetEvent e) {
			showBorder(false);
		}

		@Override
		public void drop(DropTargetDropEvent dtde) {
			System.out.println("Source : " + dtde.getDropTargetContext().getComponent().getName());
			
		}
		
	}
	
	*//**
	 * Drag Gesture Listener
	 * @author tp250177
	 *
	 *//*
	class DGListener implements DragGestureListener{

		@Override
		public void dragGestureRecognized(DragGestureEvent dge) {
			// check to see if action is OK ...
		      try {
		    Transferable transferable =  new Transferable() {
		    	public final DataFlavor imageIconFlavor = DataFlavor.imageFlavor;
				
				@Override
				public boolean isDataFlavorSupported(DataFlavor flavor) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public DataFlavor[] getTransferDataFlavors() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
					// TODO Auto-generated method stub
					return null;
				}
			};
		    //initial cursor, transferable, dsource listener      
			dge.startDrag(DragSource.DefaultCopyNoDrop, transferable, ReelIconLabel.this.dsListner);
		    // or if dragSource is an instance variable:
		    // dragSource.startDrag(e, DragSource.DefaultCopyNoDrop, transferable, dsListener);
		      }catch( InvalidDnDOperationException idoe ) {
		    System.err.println( idoe );
		      }
			
		}
		
	}
	
	*//**
	 * Drag Source Listener
	 * @author tp250177
	 *
	 *//*
	class DSListener implements DragSourceListener{

		@Override
		public void dragEnter(DragSourceDragEvent dsde) {
			 DragSourceContext context = dsde.getDragSourceContext();
			 System.out.println(context.getComponent().getName());
		      //intersection of the users selected action, and the source and target actions
		      int myaction = dsde.getDropAction();
		      if( (myaction & DnDConstants.ACTION_COPY) != 0) { 
		    context.setCursor(DragSource.DefaultCopyDrop);    
		      } else {
		    context.setCursor(DragSource.DefaultCopyNoDrop);        
		      }
			
		}

		@Override
		public void dragOver(DragSourceDragEvent dsde) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dropActionChanged(DragSourceDragEvent dsde) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dragExit(DragSourceEvent dse) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dragDropEnd(DragSourceDropEvent dsde) {
			if ( dsde.getDropSuccess() == false ) {
			      return;
			    }
			    int dropAction = dsde.getDropAction();
			    if ( dropAction == DnDConstants.ACTION_MOVE ){
			    	System.out.println("drop ended successfully");
			    }
			
		}
		
	}*/
	
	/**
	 * Set the Animation to available Icons
	 * @param icon
	 * @param images
	 */
	public void setAnimatedReelIcons(ImageIcon icon, List<ImageIcon> icons){
		AnimatedIcon animated = new AnimatedIcon(this , 200, 5, icons);
		animated.start();
		this.setIcon(animated);
	}
	
	

}
