package com.halaesus.kabasuji.player.boundary;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.halaesus.kabasuji.player.entity.SplashModel;

public class SplashView extends JPanel {

	SplashModel splashModel;

	public SplashView(SplashModel splashModel) {
		this.splashModel = splashModel;
		// Initialize
		// TODO: initialize();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its job
		g.drawImage(splashModel.getSplashImage().getScaledInstance(1280, -1, Image. SCALE_SMOOTH), 0, 0, null);
	}

}