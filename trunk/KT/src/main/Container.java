package main;

public interface Container {
	
	/** Container steht f�r die Frames. Jeder Container hat 4 Seperatoren */
	
	public void setSeperator(Seperator seperator, char direction);
	public void draw();
	public void update();
	
}
