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
    }

    
    public PlayerProgressMemento getState() {
		return new PlayerProgressMemento(starsEarned);
	}
    
	public void restore(PlayerProgressMemento m) {
		starsEarned = m.getStored();
	}

}