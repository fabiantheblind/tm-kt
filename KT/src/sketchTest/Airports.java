//package sketchTest;
//
//public class Airport{
//	public float lat, lon;
//	public String name;
//	public Location location;
//	
//	
//	Airport(float cLat,float cLon, String cName){
//		this(new Location(cLat,cLon),cName);
//	}
//
//	Airport(Location cLocation,String cName){
//		name = cName;
//		location = cLocation;
//	}
//
//        public void drawAirport(){
//          	smooth();
//		fill(0);
//		stroke(0);
//                //Point2f myPoint = map.locationPoint((Location)((WeatherStation)stationsList.get(i)).location);
//                Point2f myPoint = map.locationPoint(location);
//		ellipse(myPoint.x, myPoint.y, 10, 10);
//		float tw = textWidth(name);
//		rect(myPoint.x + 9, myPoint.y - 21, tw + 6 , 18);
//		line(myPoint.x, myPoint.y, myPoint.x + 8, myPoint.y - 3);
//		fill(128,128,128);
//		text(name, myPoint.x + 11, myPoint.y - 8);
//        }
//	
//	
//	
//}

