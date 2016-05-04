package com.halaesus.kabasuji.builder.boundary;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Model;

/**
 * The overall view container for the KabaSuji builder.
 */
@SuppressWarnings("serial")
public class Application extends JFrame {
	/** The single Application instance that is running*/
	static Application inst;
	
	/** Collection to hold all of the images loaded from disc in memory */
	HashMap<String, BufferedImage> images;

	/** The View currently displayed */
    JPanel currentView;
    /** The Model to display */
    Model masterModel;
    
    /** The View to display the splash screen */
    SplashView splashView;
    /** The View to display the level being built */
    AbstractBuilderView view;
    
    /** 
     * Generates a new Application based on the given Model
     * @param m Model on which this view is based
     */
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
		showSplashScreen();
    }
    
    /**
     * Creates an instance based on the given model if there is not already
     * an instance configured
     * @param m The model to base the view on
     * @return Reference to the instance
     */
    public static Application instance(Model m) {
		if (inst == null) {
			inst = new Application(m);
			return inst;
		}
		
		throw new IllegalStateException("Application already configured.");
    }
    
    /**
     * Gets the instance if it exists
     * @return Reference to the instance
     */
	public static Application instance() {
		if (inst == null) {
			throw new IllegalStateException("Application not yet configured.");
		}
		
		return inst;
	}
	
	 public void destroyInstance(){
		   this.inst = null;
		}

	/**
	 * Shows the splash screen
	 */
	public void showSplashScreen() {
		currentView = splashView;
		setContentPane(currentView);
	}
	
	/**
	 * Shows a level for editing.
	 * 
	 * @param level The level to edit
	 * @param type The level type
	 * @param levelIndex The level's index
	 */
	public void showLevel(AbstractLevel level, String type, int levelIndex) {
		if (type.toUpperCase().equals("PUZZLE")) {
			view = new PuzzleBuilderView(this, level, levelIndex);
		} else if (type.toUpperCase().equals("LIGHTNING")) {
			view = new LightningBuilderView(this, level, levelIndex);
		} else if (type.toUpperCase().equals("RELEASE")) {
			view = new ReleaseBuilderView(this, level,  levelIndex);
		}
		
		currentView = view;
		setContentPane(view);
	}
	
	/**
	 * Gets the requested image from the collection.
	 * @param name Name of image
	 * @return The requested image
	 */
	public BufferedImage getImage(String name) {
		return images.get(name);
	}
	
	/**
	 * Gets the entire collection of images
	 * @return The collection of images
	 */
	public HashMap<String, BufferedImage> getAllImages() {
		return images;
	}
}
