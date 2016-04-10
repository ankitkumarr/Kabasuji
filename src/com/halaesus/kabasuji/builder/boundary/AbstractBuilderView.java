package com.halaesus.kabasuji.builder.boundary;

import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */
public class AbstractBuilderView extends JPanel {

    /**
     * Default constructor
     */
    public AbstractBuilderView() {
    }

    /**
     * 
     */
    JButton saveBtn;

    /**
     * 
     */
    JButton openBtn;

    /**
     * 
     */
    JButton undoBtn;

    /**
     * 
     */
    JButton redoBtn;

    /**
     * 
     */
    JButton flipH;

    /**
     * 
     */
    JButton flipV;

    /**
     * 
     */
    JButton rotateCC;

    /**
     * 
     */
    JButton rotateCW;

    /**
     * 
     */
    JButton newBtn;

    /**
     * 
     */
    JLabel levelInfo;

    /**
     * 
     */
    JLabel bullpenLevel;

    /**
     * 
     */
    BufferedImage[] workspacePiece;

    /**
     * 
     */
    BufferedImage[] bullpenPaletteHexs;

    /**
     * 
     */
    BufferedImage[] playerPaletteHexs;

    /**
     * 
     */
    JLabel[] playerHexsCount;

    /**
     * 
     */
    BufferedImage[] boardSquares;

    /**
     * 
     */
    BufferedImage[] boardPieceSquares;

}