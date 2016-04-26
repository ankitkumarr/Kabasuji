package com.halaesus.kabasuji.builder.boundary;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.halaesus.kabasuji.shared.AbstractLevel;
import com.halaesus.kabasuji.shared.Model;

@SuppressWarnings("serial")
public class Application extends JFrame {
	
	static Application inst;
	HashMap<String, BufferedImage> images;

    JPanel currentView;
    Model masterModel;
    
    SplashView splashView;
    AbstractBuilderView view;
    
    Application(Model m) {
    	// Initialize fields
		masterModel = m;
		splashView = new SplashView(masterModel.getSplashModel());
		images = new HashMap<String, BufferedImage>();
		
		// Set up GUI
		setTitle("Kabasuji Builder by Team Halaesus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setResizable(false);
		setPreferredSize(new Dimension(1280, 720)); // Force the window to be 1280x720
		pack(); // Force Swing to respect it
		// Now, calculate the difference and set the new size
		setPreferredSize(new Dimension(1280 + (1280 - getContentPane().getSize().width), 
				                       720 + (720 - getContentPane().getSize().height)));
		pack(); // Force Swing to resize
		// Center in screen
		setLocationRelativeTo(null); // Center in screen

		// Initialize
		splashView = new SplashView(masterModel.getSplashModel());
		
		// Start on Splash Screen
		showSplashScreen(-1);
    }
    
    public static Application instance(Model m) {
		if (inst == null) {
			inst = new Application(m);
			return inst;
		}
		
		throw new IllegalStateException("Application already configured.");
    }
    
	public static Application instance() {
		if (inst == null) {
			throw new IllegalStateException("Application not yet configured.");
		}
		
		return inst;
	}

	public void showSplashScreen(int time) {
		currentView = splashView;
		setContentPane(currentView);

		if (time < 0) return;
		
		LevelManagerDialog.main(null);
	}
	
	public void show(AbstractLevel level, String type) {
		if (type.toUpperCase().equals("PUZZLE")) {
			view = new PuzzleBuilderView(this, level);
		} else if (type.toUpperCase().equals("LIGHTNING")) {
			view = new LightningBuilderView(this, level);
		} else if (type.toUpperCase().equals("RELEASE")) {
			view = new ReleaseBuilderView(this, level);
		}
		
		currentView = view;
		setContentPane(view);
	}
	
	public BufferedImage getImage(String name) {
		return images.get(name);
	}
	
	public HashMap<String, BufferedImage> getAllImages() {
		return images;
	}
}
