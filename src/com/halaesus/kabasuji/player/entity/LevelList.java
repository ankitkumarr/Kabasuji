package com.halaesus.kabasuji.player.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class LevelList {
	
	ArrayList<LevelData> levels;
	
	
	// TODO we do not need the storeState functions in the Player once the Builder works
	// store state is a save to disk function
	
	 public void storeStatePuzzle(PuzzleLevel p, String location) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(location));
			oos.writeObject(p.getState());
		} catch (Exception e) {
			System.err.println("Unable to store state for PuzzleLevel:" + e.getMessage());
		}
		
		if (oos != null) {
			try { oos.close(); } catch (IOException ioe) { } 
		}
		
	}
	
	public PuzzleLevel loadStatePuzzle(String location) {
		 ObjectInputStream ois = null;
		 PuzzleLevel p = new PuzzleLevel();
		 try {
			 ois = new ObjectInputStream (new FileInputStream(location));
			 MementoPuzzleLevel m = (MementoPuzzleLevel) ois.readObject();
			 ois.close();
			 p.restore(m);
		 } catch (Exception e) {
			 e.printStackTrace();
			 // unable to perform restore. 
			 System.err.println("Unable to load PuzzleLevel state from:" + location);
			 p = null;
		 } 
		 // close down safely
		 if (ois != null) {
			 try { ois.close(); } catch (IOException ioe) { }
		 }	 
		 return p;
	}
	
	 public void storeStateRelease(ReleaseLevel r, String location) {
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(location));
				oos.writeObject(r.getState());
			} catch (Exception e) {
				System.err.println("Unable to store state for ReleaseLevel:" + e.getMessage());
			}
			
			if (oos != null) {
				try { oos.close(); } catch (IOException ioe) { } 
			}
			
		}
	 
	 public ReleaseLevel loadStateRelease(String location) {
		 ObjectInputStream ois = null;
		 ReleaseLevel r = new ReleaseLevel();
		 try {
			 ois = new ObjectInputStream (new FileInputStream(location));
			 MementoReleaseLevel m = (MementoReleaseLevel) ois.readObject();
			 ois.close();
			 r.restore(m);
		 } catch (Exception e) {
			 // unable to perform restore. 
			 System.err.println("Unable to load ReleaseLevel state from:" + location);
			 r = null;
		 } 
		 // close down safely
		 if (ois != null) {
			 try { ois.close(); } catch (IOException ioe) { }
		 }	 
		 return r;
	}
				
		
		public void storeStateLightning(LightningLevel l, String location) {
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(location));
				oos.writeObject(l.getState());
			} catch (Exception e) {
				System.err.println("Unable to store state for LightningLevel:" + e.getMessage());
			}
			
			if (oos != null) {
				try { oos.close(); } catch (IOException ioe) { } 
			}
			
		}
		
		public LightningLevel loadStateLightning(String location) {
			 ObjectInputStream ois = null;
			 LightningLevel l = new LightningLevel();
			 try {
				 ois = new ObjectInputStream (new FileInputStream(location));
				 MementoLightningLevel m = (MementoLightningLevel) ois.readObject();
				 ois.close();
				 l.restore(m);
			 } catch (Exception e) {
				 // unable to perform restore. 
				 System.err.println("Unable to load Lightning state from:" + location);
				 l = null;
			 } 
			 // close down safely
			 if (ois != null) {
				 try { ois.close(); } catch (IOException ioe) { }
			 }	 
			 return l;
		}

		
	public LevelList() {
		// Initialize
		levels = new ArrayList<LevelData>();
		String prebuiltDir = "prebuilt_levels/";
		// load all the levels and create them and give them to level data and
		// store in LevelList
		File prebuiltFolder = new File(prebuiltDir);
		File[] listOfFiles = prebuiltFolder.listFiles();

		
		int levelIndex = 1; // it's smarter to create the level index while reading in files
		// the index is just the order they are read in -Brian KD
		for (File f : listOfFiles) {
			if (f.isFile()) {	
				
				// get the extension, valid extensions are: .puzzle, .lightning, and .release
				String extension = "";
				int i = f.getName().lastIndexOf('.');
				if (i > 0) {
				    extension = f.getName().substring(i+1);
				}
				LevelData levelData = null;
				if (extension.equals("puzzle")){
					PuzzleLevel p = loadStatePuzzle(prebuiltDir + f.getName());
					if (p != null)
					levelData = new LevelData(levelIndex, "Puzzle", p);
				}
				if (extension.equals("lightning")){
					LightningLevel l = loadStateLightning(prebuiltDir + f.getName());
					if (l != null)
					levelData = new LevelData(levelIndex, "Lightning", l);
				}
				if (extension.equals("release")){
					ReleaseLevel r = loadStateRelease(prebuiltDir + f.getName());
					if (r != null)
					levelData = new LevelData(levelIndex, "Release", r);
				}
				
				// if we actually have a level
				if (levelData != null){
					levels.add(levelData);	
					levelIndex ++; // we need  to increment our levelIndex after successfully adding a level
				}
				
			}

		}
		System.out.println(levels.size());
	}
		
    public void addLevelData(LevelData levelData) {
    	levels.add(levelData);
    }
    
    public ArrayList<LevelData> getLevels(){
    	return this.levels;
    }
    
    public int getLevelCount() {
    	return levels.size();
    }
    
    public Iterator<LevelData> getIterator() {
    	return levels.iterator();
    }

}