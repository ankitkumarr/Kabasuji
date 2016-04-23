package com.halaesus.kabasuji.builder.entity;


import java.io.File;

/**
 * 
 */
public class AbstractLevel {

    /**
     * Default constructor
     */
    public AbstractLevel() {
    	
    	bullpen = new Bullpen();
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
    AbstractLevel level;

    /**
     * 
     */
    Palette playerPalette;

    /**
     * 
     */
    MoveManager moveManager;



    /**
     * @param int levelIndex
     */
    public AbstractLevel(int levelIndex) {
        // TODO implement here
    }

    /**
     * @param File file
     */
    public boolean saveFile(File file) {
        // TODO implement here
    	return false; // stub
    }
    
    public Bullpen getLevelBullpen(){
    	return bullpen;
    }

}