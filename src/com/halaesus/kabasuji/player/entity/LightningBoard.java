package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;

public class LightningBoard extends AbstractBoard implements Serializable {

   
    /**
	 * 
	 */
	private static final long serialVersionUID = 8527273711373557838L;


	public LightningBoard(BoardSquare[][] squares) {
    	super(squares);
        // TODO implement here
    }
    
    
    @Override
    public boolean doesCollide(Piece p) {
    	
    	return false;
    }


    public int countMarked() {
    	return 0;
        // TODO implement here
    }
    
    

}