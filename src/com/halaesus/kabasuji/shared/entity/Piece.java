package com.halaesus.kabasuji.shared.entity;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;

import com.halaesus.kabasuji.utils.PieceGenerator;

/**
 * Represents the most basic unit of KabaSuji, a Piece
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu), Brian Keeley-DeBonis (bjkeeleydebonis@wpi.edu)
 */
public class Piece implements Serializable {

	private static final long serialVersionUID = 8539046452906569460L;
	
	/** Represents the original PieceSquares with which this Piece was generated */
	PieceSquare[] originalSquares;
	/** Represents the Parent Hexomino the Piece is related to */
	Hexomino parentHexomino;
	/** Represents the current PieceSquares of the Piece */
    PieceSquare[] squares; 
    /** Holds the color ID of the Piece to decide which color should be used to render the Piece */
    int colorID;

    /**
     * Instantiates a Piece with the given attributes
     * @param colorID the Color ID of the Piece to assign a color at render time
     * @param squareMap the PieceSquare map for the Piece
     * @param parentHexomino the Parent Hexomino the Piece is linked to
     */
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
     * A copy constructor for a Piece which generates a new Piece with all the same attributes as the original Piece
     * @param toCopy  the source Piece whose attributes are to be copied
     */
    public Piece(Piece toCopy) {
    	this(toCopy.colorID, toCopy.getPieceSquares(), toCopy.parentHexomino);
    }
    
    /**
     * Return an array of the original PieceSquares that were used to initialize the Piece
     * @return
     */
    public PieceSquare[] getOriginalPieceSquares() {
    	return originalSquares;
    }
    
    /**
     * Returns an array of the PieceSquares that are currently in use by the Piece
     * @return
     */
    public PieceSquare[] getPieceSquares() {
    	return squares;
    }
    
    /**
     * Returns the color that the Piece should be rendered in
     * @return
     */
    public Color getColor() {
    	return PieceGenerator.colors[colorID];
    }
    
    /**
     * Returns the color ID of the Piece to determine the color to render the Pieces
     * @return
     */
    public int getColorID() {
    	return colorID;
    }
    
    /**
     * Sets the Parent Hexomino to the desired <code>Hexomino</code>
     * @param parentHexomino
     */
    public void setParentHexomino(Hexomino parentHexomino) {
    	this.parentHexomino = parentHexomino;
    }
    
    /**
     * Fetches the Parent Hexomino
     * @return
     */
    public Hexomino getParentHexomino() {
    	return this.parentHexomino;
    }

    public void flipH() {
    	swapCols();
    }

    public void flipV() {
        swapRows();
    }
    
    private void transpose(){
    	for (PieceSquare s: this.squares){
			int r = s.getRow();
			int c = s.getCol();
			s.setRow(c);
			s.setCol(r);
		}
    }
    
    private void swapRows(){
    	for (PieceSquare s: this.squares){
			int r = s.getRow();	
			s.setRow(Math.abs(5-r));
		}
    }
    
    private void swapCols(){
    	for (PieceSquare s: this.squares){
			int c = s.getCol();	
			s.setCol(Math.abs(5-c));
		}
    }
    
    public void rotateCC(){
    	transpose();
    	swapRows();
    }
    
    public void rotateCW(){
    	swapRows();
    	transpose();
    }  
        
    // centers underlying PieceSquares within a 6x6 matrix.
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
	
	/**
	 * Returns an array of PieceSquares with all the PieceSquares pushed to the top-left of a 6x6 matrix
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
	
	public boolean noSquareAbove(PieceSquare source){
		for (PieceSquare s: this.squares){
			if (source.getRow() -1 == s.getRow()
					&& source.getCol() == s.getCol())return false;
		}	
		return true;
	}
	
	public boolean noSquareBelow(PieceSquare source){
		for (PieceSquare s: this.squares){
			if (source.getRow() +1 == s.getRow()
					&& source.getCol() == s.getCol())return false;
		}	
		return true;
	}
	
	public boolean noSquareRight(PieceSquare source){
		for (PieceSquare s: this.squares){
			if (source.getRow() == s.getRow()
					&& source.getCol() + 1 == s.getCol())return false;
		}	
		return true;
	}

	public boolean noSquareLeft(PieceSquare source){
		for (PieceSquare s: this.squares){
			if (source.getRow() == s.getRow()
					&& source.getCol() - 1 == s.getCol())return false;
		}	
		return true;
	}
	
	/**
	 * Checks for equality of two Pieces
	 */
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