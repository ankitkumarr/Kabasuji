package com.halaesus.kabasuji.builder.entity;


import java.io.File;

public class AbstractLevel {

    public AbstractLevel() {
    	bullpen = new Bullpen();
    }

    AbstractBoard board;
    int levelIndex;
    Bullpen bullpen;
    AbstractLevel level;
    Palette playerPalette;
    MoveManager moveManager;

    public AbstractLevel(int levelIndex) {
   this.levelIndex = levelIndex;
    }

    public boolean saveFile(File file) {
   
    	return false; // stub
    }
    
    public Bullpen getLevelBullpen(){
    	return bullpen;
    }

}