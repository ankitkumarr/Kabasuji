/**
 * 
 */
package com.halaesus.kabasuji.shared.memento;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.PaletteMemento;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;

/**
 * @author Corey Dixon
 *
 */
public class PuzzleLevelMemento extends AbstractLevelMemento {

	private static final long serialVersionUID = 1250292721040817111L;
	
	int allowedMoves;
	
	public PuzzleLevelMemento(int levelIndex) {
		super(levelIndex);
		allowedMoves = 0;
		board = new PuzzleBoardMemento();
	}

	public PuzzleLevelMemento(AbstractBoardMemento board, PaletteMemento palette, int levelIndex, int allowedMoves) {
		super(board, palette, levelIndex);
		this.allowedMoves = allowedMoves;
	}

	@Override
	public AbstractLevel generateLevel() {
		return new PuzzleLevel(this);
	}
	
	public int getAllowedMoves() {
		return allowedMoves;
	}

}
