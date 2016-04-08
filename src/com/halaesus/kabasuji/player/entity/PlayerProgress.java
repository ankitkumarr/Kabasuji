package com.halaesus.kabasuji.player.entity;
import java.io.File;
import java.util.*;

/**
 * 
 */
public class PlayerProgress {

    /**
     * Default constructor
     */
    public PlayerProgress() {
    }

    /**
     * 
     */
    LevelList levels;

    /**
     * 
     */
    File progressFile;

    /**
     * 
     */
    ArrayList<Integer> starsEarned;



    /**
     * @param File progressFile
     */
    public PlayerProgress(File progressFile) {
        // TODO implement here
    }

    /**
     * @param File progressFile
     */
    public boolean saveToFile(File progressFile) {
    	return false;
        // TODO implement here
    }

}