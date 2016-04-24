/**
 * 
 */
package com.halaesus.kabasuji.player.entity;

/**
 * @author Corey Dixon
 *
 */
public class ReleaseLevelMemento extends AbstractLevelMemento {

	private static final long serialVersionUID = 7891441750308402781L;

	/**
	 * @param board
	 * @param palette
	 */
	public ReleaseLevelMemento(AbstractBoardMemento board, Palette palette) {
		super(board, palette);
	}

	@Override
	AbstractLevel generateLevel() {
		return new ReleaseLevel(this);
	}

}
