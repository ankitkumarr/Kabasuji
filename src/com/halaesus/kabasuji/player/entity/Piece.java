package com.halaesus.kabasuji.player.entity;

import java.awt.Color;

public class Piece {

    PieceSquare[] squares; 
    int pivotRow;
    int pivotCol;
    Color color;

    public Piece(int pivotRow, int pivotCol, Color color, PieceSquare squareMap[]) {
        // Save the data
    	this.squares = squareMap;
    	this.pivotRow = pivotRow;
    	this.pivotCol = pivotCol;
    	this.color = color;
    }
    
    public PieceSquare[] getPieceSquares() {
    	return squares;
    }
    
    public Color getColor() {
    	return color;
    }

    public void rotateCC() {
        // TODO implement here
    }

    public void rotateCW() {
        // TODO implement here
    }

    public void flipH() {
        // TODO implement here
    }

    public void flipV() {
        // TODO implement here
    }

}