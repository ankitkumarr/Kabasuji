package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;
import com.halaesus.kabasuji.shared.memento.PuzzleLevelMemento;

/**
 * Represents a Puzzle Level
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class PuzzleLevel extends AbstractLevel {

	/** The number of total allowed moves in the <code>PuzzleLevel</code> */
	int allowedMoves;
	
	/** The number of moves used by the user at any point in the game in the <code>PuzzleLevel</code> */
	int usedMoves;

	/**
	 * Constructs a <code>PuzzleLevel</code> with a given <code>PuzzleLevelMemento</code>
	 * @param memento
	 */
	public PuzzleLevel(PuzzleLevelMemento memento) {
		super(memento);
		// Set the game type in here
		levelType = "Puzzle";
		// Fill out the rest of the fields
		allowedMoves = memento.getAllowedMoves();
	}

	/**
	 * Public Copy Constructor for a <code>PuzzleLevel</code> which instantiates another <code>PuzzleLevel</code> with the
	 * same attributes as the original.
	 * <p>
	 * @param anotherLevel  The <code>PuzzleLevel</code> to be copied over
	 */
	public PuzzleLevel(PuzzleLevel anotherLevel) {
		super(anotherLevel); // Fill out Super Fields
		// Get some other fields
		this.allowedMoves = anotherLevel.allowedMoves;
		this.usedMoves = anotherLevel.usedMoves;
	}

	/**
	 * Returns the number of moves left that the player can play
	 * @return
	 */
	public int getMovesLeft() {
		return (allowedMoves - usedMoves);
	}
	
	/**
	 * Returns the total number of allowed moves for the <code>PuzzleLevel</code>
	 * @return
	 */
	public int getAllowedMoves() {
		return allowedMoves;
	}
	
	/**
	 * Sets the total number of allowed moves for the <code>PuzzleLevel</code>
	 * @param allowedmoves
	 */
	public void setAllowedMoves(int allowedmoves) {
		this.allowedMoves = allowedmoves;
	}

	// Abstract Methods Filler

	/**
	 * Returns the number of stars achieved by the Player on the <code>PuzzleLevel</code>
	 * @return 3 if the user has won the <code>PuzzleLevel</code>
	 */
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

	/**
	 * This function is called when a Piece is dropped onto the PuzzleBoard of the <code>PuzzleLevel</code>.
	 * <p>
	 * The function for the PuzzleLevel increments the number of moves used by the player.
	 * <p>
	 * @param p  the Piece that was added to the Board of the <code>PuzzleLevel</code>
	 */
	@Override
	public void newPieceDropped(Piece p) {
		usedMoves++; // The user has used one extra move
		// Calculate things and do necessary actions
		checkIfRanOut();
	}

	/**
	 * This function is called when a Piece already existent on the Board was dragged to a new location on the Board of the <code>PuzzleLevel</code>
	 * <p>
	 * The function for the PuzzleLevel also increments the number of moves used by the player.
	 */
	@Override
	public void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece) {
		usedMoves++; // The user has used one extra move
		// Calculate things and do necessary actions
		checkIfRanOut();
	}

	/**
	 * This function is called when a Piece already existent on the Board is dragged to the Player Bullpen to get rid of the Piece from the <code>PuzzleLevel</code>
	 * <p>
	 * The function for the PuzzleLevel does nothing.
	 */
	@Override
	public void boardPieceRemoved(Piece p) {
		// Nothing has to be done if a Piece has been removed from the board
	}

	/**
	 * Checks if the user ran out of moves or finished the level. The function takes necessary actions to reflect the LevelCompletion
	 * Information.
	 */
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

	/**
	 * Generates a PuzzleLevelMemento with the attributes of the <code>PuzzleLevel</code>
	 */
	@Override
	public AbstractLevelMemento generateMemento() {
		return new PuzzleLevelMemento(board.generateMemento(), bullpen.palette.generateMemento(), this.levelIndex, this.allowedMoves, this.levelType, this.levelName);
	}

}