package com.halaesus.kabasuji.builder.entity;



/**
 * 
 */
public class Palette {

    /**
     * Default constructor
     */
    

    /**
     * 
     */
    Hexomino[] hexominos;


    /**
     * @param Hexominos[35] hex
     */
    public Palette(Hexomino[] hex) {
        this.hexominos = hex;
    }
    
    public Hexomino getHexomino(int i){
    	return hexominos[i];
    }

}