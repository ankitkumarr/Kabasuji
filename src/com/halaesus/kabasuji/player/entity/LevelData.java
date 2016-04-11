package com.halaesus.kabasuji.player.entity;

import java.io.File;

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

	public AbstractLevel produceLevel() {
		// TODO: Parse the file and return the correct Level Type. For now I'm returning a dummy level
		// return new AbstractLevel(new File(fileName));
		if( levelType.equals("Lightning") )
			return new LightningLevel(new File(fileName));
		else if( levelType.equals("Puzzle") )
			return new PuzzleLevel(new File(fileName));
		else if( levelType.equals("Release") )
			return new ReleaseLevel(new File(fileName));
		// If nothing matches, return a generic AbstractLevel
		return new AbstractLevel(new File(fileName));
	}

}