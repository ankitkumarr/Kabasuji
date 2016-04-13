package com.halaesus.kabasuji.builder.boundary;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.halaesus.kabasuji.builder.entity.SplashModel;

@SuppressWarnings("serial")
public class AbstractBuilderView extends JPanel { // you'll note this isn't
													// actually an abstract
													// class
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

	BufferedImage[] workspacePiece;
	JButton[] hexominoImages;
	BufferedImage[] boardSquares;
	BufferedImage[] boardPieceSquares;


	public AbstractBuilderView() {
		// Set GUI Bounds
		setBounds(0, 0, 1280, 720);
		// Set up LayoutManager to null
		setLayout(null);
		// Set up the popup that handles selecting the level type
		try {
			newBtn = new JButton("New");
			newBtn.setFont(new Font("Arial", Font.PLAIN, 14));
			newBtn.setBounds(10, 15, 60, 60);
			this.add(newBtn);
			saveBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/save.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH))));
			saveBtn.setBounds(10 + 60 * 2, 15, 60, 60);
			this.add(saveBtn);
			openBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/open.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH))));
			openBtn.setBounds(10 + 60 * 1, 15, 60, 60);
			this.add(openBtn);
			undoBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/undo.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH))));
			undoBtn.setBounds(10 + 60 * 3, 15, 60, 60);
			this.add(undoBtn);
			redoBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/redo.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH))));
			redoBtn.setBounds(10 + 60 * 4, 15, 60, 60);
			this.add(redoBtn);
			
			flipHBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/flipHorizontal.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH))));
			flipHBtn.setBounds(230, 615, 60, 60);
			this.add(flipHBtn);
			
			flipVBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/flipVertical.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH))));
			flipVBtn.setBounds(10, 395, 60, 60);
			this.add(flipVBtn);
			
			rotateCWBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/rotateCW.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH))));
			rotateCWBtn.setBounds(10, 615, 60, 60);
			this.add(rotateCWBtn);
			
			rotateCCBtn = new JButton((new ImageIcon(ImageIO.read(getClass().getResource("/resources/rotateCC.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH))));
			rotateCCBtn.setBounds(230, 395, 60, 60);	
			this.add(rotateCCBtn);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showDialog(Application app) {
		// options in drop down menu
		String[] choices = { "Open from file", "New Puzzle Level", "New Lightning Level", "New Release Level" };

		// create popup, store response as String
		String action = (String) JOptionPane.showInputDialog(app, "Open or create a new level", // text
																								// within
																								// popup
				"Select an option", // window title
				JOptionPane.QUESTION_MESSAGE, // icon
				null, choices, choices[0]);
		if (action == null) { // no input
			// TODO
			return;
		} else if (action.equals(choices[0])) { // open from file
			// TODO
			app.showPuzzleBuilderView();
			return;
		} else if (action.equals(choices[1])) { // new puzzle
			app.showPuzzleBuilderView();
			return;
		} else if (action.equals(choices[2])) { // new lightning
			app.showLightningBuilderView();
			return;
		} else if (action.equals(choices[3])) { // new release
			app.showReleaseBuilderView();
			return;
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the JPanel do its stuff
		// Render the background image
		try {
			g.drawImage(ImageIO.read(SplashModel.class.getResourceAsStream("/resources/playerBackground.jpg"))
					.getScaledInstance(1280, -1, Image.SCALE_SMOOTH), 0, 0, null);
		} catch (IOException e) {
			// Don't render the background
		}
		drawWorkspace(g);

		drawPlayerPalette(g);
		drawBuilderPalette(g);
		drawBoard(g);
	}
	
	private void drawWorkspace(Graphics g) {
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
		// Load up the board image in the middle of the two panels
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/board.jpg")).getScaledInstance(656, 612,
					Image.SCALE_SMOOTH), 309, 80, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}
}
