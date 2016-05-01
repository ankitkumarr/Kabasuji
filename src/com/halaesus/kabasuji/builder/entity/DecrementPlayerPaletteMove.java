package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Hexomino;

public class DecrementPlayerPaletteMove {
	Hexomino hex;
	
	public DecrementPlayerPaletteMove(Hexomino h) {
		hex = h;
	}

    public boolean doMove(AbstractLevel level) {
    	if (!isValid(level)) return false;
    	
    	hex.changeCount(-1);
        return true;
    }
   
    public boolean isValid(AbstractLevel level) {
    	if (hex.getCount() > 0) return true;
        return false;
    }
   
    public boolean undo(AbstractLevel level) {
    	hex.changeCount(+1);
        return true;
    }

    public boolean redo(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }
}
