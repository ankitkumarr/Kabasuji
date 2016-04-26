package com.halaesus.kabasuji.shared.entity;
import java.util.*;

/**
 * 
 * @author Corey Dixon
 *
 */
public class ReleaseBoard extends AbstractBoard {

    Set<ReleaseNumber> numbers;

	public ReleaseBoard(BoardSquare[][] squares, Set<ReleaseNumber> numbers) {
		super(squares);

		// TODO remove this when we actually pass in releaseNumbers from a file
		// ReleaseNumbers
		this.numbers = new HashSet<ReleaseNumber>();
		// TODO: Add all the numbers; When saving is done, read from the file
		for(int i = 0; i < 3; i++)
			for(int val = 0; val < 6; val++)
				this.numbers.add(new ReleaseNumber(val + 1, i + 1, i * 3, val));
		/* this.numbers.add(new ReleaseNumber(5, 1, 5, 5));
		this.numbers.add(new ReleaseNumber(1, 2, 3, 5));
		this.numbers.add(new ReleaseNumber(6, 3, 5, 8)); */
		//
	}

    public Set<ReleaseNumber> getReleaseNumbers(){
    	return numbers;  	
    }

	@Override
	public AbstractBoardMemento generateMemento() {
		return new ReleaseBoardMemento(getSquares(), numbers);
	}
    
}