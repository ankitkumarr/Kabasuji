package com.halaesus.kabasuji.player.entity;
import java.io.File;
import java.util.*;

public class PlayerProgress {

	LevelList levels;
    File progressFile;
    ArrayList<Integer> starsEarned;
    
    public PlayerProgress() {
    	// Create a new object of type LeveList
    	levels = new LevelList();
    	// Add Dummy Levels to reflect the real game (To get 20 levels)
    	for(int i = 0; i < 6; i++) {
    		levels.addLevelData(new LevelData((3*i) + 1, "Puzzle", "PuzzleLevel" + (i+1), null));
    		levels.addLevelData(new LevelData((3*i) + 2, "Lightning", "LightningLevel" + (i+1), null));
    		levels.addLevelData(new LevelData((3*i) + 3, "Release", "ReleaseLevel" + (i+1), null));
    	}
    	levels.addLevelData(new LevelData((3*6) + 1, "Puzzle", "PuzzleLevel7", null));
		levels.addLevelData(new LevelData((3*6) + 2, "Lightning", "LightningLevel7", null));
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