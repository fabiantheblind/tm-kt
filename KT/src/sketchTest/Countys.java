package sketchTest;

public class County{
	public float lat, lon;
	public String name;
	public Location location;
	public ArrayList points;
	
	
   /* 	County(float cLat,float cLon){
		this(new Location(cLat,cLon));
	} */


	County(ArrayList cPoints){
		points = cPoints;
	}

        public void drawCounty(){
          	smooth();
		fill(160,125,125);
		stroke(0);
		beginShape();
		
		for(int i = 0; i<points.size();i++){
			
		
                //Point2f myPoint = map.locationPoint((Location)((WeatherStation)stationsList.get(i)).location);
                Point2f myPoint = map.locationPoint(((Location)points.get(i)));
		vertex(myPoint.x, myPoint.y);
	//	float tw = textWidth(name);
	//	rect(myPoint.x + 9, myPoint.y - 21, tw + 6 , 18);
	//	line(myPoint.x, myPoint.y, myPoint.x + 8, myPoint.y - 3);
	//	fill(255);
	//	text(name, myPoint.x + 11, myPoint.y - 8);
        }
	endShape();

	}
	
	
}
