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
		// Fetch the numbers from the Set given to us
		this.numbers = numbers;
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
	
	public boolean isOutsideBounds(ReleaseNumber rn) {
		BoardSquare bs[][] = this.getSquares();
		if (bs[rn.getRow()][rn.getCol()].active) {
			return false;
		}
		return true;
	}
    
}