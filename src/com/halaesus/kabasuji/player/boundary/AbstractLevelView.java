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
import com.halaesus.kabasuji.utils.JButtonHelper;
import com.halaesus.kabasuji.utils.JLabelHelper;
import com.halaesus.kabasuji.utils.PieceHelper;

@SuppressWarnings("serial")
public abstract class AbstractLevelView extends JPanel {

	// Initialization Variables
	private boolean paintInitialized;
	// View-based (UI and user interaction based) variables
	HashMap<Rectangle, MouseListener> clickMap;
	Point bullpenPiecesBoardTopPoint;
	Point boardPiecesTopPoint;
	Application myApplication;
	AbstractLevel level;
	// Image storage variables
	private Image backgroundImage;
	private Image backButton;
	private Image starShadow;
	private ImageIcon[] hexominoImages;
	private ImageIcon[] hexominoDisabledImages;
	private Image trashCanUnfilled;
	private Image trashCanFilled;
	private Image rotateCCImage;
	private Image rotateCWImage;
	private Image flipVImage;
	private Image flipHImage;
	// View-based (UI Objects) variables
	JLabel levelInfo;
	BufferedImage[] stars;
	HexominoButtonView[] hexominoButton;
	JButton rotateCC;
	JButton rotateCW;
	JButton flipV;
	JButton flipH;
	BufferedImage[] boardPieceSquares;
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
			backgroundImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/gridWithBoard.jpg")).getScaledInstance(1280, -1, Image.SCALE_SMOOTH);
			backButton = ImageIO.read(getClass().getResource("/resources/backButton.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			starShadow = ImageIO.read(getClass().getResource("/resources/starShadow.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
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

	private void setuphexominoCountLabels() {
		// Go on and add the count labels to all of them
		for(int i = 0; i < 35; i++) {
			// Set up the count label
			hexominoButton[i].setHexominoCount(level.getLevelBullpen().getPalette().getHexomino(i).getCount());
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); // Let the super do its stuff
		// Draw a dragging piece
		if( this.level.isDraggingActive() ) {
			drawBullpenTrashCan(g); // Draw the trash can over the Bullpen and then draw the Piece
			drawDraggingPiece(g); // Paint the piece out now
		}
	}
	
	private void drawBullpenTrashCan(Graphics g) {
		assert( this.level.isDraggingActive() == true ); // This function can only be called if there is a piece being dragged
		// Check dragging state; Source needs to be the Board
		if( this.level.getDragSource() == AbstractLevel.DRAG_SOURCE_BOARD ) {
			
			// Backup the old color
			Color oldColor = g.getColor();
			// Draw the Gray Overlay
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
			if( this.level.isPieceOverBullpen() )
				g.drawImage(trashCanFilled, imageRect.x, imageRect.y, imageRect.width, imageRect.height, null);
			else
				g.drawImage(trashCanUnfilled, imageRect.x, imageRect.y, imageRect.width, imageRect.height, null);
			
		}
	}

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
		Rectangle overallBoardRectangle = new Rectangle(boardPiecesTopPoint.x, boardPiecesTopPoint.y, 12 * 53, 12 * 53);
		
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
			PieceHelper.drawBevel(g, toBeDrawn, bevelRects);
						
			// Revert back to the old color
			g.setColor(oldColor);
			// Get the piece snapped to the board
			Piece snappedPieceToBoard = PieceHelper.snapToNearestBoardSquare(this.level, AbstractLevelView.this);
			// Check if the Piece falls within the right bounds and if it collides or not
			if( !this.level.getBoard().doesCollide(snappedPieceToBoard) && 
				!this.level.getBoard().isOutsideBounds(snappedPieceToBoard) ) {
				// Draw the outline on the underlying board
				// Iterate over all the Board Squares to see where to draw
				Point checkPoint = new Point(topPointToDraw.x + 25, topPointToDraw.y + 25);
				boolean exit = false; // To keep track if the loop should exit
				for(int r = 0; r < 12 && !exit; r++) {
					for(int c = 0; c < 12 && !exit; c++) {
						Rectangle boardRectangle = getBoardPieceRectangle(r, c);
						// See if point lies there
						if( boardRectangle.contains(checkPoint) ) {
							// Create new Graphics Object
							Graphics2D graphics2d = (Graphics2D)g;
							// Backup old stroke and color
							Stroke oldStroke = graphics2d.getStroke();
							Color prevColor = graphics2d.getColor();
							// Dashed Stroke
							float dash[] = {6.0f};
							BasicStroke dashed = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
							// Set Color
							graphics2d.setColor(Color.WHITE);
							graphics2d.setStroke(dashed);
							// Go over each PieceSquare now
							for( PieceSquare square : toBeDrawn.getPieceSquares() ) {
								// Draw them out on the board
								Rectangle rect = getBoardPieceRectangle(r + square.getRow(), c + square.getCol());
								graphics2d.draw(rect);
							}
							// Reset the color and stroke
							graphics2d.setColor(prevColor);
							graphics2d.setStroke(oldStroke);
							// Finally, we're done painting, so exit the loop
							exit = true;
						}
					}
				}
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
			
			PieceHelper.drawBevel(g, toBeDrawn, bevelRects);
			
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Check if the Pallette Controllers should be shown or not
		fixPalletteControllersVisibility();
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
		paintInitialized = true; // TODO: Check if this should be here or in the entity
	}

	private void fixPalletteControllersVisibility() {
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

	private void fixHexominoButtonCount() {
		// Set the Hexomino Count only if the Count has changed (to prevent excessive Swing repainting)
		for(int i = 0; i < 35; i++)
			if( hexominoButton[i].getHexominoCount() != level.getLevelBullpen().getPalette().getHexomino(i).getCount() )
				hexominoButton[i].setHexominoCount(level.getLevelBullpen().getPalette().getHexomino(i).getCount());
	}

	private void showBackToMainButton(Graphics g) {
		// Load up the image
		g.drawImage(backButton, 10, 10, null);
		// If this is the first paint:
		if(!paintInitialized)
			// Add it to the HashMap
			clickMap.put(new Rectangle(10, 10, 60, 50), new ReturnToLevelSelector(myApplication));
	}
	
	private void showUserStars(Graphics g) {
		stars = new BufferedImage[3];
		// Load up the images
		g.drawImage(starShadow, 1040, 9, null);
		g.drawImage(starShadow, 1120, 9, null);
		g.drawImage(starShadow, 1200, 9, null);
	}
	
	private void setupGameBoard(Graphics g) {
		// Backup old Graphics color
		Color oldColor = g.getColor();
		// Put in the new color
		g.setColor(new Color(0, 0, 0));
		// Load up the inactive squares and fill them in
		for(int r = 0; r < 12; r++) {
			for(int c = 0; c < 12; c++) {
				if( !this.level.getBoard().isActive(r, c) ) {
					Rectangle boardRect = getBoardPieceRectangle(r, c);
					g.fillRect(boardRect.x, boardRect.y, boardRect.width, boardRect.height);
				}
			}
		}
		// Revert back to old color
		g.setColor(oldColor);
	}

	private void setupBoardPieces(Graphics g) {
		// Go over all the Pieces on the board and draw them out
		for( Iterator<Piece> piecesIter = this.level.getBoard().getPieces(); piecesIter.hasNext();  ) {
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
			PieceHelper.drawBevel(g, piece, bevelRects);
			// Set Graphics back to original color
			g.setColor(oldColor);
		}
	}

	
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
			PieceHelper.drawBevel(g, toBeDrawn, bevelRects);
		}
	}
	
	private void setupBoardPiecesTopPoint() {
		boardPiecesTopPoint = new Point(320, 80);
	}
	
	public Rectangle getBoardPieceRectangle(int row, int col) {
		return new Rectangle(boardPiecesTopPoint.x + (53 * col), 
				             boardPiecesTopPoint.y + (53 * row),
				             53, 53);
	}
	
	private void setupBullpenPiecesBoardTopPoint() {
		bullpenPiecesBoardTopPoint = new Point(0, 399);
	}
	
	public Rectangle getBullpenWorkspacePieceRectangle(int row, int col) {
		return new Rectangle(bullpenPiecesBoardTopPoint.x + (53 * col), 
				             bullpenPiecesBoardTopPoint.y + (53 * row), 
				             53, 53);
	}
	
	public Rectangle getBullpenBounds() {
		return new Rectangle(0, 80, 320, 640);
	}
	
	public void setPieceInWorkspace(Piece p) {
		level.getLevelBullpen().getWorkspace().addPiece(p); // Add the piece to the Workspace of the Level Bullpen
		repaint(); // Force a repaint
	}
	
}