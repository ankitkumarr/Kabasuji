package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractBoard;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

public class NumberToBoardMove {

   
    ReleaseNumber number;
    AbstractBoard board;

    public boolean doMove(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }

    public boolean isValid(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }

   
    public boolean undo(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }

    public boolean redo(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }

}