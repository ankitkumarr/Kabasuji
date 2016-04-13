package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.halaesus.kabasuji.player.entity.LightningLevel;
import com.halaesus.kabasuji.utils.JLabelHelper;

@SuppressWarnings("serial")
public class LightningLevelView extends AbstractLevelView {

	LightningLevel level;
	Timer countdownTimer;
	
	JLabel lightningModeLabel;
	JLabel timeRemaining;

	public LightningLevelView(Application anApplication, LightningLevel aLevel) {
		super(anApplication, aLevel); // Let the super initialize itself
		// Save the level
		level = aLevel;
		// Set up Lightning Specific Layout Stuff
		setupLevelTypeLabel();
		setupTimeRemainingLabel();
		// TODO: Rest of the setup
	}

	private void setupLevelTypeLabel() {
		// Create the label	
		lightningModeLabel = new JLabel("Lightning Mode");
		lightningModeLabel.setBounds(100, 10, 350, 60);
		lightningModeLabel.setForeground(Color.ORANGE);
		lightningModeLabel.setFont(new Font(lightningModeLabel.getFont().getName(), Font.BOLD, lightningModeLabel.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(lightningModeLabel);
		// Add it to the GUI
		add(lightningModeLabel);
	}
	
	public void performContentPaneShownActions() {
		// Reset time in level
		level.resetElapsedTime();
		// Start up the countdown timer
		if( countdownTimer != null ) {
			countdownTimer.stop();
			countdownTimer.start(); // Restart for the first time the timer
		} else {
			setupCountdownTimer(); // Create the timer
			countdownTimer.start(); // Start the timer for the first time
		}
	}
	
	private void setupTimeRemainingLabel() {
		// Calculate some values
		int seconds = level.getTimeLeft() % 60;
		int minutes = level.getTimeLeft() / 60;
		// Create the label	
		if( minutes == 0 )
			timeRemaining = new JLabel(String.valueOf(seconds).concat("s"), SwingConstants.RIGHT);
		else
			timeRemaining = new JLabel(String.valueOf(minutes).concat("m ").concat(String.valueOf(seconds).concat("s")), SwingConstants.RIGHT);
		timeRemaining.setBounds(470, 10, 150, 60);
		timeRemaining.setForeground(Color.ORANGE);
		timeRemaining.setFont(new Font(timeRemaining.getFont().getName(), Font.BOLD, timeRemaining.getFont().getSize()));
		timeRemaining.setOpaque(false); // Transparent background
		JLabelHelper.resizeTextBasedOnAvailableSize(timeRemaining); // One-time resizing of the font
		// Add it to the GUI
		add(timeRemaining);
		timeRemaining.repaint(); // Render the changes
	}

	private void setupCountdownTimer() {
		// TODO: Check if this Timer should somehow be converted to a controller?
		countdownTimer = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        // STEP 1: Check time to see if user ran out of time
		    	if( level.getTimeLeft() == 0 ) {
		    		countdownTimer.stop(); // Stop the timer
		    		// TODO: Do stuff which will tell the user he ran out of time
		    		return;
		    	}
		    	// STEP 2: Increment the elapsed time
		    	level.incrementElapsedTime();
		    	// STEP 3: Get the new time remaining
		    	int tRemaining = level.getTimeLeft();
		    	int seconds = tRemaining % 60;
		    	int minutes = tRemaining / 60;
		    	// STEP 4: Update the label
		    	if( minutes == 0 )
		    		timeRemaining.setText(String.valueOf(seconds).concat("s"));
		    	else
		    		timeRemaining.setText(String.valueOf(minutes).concat("m ").concat(String.valueOf(seconds).concat("s")));
		    	// STEP 5: Force repaint of the label
		    	timeRemaining.repaint();
		    }
		});
		countdownTimer.setRepeats(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its painting first
	}

}