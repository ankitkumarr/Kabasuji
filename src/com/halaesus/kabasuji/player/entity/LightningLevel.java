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