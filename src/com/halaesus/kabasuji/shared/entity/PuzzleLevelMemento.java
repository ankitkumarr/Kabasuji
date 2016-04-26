/**
 * 
 */
package com.halaesus.kabasuji.shared.entity;

/**
 * @author Corey Dixon
 *
 */
public class PuzzleLevelMemento extends AbstractLevelMemento {

	private static final long serialVersionUID = 1250292721040817111L;
	
	int allowedMoves;
	public PuzzleLevelMemento() {
		super();
		allowedMoves = 0;
		board = new PuzzleBoardMemento();
	}

	public PuzzleLevelMemento(AbstractBoardMemento board, PaletteMemento palette, int allowedMoves) {
		super(board, palette);
		this.allowedMoves = allowedMoves;
	}

	@Override
	public AbstractLevel generateLevel() {
		return new PuzzleLevel(this);
	}

}
