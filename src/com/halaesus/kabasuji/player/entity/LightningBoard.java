package com.halaesus.kabasuji.player.entity;

public class LightningBoard extends AbstractBoard {

   
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