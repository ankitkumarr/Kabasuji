package com.halaesus.kabasuji.player.entity;

import java.util.*;

public class LevelList {
	
    Set<LevelData> levels;

	public LevelList() {
		// Initialize
		levels = new HashSet<LevelData>();
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