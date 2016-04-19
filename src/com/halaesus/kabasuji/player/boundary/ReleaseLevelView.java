package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.entity.ReleaseBoard;
import com.halaesus.kabasuji.player.entity.ReleaseLevel;
import com.halaesus.kabasuji.player.entity.ReleaseNumber;
import com.halaesus.kabasuji.utils.JLabelHelper;

@SuppressWarnings("serial")
public class ReleaseLevelView extends AbstractLevelView {

	JLabel releaseModeLabel;
	NumberBarView numberBarView;
	Set<JLabel> numbers;
	ReleaseLevel level;
	ReleaseBoard rBoard;
	
	public ReleaseLevelView(Application anApplication, ReleaseLevel aLevel ) {
		super(anApplication, aLevel);  // Let the super do its stuff
		// Save the level
		level = aLevel;
		
		rBoard = (ReleaseBoard) aLevel.getBoard();
		// Set up Puzzle Specific Layout Stuff
		setupLevelTypeLabel();

		numbers = new HashSet<JLabel>();
		setupNumberLabels(rBoard.getReleaseNumbers());
		numberBarView = new NumberBarView(level.getNumberBar());		
	}

	private void setupLevelTypeLabel() {
		// Create the label
		releaseModeLabel = new JLabel("Release Mode");
		releaseModeLabel.setBounds(125, 10, 350, 60);
		releaseModeLabel.setForeground(Color.ORANGE);
		releaseModeLabel.setFont(new Font(releaseModeLabel.getFont().getName(), Font.BOLD, releaseModeLabel.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(releaseModeLabel);
		// Add it to the GUI
		add(releaseModeLabel);
	}
	
	private void setupNumberLabels(Set<ReleaseNumber> rNumbers) {
		Font releaseNumberFont = new Font("releaseNumberFont", Font.BOLD, 35);
		
		for (ReleaseNumber num: rNumbers){
			JLabel n = new JLabel(Integer.toString(num.getValue()));
			n.setHorizontalAlignment(SwingConstants.CENTER);
			n.setBounds(330 + 51 * num.getCol(), 80 + 51 * num.getRow(), 51, 51);
			if (num.getColor() == 1)n.setForeground(Color.RED);
			if (num.getColor() == 2)n.setForeground(Color.YELLOW);
			if (num.getColor() == 3)n.setForeground(Color.CYAN);
			n.setFont(releaseNumberFont);
			this.numbers.add(n);
			// Add it to the GUI
			add(n);
		}
	}

}