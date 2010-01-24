package main;

public interface Container {
	
	/** Container steht für die Frames. Jeder Container hat 4 Seperatoren */
	
	public void setSeperator(Seperator seperator, char direction);
	public void draw();
	public void update();
	
}
