package com.halaesus.kabasuji.player;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.halaesus.kabasuji.player.boundary.Application;

public class Main {
	public static void main(String[] args) {
		final Application app = new Application();

		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.dispose();
			}      
		});

		app.setVisible(true);
	}
}
