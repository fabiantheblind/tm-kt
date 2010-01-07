package svnTest;

import processing.core.*;

@SuppressWarnings("serial")
public class Main extends PApplet {
	public void setup(){
		size(400,400);
	}
	
	public void draw(){
		background(0,0,0);
		ellipse(50, 50, 30, 20);
		ellipse(150, 50, 30, 20);		
	}
}
