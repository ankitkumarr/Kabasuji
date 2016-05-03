package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.builder.controller.DragNumberFromBoardToNumberBarMove;
import com.halaesus.kabasuji.builder.controller.DragNumberToBoardMove;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseBoard;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

@SuppressWarnings("serial")
public class ReleaseBuilderView extends AbstractBuilderView {
	
    JLabel[][] numberBarNumbers;
    ReleaseLevel level;
    ReleaseNumber rNumbers[][];
    
	private JLabel draggingLabel;
	private ArrayList<JLabel> numbersOnTheBoard;
    
    public ReleaseBuilderView(Application application, AbstractLevel aLevel, int levelIndex) {
        
    	super(application, aLevel, levelIndex);
    	this.level = (ReleaseLevel) aLevel;
    	
    	DragNumberToBoardMove theMove = new DragNumberToBoardMove(this.level, this);
    	addMouseListener(theMove);
    	addMouseMotionListener(theMove);
    	
    	DragNumberFromBoardToNumberBarMove NfromBtoNB = new DragNumberFromBoardToNumberBarMove(this, this.level);
    	addMouseListener(NfromBtoNB);
    	addMouseMotionListener(NfromBtoNB);

    	draggingLabel = new JLabel("");
    	draggingLabel.setVisible(false);
    	add(draggingLabel);
    	
    	numbersOnTheBoard = new ArrayList<JLabel>();
    	
    	setupReleasePalette();
    	
    }
    
    public void setupReleasePalette() {
    	Font releaseNumberFont = new Font("releaseNumberFont", Font.BOLD, 35);
    	rNumbers = this.level.getNumberBar().getNumbers();
    	numberBarNumbers = new JLabel[3][6];
    	//rNumbers = new ReleaseNumber[3][6];
    	
    	for (int j = 1; j <= 3; j++) {
			for (int i = 1; i <= 6; i++) {
				//ReleaseNumber rn = new ReleaseNumber(i, j, 0 , 0);
				ReleaseNumber rn = rNumbers[j-1][i-1];
				//rNumbers[j-1][i-1] = rn; //956
				JLabel n = new JLabel(Integer.toString(rn.getValue()));
				n.setHorizontalAlignment(SwingConstants.CENTER);
				n.setBounds(960 + (53*(i-1)) , 80 + (53*(j-1)) , 53, 53);
				
				if (rn.getColor() == 1)n.setForeground(Color.RED);
				if (rn.getColor() == 2)n.setForeground(Color.YELLOW);
				if (rn.getColor() == 3)n.setForeground(Color.CYAN);
				n.setFont(releaseNumberFont);
				n.setVisible(true);
				numberBarNumbers[j-1][i-1] = n;
				add(numberBarNumbers[j-1][i-1]);
			}
		}    	
    }
    
    public Rectangle getReleaseNumberRectangle(int row, int col) {
    	return new Rectangle(960 + (53*(col-1)) , 80 + (53*(row-1)) , 53, 53);
    }
    
    private void drawDraggingNumber(Graphics g) {
		assert( this.level.isDraggingActive() == true ); // This function can only be called if there is a piece being dragged
		ReleaseNumber numberToBeDrawn = this.level.getnumberBeingDragged();
		Font releaseNumberFont = new Font("releaseNumberFont", Font.BOLD, 35);
		
		//TODO: might change this getdrag function
		Point topPointToDraw = this.level.getTopPointOfDraggingPiece();
		
		remove(draggingLabel);
		
		draggingLabel = new JLabel(Integer.toString(numberToBeDrawn.getValue()));
		draggingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		draggingLabel.setBounds(topPointToDraw.x , topPointToDraw.y , 53, 53);
		if (numberToBeDrawn.getColor() == 1) draggingLabel.setForeground(Color.RED);
		if (numberToBeDrawn.getColor() == 2) draggingLabel.setForeground(Color.YELLOW);
		if (numberToBeDrawn.getColor() == 3) draggingLabel.setForeground(Color.CYAN);
		draggingLabel.setFont(releaseNumberFont);
		draggingLabel.setVisible(true);
		
		add(draggingLabel);
	}
    
 
   
    protected void paintComponent(Graphics g) {
		
		if (this.level.isDraggingActive() && this.level.getnumberBeingDragged()!=null)
			this.drawDraggingNumber(g);
		else
			this.remove(this.draggingLabel); 
		
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, null);
		

		// Set up the game board
		setupGameBoard(g);
		// Draw all the board pieces
		setupBoardPieces(g);
		drawWorkspacePiece(g);
		setupNumberLabels(g);
		
	}

    private void setupNumberLabels(Graphics g) {
		Font releaseNumberFont = new Font("releaseNumberFont", Font.BOLD, 35);
		ReleaseBoard rb = (ReleaseBoard) this.level.getBoard();
		Set<ReleaseNumber> rNumbers = rb.getReleaseNumbers();
		
			// Remove them all
			for( JLabel theLabel : numbersOnTheBoard )
				remove(theLabel);
			// Now add them all again
			for (ReleaseNumber num: rNumbers){
				JLabel n = new JLabel(Integer.toString(num.getValue()));
				n.setHorizontalAlignment(SwingConstants.CENTER);
				n.setBounds(320 + 53 * num.getCol(), 80 + 53 * num.getRow(), 53, 53);
				if (num.getColor() == 1)n.setForeground(Color.RED);
				if (num.getColor() == 2)n.setForeground(Color.YELLOW);
				if (num.getColor() == 3)n.setForeground(Color.CYAN);
				n.setFont(releaseNumberFont);
				// Add it to the GUI
				add(n);
				// Add it to the List
				numbersOnTheBoard.add(n);
			}
	}
    
    public Rectangle getReleaseNumberBarBounds() {
    	return new Rectangle(960, 80, 53*6, 53*6);
    }
    
   
}
