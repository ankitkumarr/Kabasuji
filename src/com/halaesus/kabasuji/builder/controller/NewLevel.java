package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.boundary.Application;

/**
 * 
 */
public class NewLevel implements ActionListener {
	
    AbstractBuilderView builderView;
    Application app;

    public NewLevel(AbstractBuilderView builderView, Application app) {
        // TODO implement here
    	this.builderView = builderView;
    	this.app = app;
    }

    public void actionPerformed(ActionEvent e) {
        // TODO implement here
    }

}