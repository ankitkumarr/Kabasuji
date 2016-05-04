package com.halaesus.kabasuji.shared.entity;

import java.util.*;

import com.halaesus.kabasuji.shared.memento.AbstractBoardMemento;
import com.halaesus.kabasuji.shared.memento.ReleaseBoardMemento;

/**
 * Represents a Release Board.
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class ReleaseBoard extends AbstractBoard {

	/** Set of the ReleaseNumbers that reside on the Board */
    Set<ReleaseNumber> numbers;

    /**
	 * Construct an instance of ReleaseBoard with the given BoardSquare array,list of Pieces and a set of
	 * ReleaseNumbers that reside on the board
	 * @param squares The 12x12 matrix of BoardSquares to represent the 144 Squares on the Board
	 * @param pieces A list of all the Pieces placed on the Board
	 * @param numbers A set of ReleaseNumbers that exist on the Board
	 */
	public ReleaseBoard(BoardSquare[][] squares, ArrayList<Piece> pieces, Set<ReleaseNumber> numbers) {
		super(squares, pieces);
		// Fetch the numbers from the Set given to us
		this.numbers = numbers;
	}

	/**
	 * Returns the Set of ReleaseNumbers on the <code>ReleaseBoard</code>
	 * @return
	 */
    public Set<ReleaseNumber> getReleaseNumbers(){
    	return numbers;  	
    }
    
    /**
     * Adds a ReleaseNumber to the <code>ReleaseBoard</code>
     * @param rn
     */
    public void addNumber(ReleaseNumber rn) {
    	this.numbers.add(rn);
    }
    
    /**
     * Remove a ReleaseNumber on the <code>ReleaseBoard</code>
     * @param rn
     */
    public void removeNumber(ReleaseNumber rn) {
    	this.numbers.remove(rn);
    }
	
    /**
     * Determines if the given ReleaseNumber <code>rn</code> collides with any other ReleaseNumbers on the Board
     * @param rn the ReleaseNumber to be added
     * @return true if the ReleaseNumber <code>rn</code> being tested for wouldn't collide with any of the existent ReleaseNumbers
     */
	public boolean doesCollide(ReleaseNumber rn) {
		for (ReleaseNumber rno: this.numbers) {
			if ((rno.getCol() == rn.getCol()) && (rno.getRow() == rn.getRow())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determine if the given ReleaseNumber <code>rn</code> is outside active Board bounds
	 * @param rn the ReleaseNumber to be addeded
	 * @return true if the ReleaseNumber <code>rn</code> being tested is within active Board Bounds
	 */
	public boolean isOutsideBounds(ReleaseNumber rn) {
		BoardSquare bs[][] = this.getSquares();
		if (bs[rn.getRow()][rn.getCol()].active) {
			return false;
		}
		return true;
	}

	/**
	 * Generates an ReleaseBoardMemento with the necessary attributes
	 */
	@Override
	public AbstractBoardMemento generateMemento() {
		return new ReleaseBoardMemento(getSquares(), getPieces(), numbers);
	}

	/**
	 * Generates a deep copy of the ReleaseBoard with all the attributes of the unchanged
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
		return new ReleaseBoard(newSquares, new ArrayList<Piece>(), new HashSet<>(numbers));
	}
    
}