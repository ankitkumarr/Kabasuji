package com.halaesus.kabasuji.shared.entity;

import java.io.File;

/**
 * 
 * @author Corey Dixon
 *
 */
public class ReleaseLevel extends AbstractLevel {
  
	NumberBar numberBar;
	//ReleaseBoard releaseBoard;
	public  ReleaseLevel(File file) {
		super(file);
		
		// TODO construct releaseBoard from file data
		board = new ReleaseBoard(null, null); 
		
		// Set the game type in here
		levelType = "Release";
		
		numberBar = new NumberBar();
		
		// Setup the board; TODO: Clear up board loop
		BoardSquare[][] squares = new BoardSquare[12][12];
		for(int r = 0; r < 12; r++)
			for(int c = 0; c < 12; c++)
				squares[r][c] = new BoardSquare(true);
		board = new ReleaseBoard(squares, null);
	}
	
	public ReleaseLevel(ReleaseLevelMemento memento) {
		super(memento);
		numberBar = new NumberBar();
	}
	
    public NumberBar getNumberBar(){
    	return this.numberBar;
	}
        
    // Abstract Methods Filler

    @Override
    public int getStarsAchieved() {
    	return 0; // TODO: Fix this
    }
    
    @Override
	public void newPieceDropped(Piece p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boardPieceRemoved(Piece p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractLevelMemento generateMemento() {
		return new ReleaseLevelMemento(board.generateMemento(), bullpen.palette);
	}

}