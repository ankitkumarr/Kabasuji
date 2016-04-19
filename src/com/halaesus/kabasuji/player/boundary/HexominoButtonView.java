package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class HexominoButtonView extends JButton {

	private int hexominoCount;
	private JLabel theLabel;
	
	public HexominoButtonView(Icon icon) {
		super(icon); // Let the do its stuff
		this.setLayout(null); // We'd prefer a null layout
		hexominoCount = -1;
		// We gotta print the count label
		theLabel = new JLabel(String.valueOf(hexominoCount), SwingConstants.RIGHT);
		theLabel.setBounds(36, 36, 15, 15);
		theLabel.setForeground(new Color(0, 100, 0));
		// Make the text bold
		Font labelFont = theLabel.getFont();
		theLabel.setFont(labelFont.deriveFont(labelFont.getStyle() | Font.BOLD));
		// Add it to the screen
		add(theLabel);
		// Hide it though as count = -1
		theLabel.setVisible(false);
	}

	public void setHexominoCount(int hexominoCount) {
		this.hexominoCount = hexominoCount;
		// Update Label Text
		theLabel.setText(String.valueOf(hexominoCount));
		// Set the necessary visibility
		if( hexominoCount >= 0 )
			theLabel.setVisible(true);
		else
			theLabel.setVisible(false);
		// Request a repaint
		repaint();
	}
	
	public int getHexominoCount() {
		return hexominoCount;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its painting
	}
	
}