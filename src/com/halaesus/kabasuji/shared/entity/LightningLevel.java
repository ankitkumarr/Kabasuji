package com.halaesus.kabasuji.shared.entity;

import java.util.Random;

import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;
import com.halaesus.kabasuji.shared.memento.LightningLevelMemento;

/**
 * Represents a Lightning Level
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class LightningLevel extends AbstractLevel {

	/** The number of elapsed seconds in the <code>LightningLevel</code> */
	int elapsedTime;
	
	/** The maximum time the user can play for in the <code>LightningLevel</code> */
	int maxTime;
	
	/** The number of random Pieces available for the Player to play at anytime during a <code>LightningLevel</code> */
	int numRandomPieces;

	/**
	 * Constructs a <code>LightningLevel</code> with a given <code>LightningLevelMemento</code>
	 * @param memento
	 */
	public LightningLevel(LightningLevelMemento memento) {
		super(memento); // Let the super do its stuff with the memento
		// Fill out some fields
		numRandomPieces = memento.getNumRandomPieces();
		maxTime = memento.getMaxTime();
		elapsedTime = 0;
		// Set the game type in here
		levelType = "Lightning";
		// Update Bullpen Pieces
		updateBullpenPieces();
	}

	/**
	 * Public Copy Constructor for a <code>LightningLevel</code> which instantiates another <code>LightningLevel</code> with the
	 * same attributes as the original.
	 * <p>
	 * @param anotherLevel  The <code>LightningLevel</code> to be copied over
	 */
	public LightningLevel(LightningLevel anotherLevel) {
		super(anotherLevel); // Fill out Super Fields
		// Fill out our fields now
		this.numRandomPieces = anotherLevel.numRandomPieces;
		this.elapsedTime = anotherLevel.elapsedTime;
		this.maxTime = anotherLevel.maxTime;
	}

	/**
	 * Returns the time left for the player to play the <code>LightningLevel</code>
	 * @return
	 */
	public int getTimeLeft() {
		return (maxTime - elapsedTime);
	}

	/**
	 * Increments the time used up by the Player on the <code>LightningLevel</code> by 1 second
	 */
	public void incrementElapsedTime() {
		elapsedTime++;
	}

	/**
	 * Sets the elapsed time by the Player on the <code>LightningLevel</code> to 0 seconds
	 */
	public void resetElapsedTime() {
		elapsedTime = 0;
	}
	
	/**
	 * Returns the maximum allowed time for the Player to play the <code>LightningLevel</code>
	 * @return
	 */
	public int getMaxTime() {
		return this.maxTime;
	}
	
	/**
	 * Returns the number of random Pieces that will be visible to a Player playing the <code>LightningLevel</code> at a given point
	 * @return
	 */
	public int getNumRandomPieces() {
		return this.numRandomPieces;
	}
	
	/**
	 * Sets the maximum time given to the Player to play the <code>LightningLevel</code>
	 * @param num
	 */
	public void setMaxTime(int num) {
		this.maxTime = num;
	}
	
	/**
	 * Sets the number of random Pieces that will be visible to a Player playing the <code>LightningLevel</code>
	 * @param randomMoves
	 */
	public void setRandomMoves(int randomMoves) {
		this.numRandomPieces = randomMoves;
	}

	// Abstract Methods Filler

	/**
	 * Returns the number of stars achieved by the Player on the <code>LightningLevel</code>
	 * @return 3 if the user has won the <code>LightningLevel</code>
	 */
	@Override
	public int getStarsAchieved() {
		int activeSquares = 0;
		int markedSquares = 0;
		// Calculate the number of active and filled squares
		for (int r = 0; r < 12; r++) {
			for (int c = 0; c < 12; c++) {
				if (this.board.isActive(r, c))
					activeSquares++; // We just got an active square
				if (this.board.isFilled(r, c))
					markedSquares++; // We just got a filled (marked) square
			}
		}
		// Now return based on what the user has achieved
		if (activeSquares == markedSquares) {
			// Change necessary Level Completion Info if necessary
			if( !this.levelCompletedShown ) {
				this.levelCompletedShown = true; // Level has been completed
				this.levelCompletionStatus = AbstractLevel.LEVEL_COMPLETION_FINISHED_LEVEL; // The user finished the level
			}
			// Return the number of stars
			return 3;
		} else if ((activeSquares - markedSquares) <= 6)
			return 2;
		else if ((activeSquares - markedSquares) <= 12)
			return 1;
		else {
			return 0;
		}
	}

	/**
	 * This function is called when a Piece is dropped onto the LightningBoard of the <code>LightningLevel</code>.
	 * <p>
	 * The function for the LightningLevel removes the Piece from the LightningBoard as there are not suppose to be any pieces on
	 * a LightningBoard.
	 * <p>
	 * @param p  the Piece that was added to the Board of the <code>LightningLevel</code>
	 */
	@Override
	public void newPieceDropped(Piece p) {
		// Remove the Piece from the Board
		this.board.pieces.remove(p);
		// Update Bullpen Pieces
		updateBullpenPieces();
	}

	/**
	 * Generates random pieces on the board to be added to the Player Palette
	 */
	private void updateBullpenPieces() {
		// STEP: Count how many are we short of
		int hexominoTotalCount = 0;
		for(int idx = 0; idx < 35; idx++)
			hexominoTotalCount += this.getLevelBullpen().getPalette().getHexomino(idx).getCount();
		int shortOfCount = numRandomPieces - hexominoTotalCount;
		// STEP: Add those many random numbers
		Random randGen = new Random();
		while( shortOfCount-- > 0 ) {
			int idx = randGen.nextInt(35);
			this.getLevelBullpen().getPalette().getHexomino(idx).setCount( this.getLevelBullpen().getPalette().getHexomino(idx).getCount() + 1 );
		}
	}

	/**
	 * This function is called when a Piece already existent on the Board was dragged to a new location on the Board of the <code>LightningLevel</code>
	 * <p>
	 * The function for the LightningLevel does nothing as this is not a valid move for a <code>LightningLevel</code>
	 */
	@Override
	public void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece) {
		// This should never be called on the LightningLevel as once a Piece is
		// dropped, it gets soaked by the board and no longer exists.
	}

	/**
	 * This function is called when a Piece already existent on the Board is dragged to the Player Bullpen to get rid of the Piece from the <code>LightningLevel</code>
	 * <p>
	 * The function for the LightningLevel does nothing as this is also not an allowed move for <code>LightningLevel</code>
	 */
	@Override
	public void boardPieceRemoved(Piece p) {
		// This should never be called on the LightningLevel either because once a
		// Piece is dropped, it gets soaked by the board and no longer exists to be removed.
	}

	/**
	 * Generates an LightningLevelMemento with the attributes of the <code>LightningLevel</code>
	 */
	@Override
	public AbstractLevelMemento generateMemento() {
		return new LightningLevelMemento(board.generateMemento(), bullpen.palette, this.levelIndex, maxTime, numRandomPieces, this.levelType, this.levelName);
	}

}