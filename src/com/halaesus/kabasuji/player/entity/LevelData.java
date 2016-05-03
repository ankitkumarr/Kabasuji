package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;


/**
 * 
 * @author Corey Dixon
 *
 */
public class LevelData implements Serializable, Comparable<LevelData> {

	private static final long serialVersionUID = -2749227260904434774L;
	int levelIndex;
    String levelType;
    String levelName;
    String fileName;

    public LevelData(int index, String type, String name, String fileName) {
        this.levelIndex = index;
        this.levelType = type;
        this.levelName = name;
        this.fileName = fileName;
    }

    public AbstractLevel loadLevel() {
    	return null;
    }

	public int getLevelIndex() {
		return levelIndex;
	}

	public String getLevelType() {
		return levelType;
	}

	public String getLevelName() {
		return levelName;
	}

	public String getFileName() {
		return fileName;
	}


	@Override
	public int compareTo(LevelData other) {
		return levelIndex - other.levelIndex;
	}
	
	@Override
	public String toString() {
		return "" + levelIndex + " " + levelName + " (" + levelType + ")";
	}
	

}