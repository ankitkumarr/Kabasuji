package com.halaesus.kabasuji.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Timer;

import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.player.boundary.SplashView;
import com.halaesus.kabasuji.player.entity.Model;
import com.halaesus.kabasuji.player.entity.SplashModel;

public class Main {
	
	public static void main(String[] args) {
		// The Master Model
		Model masterModel = new Model();
		// The Application to render the Model
		final Application app = new Application(masterModel);
		// Close Listener
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.dispose();
			}      
		});
		// Show the application
		app.setVisible(true);
		// To switch over to the next Screen
		Timer timer = new Timer(5000, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	System.out.println("In timer event");
		        app.showLevelSelector();
		    }
		});
		// Start up the timer
		timer.setRepeats(false);
		timer.start();
	}
	
}
