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

	private Image backgroundImage;
	private Image backButton;
	private Image starShadow;
	private Image bullpenWindow;
	private Image boardImage;
	private Image paletteView;
	private Image[] hexominoImages;
	
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
		// Calculate some scaled images for paintComponent function
		calculateScaledImages();
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
	
	private void calculateScaledImages() {
		try {
			backgroundImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/playerBackground.jpg")).getScaledInstance(1280, -1, Image.SCALE_SMOOTH);
			backButton = ImageIO.read(getClass().getResource("/resources/backButton.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			starShadow = ImageIO.read(getClass().getResource("/resources/starShadow.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			bullpenWindow = ImageIO.read(getClass().getResource("/resources/bullpenWindow.jpg")).getScaledInstance(-1, 612, Image.SCALE_SMOOTH);
			boardImage = ImageIO.read(getClass().getResource("/resources/board.jpg")).getScaledInstance(656, 612, Image.SCALE_SMOOTH);
			paletteView = ImageIO.read(getClass().getResource("/resources/paletteWindow.jpg")).getScaledInstance(288, 210, Image.SCALE_SMOOTH);
			// Hexomino Images
			hexominoImages = new Image[35];
			for(int i = 0; i < 35; i++)
				hexominoImages[i] = ImageIO.read(getClass().getResource("/resources/" + (i + 1) + ".jpg")).getScaledInstance(39, 39, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// Can't do anything
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the JPanel do its stuff
		// Render the background image
		g.drawImage(backgroundImage, 0, 0, null);
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
		g.drawImage(backButton, 10, 15, null);
		// Add it to the HashMap
		clickMap.put(new Rectangle(10, 15, 60, 50), new ReturnToLevelSelector(myApplication));
	}
	
	private void showUserStars(Graphics g) {
		stars = new BufferedImage[3];
		// Load up the images
		g.drawImage(starShadow, 1040, 9, null);
		g.drawImage(starShadow, 1120, 9, null);
		g.drawImage(starShadow, 1200, 9, null);
	}
	
	private void setupLeftPanel(Graphics g) {
		// Load up the left panel image
		g.drawImage(bullpenWindow, 0, 80, null);
	}
	
	private void setupRightPanel(Graphics g) {
		// Load up the right panel image
		g.drawImage(bullpenWindow, 968, 80, null);
	}
	
	private void setupGameBoard(Graphics g) {
		// Load up the board image in the middle of the two panels
		g.drawImage(boardImage, 309, 80, null);
	}
	
	private void setupPalette(Graphics g) {
		// Load up the palette board image in the left panel
		g.drawImage(paletteView, 9, 90, null);
	}
	
	private void setupHexominoes(Graphics g) {
		int paletteRow = 0; // To keep track of positions on the board
		int paletteColumn = 0;
		// Iterate over all 35 hexominoes and add them to the board
		for(int i = 0; i < 35; i++) {
			// Set up coordinates
			int width = 39; int height = 39;
			int x = 16 + (39 * paletteColumn);
			int y = 98 + (39 * paletteRow);
			// Add the image
			g.drawImage(hexominoImages[i], x, y, null);
			// Add it to the HashMap
			// TODO: clickMap.put(new Rectangle(x, y, width, height), new ClickPieceInPalette(new Hexomino(), AbstractLevelView.this));
			
			// Deal with the cycle overs of the rows and columns
			if( (paletteColumn != 0) && (paletteColumn % 6 == 0) ) {
				paletteRow += 1; // We move to the next row
				paletteColumn = 0; // We start from the zeroth column
			} else
				paletteColumn++; // We move to the next column
		}
	}

}