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
    	
    	// TODO remove these center method and rotate methods from constructor. 
    	// Only call when needed 
    	center();
    	//rotateCC();
    	//flipV();
    }
    
    public PieceSquare[] getPieceSquares() {
    	return squares;
    }
    
    public Color getColor() {
    	return color;
    }


    public void flipH() {
    	swapRows();
    }

    public void flipV() {
        swapCols();
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
	public void center(){
		PieceSquare[] centeredSquares = new PieceSquare[6];
			int xMin = squares[0].getRow();
			int xMax = squares[0].getRow();
			int yMin = squares[0].getCol();
			int yMax = squares[0].getCol();
	
			for (PieceSquare s: this.squares){
				if (s.getRow() < xMin) xMin = s.getRow();
				if (s.getRow() > xMax) xMax = s.getRow();
				if (s.getCol() < yMin) yMin = s.getCol();
				if (s.getCol() > yMax) yMax = s.getCol();
			}
			/* Depreciated method of centering
			int width = xMax + 1 - xMin;
			int height = yMax + 1 - yMin;
			
			int xTarg = (6 - width)/2;
			int yTarg = (6 - height)/2;
			
			int xDist = xTarg - xMin;
			int yDist = yTarg - yMin;
			*/
			int xDist = (0-xMin)/2 + (5-xMax)/2;
			int yDist = (0-yMin)/2 + (5-yMax)/2;
			
			for(int i = 0; i < 6; i++) {
				centeredSquares[i]= new PieceSquare(true,
						squares[i].getRow() + xDist,
						squares[i].getCol() + yDist);
			}
	
			squares = centeredSquares;	
	}
	
	
	
	

}