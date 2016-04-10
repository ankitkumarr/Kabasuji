package com.halaesus.kabasuji.player.boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.halaesus.kabasuji.player.entity.Model;

public class Application extends JFrame {
	
	JPanel currentView;
	Model masterModel;

	public Application(Model masterModel) {
		setTitle("Halaesus Kabasuji");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		
		this.masterModel = masterModel;
	}

	public void changeView(JPanel newPanel) {
		currentView = newPanel;
	}

}