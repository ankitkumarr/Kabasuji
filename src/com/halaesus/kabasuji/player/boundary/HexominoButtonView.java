package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Represents a Hexomino in the Player Palette
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
@SuppressWarnings("serial")
public class HexominoButtonView extends JButton {

	/** Represents the Hexomino Count */
	private int hexominoCount;
	
	/** Represents the label that will show the Hexomino Count */
	private JLabel theLabel;
	
	/** Icon that will be shown if the Hexomino Count is zero */
	private Icon inactiveIcon;
	
	/** Icon that will be shown if the Hexomino Count is greater than zero */
	private Icon activeIcon;
	
	/**
	 * Creates a HexominoButtonView with the necessary Inactive and Active icons
	 * @param activeIcon
	 * @param inactiveIcon
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
	 * Sets the Hexomino Count Label on the HexominoButtonView. Necessary icon changes are automatically reflected.
	 * @param hexominoCount
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
	 * Returns the number of pieces of the hexomino that is accessible to the user
	 * @return
	 */
	public int getHexominoCount() {
		return hexominoCount;
	}

	/**
	 * Overrides the paintComponent(g)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its painting
	}
	
}