/**
 * 
 */
package com.halaesus.kabasuji.shared;

/**
 * @author Corey Dixon
 *
 */
public class LightningLevelMemento extends AbstractLevelMemento {

	private static final long serialVersionUID = 7718035734811881075L;
	int maxTime;
	
	LightningLevelMemento(AbstractBoardMemento board, Palette palette, int maxTime) {
		super(board, palette);
		this.maxTime = maxTime;
	}

	@Override
	public AbstractLevel generateLevel() {
		return new LightningLevel(this);
	}


}
