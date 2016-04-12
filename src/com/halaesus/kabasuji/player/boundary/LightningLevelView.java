package com.halaesus.kabasuji.player.boundary;

import java.awt.Graphics;

import javax.swing.JLabel;

import com.halaesus.kabasuji.player.entity.LightningLevel;

@SuppressWarnings("serial")
public class LightningLevelView extends AbstractLevelView {

	JLabel timeRemaining;
	LightningLevel level;

	public LightningLevelView(Application anApplication, LightningLevel aLevel) {
		super(anApplication); // Let the super initialize itself
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}