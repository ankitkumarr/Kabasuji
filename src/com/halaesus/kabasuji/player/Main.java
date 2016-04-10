package com.halaesus.kabasuji.player;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.player.entity.Model;

public class Main {
	
	public static void main(String[] args) {
		Model masterModel = new Model();
		
		final Application app = new Application(masterModel);

		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.dispose();
			}      
		});

		app.setVisible(true);
		
		// Show up the Splash Screen
		// TODO
	}
	
}
