package com.halaesus.kabasuji.player.entity;

import java.io.File;

public class LightningLevel extends AbstractLevel {

    int numRandPieces;
    int elapsedTime;
    int maxTime;

    public LightningLevel(File file) {
    	super(file);
    	// Read from file
    	maxTime = 65; elapsedTime = 0; // TODO: Dummy values
    	// Set the game type in here
    	levelType = "Lightning";
    }

    public int getTimeLeft() {
    	return (maxTime - elapsedTime);
    }
    
    public void incrementElapsedTime() {
    	elapsedTime++;
    }
    
    public void resetElapsedTime() {
    	elapsedTime = 0;
    }

}