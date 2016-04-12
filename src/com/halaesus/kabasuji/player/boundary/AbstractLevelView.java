package com.halaesus.kabasuji.player.boundary;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.halaesus.kabasuji.player.controller.ReturnToLevelSelector;
import com.halaesus.kabasuji.player.entity.SplashModel;

@SuppressWarnings("serial")
public class AbstractLevelView extends JPanel {

	HashMap<Rectangle, MouseListener> clickMap;
	Application myApplication;
	
	JLabel levelInfo;
	BufferedImage[] stars;
	BufferedImage[] hexButtons;
	JButton rotateCC;
	JButton rotateCW;
	JButton flipH;
	JButton flipV;
	BufferedImage[] workspacePieceSquares;
	BufferedImage[] boardPieceSquares;
	BufferedImage[] boardSquares;
	JLabel[] hexCount;
	
	public AbstractLevelView(Application application) {
		this.myApplication = application; // Save the application object passed to us
		// Set GUI Bounds
		setBounds(0, 0, 1280, 720);
		// Set up LayoutManager to null
		setLayout(null);
		// Initialize HashMap
		clickMap = new HashMap<Rectangle, MouseListener>();
		// Implement MouseListener
		implementMouseListener();
	}
	
	private void implementMouseListener() {
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) { /* Do nothing */ }
			
			@Override
			public void mousePressed(MouseEvent e) { /* Do nothing */ }
			
			@Override
			public void mouseExited(MouseEvent e) { /* Do nothing */ }
			
			@Override
			public void mouseEntered(MouseEvent e) { /* Do nothing */ }
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// Map coordinates in the Rectangles of the HashMap
				for( Rectangle rectangle : clickMap.keySet() ) {
					if( rectangle.contains(e.getX(), e.getY()) ) {
						// Invoke the mouseClicked function
						clickMap.get(rectangle).mouseClicked(e);
					}
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the JPanel do its stuff
		// Render the background image
		try {
			g.drawImage(ImageIO.read(SplashModel.class.getResourceAsStream("/resources/playerBackground.jpg")).getScaledInstance(1280, -1, Image.SCALE_SMOOTH), 0, 0, null);
		} catch (IOException e) {
			// Don't render the background
		}
		// Render BackToMainButton
		showBackToMainButton(g);
		// Setup left and right panels
		setupLeftPanel(g);
		setupRightPanel(g);
		// Set up the game board
		setupGameBoard(g);
		// Set up hexomino views
		setupHexominoes(g);
	}

	private void showBackToMainButton(Graphics g) {
		// Load up the image
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/backButton.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH), 10, 15, null);
			// Add it to the HashMap
			clickMap.put(new Rectangle(10, 15, 60, 50), new ReturnToLevelSelector(myApplication));
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}
	
	private void setupLeftPanel(Graphics g) {
		// Load up the left panel image
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/bullpenWindow.jpg")).getScaledInstance(-1, 612, Image.SCALE_SMOOTH), 0, 80, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}
	
	private void setupRightPanel(Graphics g) {
		// Load up the right panel image
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/bullpenWindow.jpg")).getScaledInstance(-1, 612, Image.SCALE_SMOOTH), 968, 80, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}
	
	private void setupGameBoard(Graphics g) {
		// Load up the board image in the middle of the two panels
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/board.jpg")).getScaledInstance(656, 612, Image.SCALE_SMOOTH), 309, 80, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}
	
	private void setupHexominoes(Graphics g) {
		int paletteRow = 0; // To keep track of positions on the board
		int paletteColumn = 0;
		// Iterate over all 35 hexominoes and add them to the board
		for(int i = 0; i < 35; i++) {
			try {
				g.drawImage(ImageIO.read(getClass().getResource("/resources/" + (i + 1) + ".jpg")).getScaledInstance(41, 41, Image.SCALE_SMOOTH), 
						    9 + (41 * paletteColumn),
						    90 + (41 * paletteRow), 
						    null);
			} catch (IOException ex) {
				// Cannot do anything. Skip over this hexomino
			}
			// Deal with the cycle overs of the rows and columns
			if( (paletteColumn != 0) && (paletteColumn % 6 == 0) ) {
				paletteRow += 1; // We move to the next row
				paletteColumn = 0; // We start from the zeroth column
			} else
				paletteColumn++; // We move to the next column
		}
	}

}