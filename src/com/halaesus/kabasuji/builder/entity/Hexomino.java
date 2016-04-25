package com.halaesus.kabasuji.builder.entity;



public class Hexomino {

    int count;
    Piece piece;
    Workspace workspace;

    public Hexomino(int count, Piece piece, Workspace workspace) {
       this.count = count;
       this.piece = new Piece(piece);
       this.workspace = workspace;
    }
    
    public Piece getPiece() {
    	return piece;
    }

}