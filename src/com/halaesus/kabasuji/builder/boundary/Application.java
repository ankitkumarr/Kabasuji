package com.halaesus.kabasuji.builder.boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.halaesus.kabasuji.builder.entity.Model;
import com.halaesus.kabasuji.builder.boundary.SplashView;

@SuppressWarnings("serial")
public class Application extends JFrame {

    JPanel currentView;
    Model masterModel;
    
    SplashView splashView;
    SuperBuilderView sbv;
    
    public Application(Model m) {
		// Basic GUI Stuff
		setTitle("Kabasuji Builder by Team Halaesus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setResizable(false); // Cannot rescale the window
		setLocationRelativeTo(null); // Center in screen
		
		masterModel = m;
		// Initialize
		splashView = new SplashView(masterModel.getSplashModel());
		// Show Splash Screen
		showSplashScreen();
    }

	public void showSplashScreen() {
		currentView = splashView; // Our current view is the Splash Screen
		setContentPane(currentView); // Show it on the UI
	}
	
	public void showSuperBuilderView() {
		currentView = sbv;
		setContentPane(currentView);
	}
}
