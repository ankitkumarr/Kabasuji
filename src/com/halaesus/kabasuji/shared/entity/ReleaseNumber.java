package com.halaesus.kabasuji.shared.entity;

import java.io.Serializable;

/**
 * Representation for a ReleaseNumber for ReleaseLevel.
 * <p>
 * The valid numbers are 1 through 6. There are only 3 valid colors, 1, 2, and 3.
 * @author Brian Keeley-DeBonis (bjkeeleydebonis@wpi.edu), Corey Dixon
 */
public class ReleaseNumber implements Serializable {

	private static final long serialVersionUID = -5097755564606845686L;
	
	/** Represented if the Player has collected the ReleaseNumber */
	boolean collected;
	/** Represents the color of the ReleaseNumber, 1 indicating Red, 2 indicating Green and 3 indicating Yellow */
    int color;
    /** Signifies the column that the ReleaseNumber exists in */
    int col;
    /** Signifies the row that the ReleaseNumber exists in */
    int row;
    /** Represents the value of the ReleaseNumber, valid values being 1 through 6 both inclusive */
    int value;
    
    /**
     * Constructs a ReleaseNumber with collected as false by default
     * @param value
     * @param color
     * @param col
     * @param row
     */
    public ReleaseNumber(int value, int color, int col, int row){
      	this.value = value;
    	this.color = color;
    	this.col = col;
    	this.row = row;  	
    	this.collected = false;
    }
        
    /**
     * Returns the value of the <code>ReleaseNumber</code>
     * @return
     */
    public int getValue(){
    	return this.value;
    }
    
    /**
     * Returns if the user has collected the <code>ReleaseNumber</code>
     * @return
     */
    public boolean isCollected(){
    	return this.collected;    	
    }
    
    /**
     * Sets if the user has collected the <code>ReleaseNumber</code>
     */
    public void setCollected(){
    	this.collected = true;
    }
    
    /**
     * Returns the color associated with the <code>ReleaseNumber</code>
     * @return
     */
    public int getColor(){
    	return this.color;
    }
    
    /**
     * Returns the column associated with the <code>ReleaseNumber</code>
     * @return
     */
    public int getCol(){
    	return this.col;
    }
    
    /**
     * Returns the row associated with the <code>ReleaseNumber</code>
     * @return
     */
    public int getRow(){
    	return this.row;
    }
    
    /**
     * Sets the row associated with the <code>ReleaseNumber</code>
     * @param r
     */
    public void setRow(int r) {
    	this.row = r;
    }
    
    /**
     * Sets the column associated with the <code>ReleaseNumber</code>
     * @param c
     */
    public void setCol(int c) {
    	this.col = c;
    }
}
