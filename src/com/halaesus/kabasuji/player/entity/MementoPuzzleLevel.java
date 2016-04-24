package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;


public class MementoPuzzleLevel implements Serializable{

	private static final long serialVersionUID = -6379036789267954418L;
	
	int levelIndex;
	int allowedMoves;
	AbstractBoard puzzleBoard;
	Bullpen bullpen;

	public MementoPuzzleLevel(int levelIndex, int allowedMoves, AbstractBoard puzzleBoard, Bullpen bullpen) {
		this.levelIndex = levelIndex;
		this.allowedMoves = allowedMoves;
		this.puzzleBoard = puzzleBoard;
		this.bullpen = bullpen;
	}
}
