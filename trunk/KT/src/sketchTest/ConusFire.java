//package sketchTest;
//
//public class ConusFire {
//	public float lat, lon;
//	//public String xml, name, state;
//	public Location location;
//	public String discription;
//	public String styleUrlString;
//	
//	
//	ConusFire(float cLat,float cLon,String cDiscription, String cStyleUrlString){
//		this(new Location(cLat,cLon), cDiscription, cStyleUrlString);
//	}
//
//	ConusFire(Location cLocation,String cDiscription, String cStyleUrlString){
//		discription = cDiscription;
//		
//		styleUrlString =  cStyleUrlString;
//		location = cLocation;
//	}
//	
//	
//
//        public void drawFire(){
//          	smooth();
//		fill(255);
//		stroke(0);
//                //Point2f myPoint = map.locationPoint((Location)((WeatherStation)stationsList.get(i)).location);
//                Point2f myPoint = map.locationPoint(location);
//		ellipse(myPoint.x, myPoint.y, 4, 4);
//		float tw = textWidth(styleUrlString);
//		rect(myPoint.x + 9, myPoint.y - 21, tw + 6 , 18);
//		line(myPoint.x, myPoint.y, myPoint.x + 8, myPoint.y - 3);
//		fill(0);
//		text(styleUrlString, myPoint.x + 11, myPoint.y - 8);
//        }
//	
//	
//	
//}

