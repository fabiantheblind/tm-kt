package layer;

import utils.PMapContainer;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

public class PolygonObjekte {
	
	public float xPos;
	public float yPos;
	public Location thatLocation;
	public Point2f nowPoint;
	public PMapContainer pmapc;

	public PolygonObjekte(int thatMouseX, int thatMouseY, PMapContainer _pmapc){
		pmapc = _pmapc;
		xPos = thatMouseX;
		yPos = thatMouseY;
		
		thatLocation = pmapc.pointLocation(new Point2f(thatMouseX,thatMouseY));
	}
	
	public void update() {
		nowPoint = pmapc.locationPoint(thatLocation);
	}
	
// die draw wird zwar nicht gebraucht, aber vielleict für später?!
	public void draw() {
//		
//		update();
//
//		p.ellipseMode(CENTER);
//		p.fill(255);
//		p.ellipse(nowPoint.x, nowPoint.y,15,15);
	}	

}
