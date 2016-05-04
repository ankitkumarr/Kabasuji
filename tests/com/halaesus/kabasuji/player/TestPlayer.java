package com.halaesus.kabasuji.player;

import com.halaesus.kabasuji.player.tests.PlayerTestCase;

import java.io.File;
import java.util.ArrayList;

import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.player.entity.LevelList;
import com.halaesus.kabasuji.player.entity.PlayerProgress;
import com.halaesus.kabasuji.shared.entity.Model;

public class TestPlayer extends PlayerTestCase {
	
	// The Master Model
	Model masterModel;
	// The Application to render the Model
	Application app;
	ArrayList<String> fileNames;
	
	protected void setUp(){
		// initialize our list of fileNames
		fileNames = new ArrayList<String>();
		
		PlayerProgress playerProgress = new PlayerProgress();
		// by default PlayerProgress creates a levelList with levels from our levelIndex
		// we instead want some new specially constructed test levels
		LevelList levelList = new LevelList(); // create an empty LevelList
		
		
		// add some levelData to the levelList
		// first of, LeveList does level opening and saving
		
		// create 3 test levels on disk
		// TODO make these test levels actually have something
		fileNames.add(levelList.newTestLevel("TestPuzzle", "PUZZLE" ));
		fileNames.add(levelList.newTestLevel("TestLightning", "LIGHTNING" ));
		fileNames.add(levelList.newTestLevel("TestRELEASE", "RELEASE" ));
			
		// update the LevelList in the playerProgress
		playerProgress.setLevelList(levelList);
		
		
		// now that we have PlayerProgress and LevelList set up, we can procede to
		// creating our Model and View and then navigate and test everything inside
		
		
		// The Master Model
		masterModel = new Model(playerProgress);
		// The Application to render the Model
		app = new Application(masterModel);
	}
	
	protected void tearDown() {
		app.dispose();
		// delete the level files we created for testing
		for (String fileName : fileNames) {
			try {
				File file = new File(fileName);
				if (file.delete()) {
					System.out.println(file.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void testSomething(){
		assertTrue(true);
	}
	
	public void testPlayUnlockedLevel(){
		assertTrue(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	


}
