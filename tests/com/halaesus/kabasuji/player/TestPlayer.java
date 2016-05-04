package com.halaesus.kabasuji.player;

import com.halaesus.kabasuji.player.tests.PlayerTestCase;

import java.io.File;
import java.util.ArrayList;

import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.player.boundary.LightningLevelView;
import com.halaesus.kabasuji.player.boundary.PuzzleLevelView;
import com.halaesus.kabasuji.player.entity.LevelList;
import com.halaesus.kabasuji.player.entity.PlayerProgress;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Model;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;
import com.halaesus.kabasuji.utils.PieceGenerator;

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
		
		// Show the application
		app.setVisible(true);
		app.showSplashScreen();
		app.showLevelSelector();
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

	public void testPlayUnlockedPuzzleLevel(){
		// test opening the first level to play
		AbstractLevel level = masterModel.getLevelSelectorModel().getLevelList().loadLevel(0);
		app.showLevel(level);
		assertTrue(true);
	}
	
	public void testPlayUnlockedLightningLevel(){
		// test opening the first level to play
		AbstractLevel level = masterModel.getLevelSelectorModel().getLevelList().loadLevel(1);
		app.showLevel(level);
		assertTrue(true);
	}
	
	public void testPlayUnlockedReleaseLevel(){
		// test opening the first level to play
		AbstractLevel level = masterModel.getLevelSelectorModel().getLevelList().loadLevel(2);
		app.showLevel(level);
		assertTrue(true);
	}
	
	
	
	public void testPiece(){
		// create the first hexomino piece
		Piece piece  = new Piece(PieceGenerator.pieces[0]);
		piece.centerPiece();
		piece.rotateCC();
		piece.rotateCW();
		piece.flipH();
		piece.flipV();
		PieceSquare square = new PieceSquare(false, 1, 1);
		piece.noSquareAbove(square);
		piece.noSquareBelow(square);
		piece.noSquareLeft(square);
		piece.noSquareRight(square);
		piece.pushTopLeft();
		assertTrue(true);	
	}
	
	
	
/*	public void testTrashcan(){
		AbstractLevel level = masterModel.getLevelSelectorModel().getLevelList().loadLevel(2);
		PuzzleLevelView  puzzleLevelView = new PuzzleLevelView(app, (PuzzleLevel) level);
		
	}*/
	
	
	
	
	
	
	
	
	
	


}
