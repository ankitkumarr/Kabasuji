package com.halaesus.kabasuji.player.entity;

import java.util.*;

public class LevelList {
	
    LinkedList<LevelData> levels;

	public LevelList() {
		// Initialize
		levels = new LinkedList<LevelData>();
	}
    
    public void addLevelData(LevelData levelData) {
    	levels.add(levelData);
    }
    
    public int getLevelCount() {
    	return levels.size();
    }
    
    public Iterator<LevelData> getIterator() {
    	return levels.iterator();
    }

}