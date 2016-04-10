package com.halaesus.kabasuji.player.boundary;

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
		// TODO implement here
	}

}