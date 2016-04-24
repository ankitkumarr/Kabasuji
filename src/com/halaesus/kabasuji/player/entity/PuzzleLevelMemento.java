/**
 * 
 */
package com.halaesus.kabasuji.player.entity;

/**
 * @author Corey Dixon
 *
 */
public class PuzzleLevelMemento extends AbstractLevelMemento {

	private static final long serialVersionUID = 1250292721040817111L;
	
	int allowedMoves;

	PuzzleLevelMemento(AbstractBoardMemento board, Palette palette, int allowedMoves) {
		super(board, palette);
		this.allowedMoves = allowedMoves;
	}

	@Override
	AbstractLevel generateLevel() {
		return new PuzzleLevel(this);
	}

}
