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
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.halaesus.kabasuji.player.controller.ClickPieceInPalette;
import com.halaesus.kabasuji.player.controller.DragPieceFromWorkspaceToBoard;
import com.halaesus.kabasuji.player.controller.FlipHInWorkspace;
import com.halaesus.kabasuji.player.controller.FlipVInWorkspace;
import com.halaesus.kabasuji.player.controller.RequestBackToLevelSelector;
import com.halaesus.kabasuji.player.controller.ReturnToLevelSelector;
import com.halaesus.kabasuji.player.controller.RotateCCInWorkspace;
import com.halaesus.kabasuji.player.controller.RotateCWInWorkspace;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;
import com.halaesus.kabasuji.shared.entity.SplashModel;
import com.halaesus.kabasuji.utils.JButtonHelper;
import com.halaesus.kabasuji.utils.JLabelHelper;
import com.halaesus.kabasuji.utils.PieceGenerator;
import com.halaesus.kabasuji.utils.PieceHelper;

/**
 * Represents the generic LevelView for the Player Side
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
@SuppressWarnings("serial")
public abstract class AbstractLevelView extends JPanel {
	
	// Initialization Variables
	/** Keeps track if the paintComponent(g) and paint(g) functions have been called atleast once */
	private boolean paintInitialized;
	
	/** Keeps track if the pain(g) function has been called atleast once after painting the LevelCompletion Info Overlay */
	private boolean paintLevelCompleted;
	
	// View-based (UI and user interaction based) variables
	/** HashMap of Rectangle and MouseListeners to track clicks that are not related to any JComponent */
	HashMap<Rectangle, MouseListener> clickMap;
	
	/** Represents the top-left point of the Bullpen Workspace */
	Point bullpenPiecesBoardTopPoint;
	
	/** Represents the top-left point of the Player Board */
	Point boardPiecesTopPoint;
	
	/** The top-level application */
	Application myApplication;
	
	/** The level that this LevelView represents */
	AbstractLevel level;
	
	// Image storage variables
	/** Background Image of the entire LevelView */
	private Image backgroundImage;
	
	/** Image for the Back Button to the Top-Left */
	private Image backButton;
	
	/** Image for Gold Star (filled Star) */
	private Image starGold;
	
	/** Image for high-res Gold Star (filled Star) */
	private Image starGoldBig;
	
	/** Image for Shadow Star (unfilled Star) */
	private Image starShadow;
	
	/** Image for high-res Shadow Star (filled Star) */
	private Image starShadowBig;
	
	/** Images for all 35 enabled Hexominos */
	private ImageIcon[] hexominoImages;
	
	/** Images for all 35 disabled Hexominos */
	private ImageIcon[] hexominoDisabledImages;
	
	/** Image for the unfilled Trash Can */
	private Image trashCanUnfilled;
	
	/** Image for filled Trash Can */
	private Image trashCanFilled;
	
	/** Image for Button to rotate Piece Counter-Clockwise */
	private Image rotateCCImage;
	
	/** Image for Button to rotate Piece Clockwise */
	private Image rotateCWImage;
	
	/** Image for Button to flip Vertically */
	private Image flipVImage;
	
	/** Image for Button to flip horizontally */
	private Image flipHImage;
	
	// View-based (UI Objects) variables
	/** Buttons to show the Hexomino Pieces */
	HexominoButtonView[] hexominoButton;
	
	/** Images to show the Board PieceSquares */
	BufferedImage[] boardPieceSquares;
	
	/** Label to show the Level Information */
	JLabel levelInfo;
	
	/** Button to handle rotating the piece in CC fashion */
	JButton rotateCC;
	
	/** Button to handle rotating the piece in CW fashion */
	JButton rotateCW;
	
	/** Button to handle rotating the piece vertically */
	JButton flipV;
	
	/** Button to handle rotating the piece horizontally */
	JButton flipH;
	
	/**
	 * Constructs an AbstractLevelView with the associated Application and AbstractLevel whose information has to be shown
	 * @param application
	 * @param aLevel
	 */
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
		// Set up Board Pieces Map and Bullpen Pallette Pieces Top Point
		setupBoardPiecesTopPoint();
		setupBullpenPiecesBoardTopPoint();
		// Set up hexomino button views
		setupHexominoesButtons();
		// Set up Hexomino Count Labels
		setuphexominoCountLabels();
		// Set up Palette Controllers
		setupPaletteControllers();
		// By default the paint hasn't occurred
		paintInitialized = false;
	}

	/**
	 * Places all the Palette Controllers in the JPanel and adds necessary MouseListeners to them
	 */
	private void setupPaletteControllers() {
		// Create the FlipV Button
		flipV = new JButton(new ImageIcon(flipVImage));
		flipV.setBounds(1, 400, 90, 90);
		flipV.addMouseListener(new FlipVInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractLevelView.this));
		JButtonHelper.makeBackgroundTransparent(flipV);
		add(flipV);
		// Create the FlipH Button
		flipH = new JButton(new ImageIcon(flipHImage));
		flipH.setBounds(230, 630, 90, 90);
		flipH.addMouseListener(new FlipHInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractLevelView.this));
		JButtonHelper.makeBackgroundTransparent(flipH);
		add(flipH);
		// Create the RotateCC Button
		rotateCC = new JButton(new ImageIcon(rotateCCImage));
		rotateCC.setBounds(230, 400, 90, 90);
		rotateCC.addMouseListener(new RotateCCInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractLevelView.this));
		JButtonHelper.makeBackgroundTransparent(rotateCC);
		add(rotateCC);
		// Create the RotateCW Button
		rotateCW = new JButton(new ImageIcon(rotateCWImage));
		rotateCW.setBounds(1, 630, 90, 90);
		rotateCW.addMouseListener(new RotateCWInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractLevelView.this));
		JButtonHelper.makeBackgroundTransparent(rotateCW);
		add(rotateCW);
	}

	/**
	 * Implements HashMap-based MouseListener and the generic WorkspaceToBoard Move MouseListener
	 */
	private void implementMouseListeners() {
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
		// Add Listener for DragPieceFromWorkspaceToBoard
		DragPieceFromWorkspaceToBoard dragWorkspaceToBoard = new DragPieceFromWorkspaceToBoard(this.level, AbstractLevelView.this);
		addMouseListener(dragWorkspaceToBoard);
		addMouseMotionListener(dragWorkspaceToBoard);
	}
	
	/**
	 * Adds all level based information as GUI elements
	 */
	private void showLevelInfo() {
		// Create the label
		//levelInfo = new JLabel("Level ".concat(String.valueOf(level.getLevelIndex() + 1)));
		levelInfo = new JLabel(level.getLevelName());
		levelInfo.setBounds(750, 10, 200, 60);
		levelInfo.setForeground(Color.ORANGE);
		levelInfo.setFont(new Font(levelInfo.getFont().getName(), Font.BOLD, levelInfo.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(levelInfo);
		// Add it to the GUI
		add(levelInfo);
	}
	
	/**
	 * Scales down/up all images necessary for rendering the AbstractLevelView and caching them to speed up performance
	 */
	private void calculateScaledImages() {
		try {
			backgroundImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/gridWithBoard.jpg")).getScaledInstance(1280, -1, Image.SCALE_SMOOTH);
			backButton = ImageIO.read(getClass().getResource("/resources/backButton.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			starGold = ImageIO.read(getClass().getResource("/resources/starGold.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			starGoldBig = ImageIO.read(getClass().getResource("/resources/starGoldLarge.png"));
			starShadow = ImageIO.read(getClass().getResource("/resources/starShadow.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			starShadowBig = ImageIO.read(getClass().getResource("/resources/starShadowLarge.png"));
			rotateCCImage = ImageIO.read(getClass().getResource("/resources/rotateCC.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
			rotateCWImage = ImageIO.read(getClass().getResource("/resources/rotateCW.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
			flipHImage = ImageIO.read(getClass().getResource("/resources/flipHorizontal.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
			flipVImage = ImageIO.read(getClass().getResource("/resources/flipVertical.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
			trashCanUnfilled = ImageIO.read(getClass().getResource("/resources/trashcan_empty.png")).getScaledInstance(128, 128, Image.SCALE_SMOOTH);
			trashCanFilled = ImageIO.read(getClass().getResource("/resources/trashcan_full.png")).getScaledInstance(128, 128, Image.SCALE_SMOOTH);
			// Hexomino Images
			hexominoImages = new ImageIcon[35];
			hexominoDisabledImages = new ImageIcon[35];
			for(int i = 0; i < 35; i++) {
				hexominoImages[i] = new ImageIcon(ImageIO.read(getClass().getResource("/resources/" + (i + 1) + ".jpg")).getScaledInstance(53, 53, Image.SCALE_SMOOTH));
				hexominoDisabledImages[i] = new ImageIcon(ImageIO.read(getClass().getResource("/resources/" + (i + 1) + "_disabled.jpg")).getScaledInstance(53, 53, Image.SCALE_SMOOTH));
			}
		} catch (IOException e) {
			// Can't do anything
		}
	}
	
	/**
	 * Sets up Hexomino Buttons in the Player Palette
	 */
	private void setupHexominoesButtons() {
		int paletteRow = 0; // To keep track of positions on the board
		int paletteColumn = 0;
		// Initialize the array
		hexominoButton = new HexominoButtonView[35];
		// Iterate over all 35 hexominoes and add them to the board
		for(int i = 0; i < 35; i++) {
			// Set up coordinates
			int width = 53; int height = 53;
			int x = 1 + (53 * paletteColumn);
			int y = 81 + (53 * paletteRow);
			// Add the button
			hexominoButton[i] = new HexominoButtonView(hexominoImages[i], hexominoDisabledImages[i]);
			// Add it to the GUI
			hexominoButton[i].setHexominoCount(level.getLevelBullpen().getPalette().getHexomino(i).getCount());
			hexominoButton[i].setBounds(x, y, width, height);
			hexominoButton[i].addMouseListener(new ClickPieceInPalette(level.getLevelBullpen().getPalette().getHexomino(i), AbstractLevelView.this));
			add(hexominoButton[i]);
			
			// Deal with the cycle overs of the rows and columns
			if( (paletteColumn != 0) && (paletteColumn % 5 == 0) ) {
				paletteRow += 1; // We move to the next row
				paletteColumn = 0; // We start from the zeroth column
			} else
				paletteColumn++; // We move to the next column
		}
	}

	/**
	 * Sets up each Hexomino's count in all of the Hexomino Buttons in the Player Palette
	 */
	private void setuphexominoCountLabels() {
		// Go on and add the count labels to all of them
		for(int i = 0; i < 35; i++) {
			// Set up the count label
			hexominoButton[i].setHexominoCount(level.getLevelBullpen().getPalette().getHexomino(i).getCount());
		}
	}
	
	/**
	 * Override paint function to place overlays and dragging pieces 
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g); // Let the super do its stuff
		// Draw a dragging piece
		if( this.level.isDraggingActive() ) {
			drawBullpenTrashCan(g); // Draw the trash can over the Bullpen and then draw the Piece
			drawDraggingPiece(g); // Paint the piece out now
		}
		// Draw the level completion dialog
		if( this.level.isLevelCompletedShown() ) {
			drawLevelCompleted(g); // Draw the level completed overlay and text
		}
	}

	/**
	 * Draw the Bullpen Trash Can if the Piece being dragged is over the Player Bullpen Bounds
	 * @param g
	 */
	private void drawBullpenTrashCan(Graphics g) {
		assert( this.level.isDraggingActive() == true ); // This function can only be called if there is a piece being dragged
		// Check dragging state; Source needs to be the Board
		if( this.level.getDragSource() == AbstractLevel.DRAG_SOURCE_BOARD ) {
			
			// Backup the old color
			Color oldColor = g.getColor();
			// Draw the Overlay based on level and moves left decision
			if( level.getLevelType().toUpperCase().equals("PUZZLE") && (((PuzzleLevel)level).getMovesLeft() == 0) )
				g.setColor(new Color(255, 50, 50, 170));
			else
				g.setColor(new Color(84, 84, 84, 170));
			// Fill out the Overlay
			g.fillRect(this.getBullpenBounds().x,
					   this.getBullpenBounds().y,
					   this.getBullpenBounds().width, 
					   this.getBullpenBounds().height);
			// Restore the color
			g.setColor(oldColor);
			// Place the image
			Rectangle imageRect = new Rectangle( this.getBullpenBounds().x + (int)((this.getBullpenBounds().width - trashCanFilled.getWidth(null)) / 2),
												 this.getBullpenBounds().y + (int)((this.getBullpenBounds().height - trashCanFilled.getHeight(null)) / 2),
												 trashCanFilled.getWidth(null),
												 trashCanFilled.getHeight(null));
			// To draw the filled trashcan the Piece HAS to be over the bullpen and either the level is NOT a puzzle level or it is an the user has moves to perform
			if( this.level.isPieceOverBullpen() && ( !level.getLevelType().toUpperCase().equals("PUZZLE") ||
					                                 ( level.getLevelType().toUpperCase().equals("PUZZLE") && (((PuzzleLevel)level).getMovesLeft() != 0) ) ) )
				g.drawImage(trashCanFilled, imageRect.x, imageRect.y, imageRect.width, imageRect.height, null);
			else
				g.drawImage(trashCanUnfilled, imageRect.x, imageRect.y, imageRect.width, imageRect.height, null);
			
		}
	}

	/**
	 * Draws the dragging piece if there is an active drag
	 * @param g
	 */
	private void drawDraggingPiece(Graphics g) {
		assert( this.level.isDraggingActive() == true ); // This function can only be called if there is a piece being dragged
		// If a piece is being dragged, we'd draw that first
		Piece toBeDrawn = this.level.getPieceBeingDragged();
		Point topPointToDraw = this.level.getTopPointOfDraggingPiece();
		
		// for bevel effect
		ArrayList<Rectangle> bevelRects = new ArrayList<Rectangle>();
		
		// Calculate tightest rectangle around PieceSquares
		PieceSquare[] squares = toBeDrawn.getPieceSquares();
		int xMax = squares[0].getCol();
		int yMax = squares[0].getRow();
		
		for( PieceSquare s: squares ){
			if (s.getCol() > xMax)
				xMax = s.getCol(); // We found a new max, save it
			if (s.getRow() > yMax)
				yMax = s.getRow(); // We found a new max, save it
		}
		
		Rectangle tighestPieceRectangle = new Rectangle(topPointToDraw.x, 
				                                        topPointToDraw.y, 
				                                        (xMax + 1) * 53, 
				                                        (yMax + 1) * 53);
		
		// Calculate the Board Rectangle
		Rectangle overallBoardRectangle = new Rectangle(boardPiecesTopPoint.x - 25, 
				                                        boardPiecesTopPoint.y - 25,
				                                        13 * 53,
				                                        13 * 53);
		
		// Check if Piece within board bounds
		if( overallBoardRectangle.contains(tighestPieceRectangle) ) {
			
			// We render the Piece Translucent and check for further board things
			// Backup Graphics Color
			Color oldColor = g.getColor();
			// Save a new one
			g.setColor(new Color(toBeDrawn.getColor().getRed(), 
		                         toBeDrawn.getColor().getGreen(), 
		                         toBeDrawn.getColor().getBlue(), 
		                         200));
			// Draw the PieceSquares
			for( PieceSquare aPieceSquare : toBeDrawn.getPieceSquares() ) {
				int x, y, width, height;
				x = topPointToDraw.x + (aPieceSquare.getCol() * 53);
				y = topPointToDraw.y + (aPieceSquare.getRow() * 53);
				width = 53;
				height = 53;		
				g.fillRect(x, y, width, height);
				// fill our arrray of rectangles for bevel effect
				bevelRects.add(new Rectangle(x, y, width, height));
			}
			PieceHelper.drawBevel(g, toBeDrawn, bevelRects, 200);
						
			// Revert back to the old color
			g.setColor(oldColor);
			// Get the piece snapped to the board
			Piece snappedPieceToBoard = PieceHelper.snapToNearestBoardSquare(this.level, AbstractLevelView.this);
			// Check if the Piece falls within the right bounds and if it collides or not
			if( !this.level.getBoard().doesCollide(snappedPieceToBoard) && 
				!this.level.getBoard().isOutsideBounds(snappedPieceToBoard) ) {
				// Draw the outline on the underlying board
				// Create new Graphics Object
				Graphics2D graphics2d = (Graphics2D)g;
				// Backup old stroke and color
				Stroke oldStroke = graphics2d.getStroke();
				Color prevColor = graphics2d.getColor();
				// Dashed Stroke
				float dash[] = {6.0f};
				BasicStroke dashed = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
				// Decide and set color
				if( this.level.getLevelType().toUpperCase().equals("PUZZLE") && (((PuzzleLevel)this.level).getMovesLeft() <= 0) )
					graphics2d.setColor(Color.RED); // Show red lines that you cannot drop here
				else
					graphics2d.setColor(Color.WHITE); // Otherwise show the normal white Piece outline
				graphics2d.setStroke(dashed);
				// Go over each of the SnappedPiece's PieceSquares now
				for( PieceSquare square : snappedPieceToBoard.getPieceSquares() ) {
					// Draw them out on the board
					Rectangle rect = getBoardPieceRectangle(square.getRow(), square.getCol());
					graphics2d.draw(rect);
				}
				// Reset the color and stroke
				graphics2d.setColor(prevColor);
				graphics2d.setStroke(oldStroke);
			}
			
		} else {
			
			// We render the Piece normally
			// Backup Graphics Color
			Color oldColor = g.getColor();
			// Save a new one; We want this to be translucent as well
			g.setColor(new Color(toBeDrawn.getColor().getRed(), 
		                         toBeDrawn.getColor().getGreen(), 
		                         toBeDrawn.getColor().getBlue(), 
		                         200));
			// Draw the PieceSquares
			for( PieceSquare aPieceSquare : toBeDrawn.getPieceSquares() ) {
				int x, y, width, height;
				x = topPointToDraw.x + (aPieceSquare.getCol() * 53);
				y = topPointToDraw.y + (aPieceSquare.getRow() * 53);
				width = 53;
				height = 53;		
				g.fillRect(x, y, width, height);
				// fill our arrray of rectangles for bevel effect
				bevelRects.add(new Rectangle(x, y, width, height));
			}
			// Revert back to the old color
			g.setColor(oldColor);
			
			PieceHelper.drawBevel(g, toBeDrawn, bevelRects, 200);
			
		}
	}
	
	/**
	 * Draws LevelCompletion Overlay and necessary information about the LevelCompletion Stats (like Stars and Text)
	 * @param g
	 */
	private void drawLevelCompleted(Graphics g) {
		assert( this.level.isLevelCompletedShown() == true ); // The level has to be completed for this level to be called
		// Backup the Old Color and Old Font
		Color oldColor = g.getColor();
		Font oldFont = g.getFont();
		// Draw the overlay
		if( this.level.getLevelCompletionStatus() == AbstractLevel.LEVEL_COMPLETION_FINISHED_LEVEL )
			g.setColor(new Color(50, 131, 50, 210)); // Green Background
		else
			g.setColor(new Color(131, 50, 50, 210)); // Red Background
		
		// Fill the rectangle
		g.fillRect(0, 0,
				   this.myApplication.getWidth(), 
				   this.myApplication.getHeight());
		// Check if we've had one pass on this function or not
		if( !paintLevelCompleted ) {
			// Disable all of them
			for( int idx = 0; idx < 35; idx++ )
				hexominoButton[idx].setVisible(false);
			// Remove all MouseListeners
			for( MouseListener mListener : AbstractLevelView.this.getMouseListeners() )
				AbstractLevelView.this.removeMouseListener(mListener);
			for( MouseMotionListener mMListener : AbstractLevelView.this.getMouseMotionListeners() )
				AbstractLevelView.this.removeMouseMotionListener(mMListener);
			// Finally, repaint and say that we've had one pass over this function
			paintLevelCompleted = true;
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					AbstractLevelView.this.repaint(); // Force a repaint when EDT is done with this pass
				}
			});
			// Add the MouseListener to allow the user to go back to the LevelSelector
			addMouseListener(new ReturnToLevelSelector(AbstractLevelView.this.myApplication, this.level));
			// Exit out of the function. Perform the rest on the next pass
			return;
		}
		// Set text color
		g.setColor(Color.WHITE);
		// Setup the font
		Font infoFont = new Font("LevelCompletionInfoFont", Font.BOLD, 40);
		g.setFont(infoFont);
		// Print the text
		String toPrint = "";
		if( this.level.getLevelCompletionStatus() == AbstractLevel.LEVEL_COMPLETION_FINISHED_LEVEL )
			toPrint = "You finished the level!";
		else if( this.level.getLevelCompletionStatus() == AbstractLevel.LEVEL_COMPLETION_OUT_OF_PIECES )
			toPrint = "You ran out of pieces. Try again!";
		else if( this.level.getLevelCompletionStatus() == AbstractLevel.LEVEL_COMPLETION_OUT_OF_MOVES )
			toPrint = "You ran out of moves. Try again!";
		else if( this.level.getLevelCompletionStatus() == AbstractLevel.LEVEL_COMPLETION_RAN_OUT_OF_TIME )
			toPrint = "Oops, you ran out of time. Try again!";
		else if( this.level.getLevelCompletionStatus() == AbstractLevel.LEVEL_COMPLETION_QUIT_LEVEL )
			toPrint = "Don't give up so early...";

		int startX = (this.myApplication.getWidth() - g.getFontMetrics(infoFont).stringWidth(toPrint)) / 2;
		g.drawString(toPrint, startX, 250);
		// Draw the stars the user has earned
		Rectangle[] starsLocation = new Rectangle[3];
		starsLocation[0] = new Rectangle(((1286 - 480) / 2), 300, 150, 150);
		starsLocation[1] = new Rectangle(((1286 - 480) / 2) + 160, 270, 150, 150);
		starsLocation[2] = new Rectangle(((1286 - 480) / 2) + 320, 300, 150, 150);
		for(int idx = 0; idx < this.level.getStarsAchieved(); idx++)
			g.drawImage(starGoldBig, starsLocation[idx].x, starsLocation[idx].y, starsLocation[idx].width, starsLocation[idx].height, null);
		for(int idx = this.level.getStarsAchieved(); idx < 3; idx++)
			g.drawImage(starShadowBig, starsLocation[idx].x, starsLocation[idx].y, starsLocation[idx].width, starsLocation[idx].height, null);
		// Show the Back Button
		g.drawImage(backButton, 10, 10, null);
		// Reset to the Old Color and Old Font
		g.setColor(oldColor);
		g.setFont(oldFont);
	}

	/**
	 * Overrides the paintComponent method to draw out our new components
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// Check if the Palette Controllers should be shown or not
		fixPaletteControllersVisibility();
		fixHexominoButtonCount();
		// Now proceed to letting super do its job
		super.paintComponent(g); // Let the JPanel do its stuff
		// Render the background image
		g.drawImage(backgroundImage, 0, 0, null);
		// Render BackToMainButton and Level Number
		showBackToMainButton(g);
		showUserStars(g);
		// Set up the game board
		setupGameBoard(g);
		// Draw all the board pieces
		setupBoardPieces(g);
		// Draw a piece in the Workspace
		drawWorkspacePiece(g);
		// A next call to this, will not be an initialization call
		paintInitialized = true;
	}

	/**
	 * Hides/shows Palette Controllers if there exists a Piece in the Player Workspace
	 */
	private void fixPaletteControllersVisibility() {
		if( this.level.getLevelBullpen().getWorkspace().getPiece() != null ) {
			flipV.setVisible(true);
			flipH.setVisible(true);
			rotateCC.setVisible(true);
			rotateCW.setVisible(true);
		} else {
			flipV.setVisible(false);
			flipH.setVisible(false);
			rotateCC.setVisible(false);
			rotateCW.setVisible(false);
		}
	}

	/**
	 * Update the Hexomino Piece Count to buttons where the count may have changed
	 */
	private void fixHexominoButtonCount() {
		// Set the Hexomino Count only if the Count has changed (to prevent excessive Swing repainting)
		for(int i = 0; i < 35; i++)
			if( hexominoButton[i].getHexominoCount() != level.getLevelBullpen().getPalette().getHexomino(i).getCount() )
				hexominoButton[i].setHexominoCount(level.getLevelBullpen().getPalette().getHexomino(i).getCount());
	}

	/**
	 * Paints the BackToLevelSelector Button
	 * @param g
	 */
	private void showBackToMainButton(Graphics g) {
		// Load up the image
		g.drawImage(backButton, 10, 10, null);
		// If this is the first paint:
		if(!paintInitialized)
			// Add it to the HashMap
			clickMap.put(new Rectangle(10, 10, 60, 50), new RequestBackToLevelSelector(AbstractLevelView.this.level, AbstractLevelView.this));
	}
	
	/**
	 * Paints the stars the user has earned
	 * @param g
	 */
	private void showUserStars(Graphics g) {
		int starsAchieved = this.level.getStarsAchieved();
		// Load up the images
		if( starsAchieved == 3 ) {
			g.drawImage(starGold, 1040, 9, null);
			g.drawImage(starGold, 1120, 9, null);
			g.drawImage(starGold, 1200, 9, null);
		} else if( starsAchieved == 2 ) {
			g.drawImage(starGold, 1040, 9, null);
			g.drawImage(starGold, 1120, 9, null);
			g.drawImage(starShadow, 1200, 9, null);
		} else if( starsAchieved == 1 ) {
			g.drawImage(starGold, 1040, 9, null);
			g.drawImage(starShadow, 1120, 9, null);
			g.drawImage(starShadow, 1200, 9, null);
		} else if( starsAchieved == 0 ) {
			g.drawImage(starShadow, 1040, 9, null);
			g.drawImage(starShadow, 1120, 9, null);
			g.drawImage(starShadow, 1200, 9, null);
		}
	}
	
	/**
	 * Sets up the Game Board to reflect inactive and hint BoardSquares
	 * @param g
	 */
	private void setupGameBoard(Graphics g) {
		// Backup old Graphics color
		Color oldColor = g.getColor();
		// Load up the inactive squares and fill them in
		for(int r = 0; r < 12; r++) {
			for(int c = 0; c < 12; c++) {
				if( !this.level.getBoard().isActive(r, c) ) {
					// Put in the inactive color
					g.setColor(new Color(0, 0, 0, 200));
					Rectangle boardRect = getBoardPieceRectangle(r, c);
					g.fillRect(boardRect.x, boardRect.y, boardRect.width, boardRect.height);
				}
				//draw hints
				else if( !(this.level.getBoard().getHint(r, c) == -1)) {
					// Put in the hint color
					Color hintColor = new Color (PieceGenerator.colors[this.level.getBoard().getHint(r, c)].getRed(),
							PieceGenerator.colors[this.level.getBoard().getHint(r, c)].getGreen(),
							PieceGenerator.colors[this.level.getBoard().getHint(r, c)].getBlue(), 180);
					g.setColor(hintColor);
					Rectangle boardRect = getBoardPieceRectangle(r, c);
					g.fillRect(boardRect.x, boardRect.y, boardRect.width, boardRect.height);
				}
			}
		}
		// Revert back to old color
		g.setColor(oldColor);
	}

	/**
	 * Draws the Game Board Pieces on the AbstractLevelView
	 * @param g
	 */
	private void setupBoardPieces(Graphics g) {
		// Go over all the Pieces on the board and draw them out
		for( Iterator<Piece> piecesIter = this.level.getBoard().getPiecesIt(); piecesIter.hasNext();  ) {
			// Grab the next piece from the iterator
			Piece piece = piecesIter.next();
			// Setup Graphics Color
			Color oldColor = g.getColor();
			g.setColor(piece.getColor());
			// Go over all the PieceSquares and fill
			ArrayList<Rectangle> bevelRects = new ArrayList<Rectangle>();
			for( PieceSquare pieceSquare : piece.getPieceSquares() ) {
				// Get the rectangle to paint
				Rectangle pieceRectangle = getBoardPieceRectangle(pieceSquare.getRow(), pieceSquare.getCol());
				// Paint the rectangle
				g.fillRect(pieceRectangle.x, pieceRectangle.y, pieceRectangle.width, pieceRectangle.height);
				// add to our bevelRect
				bevelRects.add(pieceRectangle);
				}
			PieceHelper.drawBevel(g, piece, bevelRects, 255);
			// Set Graphics back to original color
			g.setColor(oldColor);
		}
	}

	/**
	 * Draws a Piece in the Workspace if there is one in the Workspace
	 * @param g
	 */
	private void drawWorkspacePiece(Graphics g) {
		// Check if there is a piece in the workspace
		if( level.getLevelBullpen().getWorkspace().pieceExists() ) {
			// We gotta draw it out
			Piece toBeDrawn = level.getLevelBullpen().getWorkspace().getPiece();
			// Go over all the 6 PieceSquares within
			
			ArrayList <Rectangle> bevelRects = new ArrayList<Rectangle>();
			for( PieceSquare aPieceSquare : toBeDrawn.getPieceSquares() ) {
				// Solve for the Rectangle
				Rectangle rectToDraw = getBullpenWorkspacePieceRectangle(aPieceSquare.getRow(), aPieceSquare.getCol());
				bevelRects.add(rectToDraw);				
				// Save backup Graphics color
				Color oldColor = g.getColor();
				// Set new Color
				g.setColor(toBeDrawn.getColor());
				// Draw it out
				g.fillRect(rectToDraw.x, rectToDraw.y, rectToDraw.width, rectToDraw.height);
				// Reset the color
				g.setColor(oldColor);
				
			}
			PieceHelper.drawBevel(g, toBeDrawn, bevelRects, 255);
		}
	}
	
	/**
	 * Sets up the top-left point of the Board
	 */
	private void setupBoardPiecesTopPoint() {
		boardPiecesTopPoint = new Point(320, 80);
	}
	
	/**
	 * Returns the Rectangle that bounds the BoardSquares in the Player Board
	 * @param row
	 * @param col
	 * @return A rectangle that would paint the corresponding Workspace BoardSquare
	 */
	public Rectangle getBoardPieceRectangle(int row, int col) {
		return new Rectangle(boardPiecesTopPoint.x + (53 * col), 
				             boardPiecesTopPoint.y + (53 * row),
				             53, 53);
	}
	
	/**
	 * Sets up the top-left point of the Player Workspace
	 */
	private void setupBullpenPiecesBoardTopPoint() {
		bullpenPiecesBoardTopPoint = new Point(0, 399);
	}
	
	/**
	 * Returns the Rectangle that bounds the PieceSquares in the Player Workspace
	 * @param row
	 * @param col
	 * @return A rectangle that would paint the corresponding Workspace PieceSquare
	 */
	public Rectangle getBullpenWorkspacePieceRectangle(int row, int col) {
		return new Rectangle(bullpenPiecesBoardTopPoint.x + (53 * col), 
				             bullpenPiecesBoardTopPoint.y + (53 * row), 
				             53, 53);
	}
	
	/**
	 * Returns the Player Bullpen Bounds
	 * @return
	 */
	public Rectangle getBullpenBounds() {
		return new Rectangle(0, 80, 320, 640);
	}
	
	/**
	 * Adds the Piece <code>p</code> to the Workspace
	 * @param p
	 */
	public void setPieceInWorkspace(Piece p) {
		level.getLevelBullpen().getWorkspace().addPiece(p); // Add the piece to the Workspace of the Level Bullpen
		repaint(); // Force a repaint
	}
	
	/**
	 * Returns the top-level application that was used to initialize this class
	 * @return
	 */
	public Application getApplication(){
		return this.myApplication;
	}
	
}