package com.halaesus.kabasuji.player.entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Corey Dixon
 *
 */
public class LevelList implements Serializable {

	private static final long serialVersionUID = -3275134577117921160L;
	List<LevelData> levels;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		LevelData ld;
		int index = levels.size();
		ld = new LevelData(index, name, levelType, name + ".ser");
		levels.add(ld);
		saveList();
		return index;
	}
	
	public void deleteLevel(int index) {
		for(int i = 0; i < levels.size(); i++) {
			if(levels.get(i).levelIndex == index) {
				levels.remove(i);
				levels.get(i).levelIndex -= 1;
			}
			else if(levels.get(i).levelIndex > index) {
				levels.get(i).levelIndex -= 1;
			}
		}
		saveList();
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