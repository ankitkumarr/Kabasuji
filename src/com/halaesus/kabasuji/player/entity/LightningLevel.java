package com.halaesus.kabasuji.player.entity;

import java.io.File;

public class LightningLevel extends AbstractLevel {
	
	//LightningBoard lightningBoard;
    int numRandPieces;
    int elapsedTime;
    int maxTime;

    public LightningLevel(File file) {
    	super(file);
    	// Read from file
    	maxTime = 65; elapsedTime = 0; // TODO: Dummy values
    	// Set the game type in here
    	levelType = "Lightning";
    	
    	// TODO construct releaseBoard from file data
    	board = new LightningBoard(null);
    }

    public int getTimeLeft() {
    	return (maxTime - elapsedTime);
    }
    
    public void incrementElapsedTime() {
    	elapsedTime++;
    }
    
    public void resetElapsedTime() {
    	elapsedTime = 0;
    }

    // Abstract Methods Filler
    
    @Override
    public int getStarsAchieved() {
    	int activeSquares = 0;
    	int markedSquares = 0;
    	// Calculate the number of active and filled squares
    	for(int r = 0; r < 12; r++) {
    		for(int c = 0; c < 12; c++) {
    			if( this.board.isActive(r, c) )
    				activeSquares++; // We just got an active square
    			if( this.board.isFilled(r, c) )
    				markedSquares++; // We just got a filled (marked) square
    		}
    	}
    	// Now return based on what the user has achieved
    	if( activeSquares == markedSquares )
    		return 3;
    	else if( (activeSquares - markedSquares) <= 6 )
    		return 2;
    	else if( (activeSquares - markedSquares) <= 12 )
    		return 1;
    	else {
			return 0;
		}
    }
    
	@Override
	public void newPieceDropped(Piece p) {
		// Remove the Piece from the Board
		this.board.pieces.remove(p);
	}

	@Override
	public void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece) {
		// This should never be called on the LightningLevel as once a Piece is
		//  dropped, it gets soaked by the board and no longer exists.
	}

	@Override
	public void boardPieceRemoved(Piece p) {
		// This should never be called on the LightningLevel either because once a
		//  Piece is dropped, it gets soaked by the board and no longer exists to
		//  be removed.
	}

}