package com.halaesus.kabasuji.shared;

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