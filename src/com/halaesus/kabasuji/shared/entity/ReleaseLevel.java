package com.halaesus.kabasuji.shared.entity;

import java.util.Iterator;

import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;
import com.halaesus.kabasuji.shared.memento.ReleaseLevelMemento;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class ReleaseLevel extends AbstractLevel {
  
	public static final int DRAG_SOURCE_NUMBERBAR = 5;
	NumberBar numberBar;
	ReleaseNumber numberBeingDragged;
	
	public ReleaseLevel(ReleaseLevelMemento memento) {
		super(memento);
		// Set the game type in here
		levelType = "Release";
		// TODO: Fix this
		numberBar = new NumberBar();
		numberBeingDragged = null;
	}

	public ReleaseLevel(ReleaseLevel anotherLevel) {
		super(anotherLevel); // Fill out Super Fields
		// Get the NumberBar
		this.numberBar = anotherLevel.numberBar;
		numberBeingDragged = null;
	}
	
    public NumberBar getNumberBar(){
    	return this.numberBar;
	}
        
    // Abstract Methods Filler

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

	@Override
	public void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece) {
		// This should not be allowed as in the ReleaseLevel once a piece is placed,
		// it cannot be moved around.
	}

	@Override
	public void boardPieceRemoved(Piece p) {
		// This should not be allowed as in the ReleaseLevel once a piece is placed, 
		// it cannot be removed.
	}

	@Override
	public AbstractLevelMemento generateMemento() {
		return new ReleaseLevelMemento(board.generateMemento(), bullpen.palette, this.levelIndex, this.levelType, this.levelName);
	}
	
	public ReleaseNumber getnumberBeingDragged(){
	    return this.numberBeingDragged;
	}
	    
	public boolean isNumberDraggingActive() {
	    return (this.numberBeingDragged!=null);
	}
	    
	public void setnumberBeingDragged(ReleaseNumber beingDragged){
	    this.numberBeingDragged = beingDragged;
	}

}