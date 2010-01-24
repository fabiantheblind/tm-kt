package main;

import processing.core.*;

class PanButton extends Button {
	  
	PApplet main;
	
	  int dir = PConstants.UP;
	  
	  PanButton(PApplet main, float x, float y, float w, float h, int dir) {
	    super(main, x, y, w, h);
	    this.dir = dir;
	  }
	  
	  void draw() {
	    super.draw();
	    main.stroke(0);
	    switch(dir) {
	      case PConstants.UP:
	    	  main.line(x+w/2,y+3,x+w/2,y+h-3);
	    	  main.line(x-3+w/2,y+6,x+w/2,y+3);
	    	  main.line(x+3+w/2,y+6,x+w/2,y+3);
	        break;
	      case PConstants.DOWN:
	    	  main.line(x+w/2,y+3,x+w/2,y+h-3);
	    	  main.line(x-3+w/2,y+h-6,x+w/2,y+h-3);
	    	  main.line(x+3+w/2,y+h-6,x+w/2,y+h-3);
	        break;
	      case PConstants.LEFT:
	    	  main.line(x+3,y+h/2,x+w-3,y+h/2);
	    	  main.line(x+3,y+h/2,x+6,y-3+h/2);
	    	  main.line(x+3,y+h/2,x+6,y+3+h/2);
	        break;
	      case PConstants.RIGHT:
	    	  main.line(x+3,y+h/2,x+w-3,y+h/2);
	    	  main.line(x+w-3,y+h/2,x+w-6,y-3+h/2);
	    	  main.line(x+w-3,y+h/2,x+w-6,y+3+h/2);
	        break;
	    }
	  }
}
