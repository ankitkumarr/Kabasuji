package com.halaesus.kabasuji.shared.entity;

import java.util.Random;

import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;
import com.halaesus.kabasuji.shared.memento.LightningLevelMemento;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class LightningLevel extends AbstractLevel {

	int elapsedTime;
	int maxTime;
	int numRandomPieces;

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

	public LightningLevel(LightningLevel anotherLevel) {
		super(anotherLevel); // Fill out Super Fields
		// Fill out our fields now
		this.numRandomPieces = anotherLevel.numRandomPieces;
		this.elapsedTime = anotherLevel.elapsedTime;
		this.maxTime = anotherLevel.maxTime;
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
	public int getMaxTime() {
		return this.maxTime;
	}
	
	public int getNumRandomPieces() {
		return this.numRandomPieces;
	}
	
	public void setMaxTime(int num) {
		this.maxTime = num;
	}
	
	public void setRandomMoves(int randomMoves) {
		this.numRandomPieces = randomMoves;
	}

	// Abstract Methods Filler

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

	@Override
	public void newPieceDropped(Piece p) {
		// Remove the Piece from the Board
		this.board.pieces.remove(p);
		// Update Bullpen Pieces
		updateBullpenPieces();
	}

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

	@Override
	public void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece) {
		// This should never be called on the LightningLevel as once a Piece is
		// dropped, it gets soaked by the board and no longer exists.
	}

	@Override
	public void boardPieceRemoved(Piece p) {
		// This should never be called on the LightningLevel either because once a
		// Piece is dropped, it gets soaked by the board and no longer exists to be removed.
	}

	@Override
	public AbstractLevelMemento generateMemento() {
		return new LightningLevelMemento(board.generateMemento(), bullpen.palette, this.levelIndex, maxTime, numRandomPieces, this.levelType, this.levelName);
	}

}