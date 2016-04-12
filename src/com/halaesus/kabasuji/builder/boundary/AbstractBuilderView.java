package com.halaesus.kabasuji.builder.boundary;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.halaesus.kabasuji.builder.entity.AbstractLevel;
import com.halaesus.kabasuji.player.entity.SplashModel;

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
		// Initialize stuff on the screen
    }

    protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the JPanel do its stuff
		// Render the background image
		try {
			g.drawImage(ImageIO.read(SplashModel.class.getResourceAsStream("/resources/playerBackground.jpg")).getScaledInstance(1280, -1, Image.SCALE_SMOOTH), 0, 0, null);
		} catch (IOException e) {
			// Don't render the background
		}
    }
}
