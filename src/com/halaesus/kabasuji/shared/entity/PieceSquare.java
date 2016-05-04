package com.halaesus.kabasuji.shared.entity;

/**
 * Represents a Square that forms a part of a <code>Piece</code>
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
@SuppressWarnings("serial")
public class PieceSquare extends AbstractSquare {
	
	/** Represents the Parent Piece that the PieceSquare belongs to */
    Piece parentPiece;
    /** Represents the row of the certain PieceSquare */
    int row;
    /** Represents the column of the certain PieceSquare */
	int col;

	/**
	 * Generates a PieceSquare with the given attributes
	 * @param active
	 * @param col
	 * @param row
	 */
    public PieceSquare(boolean active, int col, int row) {
        super(active); // Let the super do its job
        // Save this information
        this.row = row;
        this.col = col;
    }
    
    /**
     * Generates a PieceSquare with the given attributes
     * @param col
     * @param row
     */
    public PieceSquare(int col, int row) {
        super(true); // Let the super do its job
        // Save this information
        this.row = row;
        this.col = col;
    }
    
    /**
     * Generates a copy of the <code>PieceSquare</code> with all the attributes preserved
     * @param toCopy
     */
    public PieceSquare(PieceSquare toCopy) {
    	this(toCopy.active, toCopy.col, toCopy.row);
    }
    
    /**
     * Sets the Parent Piece linked with the <code>PieceSquare</code>
     * @param parentPiece
     */
    public void setParentPiece(Piece parentPiece) {
		this.parentPiece = parentPiece;
	}

    /**
     * Gets the Parent Piece linked with the <code>PieceSquare</code>
     * @return
     */
	public Piece getParentPiece() {
		return parentPiece;
	}
	
	/**
	 * Gets the row associated with the <code>PieceSquare</code>
	 * @return
	 */
    public int getRow() {
		return row;
	}
    
    /**
     * Gets the column associated with the <code>PieceSquare</code>
     * @return
     */
	public int getCol() {
		return col;
	}

	/**
	 * Sets the row associated with the <code>PieceSquare</code>
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Sets the column associated with the <code>PieceSquare</code>
	 * @param col
	 */
	public void setCol(int col) {
		this.col = col;
	}

}