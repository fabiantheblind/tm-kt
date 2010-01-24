package sketchTest;

public class WindMarkerManager {
	
	//extended WeatherStationManager ??
		
		public ArrayList windMarkerList;
		
		public XMLElement oneWeatherStationXML;

		public XMLElement xmlLat;
		public XMLElement xmlLon;
		public XMLElement xmlTemp_f;
		public XMLElement xmlTemp_c;
		public XMLElement xmlWind_degrees;
		public XMLElement xmlWind_mph;
	//  public XMLElement xmlRelative_humidity;

		public String stringLat;
		public String stringLon;
		public String stringTemp_f;
		public String stringTemp_c;
		public String stringWind_degrees;
		public String stringWind_mph;
//		public String stringRelative_humidity;

		public float floatLat;
		public float floatLon;
		public float floatTemp_f;
		public float floatTemp_c;
		public float floatWind_degrees;
		public float floatWind_mph;
//		public float floatRelative_humidity;

		
		WindMarkerManager(){
			windMarkerList = new ArrayList();
		}
		
		//println(WeatherStationManager.rssStation[5]);

		public void init(WeatherStationManager hereManager){
			
			for (int i = 0; i<= 170; i++){
							
				oneWeatherStationXML = hereManager.rssStation[i];
				
				//XMLElement currentObs = oneWeatherStationXML.getChild("current_observation");
				
				xmlLat = oneWeatherStationXML.getChild("latitude");
				xmlLon = oneWeatherStationXML.getChild("longitude");
				xmlTemp_f = oneWeatherStationXML.getChild("temp_f");
				xmlTemp_c = oneWeatherStationXML.getChild("temp_c");
				xmlWind_degrees = oneWeatherStationXML.getChild("wind_degrees");
				xmlWind_mph = oneWeatherStationXML.getChild("wind_mph");
				//xmlRelative_humidity= oneWeatherStationXML.getChild("relative_humidity");

				//println(xmlTemp_f + " " + xmlTemp_c + " " + xmlWind_degrees + " " + xmlWind_mph + " " + xmlRelative_humidity );

				// parse section
				// xml 2 string
				
				//try catch
			/*	
				try {
					;

				}
				catch (IOException e) {
					 = "0";	
				}
				else {
					;
					if (.equals ("NA")) { = "0";}
				}
			  }
			*/	
				try {
		    		stringLat = xmlLat.getContent();
		  		}
				catch (NullPointerException e) {
					stringLat = "0";	
		  		}
				if(stringLat !="0"){
					stringLat = xmlLat.getContent();
					if (stringLat.equals ("NA")) { stringLat = "0";}
				}
				
				try {
					stringLon = xmlLon.getContent();
				
					}
				catch (NullPointerException e) {
						stringLon = "0";	
			  		}
				if(stringLon !="0"){
					stringLon = xmlLon.getContent();
					if (stringLon.equals ("NA")) { stringLon = "0";}
				}
				
				try {
					stringTemp_f = xmlTemp_f.getContent();			
				}
				catch (NullPointerException e) {
					 stringTemp_f = "0";	
				}
				if(stringTemp_f !="0"){
					stringTemp_f = xmlTemp_f.getContent();			
					
					if (stringTemp_f.equals ("NA")) {stringTemp_f = "0";}
				}
				
				try {
					stringTemp_c = xmlTemp_c.getContent();
				}
				catch (NullPointerException e) {
					stringTemp_c = "0";	
				}
				if(stringTemp_c !="0"){
					stringTemp_c = xmlTemp_c.getContent();
					if (stringTemp_c.equals ("NA")) { stringTemp_c = "0";}
				}
				
				try {
					stringWind_degrees = xmlWind_degrees.getContent();
				}
				catch (NullPointerException e) {
					 stringWind_degrees = "0";	
				}
				if(stringWind_degrees !="0"){
					stringWind_degrees = xmlWind_degrees.getContent();
					if (stringWind_degrees.equals ("NA")) { stringWind_degrees = "0";}
				}
				
				try {
					stringWind_mph = xmlWind_mph.getContent();
				}
				catch (NullPointerException e) {
					stringWind_mph= "0";	
				}
				if(stringWind_mph !="0"){
					stringWind_mph = xmlWind_mph.getContent();
					if (stringWind_mph.equals ("NA")) { stringWind_mph = "0";}
				}
				  

				//stringRelative_humidity = xmlRelative_humidity.getContent();
				//if (stringLat.equals ("NA")) { stringLat = "0";}


				// string 2 float
				floatLat = Float.parseFloat(stringLat); 
				floatLon = Float.parseFloat(stringLon); 
				floatTemp_f = Float.parseFloat(stringTemp_f); 
				floatTemp_c = Float.parseFloat(stringTemp_c);
				floatWind_degrees = Float.parseFloat(stringWind_degrees);
				floatWind_mph = Float.parseFloat(stringWind_mph);
				//floatRelative_humidity = Float.parseFloat(stringRelative_humidity);

				
				Location oneStationLocation = new Location(floatLat, floatLon);
				
				WindMarker myWindMarker  = new WindMarker(oneStationLocation, floatTemp_f, floatTemp_c, floatWind_degrees, floatWind_mph);
				windMarkerList.add(myWindMarker);
				
				//println(floatTemp_f + " " + floatTemp_c + " " + floatWind_degrees + " " + floatWind_mph );
							
			}		
		}	
		
		public void drawWindMarkers(){
		
			for(int i = 0; i<windMarkerList.size();i++){
			//Point2f myPoint = map.locationPoint((Location)((WeatherStation)stationsList.get(i)).location);
					((WindMarker)windMarkerList.get(i)).drawArrow();
			}
			
			for(int j = 0; j < 20; j++){
				
				noStroke();
				fill(10,j*23,255);
				rect(20, 84-j*4, 20, 4);
			}
			
			for(int k = 0; k < 20; k++){
				
				noStroke();
				fill(180, k*23, 255);
				rect(20, 84 + k * 4, 20, 4);
			}
		}	
		
		public void drawWriteTheWeather(){
		
			for(int i = 0; i<windMarkerList.size();i++){
			//Point2f myPoint = map.locationPoint((Location)((WeatherStation)stationsList.get(i)).location);
					((WindMarker)windMarkerList.get(i)).writeTheWeather();
			}
			
			for(int j = 0; j < 20; j++){
				
				noStroke();
				fill(10,j*23,255);
				rect(20, 84-j*4, 20, 4);
			}
			
			for(int k = 0; k < 20; k++){
				
				noStroke();
				fill(180, k*23, 255);
				rect(20, 84 + k * 4, 20, 4);
			}
		}
	}
//maybe here is missing a right curly brace