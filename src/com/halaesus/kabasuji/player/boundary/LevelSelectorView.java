package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.halaesus.kabasuji.player.entity.LevelList;

/**
 * 
 */
public class LevelSelectorView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5441010600182846418L;

	/**
	 * Default constructor
	 */
	public LevelSelectorView() {
			
	}

	/**
	 * 
	 */
	Set<LevelThumbnailView> levelViews;

	/**
	 * 
	 */
	JButton exitProgram;

	/**
	 * 
	 */
	LevelList levels;

	/**
	 * @param LevelList
	 *            levels
	 */
	public LevelSelectorView(LevelList levels) {
		exitProgram = new JButton("Exit Game");
		this.levels = levels;
		
		// TODO implement here
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, 1280, 720);
	}

}