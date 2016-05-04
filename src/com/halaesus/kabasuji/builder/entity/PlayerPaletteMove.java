package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.Hexomino;
import com.halaesus.kabasuji.shared.entity.Piece;

/**
 * Abstract class to manage the player palette move
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public abstract class PlayerPaletteMove implements IMove {

	/**
	 * Stores the information of the hexomino
	 */
	Hexomino hexomino;
	
	/**
	 * Stores the piece that is moved
	 */
	Piece piece;

	/**
	 * getter for the hexomino
	 * @return
	 */
	public Hexomino getHexomino() {
		return hexomino;
	}
	
	/**
	 * setter for the hexomino
	 * @param hexomino
	 */
	public void setHexomino(Hexomino hexomino) {
		this.hexomino = hexomino;
	}
	
	/**
	 * Gets the piece to be moved
	 * @return
	 */
	public Piece getPiece() {
		return piece;
	}
	
	/*
	 * Sets the piece to be moved
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
}