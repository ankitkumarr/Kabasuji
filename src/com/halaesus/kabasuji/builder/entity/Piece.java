package com.halaesus.kabasuji.builder.entity;


import java.util.*;

/**
 * 
 */
public class Piece {

    /**
     * Default constructor
     */
    public Piece() {
    }

    /**
     * 
     */
    int color;

    /**
     * 
     */
    PieceSquare[] squares;

    /**
     * 
     */
    int pivotRow;

    /**
     * 
     */
    int pivotCol;


    /**
     * @param int pivotRow 
     * @param int pivotCol 
     * @param int color 
     * @param PieceSquare squareMap[6]
     */
    public Piece(int pivotRow, int pivotCol, int color, PieceSquare[] squareMap) {
        // TODO implement here
    }

    /**
     * 
     */
    public void rotateCC() {
        // TODO implement here
    }

    /**
     * 
     */
    public void rotateCW() {
        // TODO implement here
    }

    /**
     * 
     */
    public void flipH() {
        // TODO implement here
    }

    /**
     * 
     */
    public void flipV() {
        // TODO implement here
    }

}