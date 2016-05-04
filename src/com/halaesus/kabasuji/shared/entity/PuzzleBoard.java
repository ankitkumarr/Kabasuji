package com.halaesus.kabasuji.shared.entity;

import java.util.ArrayList;

import com.halaesus.kabasuji.shared.memento.AbstractBoardMemento;
import com.halaesus.kabasuji.shared.memento.PuzzleBoardMemento;

/**
 * Represents a Puzzle Board.
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class PuzzleBoard extends AbstractBoard {

	/**
	 * Construct an instance of PuzzleBoard with the given BoardSquare array and list of Pieces
	 * @param squares The 12x12 matrix of BoardSquares to represent the 144 Squares on the Board
	 * @param pieces A list of all the Pieces placed on the Board
	 */
    public PuzzleBoard(BoardSquare[][] squares, ArrayList<Piece> pieces) {
    	super(squares, pieces);
    }

    /**
	 * Generates a PuzzleBoardMemento for the calling PuzzleBoard
	 */
	@Override
	public AbstractBoardMemento generateMemento() {
		return new PuzzleBoardMemento(getSquares(), getPieces());
	}

	/**
	 * Makes a deep copy of the PuzzleBoard with all the attributes of the unchanged
	 * @return A <code>PuzzleBoard</code> with the same attributes but a different memory location
	 */
	@Override
	public AbstractBoard makeCopy() {
		BoardSquare[][] originalSquares = getSquares();
		int rows = originalSquares.length;
		int cols = originalSquares[0].length;
		// Now create copy
		BoardSquare[][] newSquares = new BoardSquare[rows][cols];
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++)
				newSquares[r][c] = new BoardSquare(originalSquares[r][c]);
		// Call and return
		return new PuzzleBoard(newSquares, new ArrayList<Piece>());
	}

}