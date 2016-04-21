package com.halaesus.kabasuji.player.entity;

import java.util.ArrayList;
import java.util.Iterator;

public class AbstractBoard {

	BoardSquare[][] squares;
	ArrayList<Piece> pieces;

	public AbstractBoard(BoardSquare[][] square) {
		// this.squares = square;
		
		// TODO remove this when we actually pass in board squares from a file
		// BoardSquares
		BoardSquare[][] boardSquares = new BoardSquare[12][12];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				boardSquares[i][j] = new BoardSquare(true);
			}
			
		}
		this.squares = boardSquares;
		// Initialize
		this.pieces = new ArrayList<Piece>();
	}
    
    public boolean isActive(int row, int col) {
    	return squares[row][col].isActive();
    }

    public boolean doesCollide(Piece p) {
    	
    	//This will store the squares 
    	PieceSquare[] pSquares = p.getPieceSquares();
    	
    	//Iterating through each piece of the board
    	for(Piece piece : pieces) {
    		//Getting the piece Squares of each piece
    		PieceSquare[] pieceSquares = piece.getPieceSquares();
    		for (int i = 0; i < 6 ; i++) {
    			for (int j = 0; j < 6; j++) {
    				// Comparing if the PieceSquares are equal
    				if( (pSquares[i].getRow() == pieceSquares[j].getRow()) &&
    				    (pSquares[i].getCol() == pieceSquares[j].getCol()) )
    					return true;
    					
    			}
    		}
    	}
		// TODO Remove
		for( Piece pi : pieces ) {
			System.out.print("Piece-DCF: ");
			for( PieceSquare ps : pi.originalSquares ) {
				System.out.print("R: " + ps.getRow() + "; C: " + ps.getCol() + "; ");
			}
			System.out.print("\n");
		}
		System.out.print("Piece-DCF-Incoming: ");
		for( PieceSquare ps : p.originalSquares ) {
			System.out.print("R: " + ps.getRow() + "; C: " + ps.getCol() + "; ");
		}
		System.out.print("\n");
 
    	return false;
    }

    public boolean isOutsideBounds(Piece p) {
    	
    	PieceSquare[] pSquares = p.getPieceSquares();
    	for (int i = 0; i < p.getPieceSquares().length; i++)
    	{
    		
    		if(!(squares[pSquares[i].getCol()][pSquares[i].getRow()].active))
    			return true;
    	}
    	return false;
        // TODO implement here
    }
    
    public AbstractBoard getBoard(){
    	return this;    	
    }

    public Iterator<Piece> getPieces() {
    	return pieces.iterator();
    }
    
}