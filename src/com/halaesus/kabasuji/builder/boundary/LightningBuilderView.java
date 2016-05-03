package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


import com.halaesus.kabasuji.builder.controller.SetNumRandPiecesLightning;
import com.halaesus.kabasuji.builder.controller.SetTimeLightning;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

@SuppressWarnings("serial")
public class LightningBuilderView extends AbstractBuilderView {
    JLabel timeRemLabel;
    JTextField minText;
    JTextField secText;
    JLabel minLabel;
    JLabel secLabel;
    JLabel randPiecesLabel;
    JTextField randPiecesText;
    JButton setTime;
    JButton setPiecesCount;
    LightningLevel level;

    public LightningBuilderView(Application application, AbstractLevel aLevel, int levelIndex) {
        
    	super(application, aLevel, levelIndex);
    	this.level = (LightningLevel) aLevel;
    	setupTimeAndPieces();
    	setupsetButtons();
    	setupTimeAndMovesLabel();
    	
    }
    
    public void setupTimeAndMovesLabel() {
    	minLabel = new JLabel(" Minutes : " + getMinutes());
    	minLabel.setBounds(320 + 56 * 12, 140, 324, 60);
    	minLabel.setForeground(Color.ORANGE);
    	minLabel.setFont(new Font(minLabel.getFont().getName(),
				Font.BOLD, 26));
		//JLabelHelper.resizeTextBasedOnAvailableSize(movesLabel);
		// Add it to the GUI
		add(minLabel);
		
		secLabel =  new JLabel(" Seconds : " + getSeconds());
		secLabel.setBounds(320 + 56 * 12, 240, 324, 60);
		secLabel.setForeground(Color.ORANGE);
		secLabel.setFont(new Font(secLabel.getFont().getName(),
				Font.BOLD, 26));
		//JLabelHelper.resizeTextBasedOnAvailableSize(movesLabel);
		// Add it to the GUI
		add(secLabel);
		
		randPiecesLabel =  new JLabel(" Random Moves : " + getMoves());
		randPiecesLabel.setBounds(320 + 56 * 12, 340, 324, 60);
		randPiecesLabel.setForeground(Color.ORANGE);
		randPiecesLabel.setFont(new Font(randPiecesLabel.getFont().getName(),
				Font.BOLD, 26));
		//JLabelHelper.resizeTextBasedOnAvailableSize(movesLabel);
		// Add it to the GUI
		add(randPiecesLabel);
    }
    
    public String getMinutes() {
    	return Integer.toString(level.getMaxTime()/60);
 
    }
    
    public String getSeconds() {
    	return Integer.toString(level.getMaxTime()%60);
    }
    
    public String getMoves() {
    	return Integer.toString(level.getRandomMoves());
    }
    
    public void setupTimeAndPieces() {
    	minText = new JTextField();
    	//TODO: Ensure only number is entered
    	minText.setBounds(320 + 56 * 12, 100, 70, 50);
    	//movesBox.setForeground(Color.ORANGE);
    	minText.setFont(new Font(minText.getFont().getName(),
				Font.BOLD, 28));
		// Add it to the GUI
		add(minText);
		
		secText = new JTextField();
    	//TODO: Ensure only number is entered
		secText.setBounds(320 + 56 * 12, 200, 70, 50);
    	//movesBox.setForeground(Color.ORANGE);
		secText.setFont(new Font(secText.getFont().getName(),
				Font.BOLD, 28));
		// Add it to the GUI
		add(secText);
		
		randPiecesText = new JTextField();
    	//TODO: Ensure only number is entered
		randPiecesText.setBounds(320 + 56 * 12, 300, 70, 50);
    	//movesBox.setForeground(Color.ORANGE);
		randPiecesText.setFont(new Font(randPiecesText.getFont().getName(),
				Font.BOLD, 28));
		// Add it to the GUI
		add(randPiecesText);
		
		
    }
    
    public void setupsetButtons(){
    	setTime = new JButton("Set Time");
    	setTime.setBounds(320 + 67 * 12, 200, 130, 50);
    	setTime.setFont(new Font(setTime.getFont().getName(),
				Font.BOLD, 18));
    	setTime.addMouseListener(new SetTimeLightning(this, level));
    	add(setTime);
    	
    	setPiecesCount = new JButton("Set Pieces");
    	setPiecesCount.setBounds(320 + 67 * 12, 300, 130, 50);
    	setPiecesCount.setFont(new Font(setPiecesCount.getFont().getName(),
				Font.BOLD, 18));
    	setPiecesCount.addMouseListener(new SetNumRandPiecesLightning(this, level));
    	add(setPiecesCount);


    }
    
    public String getMinutesValue(){
    	return minText.getText();
    }
    public String getSecondsValue(){
    	return secText.getText();
    }
    public String getRandPiecesValue(){
    	return randPiecesText.getText();
    }
    
    
    public void setmovesLabel(String text) {
    	randPiecesLabel.setText(" Random Moves : " + text);
    } 
    
    public void setMinutesLabel(String text) {
    	minLabel.setText(" Minutes : " + text);
    }
    
    public void setSecondsLabel(String text) {
    	secLabel.setText(" Seconds : " + text);
    }
    
}