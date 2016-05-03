package com.halaesus.kabasuji.shared.entity;

import java.io.Serializable;

/**
 * Represents the most basic form of a Square in KabaSuji.
 * <p>
 * A Square could either be a PieceSquare or a BoardSquare
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public abstract class AbstractSquare implements Serializable {

	private static final long serialVersionUID = 1328718228720205152L;
	
	/** Boolean to keep track if the Square is active */
	boolean active;
	/** Integer to track the Color of the Square */
    int color;

    /**
     * Constructs an instance of <code>AbstractSquare</code> with the given property, if it is active or not
     * @param active
     */
    public AbstractSquare(boolean active) {
        this.active = active;
    }

    /**
     * Returns if the <code>AbstractSquare</code> is active
     * @return  false if the <code>AbstractSquare</code> is not active
     */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets if the <code>AbstractSquare</code> is active
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Returns the color of the <code>AbstractSquare</code>
	 * @return
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Sets the <code>AbstractSquare</code> color
	 * @param color
	 */
	public void setColor(int color) {
		this.color = color;
	}

}