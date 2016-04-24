package com.halaesus.kabasuji.player.entity;
import java.io.Serializable;
import java.util.*;


public class ReleaseBoard extends AbstractBoard implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7151804234480066207L;
	ArrayList<ReleaseNumber> numbers;

	public ReleaseBoard(BoardSquare[][] squares, Set<ReleaseNumber> numbers) {
		super(squares);

		// TODO remove this when we actually pass in releaseNumbers from a file
		// ReleaseNumbers
		this.numbers = new ArrayList<ReleaseNumber>();
		this.numbers.add(new ReleaseNumber(5, 1, 5, 5));
		this.numbers.add(new ReleaseNumber(1, 2, 3, 5));
		this.numbers.add(new ReleaseNumber(6, 3, 5, 8));
		//
	}
	
	public ReleaseBoard(){super();}

    public ArrayList<ReleaseNumber> getReleaseNumbers(){
    	return numbers;  	
    }
    
}