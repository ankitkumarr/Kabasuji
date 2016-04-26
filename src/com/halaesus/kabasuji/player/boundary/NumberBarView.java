package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.shared.entity.NumberBar;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

//TODO make it so NumberBarView appears in the ReleaseLevelView UI

/**
 * @author Brian Keeley-DeBonis (bjkeeleydebonis@wpi.edu)
 */

@SuppressWarnings("serial")
public class NumberBarView extends JPanel {
	
	NumberBar numberBar;
	JLabel[][] bars = new JLabel[3][6];
	
	//private static final long serialVersionUID = -1510543478406922319L;

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
				if (!rn.getCollected()) n.setText("");
				n.setHorizontalAlignment(SwingConstants.CENTER);
				n.setBounds(53 * (rn.getValue()-1),
						53 * (rn.getColor()-1), 53, 53);
				if (rn.getColor() == 1)n.setForeground(Color.RED);
				if (rn.getColor() == 2)n.setForeground(Color.YELLOW);
				if (rn.getColor() == 3)n.setForeground(Color.CYAN);
				n.setFont(releaseNumberFont);
				this.bars[j - 1][i - 1] = n;
				add(n);
			}
		}
	}
}