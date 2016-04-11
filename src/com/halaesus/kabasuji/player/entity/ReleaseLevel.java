package com.halaesus.kabasuji.player.entity;

import java.io.File;

public class ReleaseLevel extends AbstractLevel {
  
    NumberBar numberBar;

    public  ReleaseLevel(File file) {
    	super(file);
    	// Set the game type in here
    	levelType = "Release";
    }

}