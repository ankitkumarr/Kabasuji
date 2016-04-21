package com.halaesus.kabasuji.utils;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.player.entity.AbstractLevel;
import com.halaesus.kabasuji.player.entity.Piece;
import com.halaesus.kabasuji.player.entity.PieceSquare;

public class PieceHelper {

	public static Piece snapToNearestBoardSquare(AbstractLevel theLevel, AbstractLevelView theLevelView) {
		// Solve for where the Piece will snap to
		Point pushPoint = new Point(theLevel.getTopPointOfDraggingPiece().x + 25, 
									theLevel.getTopPointOfDraggingPiece().y + 25);
		boolean exit = false; // To keep track if the loop should exit
		Piece newPieceDragged = new Piece(theLevel.getPieceBeingDragged()); // The new piece against which we'll be checking as this will be snapped to the board
		PieceSquare[] pieceSquares = newPieceDragged.getPieceSquares();
		for(int r = 0; r < 12 && !exit; r++) {
			for(int c = 0; c < 12 && !exit; c++) {
				Rectangle boardRectangle = theLevelView.getBoardPieceRectangle(r, c);
				// See if point lies there
				if( boardRectangle.contains(pushPoint) ) {
					// Go over each PieceSquare now
					for(int ctr = 0; ctr < newPieceDragged.getPieceSquares().length; ctr++) {
						// Update the row and col value
						pieceSquares[ctr].setCol(pieceSquares[ctr].getCol() + c); // Add the board Column to the base Column
						pieceSquares[ctr].setRow(pieceSquares[ctr].getRow() + r); // Add the board Row to the base Row
					}
					// Finally, we're done making changes, so exit the loop
					exit = true;
				}
			}
		}
		// Return the desired Piece now
		if(exit)
			return newPieceDragged; // We did make changes
		else
			return null; // No changes were made :(
	}
	
}