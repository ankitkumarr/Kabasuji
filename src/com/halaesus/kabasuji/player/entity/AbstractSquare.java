package com.halaesus.kabasuji.player.entity;

public class AbstractSquare {

    boolean active;
    int color;

    public AbstractSquare(boolean active) {
        this.active = active;
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}