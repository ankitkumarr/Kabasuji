/**
 * 
 */
package com.halaesus.kabasuji.shared.memento;

import java.util.ArrayList;

import com.halaesus.kabasuji.shared.entity.AbstractBoard;
import com.halaesus.kabasuji.shared.entity.BoardSquare;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PuzzleBoard;

/**
 * @author Corey Dixon
 *
 */
public class PuzzleBoardMemento extends AbstractBoardMemento {

	private static final long serialVersionUID = -403476375991445415L;
	
	public PuzzleBoardMemento() {
		super();
	}

	public PuzzleBoardMemento(BoardSquare[][] squares, ArrayList<Piece> pieces) {
		super(squares, pieces);
	}

	@Override
	public AbstractBoard generateBoard() {
		return new PuzzleBoard(squares, pieces);
	}

}
