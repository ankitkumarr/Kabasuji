package com.halaesus.kabasuji.builder.entity;



/**
 * 
 */
public class BoardSquare extends AbstractSquare {

    boolean hint;
    boolean filled;

    public BoardSquare(boolean active) {
        super(active);
    }

}