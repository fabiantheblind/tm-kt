//package sketchTest;
//
//public class PolygonObjekte {
//	
//	public float xPos;
//	public float yPos;
//	public Location thatLocation;
//	public Point2f nowPoint;
//
//	PolygonObjekt(int thatMouseX, int thatMouseY){
//		
//		xPos = thatMouseX;
//		yPos = thatMouseY;
//		
//		thatLocation = map.pointLocation(thatMouseX,thatMouseY);
//	}
//	
//	public void update() {
//		nowPoint = map.locationPoint(thatLocation);
//	}
//	
//// die draw wird zwar nicht gebraucht, aber vielleict für später?!
//	public void draw() {
//		
//		update();
//
//		ellipseMode(CENTER);
//		fill(255);
//		ellipse(nowPoint.x, nowPoint.y,15,15);
//	}	
//
//}
