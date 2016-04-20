package com.halaesus.kabasuji.player.entity;


public class NumberBar {
	
	ReleaseNumber[] numbers = new ReleaseNumber[18];
   
    public NumberBar() {
    	for (int i = 1; i <= 6; i++){
    		ReleaseNumber n = new ReleaseNumber(i,1,0,0);
    		numbers[i-1] = n;
    	}

    	for (int i = 1; i <= 6; i++){
    		ReleaseNumber n = new ReleaseNumber(i,2,0,0);
    		numbers[i-1+6] = n;
    	}	
    	for (int i = 1; i <= 6; i++){
    		ReleaseNumber n = new ReleaseNumber(i,3,0,0);
    		numbers[i-1+12] = n;
    	}
    	//TODO remove this when we have collecting numbers from board working
    	// Just here so I have something to display in the boundary class
    	addReleaseNumber(new ReleaseNumber(5, 1, 5, 5));
    	addReleaseNumber(new ReleaseNumber(1, 2, 3, 5));
    	addReleaseNumber(new ReleaseNumber(6, 3, 5, 8));
    	
    }

    
    // returns the number sets found for awarding stars
    public int setsFound() {
    	int found = 0;
    	for (ReleaseNumber num: numbers){
    		if (num.found) found++;
    	}
    	return (found%6);
    }
    
    public ReleaseNumber[] getNumbers(){
    	return this.numbers;
    }
    
    // this function requires valid a valide ReleaseNumber to be passed in
    // a valid release number has an int value [1,6] and an int color [1,3]
    public void addReleaseNumber(ReleaseNumber number) {
    	numbers[(number.getValue() * number.getColor()) - 1].setFound();
    }
}