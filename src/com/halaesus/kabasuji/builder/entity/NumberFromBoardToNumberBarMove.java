package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.NumberBar;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

public class NumberFromBoardToNumberBarMove implements IMove {

   
    ReleaseNumber number;
    NumberBar numberBar;
    
	@Override
	public Piece doMove(AbstractLevel level) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isValid(AbstractLevel level) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean redoMove(AbstractLevel level) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean undoMove(AbstractLevel level) {
		// TODO Auto-generated method stub
		return false;
	}

}