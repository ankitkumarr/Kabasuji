package com.halaesus.kabasuji.player.entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

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
	public static LevelList loadList(){
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