package com.halaesus.kabasuji.player.boundary;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.entity.PieceSquare;
import com.halaesus.kabasuji.player.controller.ClickPieceInPalette;
import com.halaesus.kabasuji.player.controller.DragPieceFromWorkspaceToBoard;
import com.halaesus.kabasuji.player.controller.FlipHInWorkspace;
import com.halaesus.kabasuji.player.controller.FlipVInWorkspace;
import com.halaesus.kabasuji.player.controller.ReturnToLevelSelector;
import com.halaesus.kabasuji.player.controller.RotateCCInWorkspace;
import com.halaesus.kabasuji.player.controller.RotateCWInWorkspace;
import com.halaesus.kabasuji.player.entity.AbstractLevel;
import com.halaesus.kabasuji.player.entity.Piece;
import com.halaesus.kabasuji.player.entity.SplashModel;
import com.halaesus.kabasuji.utils.JLabelHelper;
import com.sun.org.apache.xml.internal.utils.SuballocatedByteVector;

@SuppressWarnings("serial")
public class AbstractLevelView extends JPanel {

	// Initialization Variables
	private boolean paintInitialized;
	// View-based (UI and user interation based) variables
	HashMap<Rectangle, MouseListener> clickMap;
	ArrayList<Rectangle> bullpenWorkspacePiecesMap;
	ArrayList<Rectangle> boardPiecesMap;
	Application myApplication;
	AbstractLevel level;
	// Image storage variables
	private Image backgroundImage;
	private Image backButton;
	private Image starShadow;
	private Image bullpenWindow;
	private Image boardImage;
	private Image paletteView;
	private Image[] hexominoImages;
	private Image[] hexominoDisabledImages;
	private Image rotateCCImage;
	private Image rotateCWImage;
	private Image flipVImage;
	private Image flipHImage;
	// View-based (UI Objects) variables
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
		implementMouseListeners();
		// Show Level Info
		showLevelInfo();
		// Calculate some scaled images for paintComponent function
		calculateScaledImages();
		// Set up Board Pieces Map and Bullpen Palette Pieces Map
		setupBoardPiecesMap();
		setupBullpenPiecesMap();
		// Set up Hexomino Count Labels
		setuphexominoCountLabels();
		// By default the paint hasn't occurred
		paintInitialized = false;
	}

	private void setuphexominoCountLabels() {
		// Set up Hexomino Count Labels
		hexCount = new JLabel[35];
		// Location Tracking for the Labels
		int printRow = 0;
		int printColumn = 0;
		// Iterate over and set them all
		for(int i = 0; i < 35; i++) {
			// Set up the count label
			hexCount[i] = new JLabel(String.valueOf(level.getLevelBullpen().getPalette().getHexomino(i).getCount()), SwingConstants.RIGHT);
			hexCount[i].setOpaque(false);
			hexCount[i].setBounds(40 + (39 * printColumn), 122 + (39 * printRow), 15, 15);
			hexCount[i].setForeground(Color.GREEN);
			// Resize text to fit stuff in
			JLabelHelper.resizeTextBasedOnAvailableSize(hexCount[i]);
			// Add it to the board
			add(hexCount[i]);
			
			// Deal with the cycle overs of the rows and columns
			if( (printColumn != 0) && (printColumn % 6 == 0) ) {
				printRow += 1; // We move to the next row
				printColumn = 0; // We start from the zeroth column
			} else
				printColumn++; // We move to the next column
		}
	}

	private void implementMouseListeners() {
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// Map coordinates in the Rectangles of the HashMap
				for( Rectangle rectangle : clickMap.keySet() ) {
					if( rectangle.contains(e.getX(), e.getY()) ) {
						// Invoke the mouseReleased function
						clickMap.get(rectangle).mouseReleased(e);
					}
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// Map coordinates in the Rectangles of the HashMap
				for( Rectangle rectangle : clickMap.keySet() ) {
					if( rectangle.contains(e.getX(), e.getY()) ) {
						// Invoke the mousePressed function
						clickMap.get(rectangle).mousePressed(e);
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// Map coordinates in the Rectangles of the HashMap
				for( Rectangle rectangle : clickMap.keySet() ) {
					if( rectangle.contains(e.getX(), e.getY()) ) {
						// Invoke the mouseExited function
						clickMap.get(rectangle).mouseExited(e);
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// Map coordinates in the Rectangles of the HashMap
				for( Rectangle rectangle : clickMap.keySet() ) {
					if( rectangle.contains(e.getX(), e.getY()) ) {
						// Invoke the mouseEntered function
						clickMap.get(rectangle).mouseEntered(e);
					}
				}
			}
			
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
		// Add Listener for DragPieceFromWorkspaceToBoard
		DragPieceFromWorkspaceToBoard dragW2Board = new DragPieceFromWorkspaceToBoard(this.level, AbstractLevelView.this);
		addMouseListener(dragW2Board);
		addMouseMotionListener(dragW2Board);
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
			boardImage = ImageIO.read(getClass().getResource("/resources/board.jpg")).getScaledInstance(612, 612, Image.SCALE_SMOOTH);
			paletteView = ImageIO.read(getClass().getResource("/resources/paletteWindow.jpg")).getScaledInstance(288, 210, Image.SCALE_SMOOTH);
			rotateCCImage = ImageIO.read(getClass().getResource("/resources/rotateCC.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
			rotateCWImage = ImageIO.read(getClass().getResource("/resources/rotateCW.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
			flipVImage = ImageIO.read(getClass().getResource("/resources/flipHorizontal.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
			flipHImage = ImageIO.read(getClass().getResource("/resources/flipVertical.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
			// Hexomino Images
			hexominoImages = new Image[35];
			hexominoDisabledImages = new Image[35];
			for(int i = 0; i < 35; i++) {
				hexominoImages[i] = ImageIO.read(getClass().getResource("/resources/" + (i + 1) + ".jpg")).getScaledInstance(39, 39, Image.SCALE_SMOOTH);
				hexominoDisabledImages[i] = ImageIO.read(getClass().getResource("/resources/" + (i + 1) + "_disabled.jpg")).getScaledInstance(39, 39, Image.SCALE_SMOOTH);
			}
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
		// Add palette controllers
		setupPaletteControllers(g);
		// Draw a piece in the Workspace if there is one there
		drawWorkspacePieceOrDraggingPiece(g);
		// A next call to this, will not be an initialization call
		paintInitialized = true; // TODO: Check if this should be here or in the entity
	}

	private void showBackToMainButton(Graphics g) {
		// Load up the image
		g.drawImage(backButton, 10, 15, null);
		// If this is the first paint:
		if(!paintInitialized)
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
		g.drawImage(boardImage, 330, 80, null);
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
			if( level.getLevelBullpen().getPalette().getHexomino(i).getCount() > 0 ) // If there are any pieces, show the colored piece image
				g.drawImage(hexominoImages[i], x, y, null);
			else // If there are no pieces, show the disabled piece image
				g.drawImage(hexominoDisabledImages[i], x, y, null);
			// If it is the first paint, then:
			if(!paintInitialized)
				// Add it to the HashMap
				clickMap.put(new Rectangle(x, y, width, height), new ClickPieceInPalette(level.getLevelBullpen().getPalette().getHexomino(i), AbstractLevelView.this));
			
			// Deal with the cycle overs of the rows and columns
			if( (paletteColumn != 0) && (paletteColumn % 6 == 0) ) {
				paletteRow += 1; // We move to the next row
				paletteColumn = 0; // We start from the zeroth column
			} else
				paletteColumn++; // We move to the next column
		}
	}
	
	private void setupPaletteControllers(Graphics g) {
		// If there exists a piece in the Workspace, then:
		if( this.level.getLevelBullpen().getWorkspace().getPiece() != null ) {
			// Load up all the images
			g.drawImage(flipHImage, 1, 360, null);
			g.drawImage(flipVImage, 214, 598, null);
			g.drawImage(rotateCCImage, 210, 355, null);
			g.drawImage(rotateCWImage, 1, 593, null);
		}
		// If it is the first paint, then:
		if(!paintInitialized) {
			// Add these to the HashMap
			clickMap.put(new Rectangle(1, 360, 90, 90), new FlipVInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractLevelView.this));
			clickMap.put(new Rectangle(214, 598, 90, 90), new FlipHInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractLevelView.this));
			clickMap.put(new Rectangle(210, 355, 90, 90), new RotateCCInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractLevelView.this));
			clickMap.put(new Rectangle(1, 593, 90, 90), new RotateCWInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractLevelView.this));
		}
	}
	
	private void drawWorkspacePieceOrDraggingPiece(Graphics g) {
		// If a piece is being dragged, we'd draw that first
		// TODO Shift this to a different function; Detect 50% Board Square
		if( this.level.isDraggingActive() ) {
			Piece toBeDrawn = this.level.getPieceBeingDragged();
			Point top6x6MatrixPoint = this.level.getTopPointOfMatrix();
			// Solve for xMin, xMax and yMin, yMax within the 6x6
			PieceSquare[] squares = toBeDrawn.getPieceSquares();
			int xMin = squares[0].getCol();
			int xMax = squares[0].getCol();
			int yMin = squares[0].getRow();
			int yMax = squares[0].getRow();
			
			for (PieceSquare s: squares){
				if (s.getCol() < xMin) xMin = s.getCol();
				if (s.getCol() > xMax) xMax = s.getCol();
				if (s.getRow() < yMin) yMin = s.getRow();
				if (s.getRow() > yMax) yMax = s.getRow();				
			}
			// Make the tightest piece rectangle
			Rectangle tightPieceRect = new Rectangle((xMin * 51) + top6x6MatrixPoint.x, 
					                                 (yMin * 51) + top6x6MatrixPoint.y, 
					                                 (xMax - xMin + 1) * 51, 
					                                 (yMax - yMin + 1) * 51);
			
			// Get the square on that board that collides with top of the 6x6 Matrix being dragged
			boolean exitLoop = false; 
			int i = 0, j = 0;
			for(i = 0; i < 12; i++) {
				for(j = 0; j < 12; j++) {
					if( boardPiecesMap.get((12 * i) + j).contains(new Point(top6x6MatrixPoint.x, 
							                                                top6x6MatrixPoint.y)) ) 
					{
						exitLoop = true;
						break;
					}
				}
				// Check
				if(exitLoop) break;
			}
			
			// Go over all the 6 PieceSquares within the Piece
			for( PieceSquare aPieceSquare : toBeDrawn.getPieceSquares() ) {
				// Save backup Graphics color
				Color oldColor = g.getColor();
				// Set new Colors
				Color origPieceColor = toBeDrawn.getColor();
				Color pieceTransparentColor = new Color(origPieceColor.getRed(), 
						                                origPieceColor.getGreen(), 
						                                origPieceColor.getBlue(), 
						                                200);

				// TODO: Check if piece is on active board bounds. Use board ka boundsFunction
				// Get entire board rectangle
				int xMinB = boardPiecesMap.get(0).x;
				int yMinB = boardPiecesMap.get(0).y;
				int xMaxB = boardPiecesMap.get(143).x + boardPiecesMap.get(143).width;
				int yMaxB = boardPiecesMap.get(143).y + boardPiecesMap.get(143).height;
				
				// If within on board: Check TL in in board bounds and BR in board bounds
				Rectangle boardRect = new Rectangle(xMinB, yMinB, xMaxB - yMinB, yMaxB - yMinB);
				if( boardRect.contains(tightPieceRect.x + tightPieceRect.width,
						               tightPieceRect.y + tightPieceRect.height) &&
					boardRect.contains(tightPieceRect.x, tightPieceRect.y))  {
					
					// Set transparent color and paint
					g.setColor(pieceTransparentColor);
					// Draw it out
					g.fillRect(top6x6MatrixPoint.x + (aPieceSquare.getCol() * 51), 
							   top6x6MatrixPoint.y + (aPieceSquare.getRow() * 51), 
							   51, 51);
					
					// Draw outline on the board
					// Create new Graphics Object
					Graphics2D graphics2d = (Graphics2D)g;
					// Dashed Stroke
					float dash[] = {10.0f};
					BasicStroke dashed = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
					// Set Color
					graphics2d.setColor(Color.WHITE);
					graphics2d.setStroke(dashed);
					// Draw it out on the board
					Rectangle rect = boardPiecesMap.get((12 * (i + aPieceSquare.getRow())) + j + aPieceSquare.getCol());
					graphics2d.draw(rect);
				} else {
					// Set solid color and paint
					g.setColor(origPieceColor);
					// Draw it out
					g.fillRect(top6x6MatrixPoint.x + (aPieceSquare.getCol() * 51),
							   top6x6MatrixPoint.y + (aPieceSquare.getRow() * 51), 
							   51, 51);
				}
				// Revert Graphics object back to original
				g.setColor(oldColor);
			}
			
			return;
		}
		// Check if there is a piece in the workspace
		if( level.getLevelBullpen().getWorkspace().pieceExists() ) {
			// We gotta draw it out
			Piece toBeDrawn = level.getLevelBullpen().getWorkspace().getPiece();
			// Go over all the 6 PieceSquares within
			for( PieceSquare aPieceSquare : toBeDrawn.getPieceSquares() ) {
				// Convert PivotRow and PivotCol to array index into bullpenPalettePiecesMap
				int arrayIndex = (aPieceSquare.getCol() * 6) + aPieceSquare.getRow();
				// Solve for the Rectangle
				Rectangle rectToDraw = bullpenWorkspacePiecesMap.get(arrayIndex);
				// Save backup Graphics color
				Color oldColor = g.getColor();
				// Set new Color
				g.setColor(toBeDrawn.getColor());
				// Draw it out
				g.fillRect(rectToDraw.x, rectToDraw.y, rectToDraw.width, rectToDraw.height);
				// Reset the color
				g.setColor(oldColor);
			}
		}
	}
	
	private void setupBoardPiecesMap() {
		// TODO: Should this map be here?
		// Setup the ArrayList
		boardPiecesMap = new ArrayList<Rectangle>();
		// Now, map all the pieces on the board
		for(int i = 0; i < 12; i++) // Iterate over 12 rows of the board
			for(int j = 0; j < 12; j++) // Iterate over 12 columns of the board
				boardPiecesMap.add(new Rectangle(330 + (51 * j), 80 + (51 * i), 51, 51));
	}
	
	public ArrayList<Rectangle> getBoardPiecesMap() {
		return boardPiecesMap;
	}
	
	private void setupBullpenPiecesMap() {
		// Set up the array list
		bullpenWorkspacePiecesMap = new ArrayList<Rectangle>();
		// Now, map them all out
		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 6; j++)
				bullpenWorkspacePiecesMap.add(new Rectangle(38 + (38 * i), 424 + (38 * j), 38, 38));
	}
	
	public ArrayList<Rectangle> getBullpenWorkspacePiecesMap() {
		return bullpenWorkspacePiecesMap;
	}
	
	public void setPieceInWorkspace(Piece p) {
		level.getLevelBullpen().getWorkspace().addPiece(p); // Add the piece to the Workspace of the Level Bullpen
		repaint(); // Force a repaint
	}
	
}