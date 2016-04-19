package com.halaesus.kabasuji.player.entity;

import java.io.File;

public class ReleaseLevel extends AbstractLevel {
  
    NumberBar numberBar;
    //ReleaseBoard releaseBoard;
    public  ReleaseLevel(File file) {
    	super(file);
    	
    	// TODO construct releaseBoard from file data
    	board = new ReleaseBoard(null, null); 
    	
    	// Set the game type in here
    	levelType = "Release";
    	
    	numberBar = new NumberBar();
    }
    
        public NumberBar getNumberBar(){
    	return this.numberBar;
    }
    
    

}