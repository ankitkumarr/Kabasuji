package com.halaesus.kabasuji.player.boundary;

import java.util.*;

import javax.swing.JLabel;

import com.halaesus.kabasuji.player.entity.ReleaseLevel;

@SuppressWarnings("serial")
public class ReleaseLevelView extends AbstractLevelView {

	NumberBarView numberBar;
	Set<JLabel> numbers;
	ReleaseLevel level;

	public ReleaseLevelView(Application anApplication, ReleaseLevel aLevel) {
		super(anApplication);  // Let the super do its stuff
	}

}