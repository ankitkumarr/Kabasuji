package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class MementoPlayerProgress implements Serializable{

	private static final long serialVersionUID = -6379036789267954418L;
	
	ArrayList<Integer> stored = new ArrayList<Integer>();
	
	public MementoPlayerProgress(ArrayList<Integer> starsEarned) {
		for (Integer s : starsEarned) {
			stored.add(s);
		}
	}
}
