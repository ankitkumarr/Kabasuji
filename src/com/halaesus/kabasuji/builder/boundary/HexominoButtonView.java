package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * JButton that displays the Hexomino on the Palette with the number of that
 * hexomino available for the player
 *
 */
@SuppressWarnings("serial")
public class HexominoButtonView extends JButton {
	/** Number of this hexomino available to the player */
	private int hexominoCount;
	/** Label to display the count */
	private JLabel theLabel;
	/** Icon to display when the count is 0 */
	private Icon inactiveIcon;
	/** Icon to display when this count is > 0 */
	private Icon activeIcon;

	/**
	 * Creates a HexominoButtonView with hexominoCount = 0 and the given icons
	 * 
	 * @param activeIcon
	 *            icon for when this button is active
	 * @param inactiveIcon
	 *            icon for when this button is inactive
	 */
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

	/**
	 * Update the hexomino count and change the appearance accordingly
	 * @param hexominoCount new count
	 */
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
	
	/**
	 * Gets the hexomino count associated with this button
	 * @return hexominoCount
	 */
	public int getHexominoCount() {
		return hexominoCount;
	}
}
