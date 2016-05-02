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
		for (int i = 0; i < levels.getLevelCount(); i++) {
			if (i < 3 || i >= 15) starsEarned.add(0); // make first 3 levels and all custom levels unlocked
			else starsEarned.add(-1); // by default, make all other levels locked
		}
    }

    public void updateStarsEarned(int levelIndex, int earned){
    	// first check if we have stars for the index?
    	if (levelIndex >= levels.getLevelCount())return;// we can't add stars
    	
    	if (levelIndex + 1 < levels.getLevelCount()){ // is there a next level to unlock
    		if (starsEarned.get(levelIndex + 1) == -1 && earned > 0){ // is next level locked and we earned stars? 
    			starsEarned.set(levelIndex + 1, 0); // then unlock next level
    		}
    	}
    	
    	
    	if (starsEarned.get(levelIndex) > earned) return; // we performed worst then our recorded best
    	this.starsEarned.set(levelIndex, earned); // if we got to here, actually update the stars earned    	
    }
    

	public PlayerProgressMemento getState() {
		return new PlayerProgressMemento(starsEarned);
	}

	
	public void restore(PlayerProgressMemento m) {
		for (int i = 0; (i < m.getStored().size() && i < levels.getLevelCount()); i++) {
			starsEarned.set(i, m.getStored().get(i)); // safely load stars previously earned
			// make sure first 3 and custom levels are playable and not messed up
			if ((i < 3 || i >= 15) && starsEarned.get(i) == -1)
				starsEarned.set(i, 0);
			// make sure there are no locked levels seceding a level with stars earned
			if (i > 0) {
				if (starsEarned.get(i - 1) > 0 && starsEarned.get(i) == -1) {
					// if we earned stars for previous level and this level is locked, unlock it
					starsEarned.set(i, 0);
				}
			}
		}
	}

}

