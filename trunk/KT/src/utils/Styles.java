package utils;

import processing.core.PApplet;
import processing.core.PFont;

public class Styles {
	
	public PApplet p;
	public PFont calibri14Reg;
	public 	PFont calibri18Reg;
	public 	PFont calibri24Reg;
	public 	PFont calibri36Reg;
	public 	PFont calibri14RegBld;
	public 	PFont calibri18RegBld;
	public 	PFont calibri24RegBld;
	public 	PFont calibri36RegBld;
	public 	int col1;
	public 	int col2; 
	public 	int col3;
	public 	int col4;
	public 	int colBG;
	public int colShadow;
	public int kontur;
	public int textColor;
	public float shadowOffset;
//	public float r ;
//	public float g ;
//	public float b ;
	public Styles(PApplet p){
		this.p = p;
		
	}

	
public void create(){
	col1 = p.color(33,33,33,255);
	col2 = p.color(93,93,92,255); 
	col3 = p.color(222,222,222,255);
	col4 = p.color(88,130,223,255);
	colBG = p.color(0,0,0,255);
	colShadow  = p.color(5,5,5,128);
	kontur = p.color(p.red(col1)+42,p.green(col1)+42,p.blue(col1)+42,200);;
	textColor = p.color(255,255,255,250);
	calibri14Reg = p.loadFont("../data/Calibri-14.vlw");
	calibri18Reg= p.loadFont("../data/Calibri-18.vlw");
	calibri24Reg= p.loadFont("../data/Calibri-24.vlw");
	calibri36Reg= p.loadFont("../data/Calibri-36.vlw");
	calibri14RegBld= p.loadFont("../data/Calibri-Bold-14.vlw");
	calibri18RegBld= p.loadFont("../data/Calibri-Bold-18.vlw");
	calibri24RegBld= p.loadFont("../data/Calibri-Bold-24.vlw");
	calibri36RegBld= p.loadFont("../data/Calibri-Bold-36.vlw");
	shadowOffset = 2.5f;
	
}


public void Ellipse(float xPos, float yPos, float diam,float myStrokeWeight, String myText,Boolean shadow){
	p.smooth();
	p.rectMode(p.CORNER);
	p.ellipseMode(p.CENTER);
	p.textFont(calibri14Reg, 14); 
	float tw = p.textWidth(myText);
	if(shadow==true){
		p.fill(colShadow);
		p.strokeWeight(myStrokeWeight);
		p.stroke(colShadow);
		p.ellipse((xPos)+shadowOffset,(yPos)+shadowOffset,diam,diam);
		p.rect(xPos+ 9+shadowOffset, yPos - 21+shadowOffset, tw + 7 , 19);
		p.text(myText,( xPos + 12+ (shadowOffset-0.75f)),( yPos - 7+(shadowOffset-0.75f)));
	}

	p.fill(col1);
	p.strokeWeight(myStrokeWeight);
	p.stroke(kontur);
	p.rect(xPos+ 9, yPos - 21, tw + 7 , 19);
	p.line(xPos, yPos, xPos + 8, yPos - 3);
	p.ellipse((xPos),(yPos),diam,diam);
	p.fill(textColor);
	p.text(myText, xPos + 12, yPos - 7);
}
	
}
