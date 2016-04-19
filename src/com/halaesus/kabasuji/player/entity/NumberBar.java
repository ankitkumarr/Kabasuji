package com.halaesus.kabasuji.player.entity;


public class NumberBar {

   
    public NumberBar() {
    }

    ReleaseNumber[] numbers;

    public ReleaseNumber getNumber(int index) {
    	if (index < 0) return null;
    	if (index > 17) return null;
    	return numbers[index];
    }

}