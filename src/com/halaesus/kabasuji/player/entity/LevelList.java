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
 * 
 * @author Corey Dixon
 *
 */
public class LevelList implements Serializable {

	private static final long serialVersionUID = -3275134577117921160L;
	ArrayList<LevelData> levels;

	public LevelList() {
		// Initialize
		levels = new ArrayList<LevelData>();
	}

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return null; // not found
	}

	public void overwriteLevel(AbstractLevelMemento memento, int index) {
		for (int i = 0; i < levels.size(); i++) {
			if (levels.get(i).levelIndex == index) {
				ObjectOutputStream out;
				try {
					System.out.println(memento.getLevelIndex()+ "  " + index);
					out = new ObjectOutputStream(new FileOutputStream(levels.get(i).fileName));
					out.writeObject(memento);
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	
	public int newLevel(String name, String levelType) {
		AbstractLevelMemento memento;
		LevelData ld;
		int index = levels.size();
		System.out.println(index);
		Random rand = new Random();
		rand.setSeed(System.nanoTime());
		ld = new LevelData(index, levelType, name, name + rand.nextInt(10000) + ".ser");
		levels.add(ld);
		if (levelType.toUpperCase().equals("PUZZLE")) {
			memento = new PuzzleLevelMemento(index);
		} else if (levelType.toUpperCase().equals("LIGHTNING")) {
			memento = new LightningLevelMemento(index);
		} else if (levelType.toUpperCase().equals("RELEASE")) {
			memento = new ReleaseLevelMemento(index);
		}
		else
			return -1; // TODO throw exception instead?
		
		overwriteLevel(memento, index);
		System.out.println(memento.getLevelIndex());
		saveList();
		return index;
	}
	
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
	public void addLevelData(LevelData levelData) {
		levels.add(levelData);
	}

	public int getLevelCount() {
		return levels.size();
	}

	public Iterator<LevelData> getIterator() {
		return levels.iterator();
	}
	
	public LevelData[] getArray() {
		return levels.toArray(new LevelData[1]);
	}
	
	public void swapIndexes(int src, int tar) {
		levels.get(src).levelIndex = tar;
		levels.get(tar).levelIndex = src;
		Collections.sort(levels);
		saveList();
	}
	
	public String getLevelType(int index) {
		return levels.get(index).levelType;
	}
}