package com.halaesus.kabasuji.shared;

import java.io.Serializable;

/**
 * Representation for a ReleaseNumber for ReleaseLevel.
 * <p>
 * The valid numbers are 1 through 6. There are only 3 valid colors, 1, 2, and 3.
 * @author Brian Keeley-DeBonis (bjkeeleydebonis@wpi.edu), Corey Dixon
 */
public class ReleaseNumber implements Serializable {

	private static final long serialVersionUID = -5097755564606845686L;
	boolean collected; // has this number been collected by the player?
    int color; // 1 = red ,2 = green ,3 = yellow
    int col;
    int row;
    int value; // the number value [1,6] of the releaseNumber
    
    public ReleaseNumber(int value, int color, int col, int row){
      	this.value = value;
    	this.color = color;
    	this.col = col;
    	this.row = row;  	
    	this.collected = false;
    }
        
    public int getValue(){
    	return this.value;
    }
    
    public boolean getCollected(){
    	return this.collected;    	
    }
    
    public void setCollected(){
    	this.collected = true;
    }
    public int getColor(){
    	return this.color;
    }
    public int getCol(){
    	return this.col;
    }
    public int getRow(){
    	return this.row;
    }
}
