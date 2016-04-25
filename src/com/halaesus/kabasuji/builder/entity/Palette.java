package com.halaesus.kabasuji.builder.entity;



public class Palette {

    Hexomino[] hexominos;
 
    public Palette(Hexomino[] hex) {
        this.hexominos = hex;
    }
    
    public Hexomino getHexomino(int i){
    	return hexominos[i];
    }

}