package com.halaesus.kabasuji.shared;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SplashModel {

    BufferedImage splashImage;
    
    public SplashModel() {
    	try {
			splashImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/splash.jpg"));
		} catch (IOException e) {
			splashImage = null; // We don't have an image to show :(
		}
    }

	public BufferedImage getSplashImage() {
		return splashImage;
	}

}