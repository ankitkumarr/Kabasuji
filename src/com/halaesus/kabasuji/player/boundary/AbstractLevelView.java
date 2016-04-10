package com.halaesus.kabasuji.player.boundary;

import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */
public class AbstractLevelView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1258037749653208863L;

	/**
	 * Default constructor
	 */
	public AbstractLevelView() {
	}

	/**
	 * 
	 */
	JButton backToMain;

	/**
	 * 
	 */
	JLabel levelInfo;

	/**
	 * 
	 */
	BufferedImage[] stars;

	/**
	 * 
	 */
	BufferedImage[] hexButtons;

	/**
	 * 
	 */
	JButton rotateCC;

	/**
	 * 
	 */
	JButton rotateCW;

	/**
	 * 
	 */
	JButton flipH;

	/**
	 * 
	 */
	JButton flipV;

	/**
	 * 
	 */
	BufferedImage[] workspacePieceSquares;

	/**
	 * 
	 */
	BufferedImage[] boardPieceSquares;

	/**
	 * 
	 */
	BufferedImage[] boardSquares;

	/**
	 * 
	 */
	JLabel[] hexCount;

}