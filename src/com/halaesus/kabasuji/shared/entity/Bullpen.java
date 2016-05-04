package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.utils.PieceGenerator;

/**
 * Represents a Player Bullpen.
 * <p>
 * A Player Bullpen holds a Palette of 35 Hexomino Pieces and a Workspace
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class Bullpen {

	/** Workspace that holds a Piece that is being constructed that is shown in the Player Bullpen */
    Workspace workspace;
    /** Bullpen Palette in the Player Bullpen which holds the 35 Hexomino Pieces */
    Palette palette;
	
    /**
     * Instantiate a Player Bullpen
     */
    public Bullpen() {
    	this.workspace = Workspace.resetInstance();
    	// Setup the Hexomino Pieces
    	Hexomino[] hexominoes = new Hexomino[35];
    	for(int i = 0; i < 35; i++) {
    		Piece hexominoPiece = new Piece(PieceGenerator.pieces[i]);
    		// Now, add it to the Hexominoes class
    		hexominoes[i] = new Hexomino(i, hexominoPiece);
    		// Set reference to the Hexomino
    		hexominoes[i].getPiece().setParentHexomino(hexominoes[i]);
    	}
    	// Set the palette up with the hexominoes
    	this.palette = new Palette(hexominoes);
    }
    
    /**
     * Instantiate a Player Bullpen with a given Palette
     * @param palette
     */
    public Bullpen(Palette palette) {
    	workspace = Workspace.resetInstance();
    	this.palette = palette;
    }
    
    /**
     * Copy Constructor for class <code>Bullpen</code>
     * <p>
     * Instantiates a Player Bullpen with the same Palette and Workspace.
     * <p>
     * @param anotherBullpen
     */
    public Bullpen(Bullpen anotherBullpen) {
    	this(); // Create a new Bullpen
    	// Initialize the fields
    	this.workspace = Workspace.instance();
    	this.palette = new Palette(anotherBullpen.palette);
    }

    /**
     * Returns the Workspace from the Player Bullpen
     * @return
     */
	public Workspace getWorkspace() {
		return workspace;
	}

	/**
	 * Returns the Palette of the 35 Hexomino Pieces from the Player Bullpen
	 * @return
	 */
	public Palette getPalette() {
		return palette;
	}    

}