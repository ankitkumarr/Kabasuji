package com.halaesus.kabasuji.builder;

import java.io.File;
import java.util.ArrayList;

import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.boundary.ImageLoader;
import com.halaesus.kabasuji.builder.boundary.LevelManagerDialog;
import com.halaesus.kabasuji.builder.tests.BuilderTestCase;
import com.halaesus.kabasuji.player.entity.LevelList;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Model;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;

public class TestBuilderVarTwo extends BuilderTestCase {
	
	Model model;
	Application app;
	ArrayList<String> fileNames;
	LevelList levelList;// our level
	
	protected void setUp(){
		// initialize our list of fileNames
		fileNames = new ArrayList<String>();
		// The Master Model
		model = new Model();
		// The Application to render the Model
		app =  Application.instance(model);
		
		levelList = new LevelList(); // create an empty LevelList
		
		// add some levelData to the levelList
		
		// create 3 test levels on disk
		fileNames.add(levelList.newTestLevel("TestPuzzle", "PUZZLE" ));
		fileNames.add(levelList.newTestLevel("TestLightning", "LIGHTNING" ));
		fileNames.add(levelList.newTestLevel("TestRELEASE", "RELEASE" ));
		
		// mimic builder main setup
		(new ImageLoader()).execute();
		// image loader works in the background and works great
		// but not so great for testing so
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e){}
		app.setVisible(true);
		
	}

	protected void tearDown() {
		app.destroyInstance();
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
	
	
	public void testSomething() {
		assertTrue(true);
	}
	
	public void testLoadPuzzleLevel(){
		AbstractLevel puzzleLevel =  levelList.loadLevel(0);
		app.showLevel(puzzleLevel,"PUZZLE",	0);
		assertTrue(true);
	}
	
	public void testLoadLightningLevel(){
		AbstractLevel lightningLevel =  levelList.loadLevel(1);
		app.showLevel(lightningLevel,"LIGHTNING",	1);
		assertTrue(true);
	}
	
	public void testLoadReleaseLevel(){
		AbstractLevel releaseLevel =  levelList.loadLevel(2);
		app.showLevel(releaseLevel,"RELEASE",	1);
		assertTrue(true);
	}
	
}
