package com.halaesus.kabasuji.builder.boundary;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.controller.FlipHInWorkspace;
import com.halaesus.kabasuji.builder.controller.FlipVInWorkspace;
import com.halaesus.kabasuji.builder.controller.LaunchLevelManager;
import com.halaesus.kabasuji.builder.controller.RedoMove;
import com.halaesus.kabasuji.builder.controller.RotateCCInWorkspace;
import com.halaesus.kabasuji.builder.controller.RotateCWInWorkspace;
import com.halaesus.kabasuji.builder.controller.SaveToFile;
import com.halaesus.kabasuji.builder.controller.ToggleHint;
import com.halaesus.kabasuji.builder.controller.UndoMove;
import com.halaesus.kabasuji.builder.boundary.HexominoButtonView;
import com.halaesus.kabasuji.builder.controller.DragPieceFromBoard;
import com.halaesus.kabasuji.builder.controller.DragPieceFromWorkspace;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.SplashModel;
import com.halaesus.kabasuji.utils.BuilderPieceHelper;
import com.halaesus.kabasuji.utils.JLabelHelper;
import com.halaesus.kabasuji.utils.PieceGenerator;
import com.halaesus.kabasuji.utils.PieceHelper;
import com.halaesus.kabasuji.builder.controller.ClickPieceInPalette;
import com.halaesus.kabasuji.builder.controller.DecrementPlayerPalette;

@SuppressWarnings("serial")
public abstract class AbstractBuilderView extends JPanel {
	Application application;
	AbstractLevel level;
	
	// top bar buttons
	JButton levelManagerBtn;
	JButton saveBtn;
	JButton openBtn;
	JButton undoBtn;
	JButton redoBtn;
	
	// workspace buttons
	JButton flipHBtn;
	JButton flipVBtn;
	JButton rotateCCBtn;
	JButton rotateCWBtn;

	// palette buttons
	JButton[] builderPaletteHexBtns;
	HexominoButtonView[] playerPaletteHexBtns;
	
	Point bullpenPiecesBoardTopPoint;
	Point boardPiecesTopPoint;

	JLabel levelInfo;
	JLabel bullpenLevel;
	JLabel[] playerHexsCount;

	JPanel builderPalette;
	JPanel playerPalette;
	
	protected Image backgroundImage;

	HashMap<Rectangle, MouseListener> clickMap;
	HexominoButtonView[] hexominoButton;
	
	public AbstractBuilderView(Application application, AbstractLevel aLevel, int levelIndex) {
		this.application = application;
		level = aLevel;
		// hacky fix to support move up and down in level manager
		level.setLevelIndex(levelIndex);
		bullpenPiecesBoardTopPoint = new Point(0, 399);
		boardPiecesTopPoint = new Point(320, 80);

		setBounds(0, 0, 1280, 720);
		setLayout(null);
		
		scalebackground();
		setupTopBar();
		setupWorkspace();
		setupBuilderPalette();
		setupPlayerPalette();
		// Initialize HashMap
		clickMap = new HashMap<Rectangle, MouseListener>();
		// Implement MouseListener
		implementMouseListeners();// Add Listener for DragPieceFromBoard
		// Show Level Info
		showLevelInfo();
		DragPieceFromBoard dragFromBoard = new DragPieceFromBoard(this.level, this);
		addMouseListener(dragFromBoard);
		addMouseMotionListener(dragFromBoard);
		
		ToggleHint toggleHint = new ToggleHint(this.level, this);
		addMouseListener(toggleHint);
		addMouseMotionListener(toggleHint);
	}
	
	private void setupTopBar() {
		levelManagerBtn = new JButton("Level Manager");
		levelManagerBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		levelManagerBtn.setBounds(75 * 0, 0, 200, 75);
		levelManagerBtn.addActionListener(new LaunchLevelManager());
		this.add(levelManagerBtn);
		
		saveBtn = new JButton(new ImageIcon(Application.instance().getImage("save.png")
				.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
		saveBtn.setToolTipText("Save");
		saveBtn.setBounds(75 * 3, 0, 75, 75);
		saveBtn.addActionListener(new SaveToFile(this));
		this.add(saveBtn);
		
		undoBtn = new JButton(new ImageIcon(Application.instance().getImage("undo.png")
				.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
		undoBtn.setBounds(75 * 5, 0, 75, 75);
		undoBtn.setToolTipText("Undo");
		undoBtn.addActionListener(new UndoMove(level, this));
		this.add(undoBtn);
		
		redoBtn = new JButton(new ImageIcon(Application.instance().getImage("redo.png")
				.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
		redoBtn.setBounds(75 * 6 + 38, 0, 75, 75);
		redoBtn.setToolTipText("Redo");
		redoBtn.addActionListener(new RedoMove(level, this));
		this.add(redoBtn);
	}

	private void setupWorkspace() {
		flipHBtn = new JButton(new ImageIcon(Application.instance().getImage("flipHorizontal.png")
				.getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
		flipHBtn.setBounds(230, 630, 90, 90);
		flipHBtn.addActionListener(new FlipHInWorkspace(level.getLevelBullpen().getWorkspace(), this));
		flipHBtn.setOpaque(false);
		flipHBtn.setContentAreaFilled(false);
		this.add(flipHBtn);

		flipVBtn = new JButton(new ImageIcon(Application.instance().getImage("flipVertical.png")
				.getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
		flipVBtn.setBounds(1, 400, 90, 90);
		flipVBtn.addActionListener(new FlipVInWorkspace(level.getLevelBullpen().getWorkspace(), this));
		flipVBtn.setOpaque(false);
		flipVBtn.setContentAreaFilled(false);
		this.add(flipVBtn);

		rotateCWBtn = new JButton(new ImageIcon(Application.instance().getImage("rotateCW.png")
				.getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
		rotateCWBtn.setBounds(1, 630, 90, 90);
		rotateCWBtn.addActionListener(new RotateCWInWorkspace(level.getLevelBullpen().getWorkspace(), this));
		rotateCWBtn.setOpaque(false);
		rotateCWBtn.setContentAreaFilled(false);
		this.add(rotateCWBtn);

		rotateCCBtn = new JButton(new ImageIcon(Application.instance().getImage("rotateCC.png")
				.getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
		rotateCCBtn.setBounds(230, 400, 90, 90);
		rotateCCBtn.addActionListener(new RotateCCInWorkspace(level.getLevelBullpen().getWorkspace(), this));
		rotateCCBtn.setOpaque(false);
		rotateCCBtn.setContentAreaFilled(false);
		this.add(rotateCCBtn);
	}
	
	private void setupBuilderPalette() {
		builderPalette = new JPanel();
		builderPalette.setBounds(8, 88, 304, 304);
		builderPalette.setLayout(new GridLayout(6, 6));
		builderPalette.setBackground(new Color(56, 83, 90));
		this.add(builderPalette);

		builderPaletteHexBtns = new JButton[35];
		for (int i = 0; i < 35; i++) {
			builderPaletteHexBtns[i] = new JButton(
					new ImageIcon(Application.instance().getImage((i + 1) + ".jpg")
							.getScaledInstance(51, 51, Image.SCALE_SMOOTH)));
			builderPaletteHexBtns[i].addMouseListener(
					new ClickPieceInPalette(level.getLevelBullpen().getPalette().getHexomino(i), this));
			builderPalette.add(builderPaletteHexBtns[i]);
		}
	}
	
	private void showLevelInfo() {
		// Create the label
		levelInfo = new JLabel(level.getLevelName());
		
		levelInfo.setBounds(750, 10, 200, 60);
		levelInfo.setForeground(Color.ORANGE);
		levelInfo.setFont(new Font(levelInfo.getFont().getName(), Font.BOLD, levelInfo.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(levelInfo);
		// Add it to the GUI
		add(levelInfo);
	}
	
	private void setupPlayerPalette() {
		playerPalette = new JPanel();
		playerPalette.setBounds(968, 408, 304, 304);
		playerPalette.setLayout(new GridLayout(6, 6));
		playerPalette.setBackground(new Color(56, 83, 90));
		this.add(playerPalette);

		playerPaletteHexBtns = new HexominoButtonView[35];

		for (int i = 0; i < 35; i++) {
			playerPaletteHexBtns[i] = new HexominoButtonView(
					new ImageIcon(Application.instance().getImage((i + 1) + ".jpg")
							.getScaledInstance(51, 51, Image.SCALE_SMOOTH)),
					new ImageIcon(Application.instance().getImage((i + 1) + "_disabled.jpg")
							.getScaledInstance(51, 51, Image.SCALE_SMOOTH)));
			playerPaletteHexBtns[i].setHexominoCount(level.getLevelBullpen().getPalette().getHexomino(i).getCount());
			playerPaletteHexBtns[i].addActionListener(
					new DecrementPlayerPalette(level.getLevelBullpen().getPalette().getHexomino(i), this));
			playerPalette.add(playerPaletteHexBtns[i]);
		}
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
		DragPieceFromWorkspace dragWorkspaceToBoard = new DragPieceFromWorkspace(this.level, this);
		addMouseListener(dragWorkspaceToBoard);
		addMouseMotionListener(dragWorkspaceToBoard);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g); // Let the super do its stuff
		// Draw a dragging piece

		if( this.level.isDraggingActive() && this.level.getDragSource()!=ReleaseLevel.DRAG_SOURCE_NUMBERBAR) {
			if (this.level.getLevelType() == "Release") {
				if (((ReleaseLevel)this.level).isNumberDraggingActive()!=true) {
					drawBullpenTrashCan(g); // Draw the trash can over the Bullpen and then draw the Piece
					drawDraggingPiece(g); // Paint the piece out now
				}
			}
			else {
				
			drawBullpenTrashCan(g); // Draw the trash can over the Bullpen and then draw the Piece
			drawDraggingPiece(g); // Paint the piece out now
			}
		}
	}
	
	

	protected void drawBullpenTrashCan(Graphics g) {
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
			Image trashCanFilled = Application.instance().getImage("trashcan_full.png");
			Image trashCanUnfilled = Application.instance().getImage("trashcan_empty.png");
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
	
	

	protected void drawDraggingPiece(Graphics g) {
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
		                         90));
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
			Piece snappedPieceToBoard = BuilderPieceHelper.snapToNearestBoardSquare(this.level, this);
			// Check if the Piece falls within the right bounds and if it collides or not
			if( !this.level.getBoard().doesCollide(snappedPieceToBoard) ) {
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
			// Save a new one; We want this to be transparent as well
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

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(Application.instance().getImage("gridWithBoard.jpg")         ///Slows down the application
				//.getScaledInstance(1280, -1, Image.SCALE_SMOOTH), 0, 0, null);
		
		g.drawImage(backgroundImage, 0, 0, null);

		// Set up the game board
		setupGameBoard(g);
		// Draw all the board pieces
		setupBoardPieces(g);
		drawWorkspacePiece(g);
	}
	
	public void updatePlayerPaletteView() {
		for (int i = 0; i < 35; i++) {
			playerPaletteHexBtns[i].setHexominoCount(level.getLevelBullpen().getPalette().getHexomino(i).getCount());
		}
		repaint();
	}

	protected void setupBoardPieces(Graphics g) {
		// Go over all the Pieces on the board and draw them out
		for( Iterator<Piece> piecesIter = this.level.getBoard().getPiecesIt(); piecesIter.hasNext();  ) {
			// Grab the next piece from the iterator
			Piece piece = piecesIter.next();
			// Setup Graphics Color
			Color oldColor = g.getColor();
			g.setColor(new Color(piece.getColor().getRed(), 
					piece.getColor().getGreen(), 
					piece.getColor().getBlue(), 70));
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
			PieceHelper.drawBevel(g, piece, bevelRects,150);
			// Set Graphics back to original color
			g.setColor(oldColor);
		}
	}

	protected void setupGameBoard(Graphics g) {
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
					g.setColor(PieceGenerator.colors[this.level.getBoard().getHint(r, c)]);
					Rectangle boardRect = getBoardPieceRectangle(r, c);
					g.fillRect(boardRect.x, boardRect.y, boardRect.width, boardRect.height);
				}
			}
		}
		// Revert back to old color
		g.setColor(oldColor);
	}

	protected void drawWorkspacePiece(Graphics g) {
		// Check if there is a piece in the workspace
				if( level.getLevelBullpen().getWorkspace().pieceExists() ) {
					// We gotta draw it out
					Piece toBeDrawn = level.getLevelBullpen().getWorkspace().getPiece();
					
					// rectangles needed to draw bevel effect
					ArrayList <Rectangle> bevelRects = new ArrayList<Rectangle>();

					// Go over all the 6 PieceSquares within
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
	
	public Rectangle getBullpenWorkspacePieceRectangle(int row, int col) {
		return new Rectangle(bullpenPiecesBoardTopPoint.x + (53 * col), 
				             bullpenPiecesBoardTopPoint.y + (53 * row), 
				             53, 53);
	}
	
	public Rectangle getBoardPieceRectangle(int row, int col) {
		return new Rectangle(boardPiecesTopPoint.x + (53 * col), 
				             boardPiecesTopPoint.y + (53 * row),
				             53, 53);
	}
	
	public Rectangle getBullpenBounds() {
		return new Rectangle(0, 80, 320, 640);
	}
	
	public AbstractLevel getLevel(){
		return this.level;
	}
	
	public JPanel getPlayerPaletteFrame() {
		return playerPalette;
	}

//TODO: Not done yet
	public void setPieceInWorkspace(Piece p) {
		level.getLevelBullpen().getWorkspace().addPiece(p); // Add the piece to the Workspace of the Level Bullpen
		repaint(); // Force a repaint
	}
	
	public void scalebackground() {
		try {
			backgroundImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/gridWithBoard.jpg")).getScaledInstance(1280, -1, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			
			//Oh well!
		}
	}
}
