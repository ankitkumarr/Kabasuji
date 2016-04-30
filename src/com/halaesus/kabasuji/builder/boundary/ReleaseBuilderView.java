package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.builder.controller.DragNumberToBoardMove;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

@SuppressWarnings("serial")
public class ReleaseBuilderView extends AbstractBuilderView {
    JLabel[][] numberBarNumbers;
    ReleaseLevel level;
    ReleaseNumber rNumbers[][];
    ReleaseNumber numberBeingDragged;

    public ReleaseBuilderView(Application application, AbstractLevel aLevel) {
        
    	super(application, aLevel);
    	this.level = (ReleaseLevel) aLevel;
    	
    	setupReleasePalette();
    	numberBeingDragged = null;
    }
    
    public void setupReleasePalette() {
    	Font releaseNumberFont = new Font("releaseNumberFont", Font.BOLD, 35);
    	rNumbers = this.level.getNumberBar().getNumbers();
    	numberBarNumbers = new JLabel[3][6];
    	//rNumbers = new ReleaseNumber[3][6];
    	
    	for (int j = 1; j <= 3; j++) {
			for (int i = 1; i <= 6; i++) {
				//ReleaseNumber rn = new ReleaseNumber(i, j, 0 , 0);
				ReleaseNumber rn = rNumbers[j-1][i-1];
				//rNumbers[j-1][i-1] = rn; //956
				JLabel n = new JLabel(Integer.toString(rn.getValue()));
				n.setHorizontalAlignment(SwingConstants.CENTER);
				n.setBounds(960 + (53*(i-1)) , 80 + (53*(j-1)) , 53, 53);
				n.addMouseListener(new DragNumberToBoardMove(this.level, this));
				if (rn.getColor() == 1)n.setForeground(Color.RED);
				if (rn.getColor() == 2)n.setForeground(Color.YELLOW);
				if (rn.getColor() == 3)n.setForeground(Color.CYAN);
				n.setFont(releaseNumberFont);
				n.setVisible(true);
				numberBarNumbers[j-1][i-1] = n;
				add(numberBarNumbers[j-1][i-1]);
			}
		}
    	
    	this.repaint();
    	
    	
    }
    
    public Rectangle getReleaseNumberRectangle(int row, int col) {
    	return new Rectangle(960 + (53*(col-1)) , 80 + (53*(row-1)) , 53, 53);
    }
    
    public ReleaseNumber getnumberBeingDragged(){
    	return this.numberBeingDragged;
    }
    
    public void setnumberBeingDragged(ReleaseNumber beingDragged){
    	this.numberBeingDragged = beingDragged;
    }
}
