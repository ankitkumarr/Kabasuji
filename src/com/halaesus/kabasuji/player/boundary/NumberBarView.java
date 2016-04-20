package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.entity.NumberBar;
import com.halaesus.kabasuji.player.entity.ReleaseNumber;

//TODO make it so NumberBarView appears in the ReleaseLevelView UI

@SuppressWarnings("serial")
public class NumberBarView extends JPanel {
	
	NumberBar numberBar;
	JLabel[] bars = new JLabel[18];
	
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
		ReleaseNumber  rNumbers[] = numberBar.getNumbers();
		for (int i = 0; i < 18; i++){
			
			JLabel n = new JLabel(Integer.toString(rNumbers[i].getValue()));
			if (!rNumbers[i].getFound()) n.setText("");
			n.setHorizontalAlignment(SwingConstants.CENTER);
			n.setBounds(53 * (rNumbers[i].getValue()-1),
					53 * (rNumbers[i].getColor()-1), 53, 53);
			if (rNumbers[i].getColor() == 1)n.setForeground(Color.RED);
			if (rNumbers[i].getColor() == 2)n.setForeground(Color.YELLOW);
			if (rNumbers[i].getColor() == 3)n.setForeground(Color.CYAN);
			n.setFont(releaseNumberFont);
			this.bars[i] = n;
			add(n);
		}
	}
	
}