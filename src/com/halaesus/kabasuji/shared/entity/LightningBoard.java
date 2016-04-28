package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.shared.memento.AbstractBoardMemento;
import com.halaesus.kabasuji.shared.memento.LightningBoardMemento;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class LightningBoard extends AbstractBoard {
   
    public LightningBoard(BoardSquare[][] squares) {
    	super(squares);
    }
    
    @Override
    public boolean doesCollide(Piece p) {
    	return false;
    }

	@Override
	public AbstractBoardMemento generateMemento() {
		return new LightningBoardMemento(getSquares());
	}

	@Override
	public AbstractBoard makeCopy() {
		BoardSquare[][] originalSquares = getSquares();
		int rows = originalSquares.length;
		int cols = originalSquares[0].length;
		// Now create copy
		BoardSquare[][] newSquares = new BoardSquare[rows][cols];
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++)
				newSquares[r][c] = new BoardSquare(originalSquares[r][c]);
		// Call and return
		return new LightningBoard(newSquares);
	}
    
}