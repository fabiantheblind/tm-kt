package main;

import processing.core.PApplet;

class ZoomButton extends Button {
	  PApplet main;
	  
	  boolean in = false;
	  
	  ZoomButton(PApplet main, float x, float y, float w, float h, boolean in) {
	    super(main, x, y, w, h);
	    this.in = in;
	  }
	  
	  void draw() {
	    super.draw();
	    main.stroke(0);
	    main.line(x+3,y+h/2,x+w-3,y+h/2);
	    if (in) {
	    	main.line(x+w/2,y+3,x+w/2,y+h-3);
	    }
	  }
	  
}