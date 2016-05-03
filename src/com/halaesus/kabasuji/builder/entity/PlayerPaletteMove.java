package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.Hexomino;
import com.halaesus.kabasuji.shared.entity.Piece;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public abstract class PlayerPaletteMove implements IMove {

	Hexomino hexomino;
	Piece piece;

	public Hexomino getHexomino() {
		return hexomino;
	}
	
	public void setHexomino(Hexomino hexomino) {
		this.hexomino = hexomino;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
}