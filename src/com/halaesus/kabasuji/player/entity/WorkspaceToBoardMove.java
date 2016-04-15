package com.halaesus.kabasuji.player.entity;

public class WorkspaceToBoardMove {
	
    AbstractBoard board;
    Piece pieceDragged;
    Bullpen bullpen;

    public boolean doMove(AbstractLevel level) {
    	return false;
    }

    public boolean isValid(AbstractLevel level) {
    	return false;
    }

}