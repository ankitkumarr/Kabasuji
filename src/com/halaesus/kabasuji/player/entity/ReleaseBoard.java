package com.halaesus.kabasuji.player.entity;
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
		this.numbers.add(new ReleaseNumber(5, 1, 5, 5));
		this.numbers.add(new ReleaseNumber(1, 2, 3, 5));
		this.numbers.add(new ReleaseNumber(6, 3, 5, 8));
		//
	}

    public Set<ReleaseNumber> getReleaseNumbers(){
    	return numbers;  	
    }

	@Override
	public AbstractBoardMemento generateMemento() {
		return new ReleaseBoardMemento(squares, numbers);
	}
    
}