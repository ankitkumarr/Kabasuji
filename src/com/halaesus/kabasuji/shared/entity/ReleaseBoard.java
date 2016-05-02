package com.halaesus.kabasuji.shared.entity;
import java.util.*;

import com.halaesus.kabasuji.shared.memento.AbstractBoardMemento;
import com.halaesus.kabasuji.shared.memento.ReleaseBoardMemento;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class ReleaseBoard extends AbstractBoard {

    Set<ReleaseNumber> numbers;

	public ReleaseBoard(BoardSquare[][] squares, ArrayList<Piece> pieces, Set<ReleaseNumber> numbers) {
		super(squares, pieces);

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
    
    public void addNumber(ReleaseNumber rn) {
    	this.numbers.add(rn);
    }

	@Override
	public AbstractBoardMemento generateMemento() {
		return new ReleaseBoardMemento(getSquares(), getPieces(), numbers);
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
		return new ReleaseBoard(newSquares, new ArrayList<Piece>(), new HashSet<>(numbers));
	}
	
	public boolean doesCollide (ReleaseNumber rn) {
		for (ReleaseNumber rno: this.numbers) {
			if ((rno.getCol() == rn.getCol()) && (rno.getRow() == rn.getRow())) {
				return true;
			}
		}
		return false;
	}
    
}