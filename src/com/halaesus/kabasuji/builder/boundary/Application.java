package com.halaesus.kabasuji.builder.boundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.halaesus.kabasuji.builder.entity.Model;
import com.halaesus.kabasuji.builder.boundary.SplashView;

@SuppressWarnings("serial")
public class Application extends JFrame {
	
	static Application inst;

    JPanel currentView;
    Model masterModel;
    
    SplashView splashView;
    AbstractBuilderView abv;
    PuzzleBuilderView pbv;
    LightningBuilderView lbv;
    ReleaseBuilderView rbv;
    
    Application(Model m) {
    	// Initialize fields
		masterModel = m;
		splashView = new SplashView(masterModel.getSplashModel());
		abv = new AbstractBuilderView();
		pbv = new PuzzleBuilderView();
		lbv = new LightningBuilderView();
		rbv = new ReleaseBuilderView();
		
		// Set up GUI
		setTitle("Kabasuji Builder by Team Halaesus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setResizable(false);
		setLocationRelativeTo(null); // Center in screen
		
		// Start on Splash Screen
		showSplashScreen(0);
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

		if (time <= 0) return;
		
		Timer timer = new Timer(time, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        Application.instance().showAbstractBuilderView();
		    }
		});
		
		timer.setRepeats(false);
		timer.start();
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
