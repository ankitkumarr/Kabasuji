package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.shared.memento.AbstractBoardMemento;
import com.halaesus.kabasuji.shared.memento.LightningBoardMemento;

/**
 * 
 * @author Corey Dixon
 *
 */
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


	@Override
	public AbstractBoardMemento generateMemento() {
		return new LightningBoardMemento(getSquares());
	}
    
    

}