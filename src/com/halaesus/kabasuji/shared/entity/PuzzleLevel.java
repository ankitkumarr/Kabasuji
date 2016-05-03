package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;
import com.halaesus.kabasuji.shared.memento.PuzzleLevelMemento;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class PuzzleLevel extends AbstractLevel {

	int allowedMoves;
	int usedMoves;

	public PuzzleLevel(PuzzleLevelMemento memento) {
		super(memento);
		// Set the game type in here
		levelType = "Puzzle";
		// Fill out the rest of the fields
		allowedMoves = memento.getAllowedMoves();
	}

	public PuzzleLevel(PuzzleLevel anotherLevel) {
		super(anotherLevel); // Fill out Super Fields
		// Get some other fields
		this.allowedMoves = anotherLevel.allowedMoves;
		this.usedMoves = anotherLevel.usedMoves;
	}

	public int getMovesLeft() {
		return (allowedMoves - usedMoves);
	}

	// Abstract Methods Filler

	@Override
	public int getStarsAchieved() {
		// Count the number of unfilled squares. Then we decide
		int unfilledSquares = 0;
		for( BoardSquare[] rowBoardSquares : this.board.getSquares() ) {
			for( BoardSquare aBoardSquare : rowBoardSquares ) {
				// Check and increment if necessary
				if( aBoardSquare.isActive() && !aBoardSquare.isFilled() )
					unfilledSquares++;
			}
		}
		// Now check
		if( 0 <= unfilledSquares && unfilledSquares <= 12 ) {
			int starsToReturn = (3 - (unfilledSquares / 6));
			// Check if 3 stars
			if( starsToReturn == 3 ) {
				// Make necessary changes to the Level
				if( !this.levelCompletedShown ) {
					this.levelCompletedShown = true; // Level has been completed
					this.levelCompletionStatus = AbstractLevel.LEVEL_COMPLETION_FINISHED_LEVEL; // The user finished the level
				}
			}
			// Return the stars
			return starsToReturn;
		}
		return 0; // Else, do this line!
	}

	@Override
	public void newPieceDropped(Piece p) {
		usedMoves++; // The user has used one extra move
		// Calculate things and do necessary actions
		checkIfRanOut();
	}

	@Override
	public void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece) {
		usedMoves++; // The user has used one extra move
		// Calculate things and do necessary actions
		checkIfRanOut();
	}

	@Override
	public void boardPieceRemoved(Piece p) {
		// Nothing has to be done if a Piece has been removed from the board
	}

	private void checkIfRanOut() {
		// Check Moves Left
		if( (this.getStarsAchieved() != 3 ) && (this.getMovesLeft() == 0) ) {
			// The user just got screwed over
			if( !this.levelCompletedShown ) {
				this.levelCompletedShown = true; // Level has been completed
				this.levelCompletionStatus = AbstractLevel.LEVEL_COMPLETION_OUT_OF_MOVES; // The user ran out of moves
			}
			// Exit as we've already shown something
			return;
		}
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
	
	public int getAllowedMoves() {
		return allowedMoves;
	}
	
	public void setAllowedMoves(int allowedmoves) {
		this.allowedMoves = allowedmoves;
	}

	@Override
	public AbstractLevelMemento generateMemento() {
		return new PuzzleLevelMemento(board.generateMemento(), bullpen.palette.generateMemento(), this.levelIndex, this.allowedMoves, this.levelType, this.levelName);
	}

}