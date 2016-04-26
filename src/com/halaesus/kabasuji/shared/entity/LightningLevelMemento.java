/**
 * 
 */
package com.halaesus.kabasuji.shared.entity;

/**
 * @author Corey Dixon
 *
 */
public class LightningLevelMemento extends AbstractLevelMemento {

	private static final long serialVersionUID = 7718035734811881075L;
	int maxTime;
	
	public LightningLevelMemento() {
		super();
		board = new LightningBoardMemento();
		maxTime = 60;
	}
	
	public LightningLevelMemento(AbstractBoardMemento board, Palette palette, int maxTime) {
		super(board, palette.generateMemento());
		this.maxTime = maxTime;
	}

	@Override
	public AbstractLevel generateLevel() {
		return new LightningLevel(this);
	}


}