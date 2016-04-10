package com.halaesus.kabasuji.utils;

import java.awt.Font;

import javax.swing.JLabel;

public class JLabelHelper {

	public static void resizeTextBasedOnAvailableSize(JLabel theLabel) {
		// REFERENCE: http://stackoverflow.com/a/2715279
		
		Font labelFont = theLabel.getFont();
		String labelText = theLabel.getText();
		// Calculate the Widths
		int stringWidth = theLabel.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = theLabel.getWidth();	
		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;
		// Solve for the new Sizes
		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = theLabel.getHeight();	
		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);	
		// Set the label's font size to the newly determined size.
		theLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
	}
	
}
