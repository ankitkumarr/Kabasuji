package com.halaesus.kabasuji.player.entity;
import java.io.File;
import java.util.*;


public class PlayerProgress {

    ArrayList<Integer> starsEarned;
	LevelList levelList;
    File progressFile;
    // TODO: Design Decision - If the level is locked, set the stars earned to -1
    
    public PlayerProgress() {
    	// Create a new object of type LeveList
    	levelList = new LevelList(); // LeveList() handles looking on disk for level files
    	starsEarned = new ArrayList<Integer>();
    	
    	if (levelList.levels.size() == 0){ // we were unable to load any levels from disk
    	for(int i = 0; i < 5; i++) {
    		// Add Dummy Levels to reflect the real game (To get 15 levels)
    		PuzzleLevel p = new PuzzleLevel(); p.setLevelIndex((3*i) + 1);
    		LightningLevel l = new LightningLevel(); l.setLevelIndex((3*i) + 2);
    		ReleaseLevel r = new ReleaseLevel(); r.setLevelIndex((3*i) + 3);
    		
    		levelList.addLevelData(new LevelData(p.getLevelIndex(), "Puzzle", p));
    		levelList.addLevelData(new LevelData(l.getLevelIndex(), "Lightning", l));
    		levelList.addLevelData(new LevelData(r.getLevelIndex(), "Release", r));
			}
		}
	}
    
    // call this to update the stars earned after beating a level
    public void addStars(int levelIndex, int starsEarned){
    	this.starsEarned.set(levelIndex, starsEarned);
    }
    
    public LevelList getLevelList(){
    	return this.levelList;
    }
    
    public MementoPlayerProgress getState() {
		return new MementoPlayerProgress(starsEarned);
	}
    
	public void restore(MementoPlayerProgress m) {
		starsEarned = new ArrayList<Integer>();
		for (Integer s : m.stored) {
			starsEarned.add(s);
		}	
	}
}