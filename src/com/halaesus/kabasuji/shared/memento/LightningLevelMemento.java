/**
 * 
 */
package com.halaesus.kabasuji.shared.memento;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.LightningLevel;
import com.halaesus.kabasuji.shared.entity.Palette;

/**
 * @author Corey Dixon
 *
 */
public class LightningLevelMemento extends AbstractLevelMemento {

	private static final long serialVersionUID = 7718035734811881075L;
	
	int maxTime;
	
	public LightningLevelMemento(int levelIndex, String levelType, String levelName) {
		super(levelIndex, levelType, levelName);
		board = new LightningBoardMemento();
		maxTime = 60;
	}
	
	public LightningLevelMemento(AbstractBoardMemento board, Palette palette,
			int levelIndex, int maxTime, String levelType, String levelName) {
		super(board, palette.generateMemento(), levelIndex, levelType, levelName);
		this.maxTime = maxTime;
	}

	@Override
	public AbstractLevel generateLevel() {
		return new LightningLevel(this);
	}

	public int getMaxTime() {
		return maxTime;
	}

}
