/**
 * 
 */
package com.halaesus.kabasuji.shared.memento;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Palette;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;

/**
 * @author Corey Dixon
 *
 */
public class ReleaseLevelMemento extends AbstractLevelMemento {

	private static final long serialVersionUID = 7891441750308402781L;
	
	public ReleaseLevelMemento(int levelIndex, String levelType) {
		super(levelIndex, levelType);
		board = new ReleaseBoardMemento();
	}

	/**
	 * @param board
	 * @param palette
	 */
	public ReleaseLevelMemento(AbstractBoardMemento board, Palette palette,
			int levelIndex, String levelType) {
		super(board, palette.generateMemento(), levelIndex, levelType);
	}

	@Override
	public AbstractLevel generateLevel() {
		return new ReleaseLevel(this);
	}

}
