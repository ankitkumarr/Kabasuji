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
import com.halaesus.kabasuji.player.entity.PlayerProgress;
import com.halaesus.kabasuji.player.memento.PlayerProgressMemento;
import com.halaesus.kabasuji.shared.entity.Model;

public class Main {
	
	/** Where player's progress persistent data is stored across executions. */
	static final String progressStorage = "progress.data";
	
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
			 PlayerProgressMemento m = (PlayerProgressMemento) ois.readObject();
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
	
	
	public static void main(String[] args) {
		// attempt to load PlayerProgress
		PlayerProgress p = loadState(progressStorage);
		if (p == null) {
			// no file so create new default PlayerProgress
			p = new PlayerProgress();
		}
		
		
		// The Master Model
		final Model masterModel = new Model(p);
		// The Application to render the Model
		final Application app = new Application(masterModel);
		// Close Listener
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		    	storeState(masterModel.getPlayerProgress(), progressStorage);
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
