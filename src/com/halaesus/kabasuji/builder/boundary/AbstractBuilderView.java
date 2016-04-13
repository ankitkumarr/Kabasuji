package com.halaesus.kabasuji.builder.boundary;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.halaesus.kabasuji.builder.entity.SplashModel;

@SuppressWarnings("serial")
public class AbstractBuilderView extends JPanel { // you'll note this isn't actually an abstract class
	
    JButton saveBtn;
    JButton openBtn;
    JButton undoBtn;
    JButton redoBtn;
    JButton flipH;
    JButton flipV;
    JButton rotateCC;
    JButton rotateCW;
    JButton newBtn;

    JLabel levelInfo;
    JLabel bullpenLevel;
    JLabel[] playerHexsCount;

    BufferedImage[] workspacePiece;
    BufferedImage[] bullpenPaletteHexs;
    BufferedImage[] playerPaletteHexs;
    BufferedImage[] boardSquares;
    BufferedImage[] boardPieceSquares;
    
    public AbstractBuilderView() {
		// Set GUI Bounds
		setBounds(0, 0, 1280, 720);
		// Set up LayoutManager to null
		setLayout(null);
		// Set up the popup that handles selecting the level type
	}


    public void showDialog(Application app) {
    	// options in drop down menu
		String[] choices = {"Open from file", "New Puzzle Level",
						"New Lightning Level", "New Release Level"};
		
		// create popup, store response as String
		String action = (String) JOptionPane.showInputDialog(app,
				"Open or create a new level", // text within popup
				"Select an option", // window title
				JOptionPane.QUESTION_MESSAGE, // icon
				null,
				choices,
				choices[0]);
		if (action == null){ // no input
			// TODO
			return;
		}
		else if (action.equals(choices[0])){ // open from file
			// TODO
			app.showPuzzleBuilderView();
			return;
		}
		else if (action.equals(choices[1])){ // new puzzle
			app.showPuzzleBuilderView();
			return;
		}
		else if (action.equals(choices[2])){ // new lightning
			app.showLightningBuilderView();
			return;
		}
		else if (action.equals(choices[3])){ // new release
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

		drawNewButton(g);
		drawOpenButton(g);
		drawSaveButton(g);
		drawUndoButton(g);
		drawRedoButton(g);
		drawWorkspace(g);

		drawPlayerPalette(g);
		drawBuilderPalette(g);
		drawBoard(g);
	}

	private void drawNewButton(Graphics g) {
		// Load up the image
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/backButton.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH), 10, 15, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}

	private void drawOpenButton(Graphics g) {
		// Load up the image
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/backButton.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH), 10 + 60, 15, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}

	private void drawSaveButton(Graphics g) {
		// Load up the image
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/backButton.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH), 10 + 60 * 2, 15, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}

	private void drawUndoButton(Graphics g) {
		// Load up the image
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/backButton.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH), 10 + 60 * 3, 15, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}

	private void drawRedoButton(Graphics g) {
		// Load up the image
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/backButton.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH), 10 + 60 * 4, 15, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
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
			// workspace background
			g.drawImage(ImageIO.read(getClass().getResource("/resources/paletteWindow.jpg")).getScaledInstance(288, -1,
					Image.SCALE_SMOOTH), 9, 394, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
		drawWorkspacePiece(g);
		drawRotateCWButton(g);
		drawRotateCCButton(g);
		drawFlipHButton(g);
		drawFlipVButton(g);
	}

	private void drawWorkspacePiece(Graphics g) {
		// TODO drawWorkspacePiece()
	}

	private void drawRotateCWButton(Graphics g) {
		// Load up the image
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/rotateCW.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH), 10, 615, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}

	private void drawRotateCCButton(Graphics g) {
		// Load up the image
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/resources/rotateCC.png")).getScaledInstance(60, 60,
					Image.SCALE_SMOOTH), 230, 395, null);
		} catch (IOException ex) {
			return; // Cannot do anything. We tried :(
		}
	}

	private void drawFlipHButton(Graphics g) {
		// TODO drawFlipHButton()
	}

	private void drawFlipVButton(Graphics g) {
		// TODO drawFlipVButton()
	}

	private void drawPlayerPalette(Graphics g) {
		// TODO drawPlayerPalette()
	}

	private void drawBuilderPalette(Graphics g) {

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
