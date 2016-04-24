package com.halaesus.kabasuji.builder.boundary;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.halaesus.kabasuji.builder.entity.SplashModel;

@SuppressWarnings("serial")
public class SplashView extends JPanel {

	SplashModel splashModel;

	public SplashView(SplashModel splashModel) {
		this.splashModel = splashModel;

		setPreferredSize(new Dimension(1280, 720));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its job
		g.drawImage(splashModel.getSplashImage().getScaledInstance(1280, -1, Image. SCALE_SMOOTH), 0, 0, null);
	}

}