package com.halaesus.kabasuji.player.moves;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;

/**
 * Represents a generic move functionality on the Player Side
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public interface IMove {

	/**
	 * Verifies if the move created is a valid move
	 * @param level
	 * @return
	 */
    public boolean isValid(AbstractLevel level);
    
    /**
     * Performs the move on the given level
     * @param level  the Level on which the changes should reflect
     * @param originalPieceSquares  the Original PieceSquares of the Piece being acted upon
     * @return
     */
    public Piece doMove(AbstractLevel level, PieceSquare[] originalPieceSquares);

}