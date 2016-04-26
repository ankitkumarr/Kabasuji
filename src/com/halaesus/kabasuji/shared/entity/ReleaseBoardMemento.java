/**
 * 
 */
package com.halaesus.kabasuji.shared;

import java.util.HashSet;
import java.util.Set;

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
	AbstractBoard generateBoard() {
		return new ReleaseBoard(squares, numbers);
	}

}
