package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.halaesus.kabasuji.builder.controller.FlipHInWorkspace;
import com.halaesus.kabasuji.builder.controller.FlipVInWorkspace;
import com.halaesus.kabasuji.builder.controller.RotateCCInWorkspace;
import com.halaesus.kabasuji.builder.controller.RotateCWInWorkspace;
import com.halaesus.kabasuji.builder.entity.PieceSquare;
import com.halaesus.kabasuji.builder.entity.Piece;
import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.controller.ClickPieceInPalette;
import com.halaesus.kabasuji.builder.entity.AbstractLevel;

@SuppressWarnings("serial")
public abstract class AbstractBuilderView extends JPanel { // you'll note this IS actually an abstract class
	JButton newBtn;
	JButton saveBtn;
	JButton openBtn;
	JButton undoBtn;
	JButton redoBtn;
	JButton flipHBtn;
	JButton flipVBtn;
	JButton rotateCCBtn;
	JButton rotateCWBtn;
	
	Point bullpenPiecesBoardTopPoint;
	Point boardPiecesTopPoint;

	JLabel levelInfo;
	JLabel bullpenLevel;
	JLabel[] playerHexsCount;

	JPanel builderPalette;
	JPanel playerPalette;

	BufferedImage[] workspacePiece;
	JButton[] builderPaletteHexBtns;
	JButton[] playerPaletteHexBtns;
	BufferedImage[] boardSquares;
	BufferedImage[] boardPieceSquares;
	
	Application application;
	AbstractLevel level;
	
	private JButton[] hexominoButton;
	private ImageIcon[] hexominoImages;
//	private Image backgroundImage;
//	private Image backButton;
//	private Image rotateCCImage;
//	private Image rotateCWImage;
//	private Image flipHImage;
//	private Image flipVImage;
//	private Image trashCanUnfilled;
//	private Image trashCanFilled;
//	private ImageIcon[] hexominoDisabledImages;

	public AbstractBuilderView(Application application, AbstractLevel aLevel) {
		this.application = application;
		level = aLevel;
		// Set GUI Bounds
		setBounds(0, 0, 1280, 720);
//		setPreferredSize(new Dimension(1280, 720));
		// Set up LayoutManager to null
		setLayout(null);
		
		// handle top bar
		newBtn = new JButton("New");
		newBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		newBtn.setBounds(10, 15, 60, 60);
		this.add(newBtn);
		saveBtn = new JButton(new ImageIcon(Application.instance().getImage("save.png")
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		saveBtn.setBounds(10 + 60 * 2, 15, 60, 60);
		this.add(saveBtn);
		openBtn = new JButton(new ImageIcon(Application.instance().getImage("open.png")
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		openBtn.setBounds(10 + 60 * 1, 15, 60, 60);
		this.add(openBtn);
		undoBtn = new JButton(new ImageIcon(Application.instance().getImage("undo.png")
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		undoBtn.setBounds(10 + 60 * 3, 15, 60, 60);
		this.add(undoBtn);
		redoBtn = new JButton(new ImageIcon(Application.instance().getImage("redo.png")
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		redoBtn.setBounds(10 + 60 * 4, 15, 60, 60);
		this.add(redoBtn);

		// handle workspace buttons
		flipHBtn = new JButton(new ImageIcon(Application.instance().getImage("flipHorizontal.png")
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		flipHBtn.setBounds(230, 615, 60, 60);
		this.add(flipHBtn);

		flipVBtn = new JButton(new ImageIcon(Application.instance().getImage("flipVertical.png")
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		flipVBtn.setBounds(10, 395, 60, 60);
		this.add(flipVBtn);

		rotateCWBtn = new JButton(new ImageIcon(Application.instance().getImage("rotateCW.png")
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		rotateCWBtn.setBounds(10, 615, 60, 60);
		this.add(rotateCWBtn);

		rotateCCBtn = new JButton(new ImageIcon(Application.instance().getImage("rotateCC.png")
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		rotateCCBtn.setBounds(230, 395, 60, 60);
		this.add(rotateCCBtn);

//
//		int count = 1;
//		int btnSize = 53;
//		for (int row = 1; row <=6; row++) {
//			for (int col = 1; col <= 6; col++, count++) {
//				if (count == 36) break;
//				int x = 1 + (53 * col);
//				int y = 81 + (53 * row);
//				builderPaletteHexBtns[count - 1] = new JButton(
//						new ImageIcon(Application.instance().getImage((count) + ".jpg")
//								.getScaledInstance(53, 53, Image.SCALE_SMOOTH))); // 40 x 40 before
//				builderPaletteHexBtns[count - 1].addMouseListener(
//						new ClickPieceInPalette(level.getLevelBullpen().getPalette().getHexomino(count - 1), this));
////				builderPaletteHexBtns[count - 1].setBounds(x, y, btnSize, btnSize);
//				builderPalette.add(builderPaletteHexBtns[count - 1]);
//			}
//		}
		
/**
		for(int i = 0; i < 35; i++) {
			// Set up coordinates
			int width = 53; int height = 53;
			int x = 1 + (53 * paletteColumn);
			int y = 81 + (53 * paletteRow);
			// Add the button
			hexominoButton[i] = new JButton(hexominoImages[i]);
			// Add it to the GUI
			hexominoButton[i].setBounds(x, y, width, height);
			hexominoButton[i].addMouseListener(new ClickPieceInPalette(level.getLevelBullpen().getPalette().getHexomino(i), AbstractBuilderView.this));
			add(hexominoButton[i]);
			
			// Deal with the cycle overs of the rows and columns
			if( (paletteColumn != 0) && (paletteColumn % 5 == 0) ) {
				paletteRow += 1; // We move to the next row
				paletteColumn = 0; // We start from the zeroth column
			} else
				paletteColumn++; // We move to the next column
		}
		*/

		// set up the palette used for building
		builderPalette = new JPanel();
		builderPalette.setBounds(17, 91, 273, 200);
//		builderPalette.setBounds(1, 91, 318, 318);
		builderPalette.setLayout(new GridLayout(5, 7));
//		builderPalette.setLayout(new GridLayout(6, 6));
//		builderPalette.setLayout(null);
		this.add(builderPalette);

		builderPaletteHexBtns = new JButton[35];
		for (int i = 0; i < 35; i++) {
			builderPaletteHexBtns[i] = new JButton(
					new ImageIcon(Application.instance().getImage((i + 1) + ".jpg")
							.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			builderPaletteHexBtns[i].addMouseListener(
					new ClickPieceInPalette(level.getLevelBullpen().getPalette().getHexomino(i), this));
			builderPalette.add(builderPaletteHexBtns[i]);
		}
		
		// set up the other palette
		playerPalette = new JPanel();
		playerPalette.setBounds(977 + 7, 395 + 7, 273, 200);
		playerPalette.setLayout(new GridLayout(5, 7));
		this.add(playerPalette);

		playerPaletteHexBtns = new JButton[35];

		for (int i = 0; i < 35; i++) {
			playerPaletteHexBtns[i] = new JButton(
					new ImageIcon(Application.instance().getImage((i + 1) + ".jpg")
							.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			playerPalette.add(playerPaletteHexBtns[i]);
		}
		
		bullpenPiecesBoardTopPoint = new Point(0, 399);
		//Temporarily initializing here, Bad Design Alert!
		/**
		boardPiecesTopPoint = new Point(320, 80);
		try {
			this.application = application;
			this.level = aLevel;
			newBtn = new JButton("New");
			newBtn.setFont(new Font("Arial", Font.PLAIN, 14));
			newBtn.setBounds(10, 15, 60, 60);
			this.add(newBtn);
			saveBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/save.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
			saveBtn.setBounds(10 + 60 * 2, 15, 60, 60);
			this.add(saveBtn);
			openBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/open.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
			openBtn.setBounds(10 + 60 * 1, 15, 60, 60);
			this.add(openBtn);
			undoBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/undo.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
			undoBtn.setBounds(10 + 60 * 3, 15, 60, 60);
			this.add(undoBtn);
			redoBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/redo.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
			redoBtn.setBounds(10 + 60 * 4, 15, 60, 60);
			this.add(redoBtn);

			/**
			flipHBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/flipHorizontal.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
			flipHBtn.setBounds(230, 615, 60, 60);
			this.add(flipHBtn);

			flipVBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/flipVertical.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
			flipVBtn.setBounds(10, 395, 60, 60);
			this.add(flipVBtn);

			rotateCWBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/rotateCW.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
			rotateCWBtn.setBounds(10, 615, 60, 60);
			this.add(rotateCWBtn);

			rotateCCBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/rotateCC.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
			rotateCCBtn.setBounds(230, 395, 60, 60);
			this.add(rotateCCBtn);
			**/

			//builderPalette = new JPanel();
			//builderPalette.setBounds(17, 91, 273, 200);
			//builderPalette.setLayout(new GridLayout(5, 7));
			//this.add(builderPalette);
			/**

			builderPaletteHexBtns = new JButton[35];

			for (int i = 0; i < 35; i++) {
				builderPaletteHexBtns[i] = new JButton(
						(new ImageIcon(ImageIO.read(getClass().getResource("/resources/" + (i + 1) + ".jpg"))
								.getScaledInstance(40, 40, Image.SCALE_SMOOTH))));
				builderPalette.add(builderPaletteHexBtns[i]);
				builderPaletteHexBtns[i].addMouseListener(new ClickPieceInPalette(level.getLevelBullpen().getPalette().getHexomino(i), AbstractBuilderView.this));
				
			}
			**/
			/**
			playerPalette = new JPanel();
			playerPalette.setBounds(977 + 7, 395 + 7, 273, 200);
			playerPalette.setLayout(new GridLayout(5, 7));
			this.add(playerPalette);

			playerPaletteHexBtns = new JButton[35];

			for (int i = 0; i < 35; i++) {
				playerPaletteHexBtns[i] = new JButton(
						(new ImageIcon(ImageIO.read(getClass().getResource("/resources/" + (i + 1) + ".jpg"))
								.getScaledInstance(40, 40, Image.SCALE_SMOOTH))));
				
				playerPalette.add(playerPaletteHexBtns[i]);
			}
			calculateScaledImages();
			setupHexominoesButtons();*/
			setupPaletteControllers();

//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private void setupPaletteControllers() {
		flipVBtn.addMouseListener(new FlipVInWorkspace(level.getLevelBullpen().getWorkspace(), this));
		flipHBtn.addMouseListener(new FlipHInWorkspace(level.getLevelBullpen().getWorkspace(), this));
		rotateCCBtn.addMouseListener(new RotateCCInWorkspace(level.getLevelBullpen().getWorkspace(), this));
		rotateCWBtn.addMouseListener(new RotateCWInWorkspace(level.getLevelBullpen().getWorkspace(), this));
	}
	
	
	private void setupHexominoesButtons() {
		int paletteRow = 0; // To keep track of positions on the board
		int paletteColumn = 0;
		// Initialize the array
		hexominoButton = new JButton[35];
		// Iterate over all 35 hexominoes and add them to the board
		for(int i = 0; i < 35; i++) {
			// Set up coordinates
			int width = 53; int height = 53;
			int x = 1 + (53 * paletteColumn);
			int y = 81 + (53 * paletteRow);
			// Add the button
			hexominoButton[i] = new JButton(hexominoImages[i]);
			// Add it to the GUI
			hexominoButton[i].setBounds(x, y, width, height);
			hexominoButton[i].addMouseListener(new ClickPieceInPalette(level.getLevelBullpen().getPalette().getHexomino(i), AbstractBuilderView.this));
			add(hexominoButton[i]);
			
			// Deal with the cycle overs of the rows and columns
			if( (paletteColumn != 0) && (paletteColumn % 5 == 0) ) {
				paletteRow += 1; // We move to the next row
				paletteColumn = 0; // We start from the zeroth column
			} else
				paletteColumn++; // We move to the next column
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the JPanel do its stuff
		g.drawImage(Application.instance().getImage("playerBackground.jpg")
				.getScaledInstance(1280, -1, Image.SCALE_SMOOTH), 0, 0, null);
		g.drawImage(Application.instance().getImage("board.jpg").
				getScaledInstance(656, 612, Image.SCALE_SMOOTH), 309, 80, null);
		drawWorkspace(g);
		drawWorkspacePiece(g);

//		drawPlayerPalette(g);
//		drawBuilderPalette(g);
		//drawBoard(g);
	}

	private void drawWorkspace(Graphics g) {
		// left border
		g.drawImage(Application.instance().getImage("bullpenWindow.jpg").getScaledInstance(-1, 612,
				Image.SCALE_SMOOTH), 0, 80, null);
		// right border
		g.drawImage(Application.instance().getImage("bullpenWindow.jpg").getScaledInstance(-1, 612,
				Image.SCALE_SMOOTH), 968, 80, null);
		// builder palette background
		g.drawImage(Application.instance().getImage("paletteWindow.jpg").getScaledInstance(288, -1,
				Image.SCALE_SMOOTH), 9, 85, null);
		// player palette background
		g.drawImage(Application.instance().getImage("paletteWindow.jpg").getScaledInstance(288, -1,
				Image.SCALE_SMOOTH), 977, 395, null);
		drawWorkspacePiece(g);
	}

	private void drawWorkspacePiece(Graphics g) {
		// Check if there is a piece in the workspace
				if( level.getLevelBullpen().getWorkspace().pieceExists() ) {
					// We gotta draw it out
					Piece toBeDrawn = level.getLevelBullpen().getWorkspace().getPiece();
					// Go over all the 6 PieceSquares within
					for( PieceSquare aPieceSquare : toBeDrawn.getPieceSquares() ) {
						// Solve for the Rectangle
						Rectangle rectToDraw = getBullpenWorkspacePieceRectangle(aPieceSquare.getRow(), aPieceSquare.getCol());
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
/**
	private void drawPlayerPalette(Graphics g) {
		// TODO drawPlayerPalette()

	}

	private void drawBuilderPalette(Graphics g) {
		// TODO drawBuilderPalette()
	}
	*/
	public Rectangle getBullpenWorkspacePieceRectangle(int row, int col) {
		return new Rectangle(bullpenPiecesBoardTopPoint.x + (53 * row), 
				             bullpenPiecesBoardTopPoint.y + (53 * col), 
				             53, 53);
	}

//TODO: Not done yet
	public void setPieceInWorkspace(Piece p) {
		level.getLevelBullpen().getWorkspace().addPiece(p); // Add the piece to the Workspace of the Level Bullpen
		repaint(); // Force a repaint
	}
}
