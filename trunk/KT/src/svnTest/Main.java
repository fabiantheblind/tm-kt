package svnTest;

import processing.core.*;

@SuppressWarnings("serial")
public class Main extends PApplet {
	
	PImage img = new PImage();
	PImage mask;
	
	public void setup(){
		size(800,800);
		img = loadImage("../resource/studio.jpg");
		mask = new PImage(img.width,img.height); 
		mask.format = ALPHA;
		for(int x=0;x<mask.width/2;x++){
			for(int y=0;y<mask.height/2;y++){
				mask.pixels[y*mask.width + x] = 255;
			}
		}
	}
	
	public void draw(){
		background(0,0,0);
		ellipse(50, 50, 30, 20);
		ellipse(150, 50, 30, 20);
		
		img.mask(mask.pixels);
		image(img,0,0);
		
	}
}
