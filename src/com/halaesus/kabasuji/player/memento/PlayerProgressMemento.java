package com.halaesus.kabasuji.player.memento;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerProgressMemento implements Serializable{

	private static final long serialVersionUID = -6379036789267954418L;
	
	ArrayList<Integer> stored = new ArrayList<Integer>();
	
	public PlayerProgressMemento(ArrayList<Integer> starsEarned) {
		for (Integer s : starsEarned) {
			stored.add(s);
		}
	}
	
	
	public ArrayList<Integer> getStored() {
		return stored;
	}
}
