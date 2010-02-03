package utils;

import processing.core.PApplet;

public class EmptyContainer {

	protected Seperator t,l,b,r;
	
	protected PApplet p;
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	public boolean isActive = true;
	public boolean isInUse = false;

	public EmptyContainer(PApplet p, Seperator[] seperators) {
		t = seperators[0];
		l = seperators[1];
		b = seperators[2];
		r = seperators[3];
		this.p = p;
		
		x = l.getX();
		y = t.getY();
		width = r.getX() - x;
		height = b.getY() - y;
	}

	public boolean isInside(int checkX, int checkY) {
		if(isInUse)
			return false;
		if(hasChanged()){
			x = l.getX();
			y = t.getY();
			width = r.getX() - x;
			height = b.getY() - y;	
		}
		return (checkX > x && checkX < x + width && checkY > y && checkY < y + height);
	}
	
	private boolean hasChanged(){
		if(		x != l.getX() || y != t.getY() 
				|| width != r.getX() - l.getX() 
				|| height != b.getY() - t.getY()){
			return true;
		}else{
			return false;
		}	
	}

	public void setSeperator(Seperator seperator, char direction) {
		// TODO Auto-generated method stub
	}
	
	public Seperator[] getSeperators(){
		Seperator[] s =  {t,l,b,r};
		return s;
	}
}
