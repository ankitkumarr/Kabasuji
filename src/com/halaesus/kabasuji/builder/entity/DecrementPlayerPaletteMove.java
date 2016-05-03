package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.Hexomino;

/**
 * @author Anthony Panetta, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class DecrementPlayerPaletteMove extends PlayerPaletteMove {
	
	public DecrementPlayerPaletteMove(Hexomino hexomino) {
		setHexomino(hexomino);
	}
   
    public boolean isValid() {
    	return (getHexomino().getCount() > 0);
    }

    public boolean doMove() {
    	// Check if the Move is valid or not
    	if (!isValid())
    		return false;
    	// If it is valid, perform the move and return
    	getHexomino().changeCount(-1);
    	// As the move was successful, so:
        return true;
    }
   
	public boolean undoMove() {
		// Undo the move by incrementing the Piece count
		getHexomino().changeCount(+1);
		// As the undo was successful, so:
        return true;
	}

	public boolean redoMove() {
		return doMove();
	}
}
