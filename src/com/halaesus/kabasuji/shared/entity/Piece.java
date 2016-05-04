package com.halaesus.kabasuji.shared.entity;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;

import com.halaesus.kabasuji.utils.PieceGenerator;

/** Represents a hexomino piece
 * @author brianjkd
 */
/**
 * @author Brian, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class Piece implements Serializable {

	private static final long serialVersionUID = 8539046452906569460L;
	PieceSquare[] originalSquares;
	Hexomino parentHexomino;
    PieceSquare[] squares; 
    int colorID;

    public Piece(int colorID, PieceSquare squareMap[], Hexomino parentHexomino) {
    	this.parentHexomino = parentHexomino;
        // Save the data
    	this.originalSquares = Arrays.copyOf(squareMap, squareMap.length);
    	this.colorID = colorID;
    	
    	// Make a fresh copy of the PieceSquares
    	PieceSquare[] newPieceSquareArray = new PieceSquare[squareMap.length];
    	for(int idx = 0; idx < squareMap.length; idx++)
    		newPieceSquareArray[idx] = new PieceSquare(squareMap[idx]);
    	this.squares = newPieceSquareArray;
    }
    
    /**
     * A copy constructor for <code>Piece</code> which instantiates a new <code>Piece</code> with the
     * same attributes as the previous.
     * <p>
     * @param toCopy
     */
    public Piece(Piece toCopy) {
    	this(toCopy.colorID, toCopy.getPieceSquares(), toCopy.parentHexomino);
    }
    
    /**
	 * Returns the original layout of the squares making up the piece <code>PieceSquare</code>
	 * @return
	 */
    public PieceSquare[] getOriginalPieceSquares() {
    	return originalSquares;
    }
    
    /**
	 * Returns the current layout of the squares making up the piece <code>PieceSquare</code>
	 * @return
	 */
    public PieceSquare[] getPieceSquares() {
    	return squares;
    }
    
    /**
	 * Returns the color of the piece as defined by the pieceGenerator
	 * @return
	 */
    public Color getColor() {
    	return PieceGenerator.colors[colorID];
    }
    
    /**
	 * Returns the id number of the color of the piece
	 * @return
	 */
    public int getColorID() {
    	return colorID;
    }
    
    
    /**
     * Sets this piece's parent hexomino <code>Hexomino</code>
     * @param parentHexomino
     */
    public void setParentHexomino(Hexomino parentHexomino) {
    	this.parentHexomino = parentHexomino;
    }
    
    /** Returns the parent hexomino of this piece
     * @return
     */
    public Hexomino getParentHexomino() {
    	return this.parentHexomino;
    }

    /**
     * flips piece horizontally
     */
    public void flipH() {
    	swapCols();
    }

    /**
     * flips piece vertically
     */
    public void flipV() {
        swapRows();
    }
    
    /**
     * transposes piece's squares <code>PieceSuqare</code>
     */
    private void transpose(){
    	for (PieceSquare s: this.squares){
			int r = s.getRow();
			int c = s.getCol();
			s.setRow(c);
			s.setCol(r);
		}
    }
    
    /**
     * swaps the row of each square of the piece
     */
    private void swapRows(){
    	for (PieceSquare s: this.squares){
			int r = s.getRow();	
			s.setRow(Math.abs(5-r));
		}
    }
    
    /**
     * swaps the column of each square of the piece
     */
    private void swapCols(){
    	for (PieceSquare s: this.squares){
			int c = s.getCol();	
			s.setCol(Math.abs(5-c));
		}
    }
    
    /**
     * rotates the piece's squares counter clockwise
     */
    public void rotateCC(){
    	transpose();
    	swapRows();
    }
    
    /**
     * rotates the piece's squares clockwise
     */
    public void rotateCW(){
    	swapRows();
    	transpose();
    }  
        
	/**
	 * centers underlying PieceSquares within a 6x6 matrix.
	 */
	public void centerPiece(){
		PieceSquare[] centeredSquares = new PieceSquare[6];
		int xMin = squares[0].getCol();
		int xMax = squares[0].getCol();
		int yMin = squares[0].getRow();
		int yMax = squares[0].getRow();
		
		for (PieceSquare s: this.squares){
			if (s.getCol() < xMin) xMin = s.getCol();
			if (s.getCol() > xMax) xMax = s.getCol();
			if (s.getRow() < yMin) yMin = s.getRow();
			if (s.getRow() > yMax) yMax = s.getRow();				
		}
		
		int xDist = (0-xMin)/2 + (5-xMax)/2;
		int yDist = (0-yMin)/2 + (5-yMax)/2;
		
		for(int i = 0; i < 6; i++) {
			centeredSquares[i]= new PieceSquare(true,
					squares[i].getCol() + xDist,
					squares[i].getRow() + yDist);
		}

		squares = centeredSquares;	
	}
	
	/** Returns the piece's squares pushed to the top left of a matrix
	 * @return
	 */
	public PieceSquare[] pushTopLeft() {
		PieceSquare[] toReturn = Arrays.copyOf(squares, squares.length); // Make a copy as we'll be maintain this copy
		// Calculate the xMin, yMin and subtract that from all squares
		int xMin = squares[0].getCol();
		int yMin = squares[0].getRow();
		
		for(PieceSquare s: toReturn){
			if (s.getCol() < xMin) xMin = s.getCol();
			if (s.getRow() < yMin) yMin = s.getRow();			
		}
		// Remove this from all squares
		for(int i = 0; i < toReturn.length; i++) {
			toReturn[i].setCol(toReturn[i].getCol() - xMin);
			toReturn[i].setRow(toReturn[i].getRow() - yMin);
		}
		// Finally, return the mutated PieceSquare[]
		return toReturn;
	}
	
	/**
	 * Returns true if piece has no square above source square
	 * @param source
	 * @return
	 */
	public boolean noSquareAbove(PieceSquare source){
		for (PieceSquare s: this.squares){
			if (source.getRow() -1 == s.getRow()
					&& source.getCol() == s.getCol())return false;
		}	
		return true;
	}
	
	/**
	 * Returns true if piece has no square below source square
	 * @param source
	 * @return
	 */
	public boolean noSquareBelow(PieceSquare source){
		for (PieceSquare s: this.squares){
			if (source.getRow() +1 == s.getRow()
					&& source.getCol() == s.getCol())return false;
		}	
		return true;
	}
	
	/**
	 * Returns true if piece has no square right of source square
	 * @param source
	 * @return
	 */
	public boolean noSquareRight(PieceSquare source){
		for (PieceSquare s: this.squares){
			if (source.getRow() == s.getRow()
					&& source.getCol() + 1 == s.getCol())return false;
		}	
		return true;
	}

	/**
	 * Returns true if piece has no square left of source square
	 * @param source
	 * @return
	 */
	public boolean noSquareLeft(PieceSquare source){
		for (PieceSquare s: this.squares){
			if (source.getRow() == s.getRow()
					&& source.getCol() - 1 == s.getCol())return false;
		}	
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if( o == null )
			return false;
		if( !(o instanceof Piece) )
			return false;
		// Now check for equality
		Piece other = (Piece)o;
		if( !(this.colorID == other.colorID) )
			return false;
		for( int i = 0; i < this.squares.length; i++ )
			if( ( this.squares[i].getRow() != other.squares[i].getRow() ) ||
				( this.squares[i].getCol() != other.squares[i].getCol() ) )
				return false;
		if( !this.parentHexomino.equals(other.parentHexomino) )
			return false;
		// Everything is the same
		return true;
	}

}