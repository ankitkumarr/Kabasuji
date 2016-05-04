package com.halaesus.kabasuji.builder;

import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.tests.BuilderTestCase;
import com.halaesus.kabasuji.shared.entity.Model;

public class TestBuilder extends BuilderTestCase {
	Model m;
	
	protected void setUp() {
		m = new Model();
	}
	
	public void testMain() {
		Application app = Main.setupApp(m);
		assertTrue(app instanceof Application);
		app.dispose();
	}
}
