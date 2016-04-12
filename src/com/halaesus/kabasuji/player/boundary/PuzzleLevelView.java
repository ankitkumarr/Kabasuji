package com.halaesus.kabasuji.player.boundary;

import javax.swing.JLabel;

import com.halaesus.kabasuji.player.entity.PuzzleLevel;

@SuppressWarnings("serial")
public class PuzzleLevelView extends AbstractLevelView {

	JLabel allowedMoves;
	PuzzleLevel level;
	
	public PuzzleLevelView(Application anApplication, PuzzleLevel aLevel) {
		super(anApplication); // Let the super do its tuff
	}

}