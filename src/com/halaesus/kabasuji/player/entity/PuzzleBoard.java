package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;

public class PuzzleBoard extends AbstractBoard implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4005703306812295949L;

	public PuzzleBoard(BoardSquare[][] squares) {
    	super(squares);
        // TODO implement here
    }
	
	public PuzzleBoard(){
		super();
	}

}