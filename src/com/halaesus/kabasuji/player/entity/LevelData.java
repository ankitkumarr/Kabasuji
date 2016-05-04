package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;

/**
 * Model class to hold data about a particular level
 * @author Corey Dixon
 */
public class LevelData implements Serializable, Comparable<LevelData> {
	/** ID for serialization */
	private static final long serialVersionUID = -2749227260904434774L;
	/** Index of the level */
	int levelIndex;
	/** Type of the level */
    String levelType;
    /** Name of the level */
    String levelName;
    /** File name where level is stored on disk */
    String fileName;

    /**
     * Constructs LevelData with given information
     * @param index Index of level
     * @param type Type of level
     * @param name Name of level
     * @param fileName File name for level
     */
    public LevelData(int index, String type, String name, String fileName) {
        this.levelIndex = index;
        this.levelType = type;
        this.levelName = name;
        this.fileName = fileName;
    }
    
    // does not look like we use this
    /*public AbstractLevel loadLevel() {
    	return null;
    }*/

    /**
     * Gets the index of the level
     * @return levelIndex
     */
	public int getLevelIndex() {
		return levelIndex;
	}

	/**
	 * Gets the type of the level
	 * @return levelType
	 */
	public String getLevelType() {
		return levelType;
	}

	/**
	 * Gets the name of the level
	 * @return levelName
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * Gets the file name of the level
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Compare 2 levels by the index
	 */
	@Override
	public int compareTo(LevelData other) {
		return levelIndex - other.levelIndex;
	}
	
	/**
	 * Display index, name, and type when converting to a string
	 */
	@Override
	public String toString() {
		return "" + levelIndex + " " + levelName + " (" + levelType + ")";
	}
}
