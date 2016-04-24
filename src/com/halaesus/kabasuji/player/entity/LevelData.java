package com.halaesus.kabasuji.player.entity;

import java.io.File;

public class LevelData {

    int levelIndex;
    String levelType;
 //   String levelName; // we dont need this unless we are supporting custom level names
   // String fileName;
    
    AbstractLevel level; // this is our actual level, could be release, puzzle, etc

    public LevelData(int index, String type, AbstractLevel level) {
        this.levelIndex = index;
        this.levelType = type;
      //  this.levelName = "blah";
        //this.fileName = fileName;
        this.level = level;
    }

    public AbstractLevel getLevel() {
    	return level;
    }

	public int getLevelIndex() {
		return levelIndex;
	}

	public String getLevelType() {
		return levelType;
	}

	//public String getFileName() {
	//	return fileName;
	//}
/*
	public AbstractLevel produceLevel() {
		// TODO: Parse the file and return the correct Level Type. For now I'm returning a dummy level
		// return new AbstractLevel(new File(fileName));
		if( levelType.equals("Lightning") )
			return new LightningLevel();
		else if( levelType.equals("Puzzle") )
			return new PuzzleLevel();
		else if( levelType.equals("Release") )
			return new ReleaseLevel();
		// If nothing matches, return a generic AbstractLevel
		return null;
	}
*/
}