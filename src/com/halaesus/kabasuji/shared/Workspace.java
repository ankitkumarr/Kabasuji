package com.halaesus.kabasuji.shared;

public class Workspace {

	private static Workspace ws;
	
	Piece piece;
	
    private Workspace() {
    	
    }
    
    public static Workspace resetInstance() {
    	ws = new Workspace();
		return ws;
    }
    
    public static Workspace instance() {
    	if(ws == null)
    		ws = new Workspace();
    	return ws;
    }

    public void removePiece() {
		piece = null; // Remove the piece from the Board
    }

    public void addPiece(Piece p) {
        piece = p; // Set the piece on the Board
    }
    
    public Piece getPiece() {
    	return piece;
    }

	public boolean pieceExists() {
		return (piece != null);
	}

}