package com.halaesus.kabasuji.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Timer;

import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.player.entity.LevelData;
import com.halaesus.kabasuji.player.entity.LevelList;
import com.halaesus.kabasuji.player.entity.LightningLevel;
import com.halaesus.kabasuji.player.entity.MementoPlayerProgress;
import com.halaesus.kabasuji.player.entity.Model;
import com.halaesus.kabasuji.player.entity.PlayerProgress;
import com.halaesus.kabasuji.player.entity.PuzzleLevel;
import com.halaesus.kabasuji.player.entity.ReleaseLevel;



public class Main {
	
	/** Where player's progress persistent data is stored across executions. */
	static final String progressStorage = "progress/progress.data";
	
	static void storeState(PlayerProgress p, String location) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(location));
			oos.writeObject(p.getState());
		} catch (Exception e) {
			System.err.println("Unable to store state for PlayerProgress:" + e.getMessage());
		}
		
		if (oos != null) {
			try { oos.close(); } catch (IOException ioe) { } 
		}
		
	}
	
	static PlayerProgress loadState(String location) {
		 ObjectInputStream ois = null;
		 PlayerProgress p = new PlayerProgress();
		 try {
			 ois = new ObjectInputStream (new FileInputStream(location));
			 MementoPlayerProgress m = (MementoPlayerProgress) ois.readObject();
			 ois.close();
			 p.restore(m);
		 } catch (Exception e) {
			 // unable to perform restore. 
			 System.err.println("Unable to load PlayerProgress state from:" + location);
			 p = null;
		 } 
		 // close down safely
		 if (ois != null) {
			 try { ois.close(); } catch (IOException ioe) { }
		 }	 
		 return p;
	}
	
	// TODO We do not need this once we can get levels from the Builder
		public static void saveLevels(LevelList levelList){
			String dir = "prebuilt_levels/";
			for (LevelData ld: levelList.getLevels()){
				if (ld.getLevelType().equals("Puzzle")){
					PuzzleLevel p = (PuzzleLevel) ld.getLevel();
					String formatIndex = String.format("%03d", p.getLevelIndex()); // put leading 0's to preserve order
					levelList.storeStatePuzzle(p, dir + formatIndex + ".puzzle");
				}
				if (ld.getLevelType().equals("Lightning")){
					LightningLevel l = (LightningLevel) ld.getLevel();
					String formatIndex = String.format("%03d", l.getLevelIndex()); // put leading 0's to preserve order
					levelList.storeStateLightning(l, dir + formatIndex + ".lightning");
				}
				if (ld.getLevelType().equals("Release")){
					ReleaseLevel r = (ReleaseLevel) ld.getLevel();
					String formatIndex = String.format("%03d", r.getLevelIndex()); // put leading 0's to preserve order
					levelList.storeStateRelease(r, dir + formatIndex + ".release");
				}	
			}	
			return;
		}
	
		
	public static void main(String[] args) {
		
		
		// Create the entity objects that form the basis of our model
		PlayerProgress p = loadState(progressStorage);
		if (p == null) {
			// no file so create new default PlayerProgress
			p = new PlayerProgress();
		}
		
		// The Master Model
		Model masterModel = new Model(p);
		// The Application to render the Model
		final Application app = new Application(masterModel);
		// Close Listener
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// save our player progress when we close
				storeState(masterModel.getPlayerProgress(), progressStorage);
				// TODO We Do not need this once we can get Levels from the Builder
				saveLevels(masterModel.getPlayerProgress().getLevelList());
				app.dispose();
			}      
		});
		// Show the application
		app.setVisible(true);
		// To switch over to the next Screen
		// TODO: Fix timer to 3s instead of 500ms
		Timer timer = new Timer(500, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        app.showLevelSelector();
		    }
		});
		// Start up the timer
		timer.setRepeats(false);
		timer.start();
	}
	
}
