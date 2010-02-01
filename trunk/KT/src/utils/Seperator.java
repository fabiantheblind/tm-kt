package utils;

import processing.core.PApplet;
import java.awt.Point;

public class Seperator {
	public PApplet main;
	public Point p1,p2;
	public Seperator neighbor[];
	
	public boolean lock,active,vertical;	
	
	public Seperator(PApplet main) {
		this(main,null,null);
		lock = true;
	}
	
	public Seperator(PApplet main,Seperator neighbor1,Seperator neighbor2){
		this.main = main;
		this.neighbor = new Seperator[2];
		this.neighbor[0] = neighbor1;
		this.neighbor[1] = neighbor2;
		active = false;
		
	}
	
	public void setPoints(Point p1,Point p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void move(int changeX, int changeY){
		if(lock)
			return;
		if(vertical){
			p2.x = p1.x = changeX;
		}else{
			p2.y = p1.y = changeY;
		}
	}
	
	public boolean isOver(){
		return isOver(main.mouseY,main.mouseX);
	}
	
	public boolean isOver(int x, int y){
		if(vertical){
			if( x > p1.x - 5 && x < p1.x + 5
				&&	y > neighbor[0].getY() - 5 && y < neighbor[1].getY() + 5
			)
				return true;
		}else{
			if( y > p1.y - 5 && y < p1.y + 5
				&&	x > neighbor[0].getX() - 5 && x < neighbor[1].getX() + 5
			)
				return true;
		}
		return false;
	}
	
	public boolean blocks(){
		
		if(active)
			return false;
		if(vertical){
			if(main.mouseX < p1.x + 30 && main.mouseX > p1.x - 30)
				return true;
		}else{
			if(main.mouseY < p1.y + 30 && main.mouseY > p1.y - 30)
				return true;
		}
		return false;
	}

	
	public void draw(){
		main.stroke(0);
		main.strokeWeight(5);
		if(neighbor[0]==null){
			main.line(p1.x,p1.y,p2.x,p2.y);
		}else{
			if(vertical){
				main.line(p1.x, neighbor[0].getY(), p2.x, neighbor[1].getY());
			}else{
				main.line(neighbor[0].getX(),p1.y,neighbor[1].getX(),p2.y);
			}
		}
	}
	
	public int getX(){
		return p1.x;
	}
	
	public int getY(){
		return p1.y;
	}
}
