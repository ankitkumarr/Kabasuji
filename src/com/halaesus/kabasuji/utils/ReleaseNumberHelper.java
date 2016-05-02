package com.halaesus.kabasuji.utils;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.builder.boundary.ReleaseBuilderView;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;


public class ReleaseNumberHelper {

	
	public static ReleaseNumber snapToNearestBoardSquare(ReleaseLevel theLevel, ReleaseBuilderView theBuilderView) {
		// Solve for where the Piece will snap to
		Point pushPoint = new Point(theLevel.getTopPointOfDraggingPiece().x + 25, 
									theLevel.getTopPointOfDraggingPiece().y + 25);
		boolean exit = false; // To keep track if the loop should exit
		ReleaseNumber newNumberDragged = new ReleaseNumber(theLevel.getnumberBeingDragged().getValue(),
				theLevel.getnumberBeingDragged().getColor(),
				theLevel.getnumberBeingDragged().getCol(), theLevel.getnumberBeingDragged().getRow()); // The new piece against which we'll be checking as this will be snapped to the board
		for(int r = 0; r < 12 && !exit; r++) {
			for(int c = 0; c < 12 && !exit; c++) {
				Rectangle boardRectangle = theBuilderView.getBoardPieceRectangle(r, c);
				// See if point lies there
				if( boardRectangle.contains(pushPoint) ) {
					newNumberDragged.setCol(c);
					newNumberDragged.setRow(r);
					exit = true;
				}
			}
		}
		// Return the desired Piece now
		if(exit)
			return newNumberDragged; // We did make changes
		else
			return null; // No changes were made :(
	}
}
