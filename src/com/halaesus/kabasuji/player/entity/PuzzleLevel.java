package com.halaesus.kabasuji.player.entity;

import java.io.File;

public class PuzzleLevel extends AbstractLevel {
	
	//PuzzleBoard puzzleBoard;
    int allowedMoves;
    int usedMoves;

    public PuzzleLevel(File file) {
    	super(file);
    	// Set the game type in here
    	levelType = "Puzzle";
    	
    	// TODO construct releaseBoard from file data
    	board = new PuzzleBoard(null);
    }

    public int getMovesLeft() {
    	return 47;
        // TODO implement here
    }
    
    // Abstract Methods Filler

	@Override
	public void newPieceDropped(Piece p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boardPieceRemoved(Piece p) {
		// TODO Auto-generated method stub
		
	}

}