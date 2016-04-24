package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;

public class Palette implements Serializable {

	private static final long serialVersionUID = 6907929958555099610L;
	Hexomino[] hexominoes;

    public Palette(Hexomino[] hexs) {
        this.hexominoes = hexs;
    }
    
    public Hexomino getHexomino(int idx) {
    	return hexominoes[idx];
    }

}