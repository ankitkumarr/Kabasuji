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
	
	public ReleaseLevelMemento() {
		super();
		board = new ReleaseBoardMemento();
	}

	/**
	 * @param board
	 * @param palette
	 */
	public ReleaseLevelMemento(AbstractBoardMemento board, Palette palette) {
		super(board, palette.generateMemento());
	}

	@Override
	public AbstractLevel generateLevel() {
		return new ReleaseLevel(this);
	}

}
