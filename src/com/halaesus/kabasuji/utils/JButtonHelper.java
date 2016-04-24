package com.halaesus.kabasuji.utils;

import javax.swing.JButton;

public class JButtonHelper {

	public static void makeBackgroundTransparent(JButton theButton) {
		// REFERENCE: http://stackoverflow.com/a/4586003/705471
		
		theButton.setOpaque(false);
		theButton.setContentAreaFilled(false);
	}
}
