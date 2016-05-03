package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.halaesus.kabasuji.builder.controller.SetNumMovePuzzle;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;

@SuppressWarnings("serial")
public class PuzzleBuilderView extends AbstractBuilderView {

    JLabel movesLabel;
    JTextField movesBox;
    JButton setMoves;
    PuzzleLevel level;

    public PuzzleBuilderView(Application application, AbstractLevel aLevel, int levelIndex) {
        // TODO implement here
    	super(application, aLevel, levelIndex);
    	this.level = (PuzzleLevel) aLevel;
    	setupSetMovesBox();
    	setupSetMoves();
    	setupSetMovesLabel();
    	
    }
    
    public void setupSetMovesLabel() {
    	movesLabel = new JLabel(" Moves : " + getMoves());
    	movesLabel.setBounds(320 + 56 * 12, 170, 324, 60);
    	movesLabel.setForeground(Color.ORANGE);
		movesLabel.setFont(new Font(movesLabel.getFont().getName(),
				Font.BOLD, 32));
		//JLabelHelper.resizeTextBasedOnAvailableSize(movesLabel);
		// Add it to the GUI
		add(movesLabel);
    	
    }
    
    public String getMoves() {
    	return Integer.toString(level.getAllowedMoves());
    }
    
    public void setupSetMovesBox() {
    	movesBox = new JTextField();
    	//TODO: Ensure only number is entered
    	movesBox.setBounds(320 + 56 * 12, 100, 100, 70);
    	//movesBox.setForeground(Color.ORANGE);
    	movesBox.setFont(new Font(movesBox.getFont().getName(),
				Font.BOLD, 28));
		// Add it to the GUI
		add(movesBox);
    }
    
    public void setupSetMoves(){
    	setMoves = new JButton("Set");
    	setMoves.setBounds(320 + 67 * 12, 100, 80, 70);
    	setMoves.setFont(new Font(movesBox.getFont().getName(),
				Font.BOLD, 18));
    	setMoves.addMouseListener(new SetNumMovePuzzle(this, level));
    	add(setMoves);

    }
    
    public String getMovesValue(){
    	return movesBox.getText();
    }
    
    public void setMovesLabel(String text) {
    	movesLabel.setText(" Moves : " + text);
    }
    
}
