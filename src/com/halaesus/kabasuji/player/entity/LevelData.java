package com.halaesus.kabasuji.player.entity;

import java.io.File;
import java.io.Serializable;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.LightningLevel;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;


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
		return null;
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