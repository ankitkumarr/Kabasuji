package com.halaesus.kabasuji.builder.boundary;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.halaesus.kabasuji.builder.entity.AbstractLevel;
import com.halaesus.kabasuji.builder.entity.Model;
import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.boundary.SplashView;

@SuppressWarnings("serial")
public class Application extends JFrame {
	
	static Application inst;
	HashMap<String, BufferedImage> images;

    JPanel currentView;
    Model masterModel;
    
    SplashView splashView;
    PuzzleBuilderView pbv;
    LightningBuilderView lbv;
    ReleaseBuilderView rbv;
    
    Application(Model m) {
    	// Initialize fields
		masterModel = m;
		splashView = new SplashView(masterModel.getSplashModel());
		images = new HashMap<String, BufferedImage>();
		// TODO these were causing problems, make them on demand now
//		pbv = new PuzzleBuilderView();
//		lbv = new LightningBuilderView();
//		rbv = new ReleaseBuilderView();
		
		// Set up GUI
		setTitle("Kabasuji Builder by Team Halaesus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//TODO: setSize(1280, 720);
		
		
		setResizable(false);
		setPreferredSize(new Dimension(1280, 720)); // Force the window to be 1280x720
		pack(); // Force Swing to respect it
		// Now, calculate the difference and set the new size
		setPreferredSize(new Dimension(1280 + (1280 - getContentPane().getSize().width), 
				                       720 + (720 - getContentPane().getSize().height)));
		pack(); // Force Swing to resize
		// Center in screen
		setLocationRelativeTo(null); // Center in screen
		// Save the Master Model given to us
		// Initialize
		splashView = new SplashView(masterModel.getSplashModel());
		//levelSelectorView = new LevelSelectorView(masterModel.getLevelSelectorModel(), Application.this);
		// Show Splash Screen
		//showSplashScreen();
		
		
		setLocationRelativeTo(null); // Center in screen
		
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
		
		Timer timer = new Timer(time, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
				// options in drop down menu
				String[] choices = { "Open from file", "New Puzzle Level", "New Lightning Level", "New Release Level" };

				// create popup, store response as String
				String action = (String) JOptionPane.showInputDialog(
						Application.instance(),
						"Open or create a new level", // text within popup
						"Select an option", // window title
						JOptionPane.QUESTION_MESSAGE, // icon
						null, choices, choices[0]);
				
				if (action == null) { // no input
					Application.instance().showSplashScreen(0);
					return;
				} else if (action.equals(choices[0])) { // open from file
					Application.instance().showPuzzleBuilderView(); // TODO
					return;
				} else if (action.equals(choices[1])) { // new puzzle
					Application.instance().showPuzzleBuilderView();
					return;
				} else if (action.equals(choices[2])) { // new lightning
					Application.instance().showLightningBuilderView();
					return;
				} else if (action.equals(choices[3])) { // new release
					Application.instance().showReleaseBuilderView();
					return;
				}
		    }
		});
		
		timer.setRepeats(false);
		timer.start();
	}
	
	
	public void showPuzzleBuilderView() {
		if (pbv == null) {
			AbstractLevel alevel = new AbstractLevel();
			pbv = new PuzzleBuilderView(Application.this, alevel); }
		currentView = pbv;
		setContentPane(currentView);
	}

	public void showLightningBuilderView() {
		if (lbv == null) {
			AbstractLevel alevel = new AbstractLevel();
			lbv = new LightningBuilderView(Application.this, alevel); }
		currentView = lbv;
		setContentPane(currentView);
	}

	public void showReleaseBuilderView() {
		if (rbv == null) {
			AbstractLevel alevel = new AbstractLevel();
			rbv = new ReleaseBuilderView(Application.this, alevel); }
		currentView = rbv;
		setContentPane(currentView);
	}
	
	public BufferedImage getImage(String name) {
		return images.get(name);
	}
	
	public HashMap<String, BufferedImage> getAllImages() {
		return images;
	}
}
