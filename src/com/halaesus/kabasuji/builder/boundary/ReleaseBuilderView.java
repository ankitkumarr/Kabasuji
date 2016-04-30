package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

@SuppressWarnings("serial")
public class ReleaseBuilderView extends AbstractBuilderView {
    JLabel[] numberBarNumbers;
    ReleaseLevel level;
    ReleaseNumber rNumbers[][];

    public ReleaseBuilderView(Application application, AbstractLevel aLevel) {
        
    	super(application, aLevel);
    	this.level = (ReleaseLevel) aLevel;
    	
    	setupReleasePalette();
    }
    
    public void setupReleasePalette() {
    	Font releaseNumberFont = new Font("releaseNumberFont", Font.BOLD, 35);
    	rNumbers = new ReleaseNumber[3][6];
    	
    	for (int j = 1; j <= 3; j++) {
			for (int i = 1; i <= 6; i++) {
				ReleaseNumber rn = new ReleaseNumber(i, j, 0 , 0);
				rNumbers[j-1][i-1] = rn; //956
				JLabel n = new JLabel(Integer.toString(rn.getValue()));
				n.setHorizontalAlignment(SwingConstants.CENTER);
				n.setBounds(960 + (53*(i-1)) , 80 + (53*(j-1)) , 53, 53);
				if (rn.getColor() == 1)n.setForeground(Color.RED);
				if (rn.getColor() == 2)n.setForeground(Color.YELLOW);
				if (rn.getColor() == 3)n.setForeground(Color.CYAN);
				n.setFont(releaseNumberFont);
				n.setVisible(true);
				add(n);
			}
		}
    	
    	this.repaint();
    	
    	
    }
}
