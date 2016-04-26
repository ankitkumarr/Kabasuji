package com.halaesus.kabasuji.shared;

import java.awt.Point;
import java.io.File;

/**
 * 
 * @author Corey Dixon
 *
 */
public abstract class AbstractLevel {

	// Some Constants
	public static final int NO_DRAG_ACTIVE = -1;
	public static final int DRAG_SOURCE_WORKSPACE = 1;
	public static final int DRAG_SOURCE_BOARD = 2;
	
	// Level Specific Information
    AbstractBoard board;
    int starsAchieved;
    String levelType;
	Bullpen bullpen;
    int levelIndex;
    // Keep track of dragging stuff
    boolean isDraggingActive = false;
    boolean pieceOverBullpen = false;
	Point topPointOfDraggingPiece;
    int draggingDistToPointX;
    int draggingDistToPointY;
    Piece pieceBeingDragged;
    int dragSource = -1;

	public AbstractLevel(File file) {
        // TODO implement here; Random stuff done here
		bullpen = new Bullpen();
	/*	// BoardSquares
		BoardSquare[] boardSquares = new BoardSquare[144];
		for(int i = 0; i < 144; i++) {
			boardSquares[i] = new BoardSquare(true);
		}
		// Board
		board = new AbstractBoard(boardSquares);*/
    }
	
	public AbstractLevel(AbstractLevelMemento memento) {
		board = memento.board.generateBoard();
		bullpen = new Bullpen(memento.palette.generatePalette());
	}
    
	public abstract AbstractLevelMemento generateMemento();
	
    public AbstractBoard getBoard(){
    	return this.board;
    }
    
    public boolean hasFinished() {
    	return false;
        // TODO implement here
    }
    
    public String getLevelType() {
		return levelType;
	}
    
    public int getLevelIndex() {
		return levelIndex;
	}
    
    public Bullpen getLevelBullpen() {
    	return bullpen;
    }
    
	public boolean isDraggingActive() {
		return isDraggingActive;
	}

	public void setDraggingActive(boolean isDraggingActive) {
		this.isDraggingActive = isDraggingActive;
	}

	public Piece getPieceBeingDragged() {
		return pieceBeingDragged;
	}

	public void setPieceBeingDragged(Piece pieceBeingDragged) {
		this.pieceBeingDragged = pieceBeingDragged;
	}

	public int getDraggingDistToPointX() {
		return draggingDistToPointX;
	}

	public void setDraggingDistToPointX(int draggingDistToPointX) {
		this.draggingDistToPointX = draggingDistToPointX;
	}

	public int getDraggingDistToPointY() {
		return draggingDistToPointY;
	}

	public void setDraggingDistToPointY(int draggingDistToPointY) {
		this.draggingDistToPointY = draggingDistToPointY;
	}

	public Point getTopPointOfDraggingPiece() {
		return topPointOfDraggingPiece;
	}

	public void setTopPointOfDraggingPiece(Point topPointOfDraggingPiece) {
		this.topPointOfDraggingPiece = topPointOfDraggingPiece;
	}

	public int getDragSource() {
		return dragSource;
	}

	public void setDragSource(int dragSource) {
		this.dragSource = dragSource;
	}
	
    public boolean isPieceOverBullpen() {
		return pieceOverBullpen;
	}

	public void setPieceOverBullpen(boolean pieceOverBullpen) {
		this.pieceOverBullpen = pieceOverBullpen;
	}
	
	// Abstract Functions
	public abstract int getStarsAchieved();
	public abstract void newPieceDropped(Piece p);
	public abstract void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece);
	public abstract void boardPieceRemoved(Piece p);

}