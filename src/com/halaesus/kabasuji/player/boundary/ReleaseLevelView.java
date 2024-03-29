package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.shared.entity.ReleaseBoard;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;
import com.halaesus.kabasuji.utils.JLabelHelper;

/**
 * Represents a ReleaseLevelView which is a type of an AbstractLevelView
 * <p>
 * @author Brian Keeley-DeBonis (bjkeeleydebonis@wpi.edu), Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
@SuppressWarnings("serial")
public class ReleaseLevelView extends AbstractLevelView {

	/** Array of ReleaseNumber which reflect ReleaseNumbers placed on the Board */
	JLabel releaseNumbersOnBoard[][];
	/** The level specific label */
	JLabel releaseModeLabel;
	/** NumberBarView to show the right-hand side NumberBar */
	NumberBarView numberBarView;
	/** JLabels to represent the ReleaseNumbers */
	Set<JLabel> numbers;
	/** The underlying ReleaseLevel (Model Class) */
	ReleaseLevel level;
	/** The underlying ReleaseBoard */
	ReleaseBoard rBoard;
	
	/**
	 * Instantiates a ReleaseLevelView with the top-level application and a ReleaseLevel
	 * @param anApplication
	 * @param aLevel
	 */
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
		numberBarView.setBounds(324 + 53*12, 80, 53*6, 53*3);
		add(numberBarView);
	}

	/**
	 * Setup the Game Level Type Label
	 */
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
	
	/**
	 * Sets up the ReleaseNumbers on the board
	 * @param rNumbers
	 */
	private void setupNumberLabels(Set<ReleaseNumber> rNumbers) {
		Font releaseNumberFont = new Font("releaseNumberFont", Font.BOLD, 35);
		// Initialize the array
		releaseNumbersOnBoard = new JLabel[12][12];
		// Iterate over now
		for (ReleaseNumber num: rNumbers){
			JLabel n = new JLabel(Integer.toString(num.getValue()));
			n.setHorizontalAlignment(SwingConstants.CENTER);
			n.setBounds(320 + 53 * num.getCol(), 80 + 53 * num.getRow(), 53, 53);
			if (num.getColor() == 1)n.setForeground(Color.RED);
			if (num.getColor() == 2)n.setForeground(Color.YELLOW);
			if (num.getColor() == 3)n.setForeground(Color.CYAN);
			n.setFont(releaseNumberFont);
			this.numbers.add(n);
			// Add it to the GUI
			add(n);
			// Add it to the array
			releaseNumbersOnBoard[num.getRow()][num.getCol()] = n;
		}
	}

	/**
	 * Overriding paintComponent(g) to draw the ReleaseNumbers on the ReleaseBoard
	 */
	@Override
	protected void paintComponent(Graphics g) {
		refreshReleaseNumberLabels();
		// Let the super do its job
		super.paintComponent(g);
	}

	/**
	 * Sets visibility of numbers in NumberBar based on if they're collected on the Board
	 */
	private void refreshReleaseNumberLabels() {
		// Go over each of the labels
		for(int r = 0; r < 12; r++) {
			for(int c = 0; c < 12; c++) {
				// If not null, proceed
				if( releaseNumbersOnBoard[r][c] == null )
					continue;
				// Go over each of the release numbers and see
				boolean numberFound = false;
				// Iterate over now
				for( ReleaseNumber[] releaseNumberRow : this.level.getNumberBar().getNumbers() ) {
					for( ReleaseNumber aReleaseNumber : releaseNumberRow ) {
						// Check if it exists here
						if( !aReleaseNumber.isCollected() ) {
							// Now compare
							if( aReleaseNumber.getValue() == Integer.parseInt(releaseNumbersOnBoard[r][c].getText()) ) {
								if( aReleaseNumber.getColor() == 1 && ( releaseNumbersOnBoard[r][c].getForeground() == Color.RED ) ) {
									numberFound = true;
									releaseNumbersOnBoard[r][c].setVisible(true);
								} else if( aReleaseNumber.getColor() == 2 && ( releaseNumbersOnBoard[r][c].getForeground() == Color.YELLOW ) ) {
									numberFound = true;
									releaseNumbersOnBoard[r][c].setVisible(true);
								} else if( aReleaseNumber.getColor() == 3 && ( releaseNumbersOnBoard[r][c].getForeground() == Color.CYAN ) ) {
									numberFound = true;
									releaseNumbersOnBoard[r][c].setVisible(true);
								}
							}
						}
					}
				}
				// If not found,
				if( !numberFound )
					releaseNumbersOnBoard[r][c].setVisible(false);
			}
		}
	}

}