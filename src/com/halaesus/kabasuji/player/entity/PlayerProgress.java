package com.halaesus.kabasuji.player.entity;
import java.util.*;

import com.halaesus.kabasuji.player.memento.PlayerProgressMemento;

public class PlayerProgress {

    ArrayList<Integer> starsEarned;
	LevelList levels;
    //File progressFile;
    // TODO: Design Decision - If the level is locked, set the stars earned to -1
    
    public PlayerProgress() {
    	// Create a new object of type LeveList
    	//levels = new LevelList();
    	levels = LevelList.loadList();// load our list of levels from file
    	starsEarned = new ArrayList<Integer>();
    	// Add Dummy Levels to reflect the real game (To get 20 levels)
    	/*for(int i = 0; i < 6; i++) {
    		levels.addLevelData(new LevelData((3*i) + 1, "Puzzle", "PuzzleLevel" + (i+1), "sampleFile"));
    		levels.addLevelData(new LevelData((3*i) + 2, "Lightning", "LightningLevel" + (i+1), "sampleFile"));
    		levels.addLevelData(new LevelData((3*i) + 3, "Release", "ReleaseLevel" + (i+1), "sampleFile"));
    	}
    	levels.addLevelData(new LevelData((3*6) + 1, "Puzzle", "PuzzleLevel7", "sampleFile"));
		levels.addLevelData(new LevelData((3*6) + 2, "Lightning", "LightningLevel7", "sampleFile"));
		// Also add dummy stars
		starsEarned.add(2); starsEarned.add(3); starsEarned.add(1); starsEarned.add(2);
		starsEarned.add(3); starsEarned.add(1); starsEarned.add(0); starsEarned.add(0);
		starsEarned.add(0); starsEarned.add(-1); starsEarned.add(-1); starsEarned.add(-1);
		starsEarned.add(-1); starsEarned.add(-1); starsEarned.add(-1); starsEarned.add(-1);
		starsEarned.add(-1); starsEarned.add(-1); starsEarned.add(-1); starsEarned.add(-1);
    	// TODO: Grab file from the drive
    	 */
    }

    
    public PlayerProgressMemento getState() {
		return new PlayerProgressMemento(starsEarned);
	}
    
	public void restore(PlayerProgressMemento m) {
		starsEarned = m.getStored();
	}

}