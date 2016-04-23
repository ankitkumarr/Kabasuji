package com.halaesus.kabasuji.builder.entity;



/**
 * 
 */
public class Workspace {

    /**
     * Default constructor
     */
    public Workspace() {
    }

    /**
     * 
     */
    Piece piece;

    /**
     * 
     */
    public Piece removePiece() {
		return null; // stub
        // TODO implement here
    }

    /**
     * @param Piece p
     */
    public void addPiece(Piece p) {
        
    	this.piece = p;
    }
    
    public boolean pieceExists() {
		return (piece != null);
	}
    
    public Piece getPiece(){
    	return piece;
    }

}