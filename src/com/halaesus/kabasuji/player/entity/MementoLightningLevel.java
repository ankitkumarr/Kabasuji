package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;


public class MementoLightningLevel implements Serializable{

	private static final long serialVersionUID = -7129996063813589602L;
	
	int levelIndex;
	int numRandPieces;
    int maxTime;
	AbstractBoard lightningBoard = new LightningBoard(null);
	Bullpen bullpen = new Bullpen();

	public MementoLightningLevel(int levelIndex, int numRandPieces, int maxTime, AbstractBoard lightningBoard, Bullpen bullpen) {
		this.levelIndex = levelIndex;
		this.numRandPieces = numRandPieces;
		this.maxTime = maxTime;
		this.lightningBoard = lightningBoard;
		this.bullpen = bullpen;
	}
}
