package com.halaesus.kabasuji.shared.entity;

import java.io.File;

import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;
import com.halaesus.kabasuji.shared.memento.ReleaseLevelMemento;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class ReleaseLevel extends AbstractLevel {
  
	NumberBar numberBar;
	//ReleaseBoard releaseBoard;
	public  ReleaseLevel(File file) {
		super(file);
		
		// TODO construct releaseBoard from file data
		board = new ReleaseBoard(null, null); 
		
		// Set the game type in here
		levelType = "Release";
		
		numberBar = new NumberBar();
		
		// Setup the board; TODO: Clear up board loop
		BoardSquare[][] squares = new BoardSquare[12][12];
		for(int r = 0; r < 12; r++)
			for(int c = 0; c < 12; c++)
				squares[r][c] = new BoardSquare(true);
		board = new ReleaseBoard(squares, null);
	}
	
	public ReleaseLevel(ReleaseLevelMemento memento) {
		super(memento);
		numberBar = new NumberBar();
	}
	
    public NumberBar getNumberBar(){
    	return this.numberBar;
	}
        
    // Abstract Methods Filler

    @Override
    public int getStarsAchieved() {
    	int setsCompleted = this.numberBar.setsFound();
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
    				for( ReleaseNumber aReleaseNumber : ((ReleaseBoard)this.board).numbers ) {
						// Check if the row and col matches
						if( (aReleaseNumber.getCol() == c) &&
							(aReleaseNumber.getRow() == r) ) {
							// Add it to the NumberBar
							this.numberBar.addReleaseNumber(aReleaseNumber);
						}
    				}
    			}
    		}
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
		return new ReleaseLevelMemento(board.generateMemento(), bullpen.palette, this.levelIndex);
	}

}