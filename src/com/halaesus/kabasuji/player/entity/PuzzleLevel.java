package com.halaesus.kabasuji.player.entity;

import java.io.File;
import java.util.ArrayList;

public class PuzzleLevel extends AbstractLevel {
	
	//PuzzleBoard puzzleBoard;
    int allowedMoves;
    int usedMoves;

    public PuzzleLevel() {
    	super();
    	
    	// Set the game type in here
    	levelType = "Puzzle";
    	// TODO: Remove Dummy Values
    	allowedMoves = 50;
    	usedMoves = 0;
    	// TODO construct releaseBoard from file data
    	board = new PuzzleBoard(null);
    }


	public int getMovesLeft() {
    	return (allowedMoves - usedMoves);
        // TODO implement here
    }
    
    // Abstract Methods Filler

    @Override
    public int getStarsAchieved() {
    	return 0; // TODO: Fill this in
    }
    
	@Override
	public void newPieceDropped(Piece p) {
		usedMoves++; // The user has used one extra move
	}

	@Override
	public void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece) {
		usedMoves++; // The user has used one extra move
	}

	@Override
	public void boardPieceRemoved(Piece p) {
		// TODO: Check if this count as a move
	}
	
	public MementoPuzzleLevel getState() {
		return new MementoPuzzleLevel(this.levelIndex, this.allowedMoves, this.board, this.bullpen);
	}
    
	public void restore(MementoPuzzleLevel m) {
		this.allowedMoves = m.allowedMoves;
		this.board = m.puzzleBoard;
		this.bullpen = m.bullpen;
		this.levelIndex = m.levelIndex;
	}

}