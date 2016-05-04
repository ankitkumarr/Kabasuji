package com.halaesus.kabasuji.builder;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.boundary.ImageLoader;
import com.halaesus.kabasuji.builder.boundary.LevelManagerDialog;
import com.halaesus.kabasuji.builder.boundary.PuzzleBuilderView;
import com.halaesus.kabasuji.builder.controller.DragPieceFromBoard;
import com.halaesus.kabasuji.builder.controller.DragPieceFromWorkspace;
import com.halaesus.kabasuji.builder.tests.BuilderTestCase;
import com.halaesus.kabasuji.player.entity.LevelList;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Model;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;
import com.halaesus.kabasuji.utils.PieceGenerator;

public class TestBuilderPhaseTwo extends BuilderTestCase {
	
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
		fileNames.add(levelList.newTestLevel("TestRelease", "RELEASE" ));
		
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
	
	protected void addPiece(AbstractLevel l) {
		l.getBoard().addPiece(PieceGenerator.pieces[0]);
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
		app.showLevel(releaseLevel,"RELEASE",	2);
		assertTrue(true);
	}
	
	public MouseEvent createPressed (AbstractBuilderView game, int dx, int dy, int click) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false, click);
		return me;
	}
	
	public void testSomeMovePuzzleLevel() {
		
		AbstractLevel level = model.getLevelSelectorModel().getLevelList().loadLevel(0);
		addPiece(level);
		AbstractBuilderView newView = new PuzzleBuilderView(app, (PuzzleLevel) level, 0);
		MouseEvent me = (createPressed(newView, newView.getBullpenWorkspacePieceRectangle(2, 1).x, newView.getBullpenWorkspacePieceRectangle(2, 1).y));
		MouseEvent me1 = (createDragged(newView, newView.getBoardPieceRectangle(6, 6).x, newView.getBoardPieceRectangle(6, 6).y));
		MouseEvent me2 = (createReleased(newView, newView.getBoardPieceRectangle(6, 6).x, newView.getBoardPieceRectangle(6, 6).y));
		MouseEvent me3 = (createPressed(newView, newView.getBoardPieceRectangle(0, 0).x, newView.getBoardPieceRectangle(0, 0).y));
		MouseEvent me4 = (createReleased(newView, newView.getBullpenWorkspacePieceRectangle(2, 1).x, newView.getBullpenWorkspacePieceRectangle(2, 1).y));
		
		MouseEvent me5 = (createPressed(newView, newView.getBoardPieceRectangle(0, 0).x, newView.getBoardPieceRectangle(0, 0).y, 1));

		DragPieceFromBoard move1 = new DragPieceFromBoard(level, newView);
		move1.mousePressed(me5);
		move1.mouseDragged(me1);
		move1.mouseReleased(me2);
		
		DragPieceFromBoard move3 = new DragPieceFromBoard(level, newView);
		move1.mousePressed(me5);
		move1.mouseDragged(me1);
		move1.mouseReleased(me4);

		
		level.getLevelBullpen().getWorkspace().addPiece(PieceGenerator.pieces[33]);
		DragPieceFromWorkspace move2 = new DragPieceFromWorkspace(level, newView);
		move2.mousePressed(me);
		move2.mouseDragged(me1);
		move2.mouseReleased(me2);
		assertTrue(true);
	}
	
}
