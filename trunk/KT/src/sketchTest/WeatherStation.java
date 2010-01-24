package sketchTest;

public class WeatherStation {
	
	public String xml, name, state;
	public Location location;
	
	
	WeatherStation(float cLat,float cLon, String cName,String cXml,String cState){
		this(new Location(cLat,cLon),cName,cXml,cState);
	}

	WeatherStation(Location cLocation, String cName,String cXml, String cState){
		
		location = cLocation;
		xml = cXml;
		name = cName;
		state = cState;
	}

    public void drawStation(){
    	smooth();
		fill(0);
		stroke(0);
      //Point2f myPoint = map.locationPoint((Location)((WeatherStation)stationsList.get(i)).location);
        Point2f myPoint = map.locationPoint(location);
		ellipse(myPoint.x, myPoint.y, 4, 4);
		float tw = textWidth(name);
		rect(myPoint.x + 9, myPoint.y - 21, tw + 6 , 18);
		line(myPoint.x, myPoint.y, myPoint.x + 8, myPoint.y - 3);
		fill(255);
		text(name, myPoint.x + 11, myPoint.y - 8);
	}	

}
