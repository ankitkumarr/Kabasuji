package com.halaesus.kabasuji.shared.entity;

/**
 * Represents the Workspace of the Player Bullpen
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class Workspace {

	/** Private instance of the Workspace because Workspace is a Singleton class */
	private static Workspace ws;
	
	/** The Piece that resides in the Workspace */
	Piece piece;
	
	/**
	 * Private constructor so that only we can initialize ourselves
	 */
    private Workspace() { }
    
    /**
     * Resets the Workspace and returns the new instance of the Workspace
     * @return
     */
    public static Workspace resetInstance() {
    	ws = new Workspace();
		return ws;
    }
    
    /**
     * Generates a new instance of the Workspace and returns it
     * @return
     */
    public static Workspace instance() {
    	if(ws == null)
    		ws = new Workspace();
    	return ws;
    }

    /**
     * Removes the Piece from the Workspace
     */
    public void removePiece() {
		piece = null; // Remove the piece from the Workspace
    }

    /**
     * Adds a Piece <code>p</code> to the Workspace
     * @param p
     */
    public void addPiece(Piece p) {
        piece = p; // Set the piece on the Workspace
    }
    
    /**
     * Returns the Piece that resides in the Workspace
     * @return
     */
    public Piece getPiece() {
    	return piece;
    }

    /**
     * Returns if there exists a Piece in the Workspace
     * @return
     */
	public boolean pieceExists() {
		return (piece != null);
	}

}