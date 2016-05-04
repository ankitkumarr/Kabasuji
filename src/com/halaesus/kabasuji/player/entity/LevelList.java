package com.halaesus.kabasuji.player.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;
import com.halaesus.kabasuji.shared.memento.LightningLevelMemento;
import com.halaesus.kabasuji.shared.memento.PuzzleLevelMemento;
import com.halaesus.kabasuji.shared.memento.ReleaseLevelMemento;

/**
 * Model class to hold data about the levels available to play
 * @author Corey Dixon
 */
public class LevelList implements Serializable {
	/** ID for serialization */
	private static final long serialVersionUID = -3275134577117921160L;
	/** Data about each level */
	ArrayList<LevelData> levels;

	/**
	 * Constructs a new LevelList
	 */
	public LevelList() {
		// Initialize
		levels = new ArrayList<LevelData>();
	}

	/**
	 * Creates a LevelList based on the index file, or d
	 * default LevelList if the index file is not available
	 * @return The LevelList created, or null
	 */
	@SuppressWarnings("resource")
	public static LevelList loadList() {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream("levelindex.ser"));
			return (LevelList) in.readObject();
		} catch (IOException e) {
			// Shhh... you didn't see this. We don't know who wrote it...
			LevelList list = new LevelList();
//			list.levels.add(new LevelData(0, "", "", ""));
			return list;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; // TODO find more elegant solution than returning null
	}

	/**
	 * Write this LevelList to the index file
	 */
	public void saveList() {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream("levelindex.ser"));
			Collections.sort(levels);
			out.writeObject(this);
			out.close();
		} catch (IOException e) {
			// TODO inform user of failure to save
			e.printStackTrace();
		}

	}

	/**
	 * Loads one of the levels in the list from disk
	 * @param index Index of level to load
	 * @return The level that was loaded
	 */
	public AbstractLevel loadLevel(int index) {
		ObjectInputStream in = null;
		for (int i = 0; i < levels.size(); i++) {
			if (levels.get(i).levelIndex == index) {
				try {
					in = new ObjectInputStream(new FileInputStream(levels.get(i).fileName));
					AbstractLevelMemento level = (AbstractLevelMemento) in.readObject();
					in.close();
					return level.generateLevel();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
		}
		return null; // not found
	}

	/**
	 * Overwrites an existing level in the list with data from a memento
	 * @param memento Level memento to write
	 * @param index Index of level to overwrite
	 */
	public void overwriteLevel(AbstractLevelMemento memento, int index) {
		for (int i = 0; i < levels.size(); i++) {
			if (levels.get(i).levelIndex == index) {
				ObjectOutputStream out;
				try {
					out = new ObjectOutputStream(new FileOutputStream(levels.get(i).fileName));
					out.writeObject(memento);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
	
	/**
	 * Adds a new level to the list
	 * @param name Name of the level
	 * @param levelType Type of the level
	 * @return Index of level
	 */
	public int newLevel(String name, String levelType) {
		AbstractLevelMemento memento;
		LevelData ld;
		int index = levels.size();
		Random rand = new Random();
		rand.setSeed(System.nanoTime());
		ld = new LevelData(index, levelType, name, name + rand.nextInt(10000) + ".ser");
		levels.add(ld);
		if (levelType.toUpperCase().equals("PUZZLE")) {
			memento = new PuzzleLevelMemento(index, levelType, name);
		} else if (levelType.toUpperCase().equals("LIGHTNING")) {
			memento = new LightningLevelMemento(index, levelType, name);
		} else if (levelType.toUpperCase().equals("RELEASE")) {
			memento = new ReleaseLevelMemento(index, levelType, name);
		}
		else
			return -1; // TODO throw exception instead?
		
		overwriteLevel(memento, index);
		saveList();
		return index;
	}
	
	/**
	 * Adds a new level to the list without saving to disk, used for testing
	 * @param name Name of level
	 * @param levelType Type of level
	 * @return File name of the level
	 */
	public String newTestLevel(String name, String levelType) {
		AbstractLevelMemento memento;
		LevelData ld;
		int index = levels.size();
		Random rand = new Random();
		rand.setSeed(System.nanoTime());
		String generatedFileName = name + rand.nextInt(10000) + ".ser";
		ld = new LevelData(index, levelType, name, generatedFileName);
		levels.add(ld);
		if (levelType.toUpperCase().equals("PUZZLE")) {
			memento = new PuzzleLevelMemento(index, levelType, name);
		} else if (levelType.toUpperCase().equals("LIGHTNING")) {
			memento = new LightningLevelMemento(index, levelType, name);
		} else if (levelType.toUpperCase().equals("RELEASE")) {
			memento = new ReleaseLevelMemento(index, levelType, name);
		}
		else
			return ""; // TODO throw exception instead?
		
		overwriteLevel(memento, index);
		return generatedFileName;
	}
	
	/**
	 * Deletes the level with the given index from this list
	 * @param index Index of level to delete
	 */
	public void deleteLevel(int index) {
		for(int i = index; i < levels.size(); i++) {
			if(levels.get(i).levelIndex == index) {
				LevelData ld = levels.get(i);
				File file = new File(ld.fileName);
				if(file.exists())
					file.delete();
				levels.remove(i);
				if(i < levels.size())
					levels.get(i).levelIndex -= 1;
			}
			else if(levels.get(i).levelIndex > index) {
				levels.get(i).levelIndex -= 1;
			}
		}
		saveList();
	}
	
	// TODO prevent other classes from changing level
	// should only use newLevel
	/**
	 * Adds a new LevelData to this list
	 * @param levelData LevelData to add
	 */
	public void addLevelData(LevelData levelData) {
		levels.add(levelData);
	}
	
	/**
	 * Gets the name of the level at the specified index
	 * @param index Index of level in question
	 * @return name of that level
	 */
	public String getLevelName(int index) {
		return levels.get(index).getLevelName();
	}

	/**
	 * Gets the number of levels stored
	 * @return levels.size()
	 */
	public int getLevelCount() {
		return levels.size();
	}

	/**
	 * Gets an iterator to go over the levels in this list
	 * @return Iterator for the levels being stored
	 */
	public Iterator<LevelData> getIterator() {
		return levels.iterator();
	}
	
	/**
	 * Gets LevelData for each level in an array
	 * @return Array containing LevelData for each level
	 */
	public LevelData[] getArray() {
		return levels.toArray(new LevelData[1]);
	}
	
	/**
	 * Swaps the indexes of two levels and resorts the list
	 * @param src Index 1
	 * @param tar Index 2
	 */
	public void swapIndexes(int src, int tar) {
		levels.get(src).levelIndex = tar;
		levels.get(tar).levelIndex = src;
		Collections.sort(levels);
		saveList();
	}
	
	/**
	 * Gets the type of the level at the given index
	 * @param index Index of level in question
	 * @return String indicating that level's type
	 */
	public String getLevelType(int index) {
		return levels.get(index).levelType;
	}
}
