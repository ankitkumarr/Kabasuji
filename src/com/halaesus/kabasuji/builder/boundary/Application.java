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
    PuzzleBuilderView pbv;
    LightningBuilderView lbv;
    ReleaseBuilderView rbv;
    
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
		pbv = new PuzzleBuilderView();
		lbv = new LightningBuilderView();
		rbv = new ReleaseBuilderView();
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
		abv.showDialog(this);
	}
	
	public void showPuzzleBuilderView() {
		currentView = pbv;
		setContentPane(currentView);
	}

	
	public void showLightningBuilderView() {
		currentView = lbv;
		setContentPane(currentView);
	}

	
	public void showReleaseBuilderView() {
		currentView = lbv;
		setContentPane(currentView);
	}
}
