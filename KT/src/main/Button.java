package main;

import processing.core.PApplet;

public class Button {
	  
	  float x, y, w, h;
	  PApplet main;
	  
	  Button(PApplet main, float x, float y, float w, float h) {
		this.main = main;
	    this.x = x;
	    this.y = y;
	    this.w = w;
	    this.h = h;
	  } 
	  
	  boolean mouseOver() {
		  return (main.mouseX > x && main.mouseX < x + w && main.mouseY > y && main.mouseY < y + h);
	  }
	  
	  void draw() {
		  main.stroke(80);
		  main.fill(mouseOver() ? 255 : 220);
		  main.rect(x,y,w,h); 
	  }
	  
	}
