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
	
	public PuzzleLevelMemento(int levelIndex, String levelType) {
		super(levelIndex, levelType);
		allowedMoves = 0;
		board = new PuzzleBoardMemento();
	}

	public PuzzleLevelMemento(AbstractBoardMemento board, PaletteMemento palette,
			int levelIndex, int allowedMoves, String levelType) {
		super(board, palette, levelIndex, levelType);
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
