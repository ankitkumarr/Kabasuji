package com.halaesus.kabasuji.shared;

import java.io.Serializable;

/**
 * 
 * @author Corey Dixon
 *
 */
public class Palette implements Serializable{

	private static final long serialVersionUID = 6907929958555099610L;
	Hexomino[] hexominoes;

    public Palette(Hexomino[] hexs) {
        this.hexominoes = hexs;
    }
    
    public Hexomino getHexomino(int idx) {
    	return hexominoes[idx];
    }

}