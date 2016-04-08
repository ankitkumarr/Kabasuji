package com.halaesus.kabasuji.player.entity;

import java.io.File;



/**
 * 
 */
public class AbstractLevel {

    /**
     * Default constructor
     */
    public AbstractLevel() {
    }

    /**
     * 
     */
    AbstractBoard board;

    /**
     * 
     */
    int levelIndex;

    /**
     * 
     */
    Bullpen bullpen;

    /**
     * 
     */
    int starsAchieved;



    /**
     * @param int levelNum 
     * @param int starsAchieved 
     * @param File file
     */
    public AbstractLevel(int levelNum, int starsAchieved, File file) {
        // TODO implement here
    }

    /**
     * 
     */
    public void loadLevel() {
        // TODO implement here
    }

    /**
     * 
     */
    public boolean hasFinished() {
    	return false;
        // TODO implement here
    }

}