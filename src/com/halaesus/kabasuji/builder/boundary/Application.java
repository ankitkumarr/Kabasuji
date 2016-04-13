package com.halaesus.kabasuji.builder.boundary;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.halaesus.kabasuji.builder.entity.Model;
import com.halaesus.kabasuji.builder.boundary.SplashView;

@SuppressWarnings("serial")
public class Application extends JFrame {

    JPanel currentView;
    Model masterModel;
    
    SplashView splashView;
    AbstractBuilderView abv;
    
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
		abv = new AbstractBuilderView();
		// Show Splash Screen
		showSplashScreen();
    }

	public void showSplashScreen() {
		currentView = splashView; // Our current view is the Splash Screen
		setContentPane(currentView); // Show it on the UI
	}
	
	public void showAbstractBuilderView() {
		currentView = abv;
		setContentPane(currentView);
		String[] choices = {"Open from file", "New Puzzle Level",
						"New Lightning Level", "New Release Level"};
		
		String action = (String) JOptionPane.showInputDialog(this,
				"Open or create a new level",
				"Select an option",
				JOptionPane.QUESTION_MESSAGE,
				null,
				choices,
				choices[0]);
		if (action == null) return;
			// TODO
		if (action.equals(choices[0])) return;
			// TODO
		else if (action.equals(choices[1])) return;
			// TODO
		else if (action.equals(choices[2])) return;
			// TODO
		else if (action.equals(choices[3])) return;
			// TODO
	}
}
