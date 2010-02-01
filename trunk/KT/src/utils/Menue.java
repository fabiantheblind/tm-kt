package utils;


import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class Menue {
	public PApplet p;
	PImage imgOverlay;
	PShape menueBG;
	int myStringIndex = 0;
	float myCenterX = 0f;
	float myCenterY = 0f;
	float myDiam01 = 400f;
	float myDiam02 = 145f;
	float myStdRotate =0f;
	float myStdRotate2 =-10f;
	float myRot = 0;

	
	public Menue(PApplet p){

		this.p = p;

}

	
	
	public void loadData(){
		
		imgOverlay =  p.loadImage("../data/MenueBG.png");
		menueBG = p.loadShape("../data/MenueBG.svg");
		
	}
	
	public void layer0(Boolean layer0,Boolean layerDraw,Boolean layerSelec){
		
		p.imageMode(p.CORNER);
		p.textFont(Styles.calibri14Reg, 14);
		p.pushMatrix();
		
		p.translate(p.width/2,p.height/2);
		p.stroke(255,100);
		p.strokeWeight(Styles.strokeW);
		p.shapeMode(p.CENTER);
		menueBG.disableStyle();
		p.fill(Styles.col1);
		p.shape(menueBG,myCenterX,myCenterY);
		p.noFill();
		p.rectMode(p.CENTER);
		p.fill(Styles.textColor);
		p.noStroke();
		p.rotate(p.radians(p.map(p.mouseX,0,p.width,-90,90)));
		
		if (layer0 == true){
			displLayer0();
		}
		
		if (layerDraw == true){
			displDraw();
		}
		
		if (layerSelec == true){
			displSelection();
		}
		
		
		p.popMatrix();
		
		p.pushMatrix();
		
		p.translate(p.width/2,p.height/2);
		p.tint(33,33,33,233);
		p.imageMode(p.CENTER);
		p.image(imgOverlay,0,0);
		p.noTint();
		
		p.popMatrix();
			
			
			
			
		}
		
		
	private void displLayer0(){
		
		float myDegree = p.map(p.mouseX,0,p.width,-90,90);
		p.fill(250,250-p.map(p.abs(myDegree),0,45,0,270));
		String myString ="draw";
		float tw = p.textWidth(myString);
		p.rotate(p.radians(myStdRotate2*-1));
		p.text(myString,myCenterX+myDiam02,myCenterX);
		p.rotate(p.radians(myStdRotate2));
		String myString2 ="selection";
		float tw2 = p.textWidth(myString);
		p.text(myString2,myCenterX+myDiam02,myCenterX);
		p.rotate(p.radians(myStdRotate2));
		String myString3 ="objects";
		float tw3 = p.textWidth(myString);
		p.text(myString3,myCenterX+myDiam02,myCenterX);

		}
		
		private void displDraw(){


		
		
		float myDegree = p.map(p.mouseX,0,p.width,-90,90);
		p.fill(250,250-p.map(p.abs(myDegree),0,45,0,270));
		String myString ="selection";
		float tw = p.textWidth(myString);
		p.rotate(p.radians(myStdRotate2*-1));
		p.text(myString,myCenterX+myDiam02,myCenterX);
		p.rotate(p.radians(myStdRotate2));
		String myString2 ="line";
		float tw2 = p.textWidth(myString);
		p.text(myString2,myCenterX+myDiam02,myCenterX);
		p.rotate(p.radians(myStdRotate2));
		String myString3 ="region";
		float tw3 = p.textWidth(myString);
		p.text(myString3,myCenterX+myDiam02,myCenterX);
		p.rotate(p.radians(myStdRotate2));
		String myString4 ="route";
		float tw4 = p.textWidth(myString);
		p.text(myString4,myCenterX+myDiam02,myCenterX);
	}
		
		private void displSelection(){




		float myDegree = p.map(p.mouseX,0,p.width,-90,90);
		p.fill(250,250-p.map(p.abs(myDegree),0,45,0,270));
		String myString ="selection";
		float tw = p.textWidth(myString);
		p.rotate(p.radians(myStdRotate2*-1));
		p.text(myString,myCenterX+myDiam02,myCenterX);
		p.rotate(p.radians(myStdRotate2));
		String myString2 ="line";
		float tw2 = p.textWidth(myString);
		p.text(myString2,myCenterX+myDiam02,myCenterX);
		p.rotate(p.radians(myStdRotate2));
		String myString3 ="region";
		float tw3 = p.textWidth(myString);
		p.text(myString3,myCenterX+myDiam02,myCenterX);
		p.rotate(p.radians(myStdRotate2));
		String myString4 ="route";
		float tw4 = p.textWidth(myString);
		p.text(myString4,myCenterX+myDiam02,myCenterX);
		}
}
