package com.halaesus.kabasuji.player.entity;

public class LevelData {

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

}