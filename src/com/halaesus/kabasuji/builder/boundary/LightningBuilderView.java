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


/**
 * Represents the basic representation of the Builder Level of Lightning type
 * <p>
 * <b> LightningBuilderView </b> contains information about the GUI elements that are specific to 
 * Lightning Level
 * </p>
 * @author Ankit Kumar
 */
@SuppressWarnings("serial")
public class LightningBuilderView extends AbstractBuilderView {
	
	/** Text Field to enter the minutes allowed */
    JTextField minText;
    
	/** Text Field to enter the seconds allowed */
    JTextField secText;
    
    /** Label to display the minutes allowed */
    JLabel minLabel;

    /** Label to display the seconds allowed */
    JLabel secLabel;

    /** Label to display the Random pieces displayed */
    JLabel randPiecesLabel;
    
    /** Text Field to display the Random pieces Allowed */
    JTextField randPiecesText;
    
    /** Button to set the Time once entered by the user */
    JButton setTime;
    
    /** Button to set the number of random pieces allowed once entered by the user */
    JButton setPiecesCount;
    
    /** LightningLevel to store the information about the Lightning Level */
    LightningLevel level;

    
    /**
	 * Constructs a LightningBuilderView to display the window by calling all the other setup functions
	 * @param application is the entire application which holds the information
	 * @param aLevel The Level that holds the information about the level to be displayed
	 * @param levelIndex  The index of the Level
	 */
    public LightningBuilderView(Application application, AbstractLevel aLevel, int levelIndex) {
        
    	super(application, aLevel, levelIndex);
    	this.level = (LightningLevel) aLevel;
    	setupTimeAndPieces();
    	setupsetButtons();
    	setupTimeAndMovesLabel();
    	
    }
    
    /**
     * Sets up the Labels that will show the Time set and the number of random moves allowed to the user
     */
    public void setupTimeAndMovesLabel() {
    	
    	//Sets up the Minutes Label
    	minLabel = new JLabel(" Minutes : " + getMinutes());
    	minLabel.setBounds(320 + 56 * 12, 140, 324, 60);
    	minLabel.setForeground(Color.ORANGE);
    	minLabel.setFont(new Font(minLabel.getFont().getName(),
				Font.BOLD, 26));
		// Add it to the GUI
		add(minLabel);
		
		//Sets up the Seconds Label
		secLabel =  new JLabel(" Seconds : " + getSeconds());
		secLabel.setBounds(320 + 56 * 12, 240, 324, 60);
		secLabel.setForeground(Color.ORANGE);
		secLabel.setFont(new Font(secLabel.getFont().getName(),
				Font.BOLD, 26));
		// Add it to the GUI
		add(secLabel);
		
		//Sets up the Random Pieces Label
		randPiecesLabel =  new JLabel(" Random Moves : " + getMoves());
		randPiecesLabel.setBounds(320 + 56 * 12, 340, 324, 60);
		randPiecesLabel.setForeground(Color.ORANGE);
		randPiecesLabel.setFont(new Font(randPiecesLabel.getFont().getName(),
				Font.BOLD, 26));
		// Add it to the GUI
		add(randPiecesLabel);
    }
    
    /**
     * Gets the number of minutes that is set to be allowed for the level
     * @return the number of minutes allowed
     */
    public String getMinutes() {
    	return Integer.toString(level.getMaxTime()/60);
 
    }
    

    /**
     * Gets the number of seconds that is set to be allowed for the level
     * @return the number of seconds allowed
     */
    public String getSeconds() {
    	return Integer.toString(level.getMaxTime()%60);
    }
    

    /**
     * Gets the number of moves that is set to be allowed for the level
     * @return the number of moves allowed
     */
    public String getMoves() {
    	return Integer.toString(level.getNumRandomPieces());
    }
    
    /**
     * Sets up the Text Fields for the user to enter the information of the minutes, seconds and random moves
     */
    public void setupTimeAndPieces() {
    	
    	//Set the minutes TextField
    	minText = new JTextField();
    	minText.setBounds(320 + 56 * 12, 100, 70, 50);
    	minText.setFont(new Font(minText.getFont().getName(),
				Font.BOLD, 28));
		// Add it to the GUI
		add(minText);
		

    	//Set the seconds TextField
		secText = new JTextField();
		secText.setBounds(320 + 56 * 12, 200, 70, 50);
		secText.setFont(new Font(secText.getFont().getName(),
				Font.BOLD, 28));
		// Add it to the GUI
		add(secText);
		

    	//Set the random pieces TextField
		randPiecesText = new JTextField();
		randPiecesText.setBounds(320 + 56 * 12, 300, 70, 50);
		randPiecesText.setFont(new Font(randPiecesText.getFont().getName(),
				Font.BOLD, 28));
		// Add it to the GUI
		add(randPiecesText);
		
		
    }
    
    /**
     * Sets up the button that sets the time and random moves for the player to play in the level
     */
    public void setupsetButtons(){
    	
    	//Set the button to set the time 
    	setTime = new JButton("Set Time");
    	setTime.setBounds(320 + 67 * 12, 200, 130, 50);
    	setTime.setFont(new Font(setTime.getFont().getName(),
				Font.BOLD, 18));
    	setTime.addMouseListener(new SetTimeLightning(this, level));
    	add(setTime);
    	
    	//Set the button to set the number of random pieces to be available for the player
    	setPiecesCount = new JButton("Set Pieces");
    	setPiecesCount.setBounds(320 + 67 * 12, 300, 130, 50);
    	setPiecesCount.setFont(new Font(setPiecesCount.getFont().getName(),
				Font.BOLD, 18));
    	setPiecesCount.addMouseListener(new SetNumRandPiecesLightning(this, level));
    	add(setPiecesCount);


    }
    
    /**
     * Gets the value of the Text entered in the minText JTextField
     * @return text in minText
     */
    public String getMinutesValue(){
    	return minText.getText();
    }
    

    /**
     * Gets the value of the Text entered in the secText JTextField
     * @return text in secText
     */
    public String getSecondsValue(){
    	return secText.getText();
    }
    

    /**
     * Gets the value of the Text entered in the randPiecesText JTextField
     * @return text in randPiecesText
     */
    public String getRandPiecesValue(){
    	return randPiecesText.getText();
    }
    

    /**
     * Sets the value of the label in the randPiecesLabel JLabel
     * @param text the text to set the value to
     */
    public void setMovesLabel(String text) {
    	randPiecesLabel.setText(" Random Moves : " + text);
    } 
    

    /**
     * Sets the value of the label in the minLabel JLabel
     * @param text the text to set the value to
     */
    public void setMinutesLabel(String text) {
    	minLabel.setText(" Minutes : " + text);
    }
    

    /**
     * Sets the value of the label in the secLabel JLabel
     * @param text the text to set the value to
     */
    public void setSecondsLabel(String text) {
    	secLabel.setText(" Seconds : " + text);
    }
    
}