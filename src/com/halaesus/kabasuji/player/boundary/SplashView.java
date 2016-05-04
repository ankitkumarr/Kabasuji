package com.halaesus.kabasuji.player.boundary;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.halaesus.kabasuji.shared.entity.SplashModel;

/**
 * Represents the Splash Model View
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
@SuppressWarnings("serial")
public class SplashView extends JPanel {

	/** Contains the Splash Model */
	SplashModel splashModel;

	/**
	 * Instantiates a SplashView with the necessary SplashModel
	 * @param splashModel
	 */
	public SplashView(SplashModel splashModel) {
		this.splashModel = splashModel;
	}

	/**
	 * Overriding paintComponent(g) to draw the Splash View background image
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its job
		g.drawImage(splashModel.getSplashImage().getScaledInstance(1280, -1, Image. SCALE_SMOOTH), 0, 0, null);
	}
}
