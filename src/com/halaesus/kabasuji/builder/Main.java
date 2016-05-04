package com.halaesus.kabasuji.builder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.boundary.ImageLoader;
import com.halaesus.kabasuji.shared.entity.Model;

/**
 * Main class for the KabaSuji Builder
 * 
 * @author apanetta
 */
public class Main {
	/**
	 * Generates a view given a model.
	 * 
	 * @param m KabaSuji model to base on the view
	 * @return Application instance of KabaSuji Builder based
	 * on the given model
	 */
	public static Application setupApp(Model m) {
		Application app = Application.instance(m);

		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.dispose();
			}      
		});

		(new ImageLoader()).execute();
		app.setVisible(true);
		return app;
	}
	
	/**
	 * Creates a new model and generates a view.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Model model = new Model();
		final Application app = setupApp(model);
	}
}
