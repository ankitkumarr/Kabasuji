package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
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

import com.halaesus.kabasuji.player.controller.ClickPieceInPalette;
import com.halaesus.kabasuji.player.controller.ReturnToLevelSelector;
import com.halaesus.kabasuji.player.entity.AbstractLevel;
import com.halaesus.kabasuji.player.entity.Hexomino;
import com.halaesus.kabasuji.player.entity.SplashModel;
import com.halaesus.kabasuji.utils.JLabelHelper;

@SuppressWarnings("serial")
public class AbstractLevelView extends JPanel {

	HashMap<Rectangle, MouseListener> clickMap;
	Application myApplication;
	AbstractLevel level;
	
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
	
	public AbstractLevelView(Application application, AbstractLevel aLevel) {
		this.myApplication = application; // Save the application object passed to us
		this.level = aLevel; // Save the level being played here
		// Set GUI Bounds
		setBounds(0, 0, 1280, 720);
		// Set up LayoutManager to null
		setLayout(null);
		// Initialize HashMap
		clickMap = new HashMap<Rectangle, MouseListener>();
		// Implement MouseListener
		implementMouseListener();
		// Show Level Info
		showLevelInfo();
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
	
	private void showLevelInfo() {
		// Create the label
		levelInfo = new JLabel("Level ".concat(String.valueOf(level.getLevelIndex() + 1)));
		levelInfo.setBounds(750, 10, 200, 60);
		levelInfo.setForeground(Color.ORANGE);
		levelInfo.setFont(new Font(levelInfo.getFont().getName(), Font.BOLD, levelInfo.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(levelInfo);
		// Add it to the GUI
		add(levelInfo);
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
		// Render BackToMainButton and Level Number
		showBackToMainButton(g);
		showUserStars(g);
		// Setup left and right panels
		setupLeftPanel(g);
		setupRightPanel(g);
		// Set up the game board and palette
		setupGameBoard(g);
		setupPalette(g);
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
	
	private void showUserStars(Graphics g) {
		stars = new BufferedImage[3];
		// Load up the images
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/starShadow.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH), 1040, 9, null);
			g.drawImage(ImageIO.read(getClass().getResource("/resources/starShadow.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH), 1120, 9, null);
			g.drawImage(ImageIO.read(getClass().getResource("/resources/starShadow.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH), 1200, 9, null);
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
	
	private void setupPalette(Graphics g) {
		// Load up the palette board image in the left panel
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/paletteWindow.jpg")).getScaledInstance(288, -1, Image.SCALE_SMOOTH), 9, 394, null);
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
				// Set up coordinates
				int width = 41; int height = 41;
				int x = 9 + (41 * paletteColumn);
				int y = 90 + (41 * paletteRow);
				// Add the image
				g.drawImage(ImageIO.read(getClass().getResource("/resources/" + (i + 1) + ".jpg")).getScaledInstance(width, height, Image.SCALE_SMOOTH), 
						    x, y, null);
				// Add it to the HashMap
				// TODO: clickMap.put(new Rectangle(x, y, width, height), new ClickPieceInPalette(new Hexomino(), AbstractLevelView.this));
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