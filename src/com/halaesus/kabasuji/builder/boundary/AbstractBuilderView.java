package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.halaesus.kabasuji.builder.entity.SplashModel;
import com.halaesus.kabasuji.builder.controller.FlipHInWorkspace;
import com.halaesus.kabasuji.builder.controller.FlipVInWorkspace;
import com.halaesus.kabasuji.builder.controller.RotateCCInWorkspace;
import com.halaesus.kabasuji.builder.controller.RotateCWInWorkspace;
import com.halaesus.kabasuji.utils.JButtonHelper;
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
	private Image backgroundImage;
	private Image backButton;
	private Image rotateCCImage;
	private Image rotateCWImage;
	private Image flipHImage;
	private Image flipVImage;
	private Image trashCanUnfilled;
	private Image trashCanFilled;
	private ImageIcon[] hexominoDisabledImages;

	public AbstractBuilderView(Application application, AbstractLevel aLevel) {
		// Set GUI Bounds
		setBounds(0, 0, 1280, 720);
		// Set up LayoutManager to null
		setLayout(null);
<<<<<<< HEAD
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

		builderPalette = new JPanel();
		builderPalette.setBounds(17, 91, 273, 200);
		builderPalette.setLayout(new GridLayout(5, 7));
		this.add(builderPalette);

		builderPaletteHexBtns = new JButton[35];

		for (int i = 0; i < 35; i++) {
			builderPaletteHexBtns[i] = new JButton(
					new ImageIcon(Application.instance().getImage((i + 1) + ".jpg")
							.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			builderPalette.add(builderPaletteHexBtns[i]);
		}
		
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
=======
		//Temporarily initializing here, Bad Design Alert!
		boardPiecesTopPoint = new Point(320, 80);
		bullpenPiecesBoardTopPoint = new Point(0, 399);
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
			setupHexominoesButtons();
			setupPaletteControllers();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
>>>>>>> 721f3dd7a0edc0e2de4ef4ad8ea7e82b414c6d0a
		}
	}
	

	private void calculateScaledImages() {
		try {
			backgroundImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/gridWithBoard.jpg")).getScaledInstance(1280, -1, Image.SCALE_SMOOTH);
			backButton = ImageIO.read(getClass().getResource("/resources/backButton.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			//starGold = ImageIO.read(getClass().getResource("/resources/starGold.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			//starShadow = ImageIO.read(getClass().getResource("/resources/starShadow.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
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
	
	
	private void setupPaletteControllers() {
		// Create the FlipV Button
		flipVBtn = new JButton(new ImageIcon(flipVImage));
		flipVBtn.setBounds(1, 400, 90, 90);
		flipVBtn.addMouseListener(new FlipVInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractBuilderView.this));
		JButtonHelper.makeBackgroundTransparent(flipVBtn);
		add(flipVBtn);
		// Create the FlipH Button
		flipHBtn = new JButton(new ImageIcon(flipHImage));
		flipHBtn.setBounds(230, 630, 90, 90);
		flipHBtn.addMouseListener(new FlipHInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractBuilderView.this));
		JButtonHelper.makeBackgroundTransparent(flipHBtn);
		add(flipHBtn);
		// Create the RotateCC Button
		rotateCCBtn = new JButton(new ImageIcon(rotateCCImage));
		rotateCCBtn.setBounds(230, 400, 90, 90);
		rotateCCBtn.addMouseListener(new RotateCCInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractBuilderView.this));
		JButtonHelper.makeBackgroundTransparent(rotateCCBtn);
		add(rotateCCBtn);
		// Create the RotateCW Button
		rotateCWBtn = new JButton(new ImageIcon(rotateCWImage));
		rotateCWBtn.setBounds(1, 630, 90, 90);
		rotateCWBtn.addMouseListener(new RotateCWInWorkspace(this.level.getLevelBullpen().getWorkspace(), AbstractBuilderView.this));
		JButtonHelper.makeBackgroundTransparent(rotateCWBtn);
		add(rotateCWBtn);
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
<<<<<<< HEAD
		g.drawImage(Application.instance().getImage("playerBackground.jpg")
				.getScaledInstance(1280, -1, Image.SCALE_SMOOTH), 0, 0, null);
		drawWorkspace(g);
=======
		// Render the background image
		try {
			g.drawImage(ImageIO.read(SplashModel.class.getResourceAsStream("/resources/playerBackground.jpg"))
					.getScaledInstance(1280, -1, Image.SCALE_SMOOTH), 0, 0, null);
		} catch (IOException e) {
			// Don't render the background
		}

		g.drawImage(backgroundImage, 0, 0, null);
		drawWorkspacePiece(g);
>>>>>>> 721f3dd7a0edc0e2de4ef4ad8ea7e82b414c6d0a

		drawPlayerPalette(g);
		drawBuilderPalette(g);
		//drawBoard(g);
	}

	private void drawWorkspace(Graphics g) {
<<<<<<< HEAD
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
=======
		// Load up the left panel image
		try {
			// left border
			g.drawImage(ImageIO.read(getClass().getResource("/resources/bullpenWindow.jpg")).getScaledInstance(-1, 612,
					Image.SCALE_SMOOTH), 0, 80, null);
			// right border
			g.drawImage(ImageIO.read(getClass().getResource("/resources/bullpenWindow.jpg")).getScaledInstance(-1, 612,
					Image.SCALE_SMOOTH), 968, 80, null);
			// builder palette background
			g.drawImage(ImageIO.read(getClass().getResource("/resources/paletteWindow.jpg")).getScaledInstance(288, -1,
					Image.SCALE_SMOOTH), 9, 85, null);
			// player palette background
			g.drawImage(ImageIO.read(getClass().getResource("/resources/paletteWindow.jpg")).getScaledInstance(288, -1,
					Image.SCALE_SMOOTH), 977, 395, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
		
>>>>>>> 721f3dd7a0edc0e2de4ef4ad8ea7e82b414c6d0a
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

	private void drawPlayerPalette(Graphics g) {
		// TODO drawPlayerPalette()

	}

	private void drawBuilderPalette(Graphics g) {
		// TODO drawBuilderPalette()
	}
	
	public Rectangle getBullpenWorkspacePieceRectangle(int row, int col) {
		return new Rectangle(bullpenPiecesBoardTopPoint.x + (53 * row), 
				             bullpenPiecesBoardTopPoint.y + (53 * col), 
				             53, 53);
	}

	private void drawBoard(Graphics g) {
		g.drawImage(Application.instance().getImage("board.jpg").getScaledInstance(656, 612,
				Image.SCALE_SMOOTH), 309, 80, null);
	}
	
//TODO: Not done yet
	public void setPieceInWorkspace(Piece p) {
		level.getLevelBullpen().getWorkspace().addPiece(p); // Add the piece to the Workspace of the Level Bullpen
		repaint(); // Force a repaint
	}
}
