package com.halaesus.kabasuji.shared.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A Model class that holds the Splash Image
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class SplashModel {

	/** Image which holds the splash image */
    BufferedImage splashImage;
    
    /**
     * Generates an instance of the SplashModel class and loads up the Splash Image
     */
    public SplashModel() {
    	try {
			splashImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/splash.jpg"));
		} catch (IOException e) {
			splashImage = null; // We don't have an image to show :(
		}
    }

    /**
     * Returns the Splash Image
     * @return
     */
	public BufferedImage getSplashImage() {
		return splashImage;
	}

}