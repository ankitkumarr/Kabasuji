package com.halaesus.kabasuji.builder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.boundary.ImageLoader;
import com.halaesus.kabasuji.shared.entity.Model;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		final Application app = Application.instance(model);

		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.dispose();
			}      
		});

		(new ImageLoader()).execute();
		app.setVisible(true);
	}
}
