package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Hexomino;

/**
 * 
 * @author Anthony Panetta
 *
 */
public class DecrementPlayerPaletteMove implements IMove {
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
   
	public boolean undoMove(AbstractLevel level) {
		hex.changeCount(+1);
        return true;
	}

	public boolean redoMove(AbstractLevel level) {
		return doMove(level);
	}

	@Override
	public boolean doMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean undoMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean redoMove() {
		// TODO Auto-generated method stub
		return false;
	}
}
