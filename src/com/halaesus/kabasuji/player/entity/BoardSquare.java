package com.halaesus.kabasuji.player.entity;

public class BoardSquare extends AbstractSquare {

    boolean filled;
    boolean hint;

    public BoardSquare(boolean active) {
        super(active); // Let the super do its job
    }

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public boolean isHint() {
		return hint;
	}

	public void setHint(boolean hint) {
		this.hint = hint;
	}

}