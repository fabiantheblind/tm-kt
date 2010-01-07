package main;

import java.awt.Point;

import processing.core.*;

@SuppressWarnings("serial")
public class Main extends PApplet {
	
	Seperator top,left,buttom,right,separator[],active[];
	int activeCount;
	boolean down;
	
	
	public void setup(){
		size(800,600);
		
		top = new Seperator(this);
		top.setPoints(new Point(0,20), new Point(width,20));
		top.vertical = false;
		left = new Seperator(this);
		left.setPoints(new Point(0,20), new Point(0,height));
		left.vertical = true;
		buttom = new Seperator(this);
		buttom.setPoints(new Point(0,height), new Point(width,height));
		buttom.vertical = false;
		right = new Seperator(this);
		right.setPoints(new Point(width,20), new Point(width,height));
		right.vertical = true;
		
		separator = new Seperator[4];
		separator[0] = new Seperator(this,top,buttom);
		separator[0].vertical = true;
		separator[0].setPoints(new Point(400,20), new Point(400,600));
		separator[1] = new Seperator(this,separator[0],right);
		separator[1].vertical = false;
		separator[1].setPoints(new Point(400,300), new Point(800,300));
		separator[2] = new Seperator(this,separator[0],right);
		separator[2].vertical = false;
		separator[2].setPoints(new Point(400,500), new Point(800,500));
		separator[3] = new Seperator(this,left,separator[0]);
		separator[3].vertical = false;
		separator[3].setPoints(new Point(0,100), new Point(400,100));
		
		
		
	}
	
	public void draw(){
		background(100,100,100);
		top.draw();
		left.draw();
		buttom.draw();
		right.draw();
		
		for (int i = 0; i < separator.length; i++) {
			separator[i].draw();
		}
		

	
		
	}
	
	@Override
	public void mousePressed() {
		down = true;
		active = new Seperator[separator.length];
		activeCount = 0;
		for (int i = 0; i < separator.length; i++) {
			if(separator[i].isOver()){
				active[activeCount++] = separator[i];
				separator[i].active = true;
			}
		}
	}
	
	@Override
	public void mouseDragged() {
		if(!down)
			return;
		
		for (int i = 0; i < activeCount; i++) {
			active[i].move(mouseX, mouseY);
		}
		/* funzt so nicht - blocken: jeder Seperator hat max 2 Stopper die er beim Move nicht übertreten darf!
		for (int i = 0; i < separator.length; i++) {
			if(separator[i].blocks()){
				down = false;
			}
		}*/
	}
	
	@Override
	public void mouseReleased() {
		for (int i = 0; i < activeCount; i++) {
			active[i].active = false;
		}
		active = null;
		activeCount = 0;
		down = false;
	}
	

	
}

