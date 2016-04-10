package com.halaesus.kabasuji.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Timer;

import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.player.boundary.SplashView;
import com.halaesus.kabasuji.player.entity.Model;
import com.halaesus.kabasuji.player.entity.SplashModel;

public class Main {
	
	public static void main(String[] args) {
		Model masterModel = new Model();
		
		final Application app = new Application(masterModel);

		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.dispose();
			}      
		});

		app.setVisible(true);
		
		ActionListener taskPerformer = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	System.out.println("In timer event");
		        app.showLevelSelector();
		    }
		};
		Timer timer = new Timer(5000, taskPerformer);
		timer.setRepeats(false);
		timer.start();
	}
	
}
