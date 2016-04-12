package com.halaesus.kabasuji.player.entity;

import java.io.File;

public class AbstractLevel {

    AbstractBoard board;
    int starsAchieved;
    String levelType;
	Bullpen bullpen;
    int levelIndex;

	public AbstractLevel(File file) {
        // TODO implement here
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

}