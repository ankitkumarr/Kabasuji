package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;


public class MementoReleaseLevel implements Serializable{

	private static final long serialVersionUID = -8568493257807759545L;
	
	int levelIndex;
	AbstractBoard releaseBoard = new ReleaseBoard(null,null);
	Bullpen bullpen = new Bullpen();

	public MementoReleaseLevel(int levelIndex, AbstractBoard releaseBoard, Bullpen bullpen) {
		this.levelIndex = levelIndex;
		this.releaseBoard = releaseBoard;
		this.bullpen = bullpen;
	}
}
