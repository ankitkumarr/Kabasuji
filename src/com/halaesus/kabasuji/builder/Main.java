package com.halaesus.kabasuji.builder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.entity.Model;

public class Main {
	static final int splashTime = 3000;
	
	public static void main(String[] args) {
		Model masterModel = new Model();
		final Application app = Application.instance(masterModel);

		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.dispose();
			}      
		});

		app.setVisible(true);
		app.showSplashScreen(splashTime);
	}
}
