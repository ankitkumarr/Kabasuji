package com.halaesus.kabasuji.player.entity;

import java.awt.Point;
import java.io.File;

public class AbstractLevel {

    AbstractBoard board;
    int starsAchieved;
    String levelType;
	Bullpen bullpen;
    int levelIndex;
    // Keep track of dragging stuff
    boolean isDraggingActive = false;
    Piece pieceBeingDragged;
    Point topPointOfMatrix;
    int draggingDistToPointX;
    int draggingDistToPointY;

	public AbstractLevel(File file) {
        // TODO implement here; Random stuff done here
		bullpen = new Bullpen();
    }

    public void loadLevel() {
        // TODO implement here
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

	public Point getTopPointOfMatrix() {
		return topPointOfMatrix;
	}

	public void setTopPointOfMatrix(Point topPointOfMatrix) {
		this.topPointOfMatrix = topPointOfMatrix;
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

}