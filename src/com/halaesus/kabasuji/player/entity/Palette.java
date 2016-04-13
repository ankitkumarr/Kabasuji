package com.halaesus.kabasuji.player.entity;

public class Palette {

    Hexomino[] hexominoes;

    public Palette(Hexomino[] hexs) {
        this.hexominoes = hexs;
    }
    
    public Hexomino getHexomino(int idx) {
    	return hexominoes[idx];
    }

}