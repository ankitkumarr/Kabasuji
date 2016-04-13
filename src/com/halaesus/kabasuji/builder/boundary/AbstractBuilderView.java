package com.halaesus.kabasuji.builder.boundary;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.halaesus.kabasuji.builder.entity.SplashModel;

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

	public AbstractBuilderView() {
		// Set GUI Bounds
		setBounds(0, 0, 1280, 720);
		// Set up LayoutManager to null
		setLayout(null);
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
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the JPanel do its stuff
		g.drawImage(Application.instance().getImage("playerBackground.jpg")
				.getScaledInstance(1280, -1, Image.SCALE_SMOOTH), 0, 0, null);
		drawWorkspace(g);

		drawPlayerPalette(g);
		drawBuilderPalette(g);
		drawBoard(g);
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
		// TODO drawWorkspacePiece()
	}

	private void drawPlayerPalette(Graphics g) {
		// TODO drawPlayerPalette()

	}

	private void drawBuilderPalette(Graphics g) {
		// TODO drawBuilderPalette()
	}

	private void drawBoard(Graphics g) {
		g.drawImage(Application.instance().getImage("board.jpg").getScaledInstance(656, 612,
				Image.SCALE_SMOOTH), 309, 80, null);
	}
}
