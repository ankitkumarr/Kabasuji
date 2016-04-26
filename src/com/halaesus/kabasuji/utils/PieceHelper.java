package com.halaesus.kabasuji.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Iterator;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.shared.AbstractLevel;
import com.halaesus.kabasuji.shared.Piece;
import com.halaesus.kabasuji.shared.PieceSquare;

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
	
	public static void drawBevel(Graphics g, Piece toBeDrawn, ArrayList<Rectangle> bevelRects, int opacity) {
		// Add a highlight and shadow to our Piece - Brian KD

		Graphics2D g2d = (Graphics2D) g.create();
		// create our settings for line
		int strokeWidth = 6;
		Stroke strokeStyle = new BasicStroke(strokeWidth, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
		g2d.setStroke(strokeStyle);

		Iterator<Rectangle> rectIterator = bevelRects.iterator();
		
		for (PieceSquare aPieceSquare : toBeDrawn.getPieceSquares()) {
			// creates a copy of the Graphics instance

			Rectangle squareRect = rectIterator.next();
			
			// add the right shadow
			Color rightShadow = toBeDrawn.getColor().darker(); 
			rightShadow = new Color(rightShadow.getRed(),rightShadow.getGreen(),rightShadow.getBlue(),opacity);
			if (toBeDrawn.noSquareRight(aPieceSquare)) {
				g2d.setColor(rightShadow);
				g2d.drawLine(squareRect.x + (int) squareRect.getWidth() - strokeWidth / 2,
						squareRect.y + strokeWidth / 2, squareRect.x + (int) squareRect.getWidth() - strokeWidth / 2,
						squareRect.y - strokeWidth / 2 + (int) squareRect.getHeight());
			}
			// add the left highlight
			Color rightHighlight = toBeDrawn.getColor().brighter();
			rightHighlight = new Color(rightHighlight.getRed(),rightHighlight.getGreen(),rightHighlight.getBlue(),opacity);
			if (toBeDrawn.noSquareLeft(aPieceSquare)) {
				g2d.setColor(rightHighlight);
				g2d.drawLine(squareRect.x + strokeWidth / 2, squareRect.y + strokeWidth / 2,
						squareRect.x + strokeWidth / 2, squareRect.y - strokeWidth / 2 + (int) squareRect.getHeight());
			}
			// add the top highlight
			Color highlight = toBeDrawn.getColor().brighter().brighter();
			highlight = new Color(highlight.getRed(),highlight.getGreen(),highlight.getBlue(),opacity);
			if (toBeDrawn.noSquareAbove(aPieceSquare)) {
				g2d.setColor(highlight);
				g2d.drawLine(squareRect.x + strokeWidth / 2, squareRect.y + strokeWidth / 2,
						squareRect.x + (int) squareRect.getWidth() - strokeWidth / 2, squareRect.y + strokeWidth / 2);
			}
			// add the bottom shadow
			Color bottomShadow = toBeDrawn.getColor().darker().darker();
			bottomShadow = new Color(bottomShadow.getRed(),bottomShadow.getGreen(),bottomShadow.getBlue(),opacity);
			if (toBeDrawn.noSquareBelow(aPieceSquare)) {
				g2d.setColor(bottomShadow);
				g2d.drawLine(squareRect.x + strokeWidth / 2,
						squareRect.y - strokeWidth / 2 + (int) squareRect.getHeight(),
						squareRect.x + (int) squareRect.getWidth() - strokeWidth / 2,
						squareRect.y - strokeWidth / 2 + (int) squareRect.getHeight());
			}
		}
		g2d.dispose();
	}

	
}