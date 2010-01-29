package utils;

import main.Main;
import processing.core.PApplet;
import processing.core.PFont;

public class Styles {
	
	public PApplet p;
	PFont calibri14Reg;
	PFont calibri18Reg;
	PFont calibri24Reg;
	PFont calibri36Reg;
	PFont calibri14RegBld;
	PFont calibri18RegBld;
	PFont calibri24RegBld;
	PFont calibri36RegBld;
//	color col1 ;
//	color col2 ;
//	color col3 ;
//	color col4 ;
//	color colBG ;
//	color cShadow ;
	float shadowOffset;
//	color kontur;
//	color textColor;
	float r ;
	float g ;
	float b ;
	public Styles(PApplet p){
		this.p = p;
		
	}

	
	
public Styles(Main p2) {
		// TODO Auto-generated constructor stub
	}



public void loadFonts(){
	calibri14Reg = p.loadFont("../data/Calibri-14.vlw");
	calibri18Reg= p.loadFont("../data/Calibri-18.vlw");
	calibri24Reg= p.loadFont("../data/Calibri-24.vlw");
	calibri36Reg= p.loadFont("../data/Calibri-36.vlw");
	calibri14RegBld= p.loadFont("../data/Calibri-Bold-14.vlw");
	calibri18RegBld= p.loadFont("../data/Calibri-Bold-18.vlw");
	calibri24RegBld= p.loadFont("../data/Calibri-Bold-24.vlw");
	calibri36RegBld= p.loadFont("../data/Calibri-Bold-36.vlw");

}

public float shdwOffset(){
	 shadowOffset = 2.5f;
	 return shadowOffset;
	 	
}




	
}
