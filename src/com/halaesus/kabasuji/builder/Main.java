package com.halaesus.kabasuji.builder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Timer;

import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.entity.Model;

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
		// TODO: Fix timer to 3s instead of 500ms
		Timer timer = new Timer(500, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        app.showSuperBuilderView();
		    }
		});
		// Start up the timer
		timer.setRepeats(false);
		timer.start();
	}
}
