package com.halaesus.kabasuji.player.entity;

import java.io.File;

public class LightningLevel extends AbstractLevel {

    int numRandPieces;
    int elapsedTime;
    int maxTime;

    public LightningLevel(File file) {
    	super(file);
    	// Set the game type in here
    	levelType = "Lightning";
    }

    public int getTimeLeft() {
    	return 0;
        // TODO implement here
    }

}