package com.halaesus.kabasuji.shared.entity;

import java.awt.Point;

import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
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
    String levelName;
	// Keep track of dragging stuff
    boolean isDraggingActive = false;
    boolean pieceOverBullpen = false;
	Point topPointOfDraggingPiece;
    int draggingDistToPointX;
    int draggingDistToPointY;
    Piece pieceBeingDragged;
    int dragSource = -1;
	
	public AbstractLevel(AbstractLevelMemento memento) {
		board = memento.getBoardMemento().generateBoard();
		//bullpen = new Bullpen(memento.getPaletteMemento().generatePalette());
		// TODO eventually use the bullpen from the builder file
		bullpen = new Bullpen(); // TODO: Remove default constructor of the Bullpen
		levelIndex = memento.getLevelIndex();
		levelType = memento.getLevelType();
		levelName = memento.getLevelName();
	}
	
	private AbstractLevel(AbstractBoard board, int starsAchieved, String levelType, String levelName, Bullpen bullpen, int levelIndex,
			boolean isDraggingActive, boolean pieceOverBullpen, Point topPointOfDraggingPiece, int draggingDistToPointX,
			int draggingDistToPointY, Piece pieceBeingDragged, int dragSource) {
		// Main goal of this PRIVATE constructor is for the copy constructor
		this.board = board;
		this.starsAchieved = starsAchieved;
		this.levelType = levelType;
		this.bullpen = bullpen;
		this.levelIndex = levelIndex;
		this.levelName = levelName;
		this.isDraggingActive = isDraggingActive;
		this.pieceOverBullpen = pieceOverBullpen;
		this.topPointOfDraggingPiece = topPointOfDraggingPiece;
		this.draggingDistToPointX = draggingDistToPointX;
		this.draggingDistToPointY = draggingDistToPointY;
		this.pieceBeingDragged = pieceBeingDragged;
		this.dragSource = dragSource;
	}

	public AbstractLevel(AbstractLevel anotherLevel) {
		this(anotherLevel.board.makeCopy(), anotherLevel.starsAchieved, anotherLevel.levelType, anotherLevel.levelName, new Bullpen(anotherLevel.bullpen), 
			 anotherLevel.levelIndex, anotherLevel.isDraggingActive, anotherLevel.pieceOverBullpen, anotherLevel.topPointOfDraggingPiece,
			 anotherLevel.draggingDistToPointX, anotherLevel.draggingDistToPointY, anotherLevel.pieceBeingDragged, anotherLevel.dragSource);
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
	
	public boolean hasWon() {
		return (getStarsAchieved() == 3);
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	// Abstract Functions
	public abstract int getStarsAchieved();
	public abstract void newPieceDropped(Piece p);
	public abstract void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece);
	public abstract void boardPieceRemoved(Piece p);

}