package com.halaesus.kabasuji.builder.boundary;

import java.awt.Color;
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

import com.halaesus.kabasuji.builder.entity.PieceSquare;
import com.halaesus.kabasuji.builder.entity.Piece;
import com.halaesus.kabasuji.builder.entity.AbstractLevel;
import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.controller.FlipHInWorkspace;
import com.halaesus.kabasuji.builder.controller.FlipVInWorkspace;
import com.halaesus.kabasuji.builder.controller.RotateCCInWorkspace;
import com.halaesus.kabasuji.builder.controller.RotateCWInWorkspace;
import com.halaesus.kabasuji.builder.controller.ClickPieceInPalette;

@SuppressWarnings("serial")
public abstract class AbstractBuilderView extends JPanel {
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

	public AbstractBuilderView(Application application, AbstractLevel aLevel) {
		this.application = application;
		level = aLevel;
		bullpenPiecesBoardTopPoint = new Point(0, 399);
		boardPiecesTopPoint = new Point(320, 80);

		setBounds(0, 0, 1280, 720);
		setLayout(null);
		
		setupTopBar();
		setupWorkspace();
		setupBuilderPalette();
		setupPlayerPalette();
	}
	
	private void setupTopBar() {
		newBtn = new JButton("New");
		newBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		newBtn.setBounds(75 * 0, 0, 75, 75);
		this.add(newBtn);
		openBtn = new JButton(new ImageIcon(Application.instance().getImage("open.png")
				.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
		openBtn.setBounds(75 * 1, 0, 75, 75);
		this.add(openBtn);
		saveBtn = new JButton(new ImageIcon(Application.instance().getImage("save.png")
				.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
		saveBtn.setBounds(75 * 2, 0, 75, 75);
		this.add(saveBtn);
		undoBtn = new JButton(new ImageIcon(Application.instance().getImage("undo.png")
				.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
		undoBtn.setBounds(75 * 3, 0, 75, 75);
		this.add(undoBtn);
		redoBtn = new JButton(new ImageIcon(Application.instance().getImage("redo.png")
				.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
		redoBtn.setBounds(75 * 4, 0, 75, 75);
		this.add(redoBtn);
	}

	private void setupWorkspace() {
		flipHBtn = new JButton(new ImageIcon(Application.instance().getImage("flipHorizontal.png")
				.getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
		flipHBtn.setBounds(230, 630, 90, 90);
		flipHBtn.addMouseListener(new FlipHInWorkspace(level.getLevelBullpen().getWorkspace(), this));
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
		rotateCWBtn.addMouseListener(new RotateCWInWorkspace(level.getLevelBullpen().getWorkspace(), this));
		rotateCWBtn.setOpaque(false);
		rotateCWBtn.setContentAreaFilled(false);
		this.add(rotateCWBtn);

		rotateCCBtn = new JButton(new ImageIcon(Application.instance().getImage("rotateCC.png")
				.getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
		rotateCCBtn.setBounds(230, 400, 90, 90);
		rotateCCBtn.addMouseListener(new RotateCCInWorkspace(level.getLevelBullpen().getWorkspace(), this));
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
	
	private void setupPlayerPalette() {
		playerPalette = new JPanel();
		playerPalette.setBounds(968, 408, 304, 304);
		playerPalette.setLayout(new GridLayout(6, 6));
		playerPalette.setBackground(new Color(56, 83, 90));
		this.add(playerPalette);

		playerPaletteHexBtns = new JButton[35];

		for (int i = 0; i < 35; i++) {
			playerPaletteHexBtns[i] = new JButton(
					new ImageIcon(Application.instance().getImage((i + 1) + ".jpg")
							.getScaledInstance(51, 51, Image.SCALE_SMOOTH)));
			playerPalette.add(playerPaletteHexBtns[i]);
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Application.instance().getImage("gridWithBoard.jpg")
				.getScaledInstance(1280, -1, Image.SCALE_SMOOTH), 0, 0, null);
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
						Rectangle rectToDraw = getBullpenWorkspacePieceRectangle(aPieceSquare.getCol(), aPieceSquare.getRow());
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
