/**
 * 
 */
package com.halaesus.kabasuji.shared.memento;

import java.util.HashSet;
import java.util.Set;

import com.halaesus.kabasuji.shared.entity.AbstractBoard;
import com.halaesus.kabasuji.shared.entity.BoardSquare;
import com.halaesus.kabasuji.shared.entity.ReleaseBoard;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

/**
 * @author Corey Dixon
 *
 */
public class ReleaseBoardMemento extends AbstractBoardMemento {

	private static final long serialVersionUID = -1440200991438024797L;
	Set<ReleaseNumber> numbers;
	
	public ReleaseBoardMemento() {
		super();
		this.numbers = new HashSet<ReleaseNumber>();
	}
	
	public ReleaseBoardMemento(BoardSquare[][] squares, Set<ReleaseNumber> numbers) {
		super(squares);
		this.numbers = numbers;
	}

	@Override
	public AbstractBoard generateBoard() {
		return new ReleaseBoard(squares, numbers);
	}

}
