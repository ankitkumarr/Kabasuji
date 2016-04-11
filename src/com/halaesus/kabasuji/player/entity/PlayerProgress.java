package com.halaesus.kabasuji.player.entity;
import java.io.File;
import java.util.*;

public class PlayerProgress {

    ArrayList<Integer> starsEarned;
	LevelList levels;
    File progressFile;
    // TODO: Design Decision - If the level is locked, set the stars earned to -1
    
    public PlayerProgress() {
    	// Create a new object of type LeveList
    	levels = new LevelList();
    	starsEarned = new ArrayList<Integer>();
    	// Add Dummy Levels to reflect the real game (To get 20 levels)
    	for(int i = 0; i < 6; i++) {
    		levels.addLevelData(new LevelData((3*i) + 1, "Puzzle", "PuzzleLevel" + (i+1), null));
    		levels.addLevelData(new LevelData((3*i) + 2, "Lightning", "LightningLevel" + (i+1), null));
    		levels.addLevelData(new LevelData((3*i) + 3, "Release", "ReleaseLevel" + (i+1), null));
    	}
    	levels.addLevelData(new LevelData((3*6) + 1, "Puzzle", "PuzzleLevel7", null));
		levels.addLevelData(new LevelData((3*6) + 2, "Lightning", "LightningLevel7", null));
		// Also add dummy stars
		starsEarned.add(2); starsEarned.add(3); starsEarned.add(1); starsEarned.add(2);
		starsEarned.add(3); starsEarned.add(1); starsEarned.add(0); starsEarned.add(0);
		starsEarned.add(0); starsEarned.add(-1); starsEarned.add(-1); starsEarned.add(-1);
		starsEarned.add(-1); starsEarned.add(-1); starsEarned.add(-1); starsEarned.add(-1);
		starsEarned.add(-1); starsEarned.add(-1); starsEarned.add(-1); starsEarned.add(-1);
    	// TODO: Grab file from the drive
    }

    public PlayerProgress(File progressFile) {
    	// TODO: We don't need this one :(
    }

    public boolean saveToFile(File progressFile) {
    	return false;
        // TODO implement here
    }

}