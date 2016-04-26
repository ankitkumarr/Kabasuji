/**
 * 
 */
package com.halaesus.kabasuji.shared;

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
	public AbstractLevel generateLevel() {
		return new PuzzleLevel(this);
	}

}
