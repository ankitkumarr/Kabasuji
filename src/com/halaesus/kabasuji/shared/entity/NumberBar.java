package com.halaesus.kabasuji.shared.entity;

/**
 * Representation for a NumberBar for ReleaseLevel.
 * <p>
 * The valid numbers are 1 through 6. There are only 3 valid colors, 1, 2, and
 * 3.
 * 
 * @author Brian Keeley-DeBonis (bjkeeleydebonis@wpi.edu)
 */
public class NumberBar {
	
	ReleaseNumber[][] numbers = new ReleaseNumber[3][6];
	
	public NumberBar() {
		initialize();

		// TODO remove this when we have collecting numbers from board working
		// Just here so I have something to display in the boundary class
		addReleaseNumber(new ReleaseNumber(5, 1, 5, 5));
		addReleaseNumber(new ReleaseNumber(1, 2, 3, 5));
		addReleaseNumber(new ReleaseNumber(6, 3, 5, 8));

	}

	private void initialize() {

		// initialize the bar to contain all 18 release numbers.
		// however these numbers have not been found by the player yet.
		// so the boolean collected flag is set to false
		for (int j = 1; j <= 3; j++) { // go through the 3 different color sets
			for (int i = 1; i <= 6; i++) { // go through the 6 numbers
				ReleaseNumber n = new ReleaseNumber(i, j, 0, 0);
				numbers[j - 1][i - 1] = n;
			}
		}
	}

	// returns the number sets found for awarding stars
	public int setsFound() {
		int found = 0;
		for (int j = 1; j <= 3; j++) {
			int count = 0; // count number found in this color set
			for (int i = 1; i <= 6; i++) {
				if (numbers[j - 1][i - 1].getCollected()) count++;
			}
			found += count / 6; // adds 1 if all 6 numbers in set were found
		}
		return found;
	}

	public ReleaseNumber[][] getNumbers() {
		return this.numbers;
	}

	// this function requires a valid Number to be passed in
	// a valid release number has an int value [1,6] and an int color [1,3]
	public void addReleaseNumber(ReleaseNumber number) {
		numbers[number.getColor() - 1][number.getValue() - 1].setCollected();
	}
}