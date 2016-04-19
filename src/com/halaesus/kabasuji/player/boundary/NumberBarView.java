package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.halaesus.kabasuji.player.entity.NumberBar;
import com.halaesus.kabasuji.utils.JLabelHelper;

public class NumberBarView extends JPanel {
	
	NumberBar numberBar;
	JLabel[] bars;
	JLabel blah;
	
	//private static final long serialVersionUID = -1510543478406922319L;

	public NumberBarView() {
		showblah();
	}
	
	
	private void showblah() {
		// Create the label
		blah = new JLabel(" 1 2 3 4 5 6 ");
		blah.setBounds(400, 10, 350, 60);
		blah.setForeground(Color.ORANGE);
		blah.setFont(new Font(blah.getFont().getName(), Font.BOLD, blah.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(blah);
		// Add it to the GUI
		add(blah);
	}
	

}