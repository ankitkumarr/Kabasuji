package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.halaesus.kabasuji.builder.controller.SetNumMovePuzzle;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;


/**
 * Represents the basic representation of the Builder Level of Puzzle type
 * <p>
 * <b> PuzzleBuilderView </b> contains information about the GUI elements that are specific to 
 * Puzzle Level
 * </p>
 * @author Ankit Kumar
 */
@SuppressWarnings("serial")
public class PuzzleBuilderView extends AbstractBuilderView {

	/** JLabel to display the Number of moves currently set to allow the player*/
    JLabel movesLabel;
    
    /** TextField for the user to enter the number of moves to be set for allowing the player */
    JTextField movesBox;
    
    /** Button which when clicked will set the Move */
    JButton setMoves;
    
    /** PuzzleLevel to keep the information of the level which is specific to the PuzzleLevel */
    PuzzleLevel level;

    
    /**
     * Creates a PuzzleBuilderView to show all the elements in the Puzzle Level type in the builder
	 * @param application is the entire application which holds the information
	 * @param aLevel The Level that holds the information about the level to be displayed
	 * @param levelIndex  The index of the Level
     */
    public PuzzleBuilderView(Application application, AbstractLevel aLevel, int levelIndex) {
    	super(application, aLevel, levelIndex);
    	this.level = (PuzzleLevel) aLevel;
    	setupSetMovesBox();
    	setupSetMoves();
    	setupSetMovesLabel();
    	
    }
    
    /**
     * Sets up the Label to show in the Puzzle Window of the Builder
     */
    public void setupSetMovesLabel() {
    	movesLabel = new JLabel(" Moves : " + getMoves());
    	movesLabel.setBounds(320 + 56 * 12, 170, 324, 60);
    	movesLabel.setForeground(Color.ORANGE);
		movesLabel.setFont(new Font(movesLabel.getFont().getName(),
				Font.BOLD, 32));
		// Add it to the GUI
		add(movesLabel);
    	
    }
    
    
    /**
     * Return the integer moves that are allowed for the game
     * @return the allowed moves
     */
    public String getMoves() {
    	return Integer.toString(level.getAllowedMoves());
    }
    
    
    /**
     * Sets up the TextField for the user to enter the number of allowed moves
     */
    public void setupSetMovesBox() {
    	movesBox = new JTextField();
    	movesBox.setBounds(320 + 56 * 12, 100, 100, 70);
    	movesBox.setFont(new Font(movesBox.getFont().getName(),
				Font.BOLD, 28));
		// Add it to the GUI
		add(movesBox);
    }
    
    /**
     * Sets up the button to set the number of allowed moves that could be allowed by the user
     */
    public void setupSetMoves(){
    	
    	//Creates the button and adds it to the GUI
    	setMoves = new JButton("Set");
    	setMoves.setBounds(320 + 67 * 12, 100, 80, 70);
    	setMoves.setFont(new Font(movesBox.getFont().getName(),
				Font.BOLD, 18));
    	setMoves.addMouseListener(new SetNumMovePuzzle(this, level));
    	add(setMoves);

    }
    
    /**
     * Gets the text entered in the TextField for the moves
     * @return the String entered by the user
     */
    public String getMovesValue(){
    	return movesBox.getText();
    }
    
    /**
     * Sets the Move Label to show the Number of moves set by the user
     * @param text to give the function the text to be show to the user
     */
    public void setMovesLabel(String text) {
    	movesLabel.setText(" Moves : " + text);
    }
    
}
