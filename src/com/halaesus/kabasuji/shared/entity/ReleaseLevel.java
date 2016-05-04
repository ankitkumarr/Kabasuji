package com.halaesus.kabasuji.shared.entity;

import java.util.Iterator;

import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;
import com.halaesus.kabasuji.shared.memento.ReleaseLevelMemento;

/**
 * Represents a Release Level
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class ReleaseLevel extends AbstractLevel {
	
	/** Constant signifying that the source of the drag is the ReleaseLevel NumberBar */
	public static final int DRAG_SOURCE_NUMBERBAR = 5;
	
	/** The ReleaseNumber that is being dragged */
	ReleaseNumber numberBeingDragged;
	
	/** Boolean to keep track if the ReleaseNumber being dragged is over the NumberBar */
	boolean numberOverNumberbar;
	
	/** NumberBar that is a part of the <code>ReleaseLevel</code> */
	NumberBar numberBar;
	
	/**
	 * Constructs a <code>ReleaseLevel</code> with a given <code>ReleaseLevelMemento</code
	 * @param memento
	 */
	public ReleaseLevel(ReleaseLevelMemento memento) {
		super(memento);
		// Set the game type in here
		levelType = "Release";
		numberBar = new NumberBar();
		numberBeingDragged = null;
	}

	/**
	 * Public Copy Constructor for a <code>ReleaseLevel</code> which instantiates another <code>ReleaseLevel</code>
	 * with the same attributes as the original.
	 * <p>
	 * @param anotherLevel  the <code>ReleaseLevel</code> to be copied over
	 */
	public ReleaseLevel(ReleaseLevel anotherLevel) {
		super(anotherLevel); // Fill out Super Fields
		// Get the NumberBar
		this.numberBar = anotherLevel.numberBar;
		numberBeingDragged = null;
	}
	
	/**
	 * Returns the NumberBar of the <code>ReleaseLevel</code>
	 * @return
	 */
    public NumberBar getNumberBar(){
    	return this.numberBar;
	}
    
    /**
     * Sets if the ReleaseNumber being dragged is over the NumberBar
     * @param val
     */
    public void setNumberOverNumberBar(boolean val) {
    	this.numberOverNumberbar = val;
    }
    
    /**
     * Gets if the ReleaseNumber being dragged is over the NumberBar
     * @return
     */
    public boolean getNumberOverNumberBar() {
    	return this.numberOverNumberbar;
    }
	
    /**
     * Returns the ReleaseNumber that is being dragged over the <code>ReleaseLevel</code>
     * @return
     */
	public ReleaseNumber getnumberBeingDragged(){
	    return this.numberBeingDragged;
	}
	   
	/**
	 * Sets if a ReleaseNumber is being dragged on <code>ReleaseLevel</code>
	 * @return
	 */
	public boolean isNumberDraggingActive() {
	    return (this.numberBeingDragged!=null);
	}
	
	/**
	 * Sets the ReleaseNumber that is being dragged over the <code>ReleaseLevel</code>
	 * @param beingDragged
	 */
	public void setnumberBeingDragged(ReleaseNumber beingDragged){
	    this.numberBeingDragged = beingDragged;
	}
        
    // Abstract Methods Filler

    /**
     * Returns the number of stars achieved by the Player on the <code>ReleaseLevel</code>
     * @return 3 if the user has won the <code>ReleaseLevel</code>
     */
    @Override
    public int getStarsAchieved() {
    	int setsCompleted = this.numberBar.setsFound();
    	// Make necessary changes if the user has achieved 3 stars
    	if( (setsCompleted == 3) && !this.levelCompletedShown ) {
			this.levelCompletedShown = true; // Level has been completed
			this.levelCompletionStatus = AbstractLevel.LEVEL_COMPLETION_FINISHED_LEVEL; // The user finished the level
		}
    	// Now, return
    	return setsCompleted; // The number of sets completed is equal to the number of sets found
    }
    
    /**
	 * This function is called when a Piece is dropped onto the PuzzleBoard of the <code>ReleaseLevel</code>.
	 * <p>
	 * The function for the <code>ReleaseLevel</code> adds any released numbers to the NumberBar and releases them from the
	 * <code>ReleaseBoard</code>.
	 * <p>
	 * @param p  the Piece that was added to the Board of the <code>ReleaseLevel</code>
	 */
	@Override
	public void newPieceDropped(Piece p) {
		// Once a Piece is dropped, update the NumberBar and hence the NumberBarView
    	for(int r = 0; r < 12; r++) {
    		for(int c = 0; c < 12; c++) {
    			// See if a Piece exists on that BoardSquare
    			boolean isFilled = this.board.isFilled(r, c);
    			if( isFilled ) {
    				// Now go over all the ReleaseNumbers and see if there is something
    				//  for this (r, c) pair
    				Iterator<ReleaseNumber> releaseNumbersIter = ((ReleaseBoard)this.board).numbers.iterator();
    				for( ; releaseNumbersIter.hasNext() ;  ) {
    					ReleaseNumber aReleaseNumber = releaseNumbersIter.next();
						// Check if the row and col matches
						if( (aReleaseNumber.getCol() == c) &&
							(aReleaseNumber.getRow() == r) ) {
							// Add it to the NumberBar
							this.numberBar.addReleaseNumber(aReleaseNumber);
							// Remove the number from the Board
							releaseNumbersIter.remove(); // Remove the current number from the board
						}
    				}
    			}
    		}
    	}
		// Calculate things and do necessary actions
		checkIfRanOut();
	}

	/**
	 * Checks if the user ran out of pieces or finished the level. The function takes necessary actions to reflect the LevelCompletion
	 * Information.
	 */
	private void checkIfRanOut() {
		// Check Pieces Left
		int piecesLeft = 0;
		for( Hexomino aHexomino : this.getLevelBullpen().getPalette().hexominoes )
			piecesLeft += aHexomino.getCount();
		if( (this.getStarsAchieved() != 3) && (piecesLeft == 0) ) {
			// The user just got screwed over
			if( !this.levelCompletedShown ) {
				this.levelCompletedShown = true; // Level has been completed
				this.levelCompletionStatus = AbstractLevel.LEVEL_COMPLETION_OUT_OF_PIECES; // The user ran out of pieces
			}
			// Exit as we've already shown something
			return;
		}
	}

	/**
	 * This function is called when a Piece already existent on the Board was dragged to a new location on the Board of the <code>ReleaseLevel</code>
	 * <p>
	 * The function for the <code>ReleaseLevel</code> doesn't do anything as once a Piece is place in a <code>ReleaseLevel</code>, it cannot be moved
	 */
	@Override
	public void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece) {
		// This should not be allowed as in the ReleaseLevel once a piece is placed,
		// it cannot be moved around.
	}

	/**
	 * This function is called when a Piece already existent on the Board is dragged to the Player Bullpen to get rid of the Piece from the <code>ReleaseLevel</code>
	 * <p>
	 * The function for the <code>ReleaseLevel</code> does nothing.
	 */
	@Override
	public void boardPieceRemoved(Piece p) {
		// This should not be allowed as in the ReleaseLevel once a piece is placed, 
		// it cannot be removed.
	}

	/**
	 * Generates a ReleaseLevelMemento with the attributes of the <code>ReleaseLevel</code>
	 */
	@Override
	public AbstractLevelMemento generateMemento() {
		return new ReleaseLevelMemento(board.generateMemento(), bullpen.palette, this.levelIndex, this.levelType, this.levelName);
	}

}