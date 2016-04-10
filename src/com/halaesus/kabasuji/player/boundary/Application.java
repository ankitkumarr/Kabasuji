package com.halaesus.kabasuji.player.boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 */
public class Application extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8128606962231914487L;

	/**
	 * Default constructor
	 */
	public Application() {
		setTitle("Halaesus Kabasuji");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
	}

	/**
	 * 
	 */
	JPanel currentView;

	/**
	 * @param JPanel
	 *            newPanel
	 */
	public void changeView(JPanel newPanel) {
		currentView = newPanel;
	}

}