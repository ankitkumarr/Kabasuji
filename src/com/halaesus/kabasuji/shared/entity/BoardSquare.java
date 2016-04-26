package com.halaesus.kabasuji.shared.entity;

import java.io.Serializable;

/**
 * 
 * @author Corey Dixon
 *
 */
public class BoardSquare extends AbstractSquare implements Serializable{

	private static final long serialVersionUID = -3708147432756262459L;
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