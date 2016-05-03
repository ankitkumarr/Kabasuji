package com.halaesus.kabasuji.shared.entity;

import java.util.ArrayList;
import java.util.Iterator;

import com.halaesus.kabasuji.shared.memento.AbstractBoardMemento;

/**
 * Represents the most basic representation of a Board
 * <p>
 * <b>AbstractBoard</b> contains information that is shared across all types of boards in this game: LightningBoard,
 * ReleaseBoard and PuzzleBoard
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public abstract class AbstractBoard {

	/** A 12x12 Array of Squares of the Board. */
	private BoardSquare[][] squares;
	
	/** Pieces placed on the Board. */
	ArrayList<Piece> pieces;

	/**
	 * Construct an instance of AbstractBoard with the given BoardSquare array and list of Pieces
	 * @param squares The 12x12 matrix of BoardSquares to represent the 144 Squares on the Board
	 * @param pieces A list of all the Pieces placed on the Board
	 */
	public AbstractBoard(BoardSquare[][] squares, ArrayList<Piece> pieces) {
		this.squares = squares;
		// Initialize
		this.pieces = pieces;
	}
    
	/**
	 * Determine if a BoardSquare with a given <code>row</code> and <code>col</col> in the 12x12 Square matrix is Active
	 * @param row
	 * @param col
	 * @return true if the underlying <code>BoardSquare[row][col].isActive()</code> equals true
	 */
    public boolean isActive(int row, int col) {
    	return squares[row][col].isActive();
    }
    
    /**
	 * Determine if a BoardSquare with a given <code>row</code> and <code>col</col> in the 12x12 Square matrix is filled with a Piece
	 * @param row
	 * @param col
	 * @return true if the underlying <code>BoardSquare[row][col].isFilled()</code> equals true
	 */
    public boolean isFilled(int row, int col) {
    	return squares[row][col].isFilled();
    }
    
    /**
     * Returns if a BoardSquare with a given <code>row</code> and <code>col</code> has a hint
     * @param row
     * @param col
     * @return Returns the value of the call <code>BoardSquare[row][col].getHint()</code> 
     */
    public int getHint(int row, int col) {
    	return squares[row][col].getHint();
    }
    
    /**
     * Adds a hint to BoardSquare at a given <code>row</code> and <code>col</code> with given <code>colorIndex</code>
     * @param row
     * @param col
     * @param colorIndex   indicates the Piece from the PieceGenerator where the hint color has to be derived
     */
    public void addHint(int row, int col, int colorIndex) {
    	 this.squares[row][col].setHint(colorIndex);
    }
    
    /**
     * Removes a hint at a BoardSquare with a given <code>row</code> and <code>col</code>
     * @param row
     * @param col
     */
    public void removeHint(int row, int col) {
    	this.squares[row][col].setHint(-1);
	}

    /**
     * Determines if the given Piece <code>p</code> collides with any other pieces on the Board
     * @param p  the Piece against which collisions have to be tested for
     * @return true if the Piece <code>p</code> being tested for would not collide with any of the existent Board Pieces
     */
    public boolean doesCollide(Piece p) {
    	
    	// This will store the squares 
    	PieceSquare[] pSquares = p.getPieceSquares();
    	// Iterating through each piece of the board
    	for(Piece piece : pieces) {
    		//Getting the piece Squares of each piece
    		PieceSquare[] pieceSquares = piece.getPieceSquares();
    		for(int i = 0; i < 6 ; i++) {
    			for(int j = 0; j < 6; j++) {
    				// Comparing if the PieceSquares are equal
    				if( (pSquares[i].getRow() == pieceSquares[j].getRow()) &&
    				    (pSquares[i].getCol() == pieceSquares[j].getCol()) )
    					
    					return true;
    			}
    		}
    	}
 
    	return false;
    }

    /**
     * Determines if the given Piece <code>p</code> is outside active Board bounds
     * @param p  the Piece against which Bound Checks will be done
     * @return true if the Piece <code>p</code> being tested is within active Board bounds
     */
    public boolean isOutsideBounds(Piece p) {
    	PieceSquare[] pSquares = p.getPieceSquares();
    	for (int i = 0; i < pSquares.length; i++) {
    		if(!(squares[pSquares[i].getRow()][pSquares[i].getCol()].active))
    			return true;
    	}
    	
    	return false;
    }

    /**
     * Returns the iterator to the <code>ArrayList&lt;Piece&gt;</code> stored on the Board
     * @return
     */
    public Iterator<Piece> getPiecesIt() {
    	return pieces.iterator();
    }
    
    /**
     * Adds Piece <code>p</code> to the list of already existent Pieces
     * @param p  the Piece to be added
     */
    public void addPiece(Piece p) {
    	this.pieces.add(p);
    }
    
    /**
     * Returns the 12x12 matrix of BoardSquares
     * @return
     */
	public BoardSquare[][] getSquares() {
		return squares;
	}
	
	/**
	 * Returns the <code>ArrayList&ltPiece&gt;</code> stored on the Board
	 * @return
	 */
	public ArrayList<Piece> getPieces(){
		return this.pieces;
	}
	
	/**
	 * Sets the BoardSquares of the Board
	 * @param squares  the final value needed for the BoardSquares of the Board
	 */
	public void setSquares(BoardSquare[][] squares) {
		this.squares = squares;
	}
	
	// Abstract Methods
	
	/**
	 * Generates an AbstractBoardMemento
	 * @return
	 */
    public abstract AbstractBoardMemento generateMemento();
    
    /**
     * Generates a deep copy of the AbstractBoard with all the attributes of the unchanged
     * @return
     */
    public abstract AbstractBoard makeCopy();
    
}