package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.Hexomino;

/**
 * Class to Decrement the Move allowed in the Player Palette
 * @author Anthony Panetta, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class DecrementPlayerPaletteMove extends PlayerPaletteMove {
	
	/**
	 * Creates an instance of the DecrementPlayerPaletteMove Class and sets the Hexomino
	 * @param hexomino
	 */
	public DecrementPlayerPaletteMove(Hexomino hexomino) {
		setHexomino(hexomino);
	}
   
	/**
	 * Checks if the move is valid
	 */
    public boolean isValid() {
    	return (getHexomino().getCount() > 0);
    }

    /**
     * Does the Move
     */
    public boolean doMove() {
    	// Check if the Move is valid or not
    	if (!isValid())
    		return false;
    	// If it is valid, perform the move and return
    	getHexomino().changeCount(-1);
    	// As the move was successful, so:
        return true;
    }
   
    /**
     * Undoes the move
     */
	public boolean undoMove() {
		// Undo the move by incrementing the Piece count
		getHexomino().changeCount(+1);
		// As the undo was successful, so:
        return true;
	}

	/**
	 * Redoes the move
	 */
	public boolean redoMove() {
		return doMove();
	}
}
