package com.halaesus.kabasuji.player.entity;

import java.awt.Color;

public class Piece {

    PieceSquare[] squares; 
    int pivotRow;
    int pivotCol;
    Color color;

    public Piece(int pivotCol, int pivotRow, Color color, PieceSquare squareMap[]) {
        // Save the data
    	this.squares = squareMap;
    	this.pivotRow = pivotRow;
    	this.pivotCol = pivotCol;
    	this.color = color;
    }
    
    public Piece(Piece toCopy) {
    	this(toCopy.pivotCol, toCopy.pivotRow, toCopy.color, toCopy.getPieceSquares());
    }
    
    public PieceSquare[] getPieceSquares() {
    	return squares;
    }
    
    public Color getColor() {
    	return color;
    }

    public void flipH() {
    	swapCols();
    }

    public void flipV() {
        swapRows();
    }    
    
    public void setPivot(int row, int col){    	
    	this.pivotRow = row;
    	this.pivotCol = col;    	
    }
    
    private void transpose(){
    	for (PieceSquare s: this.squares){
			int r = s.getRow();
			int c = s.getCol();
			s.setRow(c);
			s.setCol(r);
		}
    }
    
    private void swapRows(){
    	for (PieceSquare s: this.squares){
			int r = s.getRow();	
			s.setRow(Math.abs(5-r));
		}
    }
    
    private void swapCols(){
    	for (PieceSquare s: this.squares){
			int c = s.getCol();	
			s.setCol(Math.abs(5-c));
		}
    }
    
    public void rotateCC(){
    	transpose();
    	swapRows();
    }
    
    public void rotateCW(){
    	swapRows();
    	transpose();
    }  
        
    // centers underlying PieceSquares within a 6x6 matrix.
	public void centerPiece(){
		PieceSquare[] centeredSquares = new PieceSquare[6];
			int xMin = squares[0].getCol();
			int xMax = squares[0].getCol();
			int yMin = squares[0].getRow();
			int yMax = squares[0].getRow();
			
			for (PieceSquare s: this.squares){
				if (s.getCol() < xMin) xMin = s.getCol();
				if (s.getCol() > xMax) xMax = s.getCol();
				if (s.getRow() < yMin) yMin = s.getRow();
				if (s.getRow() > yMax) yMax = s.getRow();				
			}
			
			int xDist = (0-xMin)/2 + (5-xMax)/2;
			int yDist = (0-yMin)/2 + (5-yMax)/2;
			
			for(int i = 0; i < 6; i++) {
				centeredSquares[i]= new PieceSquare(true,
						squares[i].getCol() + xDist,
						squares[i].getRow() + yDist);
			}
	
			squares = centeredSquares;	
	}
	
	
	
	

}