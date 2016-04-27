package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.shared.entity.NumberBar;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

/**
 * @author Brian Keeley-DeBonis (bjkeeleydebonis@wpi.edu), Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */

@SuppressWarnings("serial")
public class NumberBarView extends JPanel {
	
	NumberBar numberBar;
	JLabel[][] bars = new JLabel[3][6];

	public NumberBarView(NumberBar numberBar) {
		this.numberBar = numberBar;
		setLayout(null); // make it so we can control individual swing
		// elements in the panel.
		setOpaque(false); // make the jPanel transparent 
		setupNumberBarLabels();
	}
	
	private void setupNumberBarLabels() {
		Font releaseNumberFont = new Font("releaseNumberFont", Font.BOLD, 35);
		ReleaseNumber  rNumbers[][] = numberBar.getNumbers();
		
		for (int j = 1; j <= 3; j++) {
			for (int i = 1; i <= 6; i++) {
				ReleaseNumber rn = rNumbers[j-1][i-1];
				JLabel n = new JLabel(Integer.toString(rn.getValue()));
				n.setHorizontalAlignment(SwingConstants.CENTER);
				n.setBounds(53 * (rn.getValue()-1),
						53 * (rn.getColor()-1), 53, 53);
				if (rn.getColor() == 1)n.setForeground(Color.RED);
				if (rn.getColor() == 2)n.setForeground(Color.YELLOW);
				if (rn.getColor() == 3)n.setForeground(Color.CYAN);
				n.setFont(releaseNumberFont);
				n.setVisible(false); // Initially it is not visible
				this.bars[j - 1][i - 1] = n;
				add(n);
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// Go over all the ReleaseNumbers and set visibility
		for(int r = 0; r < 3; r++)
			for(int c = 0; c < 6; c++)
				this.bars[r][c].setVisible( numberBar.getNumbers()[r][c].getCollected() );
		// Let the super do its job now
		super.paintComponent(g);
	}
}