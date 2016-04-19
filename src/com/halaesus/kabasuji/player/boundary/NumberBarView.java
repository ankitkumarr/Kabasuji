package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.entity.NumberBar;
import com.halaesus.kabasuji.player.entity.ReleaseNumber;

@SuppressWarnings("serial")
public class NumberBarView extends JPanel {
	
	NumberBar numberBar;
	JLabel[] bars = new JLabel[18];
	
	//private static final long serialVersionUID = -1510543478406922319L;

	public NumberBarView(NumberBar numberBar) {
		this.numberBar = numberBar;
		setupNumberBarLabels();		
	}
	
	private void setupNumberBarLabels() {
		Font releaseNumberFont = new Font("releaseNumberFont", Font.BOLD, 35);
		ReleaseNumber  rNumbers[] = numberBar.getNumbers();
		for (int i = 0; i < 18; i++){
			JLabel n = new JLabel(Integer.toString(rNumbers[i].getValue()));
			n.setHorizontalAlignment(SwingConstants.CENTER);
			n.setBounds((330 + 51*12) + (51 * rNumbers[i].getValue()),
					80 + 51 * rNumbers[i].getColor(), 51, 51);
			if (rNumbers[i].getColor() == 1)n.setForeground(Color.RED);
			if (rNumbers[i].getColor() == 2)n.setForeground(Color.YELLOW);
			if (rNumbers[i].getColor() == 3)n.setForeground(Color.CYAN);
			n.setFont(releaseNumberFont);
			this.bars[i] = n;
			add(n);
		}
	}
	
}