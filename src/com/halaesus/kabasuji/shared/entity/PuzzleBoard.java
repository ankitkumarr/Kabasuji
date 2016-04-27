package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.shared.memento.AbstractBoardMemento;
import com.halaesus.kabasuji.shared.memento.PuzzleBoardMemento;

/**
 * 
 * @author Corey Dixon
 *
 */
public class PuzzleBoard extends AbstractBoard {

    public PuzzleBoard(BoardSquare[][] squares) {
    	super(squares);
        // TODO implement here
    }

	@Override
	public AbstractBoardMemento generateMemento() {
		return new PuzzleBoardMemento(getSquares());
	}

}