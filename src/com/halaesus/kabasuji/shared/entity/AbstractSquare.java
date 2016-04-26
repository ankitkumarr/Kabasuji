package com.halaesus.kabasuji.shared.entity;

import java.io.Serializable;

public class AbstractSquare implements Serializable{

	private static final long serialVersionUID = 1328718228720205152L;
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