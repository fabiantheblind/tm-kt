package utils;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShape;

public class Styles {
	
	public static PApplet p;
	
	public static PFont calibri14Reg;
	public static PFont calibri18Reg;
	public static PFont calibri24Reg;
	public static PFont calibri36Reg;
	public static PFont calibri14RegBld;
	public static PFont calibri18RegBld;
	public static PFont calibri24RegBld;
	public static PFont calibri36RegBld;
	
	public static PShape fire;
	public static PShape fireStation;
	public static PShape firstAid;
	public static PShape military;
	public static PShape policeStation;
	public static PShape AirportIcon;

	
	public static int col1;
	public static int col2; 
	public static int col3;
	public static int col4;
	public static int colBG;
	public static int colShadow;
	public static int kontur;
	public static int textColor;
	
	public static int fireCol1;
	public static int fireCol2;
	public static int fireCol3;

	public static int policeCol;
	public static int fireStCol;
	public static int militaryCol;
	public static int medicCol;
	public static int airportCol;
	public static int pointCol;
	public static int lineCol;
	public static int polyCol;




	
	public static float shadowOffset;
	public static float iconSize;
	public static float strokeW;
//	public float r ;
//	public float g ;
//	public float b ;

	
	
	public static void setPApplet(PApplet _p){
		p = _p;
	 }

//	 public static void setPApplet(PApplet p){
//		this.p = p;
//		}
	
	
	public static void create(){
		
//		colors
	col1 = p.color(33,33,33,255);
	col2 = p.color(93,93,92,255); 
	col3 = p.color(222,222,222,255);
	col4 = p.color(88,130,223,255);
	colBG = p.color(0,0,0,255);
	colShadow  = p.color(5,5,5,128);
	kontur = p.color(250,50);
	textColor = p.color(255,255,255,250);
	fireCol1 = p.color(255,50,50,250);
	fireCol2 = p.color(255,100,100,250);
	fireCol3 = p.color(255,200,200,250);
	policeCol = p.color(255,255,80,250);
	fireStCol= p.color(255,10,10,250);
	militaryCol= p.color(0,130,0,250);
	medicCol= p.color(50,50,255,250);
	airportCol= p.color(50,130,255,250);

//	todo
	 pointCol=p.color(p.red(col4),p.green(col4),p.blue(col4),245);
	 lineCol= p.color(p.red(col4),p.green(col4),p.blue(col4),200);
	 polyCol= p.color(p.red(col4),p.green(col4),p.blue(col4),50);
//	sizes and distances
	shadowOffset = 2.5f;
	iconSize = 17f;
	strokeW = 1f;


//	Fonts
	calibri14Reg = p.loadFont("../data/Calibri-14.vlw");
	calibri18Reg= p.loadFont("../data/Calibri-18.vlw");
	calibri24Reg= p.loadFont("../data/Calibri-24.vlw");
	calibri36Reg= p.loadFont("../data/Calibri-36.vlw");
	calibri14RegBld= p.loadFont("../data/Calibri-Bold-14.vlw");
	calibri18RegBld= p.loadFont("../data/Calibri-Bold-18.vlw");
	calibri24RegBld= p.loadFont("../data/Calibri-Bold-24.vlw");
	calibri36RegBld= p.loadFont("../data/Calibri-Bold-36.vlw");
	shadowOffset = 2.5f;
	
//	load Icons
	
	fire = p.loadShape("../data/fire.svg");
	AirportIcon = p.loadShape("../data/AirportIcon.svg"); 
	fireStation = p.loadShape("../data/fireStation.svg");
	firstAid = p.loadShape("../data/firstAid.svg");
	military = p.loadShape("../data/military.svg");
	policeStation = p.loadShape("../data/policeStation.svg");


	
	
}

	public static void myEllipse(float xPos, float yPos, float diam,float myStrokeWeight, String myText,Boolean shadow){
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
//	TestTestTest
}
	
}
