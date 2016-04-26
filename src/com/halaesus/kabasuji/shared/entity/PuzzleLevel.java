package com.halaesus.kabasuji.shared.entity;

import java.io.File;

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
		board = new PuzzleBoard(null);
	}

	public PuzzleLevel(PuzzleLevelMemento memento) {
		super(memento);
		allowedMoves = memento.allowedMoves;
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

	@Override
	public AbstractLevelMemento generateMemento() {
		return new PuzzleLevelMemento(board.generateMemento(), bullpen.palette.generateMemento(), allowedMoves);
	}

}