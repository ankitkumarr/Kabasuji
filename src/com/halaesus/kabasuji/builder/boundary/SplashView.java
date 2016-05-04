package com.halaesus.kabasuji.builder.boundary;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.halaesus.kabasuji.shared.entity.SplashModel;

/**
 * View to display the Splash Screen for the KabaSuji Builder
 *
 */
@SuppressWarnings("serial")
public class SplashView extends JPanel {
	/** The associated model for this view */
	SplashModel splashModel;

	/**
	 * Generates a SplashView based on the data in the given SplashModel
	 * @param splashModel model to base this view on
	 */
	public SplashView(SplashModel splashModel) {
		this.splashModel = splashModel;

		setPreferredSize(new Dimension(1280, 720));
	}

	/**
	 * Paint this component by drawing the splash screen image
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(splashModel.getSplashImage().getScaledInstance(1280, -1, Image. SCALE_SMOOTH), 0, 0, null);
	}
}
