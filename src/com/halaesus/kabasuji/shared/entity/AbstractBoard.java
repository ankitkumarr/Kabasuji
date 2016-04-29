package com.halaesus.kabasuji.shared.entity;

import java.util.ArrayList;
import java.util.Iterator;

import com.halaesus.kabasuji.shared.memento.AbstractBoardMemento;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public abstract class AbstractBoard {

	private BoardSquare[][] squares;
	ArrayList<Piece> pieces;

	public AbstractBoard(BoardSquare[][] squares, ArrayList<Piece> pieces) {
		this.squares = squares;
		// Initialize
		this.pieces = pieces;
	}
    
    public boolean isActive(int row, int col) {
    	return squares[row][col].isActive();
    }
    
    public boolean isFilled(int row, int col) {
    	return squares[row][col].isFilled();
    }
    
    public int getHint(int row, int col) {
    	return squares[row][col].getHint();
    }

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

    public boolean isOutsideBounds(Piece p) {
    	PieceSquare[] pSquares = p.getPieceSquares();
    	for (int i = 0; i < pSquares.length; i++) {
    		if(!(squares[pSquares[i].getRow()][pSquares[i].getCol()].active))
    			return true;
    	}
    	
    	return false;
    }

    public Iterator<Piece> getPiecesIt() {
    	return pieces.iterator();
    }
    
    public void addPiece(Piece p) {
    	this.pieces.add(p);
    }
    
	public BoardSquare[][] getSquares() {
		return squares;
	}
	
	public ArrayList<Piece> getPieces(){
		return this.pieces;
	}
	
	public void setSquares(BoardSquare[][] squares) {
		this.squares = squares;
	}
	
    public abstract AbstractBoardMemento generateMemento();
    public abstract AbstractBoard makeCopy();
    
}