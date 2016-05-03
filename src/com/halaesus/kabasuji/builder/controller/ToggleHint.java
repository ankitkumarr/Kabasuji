package com.halaesus.kabasuji.builder.controller;

import java.awt.Point;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.builder.entity.ToggleHintMove;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class ToggleHint implements MouseListener, MouseMotionListener {

    AbstractBuilderView levelView;
    PieceSquare[] originalBoardPieceSquares;
    AbstractLevel level;

    public ToggleHint(AbstractLevel theLevel, AbstractBuilderView levelView) {
        // Save the information
    	this.levelView = levelView;
        this.level = theLevel;
    }
    
	@Override
	public void mousePressed(MouseEvent e) {
		// only allow right click
		if (!SwingUtilities.isRightMouseButton(e)){return;}
		
		// See if we should be handing this drag or not
		Rectangle overallBoardRectangle = new Rectangle(this.levelView.getBoardPieceRectangle(0, 0).x, 
				                                        this.levelView.getBoardPieceRectangle(0, 0).y,
				                                        12 * 53, 12 * 53);
		Point mouseClickLocation = new Point(e.getX(), e.getY());
		
		if( !overallBoardRectangle.contains(mouseClickLocation) )
			return; // We shouldn't be handling this move
		
		// Go over each Piece and then their PieceSquares to determine where the click happened
		Iterator<Piece> boardPieces = this.level.getBoard().getPiecesIt();
		boolean exit = false; // To keep track if the iteration loop should be quit or not
		for( ; boardPieces.hasNext() && !exit; ) {
			Piece aPiece = new Piece(boardPieces.next()); // Get the next Piece
			// Go over each of the PieceSquares
			for( PieceSquare aPieceSquare : aPiece.getPieceSquares() ) {
				// See if the click landed on this PieceSquare
				Rectangle pieceSquareRect = this.levelView.getBoardPieceRectangle(aPieceSquare.getRow(), aPieceSquare.getCol());
				if( pieceSquareRect.contains(mouseClickLocation) ) {
					// Spawn off the move
					ToggleHintMove theMove = new ToggleHintMove(level, levelView);
					theMove.setOriginalPiece(aPiece); // Set the piece whose hint has to be toggled
					if( theMove.isValid() && theMove.doMove() ) {
						// Add it to the stack of moves
						MoveManager.pushMove(theMove);
					}
										
					// Force the LevelView to repaint
					this.levelView.repaint();
					
					// We've handled the mousePress, so exit the loop
					exit = true;
					break;
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseEntered(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseExited(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseMoved(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseDragged(MouseEvent arg0) { /* Nothing to do */ }

	@Override
	public void mouseReleased(MouseEvent e) { /* Nothing to do */ }

}