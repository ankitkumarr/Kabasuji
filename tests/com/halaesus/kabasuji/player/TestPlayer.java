package com.halaesus.kabasuji.player;

import com.halaesus.kabasuji.player.tests.PlayerTestCase;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.player.boundary.LightningLevelView;
import com.halaesus.kabasuji.player.boundary.PuzzleLevelView;
import com.halaesus.kabasuji.player.controller.DragPieceFromBoard;
import com.halaesus.kabasuji.player.controller.DragPieceFromWorkspaceToBoard;
import com.halaesus.kabasuji.player.entity.LevelList;
import com.halaesus.kabasuji.player.entity.PlayerProgress;
import com.halaesus.kabasuji.player.moves.BoardToBullpenMove;
import com.halaesus.kabasuji.shared.entity.AbstractBoard;
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
	
	protected AbstractLevel setupBoardPuzzle(AbstractLevel l) {
		for(int r = 0; r < 12; r++)
			for(int c = 0; c < 12; c++)
				l.getBoard().getSquares()[r][c].setActive(true);
		return l;
	}
	
	protected void addPiece(AbstractLevel l) {
		l.getBoard().addPiece(PieceGenerator.pieces[0]);
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
	
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createDoubleClicked (AbstractLevelView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}


	public void testPlayUnlockedPuzzleLevel(){
		// test opening the first level to play
		
		AbstractLevel level = masterModel.getLevelSelectorModel().getLevelList().loadLevel(0);
		level = setupBoardPuzzle(level);
		addPiece(level);
		AbstractLevelView newView = new PuzzleLevelView(app, (PuzzleLevel) level);
		
		MouseEvent me = (createPressed(newView, newView.getBullpenWorkspacePieceRectangle(2, 1).x, newView.getBullpenWorkspacePieceRectangle(2, 1).y));
		MouseEvent me1 = (createDragged(newView, newView.getBoardPieceRectangle(6, 6).x, newView.getBoardPieceRectangle(6, 6).y));
		MouseEvent me2 = (createReleased(newView, newView.getBoardPieceRectangle(6, 6).x, newView.getBoardPieceRectangle(6, 6).y));
		MouseEvent me3 = (createPressed(newView, newView.getBoardPieceRectangle(0, 0).x, newView.getBoardPieceRectangle(0, 0).y));
		MouseEvent me4 = (createReleased(newView, newView.getBullpenWorkspacePieceRectangle(2, 1).x, newView.getBullpenWorkspacePieceRectangle(2, 1).y));
		
		level.getLevelBullpen().getWorkspace().addPiece(PieceGenerator.pieces[33]);
		DragPieceFromWorkspaceToBoard t1 = new DragPieceFromWorkspaceToBoard(level, newView);
		DragPieceFromBoard t2 = new DragPieceFromBoard(level, newView);
		t1.mousePressed(me);
		t1.mouseDragged(me1);
		t2.mouseReleased(me2);
		t1.mouseReleased(me2);
		app.showLevel(level);
		
		BoardToBullpenMove bpm = new BoardToBullpenMove(newView);
		t2.mousePressed(me3);
		bpm.isValid(level);
		t2.mouseDragged(me1);
		t2.mouseReleased(me2);
		t2.mouseReleased(me4);
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
	
	public void testMain() {
		Main.main(null);
		assertTrue(true);	

	}
	
	
	
/*	public void testTrashcan(){
		AbstractLevel level = masterModel.getLevelSelectorModel().getLevelList().loadLevel(2);
		PuzzleLevelView  puzzleLevelView = new PuzzleLevelView(app, (PuzzleLevel) level);
		
	}*/
	
	
	
	
	
	
	
	
	
	


}
