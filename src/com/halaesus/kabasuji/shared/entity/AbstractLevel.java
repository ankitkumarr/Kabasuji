package com.halaesus.kabasuji.shared.entity;

import java.awt.Point;

import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;

/**
 * Represents the most basic form of a KabaSuji Board
 * <p>
 * <b>AbstractLevel</b> contains information that is shared across all types of level:
 * PuzzleLevel, ReleaseLevel and LightningLevel
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public abstract class AbstractLevel {

	// Some Constants
	/** Constant signifying that there is no drag active */
	public static final int NO_DRAG_ACTIVE = -1;
	
	/** Constant signifying that the source of the drag is the Player Workspace */
	public static final int DRAG_SOURCE_WORKSPACE = 1;
	
	/** Constant signifying that the source of the drag is the Game Board */
	public static final int DRAG_SOURCE_BOARD = 2;
	
	// Some more constants
	/** Constant signifying that the level is not yet complete */
	public static final int LEVEL_COMPLETION_NOT_COMPLETE = -1;
	
	/** Constant signifying that the player ran out of pieces to play and thus the level finished */
	public static final int LEVEL_COMPLETION_OUT_OF_PIECES = 0;
	
	/** Constant signifying that the player ran out of moves and thus the level finished */
	public static final int LEVEL_COMPLETION_OUT_OF_MOVES = 1;
	
	/** Constant signifying that the player ran out of time to play and thus the level finished */
	public static final int LEVEL_COMPLETION_RAN_OUT_OF_TIME = 2;
	
	/** Constant signifying that the player achieved 3 stars on the level and successfully finished the level */
	public static final int LEVEL_COMPLETION_FINISHED_LEVEL = 3;
	
	/** Constant signifying that the player pressed the Back Button to go back to the LevelSelectorView */
	public static final int LEVEL_COMPLETION_QUIT_LEVEL = 4;

	// Level Completion Info Shown
	/** Boolean to keep track if the LevelCompletion Information is shown to the user */
	boolean levelCompletedShown;
	
	/** Integer value to keep track of the LevelCompletiton Status visible or to be shown to the user */
	int levelCompletionStatus;
	
	// Level Specific Information
	/** An AbstractBoard to store the Board information */
    AbstractBoard board;
    
    /** The type of the level. Could be one of: "Lightning", "Puzzle" and "Release" */
    String levelType;
    
    /** The level Bullpen which holds the Player Workspace and the 35 Hexominos */
	Bullpen bullpen;
	
	/** The level index */
    int levelIndex;
    
    /** The name for specific level */
    String levelName;
    
	// Keep track of dragging stuff
    /** Boolean to keep track if there is an active drag in progress */
    boolean isDraggingActive = false;
    
    /** Boolean to keep track if the piece being dragged is currently over the Player Bullpen */
    boolean pieceOverBullpen = false;
    
    /** Point to keep track of the topmost point of the Piece being dragged */
	Point topPointOfDraggingPiece;
	
	/** Distance of the click from the topmost point of the Object being dragged in the X direction */
    int draggingDistToPointX;
    
    /** Distance of the click from the topmost point of the Object being dragged in the Y direction */
    int draggingDistToPointY;
    
    /** The Piece that is being dragged */
    Piece pieceBeingDragged;
    
    /** The source of the Drag Event */
    int dragSource = -1;
	
    // Methods
    /**
     * Constructs an <code>AbstractLevel</code> with a given <code>AbstractLevelMemento</code>
     * @param memento
     */
	public AbstractLevel(AbstractLevelMemento memento) {
		board = memento.getBoardMemento().generateBoard();
		bullpen = new Bullpen(memento.getPaletteMemento().generatePalette());
		levelIndex = memento.getLevelIndex();
		levelType = memento.getLevelType();
		levelName = memento.getLevelName();
		// Initialize Basic Level Completion Information
		levelCompletedShown = false; // By default the level has not been completed
		levelCompletionStatus = AbstractLevel.LEVEL_COMPLETION_NOT_COMPLETE;
	}
	
	/**
	 * A private constructor to create an <code>AbstractLevel</code> to assist the Copy Constructor for <code>AbstractLevel</code>
	 * @param board
	 * @param levelType
	 * @param levelName
	 * @param bullpen
	 * @param levelIndex
	 * @param isDraggingActive
	 * @param pieceOverBullpen
	 * @param topPointOfDraggingPiece
	 * @param draggingDistToPointX
	 * @param draggingDistToPointY
	 * @param pieceBeingDragged
	 * @param dragSource
	 */
	private AbstractLevel(AbstractBoard board, String levelType, String levelName, Bullpen bullpen, int levelIndex,
			boolean isDraggingActive, boolean pieceOverBullpen, Point topPointOfDraggingPiece, int draggingDistToPointX,
			int draggingDistToPointY, Piece pieceBeingDragged, int dragSource) {
		// Main goal of this PRIVATE constructor is for the copy constructor
		this.board = board;
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

	/**
	 * A public copy constructor for <code>AbstractLevel</code> that takes in another <code>AbstractLevel</code> which has to
	 * to be copied. The new level will have the same attributes as earlier but will be a different reference. 
	 * @param anotherLevel
	 */
	public AbstractLevel(AbstractLevel anotherLevel) {
		this(anotherLevel.board.makeCopy(), anotherLevel.levelType, anotherLevel.levelName, new Bullpen(anotherLevel.bullpen), 
			 anotherLevel.levelIndex, anotherLevel.isDraggingActive, anotherLevel.pieceOverBullpen, anotherLevel.topPointOfDraggingPiece,
			 anotherLevel.draggingDistToPointX, anotherLevel.draggingDistToPointY, anotherLevel.pieceBeingDragged, anotherLevel.dragSource);
	}
    
	/**
	 * Generates an AbstractLevelMemento with the attributes in the current class
	 * @return
	 */
	public abstract AbstractLevelMemento generateMemento();
	
	/**
	 * Return the Game Board of the <code>AbstractLevel</code>
	 * @return
	 */
    public AbstractBoard getBoard(){
    	return this.board;
    }
    
    /**
     * Returns the level type of the <code>AbstractLevel</code>
     * @return
     */
    public String getLevelType() {
		return levelType;
	}
    
    /**
     * Returns the level index of the <code>AbstractLevel</code>
     * @return
     */
    public int getLevelIndex() {
		return levelIndex;
	}
    
    /**
     * Sets the level index of the <code>AbstractLevel</code> to the specified levelIndex
     * @param levelIndex  the desired level index for the <code>AbstractLevel</code>
     */
    public void setLevelIndex(int levelIndex) {
  		this.levelIndex = levelIndex;
  	}
    
    /**
     * Returns the Player Bullpen from the <code>AbstractLevel</code>
     * @return
     */
    public Bullpen getLevelBullpen() {
    	return bullpen;
    }
    
    /**
     * Returns if there is an active drag in progress on the <code>AbstractLevel</code>
     * @return
     */
	public boolean isDraggingActive() {
		return isDraggingActive;
	}

	/**
	 * Sets if there is an active drag on the <code>AbstractLevel</code>
	 * @param isDraggingActive
	 */
	public void setDraggingActive(boolean isDraggingActive) {
		this.isDraggingActive = isDraggingActive;
	}

	/**
	 * Gets the Piece that is being dragged over the <code>AbstractLevel</code>
	 * @return null if there is no drag in progress
	 */
	public Piece getPieceBeingDragged() {
		return pieceBeingDragged;
	}

	/**
	 * Sets the Piece that is being dragged on the <code>AbstractLevel</code>
	 * @param pieceBeingDragged
	 */
	public void setPieceBeingDragged(Piece pieceBeingDragged) {
		this.pieceBeingDragged = pieceBeingDragged;
	}

	/**
	 * Gets the distance of the click for the drag to the topmost point of the dragging object in the X direction
	 * @return -1 if there is no drag in progress
	 */
	public int getDraggingDistToPointX() {
		return draggingDistToPointX;
	}

	/**
	 * Sets the distance of the click for the drag to the topmost point of the dragging object in the X direction
	 * @param draggingDistToPointX
	 */
	public void setDraggingDistToPointX(int draggingDistToPointX) {
		this.draggingDistToPointX = draggingDistToPointX;
	}

	/**
	 * Gets the distance of the click for the drag to the topmost point of the dragging object in the Y direction
	 * @return -1 if there is no drag in progress
	 */
	public int getDraggingDistToPointY() {
		return draggingDistToPointY;
	}

	/**
	 * Sets the distance of the click for the drag to the topmost point of the dragging object in the Y direction
	 * @param draggingDistToPointY
	 */
	public void setDraggingDistToPointY(int draggingDistToPointY) {
		this.draggingDistToPointY = draggingDistToPointY;
	}

	/**
	 * Gets the topmost point of the Piece being dragged
	 * @return null if there is no drag in progress
	 */
	public Point getTopPointOfDraggingPiece() {
		return topPointOfDraggingPiece;
	}

	/**
	 * Sets the top-left point of the object that is being dragged
	 * @param topPointOfDraggingPiece
	 */
	public void setTopPointOfDraggingPiece(Point topPointOfDraggingPiece) {
		this.topPointOfDraggingPiece = topPointOfDraggingPiece;
	}

	/**
	 * Gets the source location of the Drag
	 * @return <code>NO_DRAG_ACTIVE</code> is returned if there is no active drag
	 */
	public int getDragSource() {
		return dragSource;
	}

	/**
	 * Sets the source location of the Drag
	 * @param dragSource
	 */
	public void setDragSource(int dragSource) {
		this.dragSource = dragSource;
	}
	
	/**
	 * Gets if the Piece being dragged over the Player Bullpen
	 * @return
	 */
    public boolean isPieceOverBullpen() {
		return pieceOverBullpen;
	}

    /**
     * Sets if the Piece being dragged is over the Player Bullpen
     * @param pieceOverBullpen
     */
	public void setPieceOverBullpen(boolean pieceOverBullpen) {
		this.pieceOverBullpen = pieceOverBullpen;
	}
	
	/**
	 * Checks if the user has won the Level
	 * @return true if the user has achieved 3 stars on the level
	 */
	public boolean hasWon() {
		return (getStarsAchieved() == 3);
	}

	/**
	 * Gets the name of the <code>AbstractLevel</code>
	 * @return
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * Sets the level name of the <code>AbstractLevel</code>
	 * @param levelName
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * Gets if the LevelCompletion Information is visible to the user
	 * @return
	 */
	public boolean isLevelCompletedShown() {
		return levelCompletedShown;
	}

	/**
	 * Sets if the LevelCompletion Information should be visible to the user
	 * @param levelCompletedShown
	 */
	public void setLevelCompletedShown(boolean levelCompletedShown) {
		this.levelCompletedShown = levelCompletedShown;
	}

	/**
	 * Gets the LevelCompletion Information shown to the user
	 * @return
	 */
	public int getLevelCompletionStatus() {
		return levelCompletionStatus;
	}

	/**
	 * Sets the LevelCompletion Information to be shown to the user
	 * @param levelCompletionStatus
	 */
	public void setLevelCompletionStatus(int levelCompletionStatus) {
		this.levelCompletionStatus = levelCompletionStatus;
	}

	// Abstract Functions
	/**
	 * Returns the number of stars achieved by the user on the specific <code>AbstractLevel</code>
	 * @return  The number of stars achieved by the user
	 */
	public abstract int getStarsAchieved();
	
	/**
	 * This function is called by the Piece Controllers when a new Piece is added to the <code>AbstractLevel</code>
	 * @param p  the Piece that was added on the <code>AbstractLevel</code>
	 */
	public abstract void newPieceDropped(Piece p);
	
	/**
	 * This function is called by the Piece Controllers when an already existent Piece is moved to a different location in the <code>AbstractLevel</code>
	 * @param oldPieceSquares  an array of the PieceSquares of the previously placed Piece on the <code>AbstractLevel</code>
	 * @param newPiece  the updated Piece on the <code>AbstractLevel</code>
	 */
	public abstract void boardPieceUpdated(PieceSquare[] oldPieceSquares, Piece newPiece);
	
	/**
	 * This function is called by the Piece Controllers when an existent Piece was removed from the <code>AbstractLevel</code>
	 * @param p  the Piece that was removed from the <code>AbstractLevel</code>
	 */
	public abstract void boardPieceRemoved(Piece p);

}