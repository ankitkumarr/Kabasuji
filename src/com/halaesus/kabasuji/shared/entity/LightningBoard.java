package com.halaesus.kabasuji.shared.entity;

import java.util.ArrayList;

import com.halaesus.kabasuji.shared.memento.AbstractBoardMemento;
import com.halaesus.kabasuji.shared.memento.LightningBoardMemento;

/**
 * Represents a Lightning Board.
 * <p>
 * @author Ankit Kumar, Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class LightningBoard extends AbstractBoard {
   
	/**
	 * Construct an instance of LightningBoard with the given BoardSquare array and list of Pieces
	 * @param squares The 12x12 matrix of BoardSquares to represent the 144 Squares on the Board
	 * @param pieces A list of all the Pieces placed on the Board
	 */
    public LightningBoard(BoardSquare[][] squares, ArrayList<Piece> pieces) {
    	super(squares, pieces);
    }
    
    /**
     * Returns if the given Piece collides with any other Pieces on the board.
     * <p>
     * For a lightning level, this function will always return false as once a Piece is placed, it is
     * removed from the LightningBoard.
     */
    @Override
    public boolean doesCollide(Piece p) {
    	return false;
    }

	/**
	 * Generates a LightningBoardMemento for the calling LightningBoard
	 */
	@Override
	public AbstractBoardMemento generateMemento() {
		return new LightningBoardMemento(getSquares(),getPieces());
	}

	/**
	 * Makes a deep copy of the LightningBoard with all the attributes of the unchanged
	 * @return A <code>LightningBoard</code> with the same attributes but a different memory location
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
		return new LightningBoard(newSquares, new ArrayList<Piece>());
	}
    
}