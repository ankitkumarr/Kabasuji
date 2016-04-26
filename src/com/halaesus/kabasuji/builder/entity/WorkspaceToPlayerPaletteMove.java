package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Bullpen;
import com.halaesus.kabasuji.shared.entity.Palette;
import com.halaesus.kabasuji.shared.entity.Piece;

public class WorkspaceToPlayerPaletteMove {

	
    Palette playerPalette;
    Bullpen bullpen;
    Piece pieceDragged;

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