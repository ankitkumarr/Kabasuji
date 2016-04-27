package com.halaesus.kabasuji.shared.entity;

import java.io.File;

import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;
import com.halaesus.kabasuji.shared.memento.PuzzleLevelMemento;

/**
 * 
 * @author Corey Dixon
 *
 */
public class PuzzleLevel extends AbstractLevel {

	// PuzzleBoard puzzleBoard;
	int allowedMoves;
	int usedMoves;

	public PuzzleLevel(File file) {
		super(file);
		// Set the game type in here
		levelType = "Puzzle";
		// TODO: Remove Dummy Values
		allowedMoves = 47;
		usedMoves = 0;
		// TODO construct releaseBoard from file data
		BoardSquare[][] squares = new BoardSquare[12][12];
		for(int r = 0; r < 12; r++)
			for(int c = 0; c < 12; c++)
				squares[r][c] = new BoardSquare(true);
		board = new PuzzleBoard(squares);
	}

	public PuzzleLevel(PuzzleLevelMemento memento) {
		super(memento);
		allowedMoves = memento.getAllowedMoves();
	}

	public int getMovesLeft() {
		return (allowedMoves - usedMoves);
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
	
	public int getallowedMoves() {
		return allowedMoves;
	}
	
	public void setallowedMoves(int allowedmoves) {
		this.allowedMoves = allowedmoves;
	}

	@Override
	public AbstractLevelMemento generateMemento() {
		return new PuzzleLevelMemento(board.generateMemento(), bullpen.palette.generateMemento(), allowedMoves);
	}

}