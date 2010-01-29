package svnTest;

import processing.core.*;
// check this link in the processing reference for all PSahpe functions
// http://processing.org/reference/PShape.html
@SuppressWarnings("serial")
public class Main extends PApplet {
	
//	PImage img = new PImage();
//	PImage mask;
	PShape mySVG;
	
	public void setup(){
		size(800,800);
		mySVG = loadShape("tmktSvgTest.svg");

//		img = loadImage("../resource/studio.jpg");
//		mask = new PImage(img.width,img.height); 
//		mask.format = ALPHA;
//		for(int x=0;x<mask.width/2;x++){
//			for(int y=0;y<mask.height/2;y++){
//				mask.pixels[y*mask.width + x] = 255;
//			}
//		}
	}
	
	public void draw(){
		background(0,0,0);
		mySVG.disableStyle();
		shapeMode(CENTER);
		
		fill(255,0,0);
		shape(mySVG);
		fill(255,0,0,200);
		shape(mySVG,5,5);
		fill(255,0,0,100);
		shape(mySVG,23,23,500,500);
		mySVG.enableStyle();

		
//		ellipse(50, 50, 30, 20);
//		ellipse(150, 50, 30, 20);
//		img.mask(mask.pixels);
//		image(img,0,0);
		
	}
}
