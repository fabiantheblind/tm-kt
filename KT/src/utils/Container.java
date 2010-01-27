package utils;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

public interface Container {
	
	/** Container steht für die Frames. Jeder Container hat 4 Seperatoren */
	
	public void setSeperator(Seperator seperator, char direction);
	public void draw();
	public void update();
	public boolean isInside(int x,int y);
	public Point2f locationPoint(Location loc);
	
}
