/**
 * 
 */
package com.halaesus.kabasuji.shared;

/**
 * @author Corey Dixon
 *
 */
public class ReleaseLevelMemento extends AbstractLevelMemento {

	private static final long serialVersionUID = 7891441750308402781L;
	
	public ReleaseLevelMemento() {
		super();
	}

	/**
	 * @param board
	 * @param palette
	 */
	public ReleaseLevelMemento(AbstractBoardMemento board, Palette palette) {
		super(board, palette);
	}

	@Override
	public AbstractLevel generateLevel() {
		return new ReleaseLevel(this);
	}

}