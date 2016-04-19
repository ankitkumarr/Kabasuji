package com.halaesus.kabasuji.player.entity;

public class ReleaseNumber {
    boolean visible;
    int color; // 1 = red ,2 = green ,3 = yellow
    int col;
    int row;
    int value;
    
    public ReleaseNumber(int value, int color, int col, int row){
    	this.value = value;
    	this.color = color;
    	this.col = col;
    	this.row = row;  	
    }
    
    public int getValue(){
    	return this.value;
    }
    public int getColor(){
    	return this.color;
    }
    public int getCol(){
    	return this.col;
    }
    public int getRow(){
    	return this.row;
    }
}