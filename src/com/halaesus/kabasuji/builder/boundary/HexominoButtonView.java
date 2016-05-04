package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class HexominoButtonView extends JButton {

	private int hexominoCount;
	private JLabel theLabel;
	private Icon inactiveIcon;
	private Icon activeIcon;
	
	public HexominoButtonView(Icon activeIcon, Icon inactiveIcon) {
		super(inactiveIcon); // Let the do its stuff with the inactive icon
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
		// Save the icons
		this.activeIcon = activeIcon;
		this.inactiveIcon = inactiveIcon;
	}

	public void setHexominoCount(int hexominoCount) {
		this.hexominoCount = hexominoCount;
		// Update Label Text
		theLabel.setText(String.valueOf(hexominoCount));
		// Set the necessary visibility
		if( hexominoCount > 0 ) {
			
			theLabel.setVisible(true);
			// Change the icon to an ActiveIcon if it is an Inactive icon
			if( this.getIcon() == inactiveIcon ) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						HexominoButtonView.this.setIcon(activeIcon);
						//HexominoButtonView.this.repaint();
					}
				});
			}
			
		} else {
			
			theLabel.setVisible(false);
			// Change the icon to an InactiveIcon if it is an Active Icon
			if( this.getIcon() == activeIcon ) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						HexominoButtonView.this.setIcon(inactiveIcon);
						//HexominoButtonView.this.repaint();
					}
				});
			}
			
		}
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