package com.halaesus.kabasuji.shared.entity;

/**
 * Representation for a NumberBar for ReleaseLevel.
 * <p>
 * The valid numbers are 1 through 6. There are only 3 valid colors, 1, 2, and 3.
 * <p>
 * @author Brian Keeley-DeBonis (bjkeeleydebonis@wpi.edu)
 */
public class NumberBar {
	
	/** 3x6 array of ReleaseNumbers that can possibly be collected by a Player in a <code>ReleaseLevel</code> */
	ReleaseNumber[][] numbers = new ReleaseNumber[3][6];
	
	/**
	 * Constructs an instance of the NumberBar class
	 */
	public NumberBar() {
		initialize();
	}

	/**
	 * Adds all the ReleaseNumbers to the 3x6 array of ReleaseNumbers
	 */
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

	/**
	 * Returns the number of sets of ReleaseNumbers marked as collected
	 * @return
	 */
	public int setsFound() {
		int found = 0;
		for (int j = 1; j <= 3; j++) {
			int count = 0; // count number found in this color set
			for (int i = 1; i <= 6; i++) {
				if (numbers[j - 1][i - 1].isCollected()) count++;
			}
			found += count / 6; // adds 1 if all 6 numbers in set were found
		}
		return found;
	}

	/**
	 * Returns the 3x6 array of ReleaseNumbers
	 * @return
	 */
	public ReleaseNumber[][] getNumbers() {
		return this.numbers;
	}

	/**
	 * Marks a certain ReleaseNumber as collected
	 * @param number A valid ReleaseNumber with a valid color, ie: between 1 and 3 both inclusive, and a valid value, ie:
	 * between 1 and 6 both inclusive
	 */
	public void addReleaseNumber(ReleaseNumber number) {
		numbers[number.getColor() - 1][number.getValue() - 1].setCollected();
	}
}