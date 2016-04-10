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
    	// TODO: Grab file from the drive
    	levels = new LevelList();
    	levels.addLevelData(new LevelData(1, "Lighning", "Level1", "nofile"));
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
    	// TODO: We don't need this one :(
    }

    /**
     * @param File progressFile
     */
    public boolean saveToFile(File progressFile) {
    	return false;
        // TODO implement here
    }

}