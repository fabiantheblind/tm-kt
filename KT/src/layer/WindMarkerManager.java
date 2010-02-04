package layer;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.xml.XMLElement;
import utils.PMapContainer;

import com.modestmaps.geo.Location;

public class WindMarkerManager extends AbstractLayer implements Layer{
	/**extends = erben => alles können was der AbstractLayer kann
	 * muss dadurch selber nur noch die Listener an die WindMarker zum Zeichnen übergeben*/
	//extended WeatherStationManager ??
		
		public ArrayList<WindMarker> windMarkerList;
		public processing.xml.XMLElement lokalRSS;
		public processing.xml.XMLElement [] lokalRSSStation;

		WeatherStationManager hereManager;
		
		public WindMarkerManager(PApplet p,WeatherStationManager hereManager){
			super(p);
			//super = Aufruf des Konstruktors der Oberklasse 
				// hier => super(p) = AbstractLayer(p)
			windMarkerList = new ArrayList<WindMarker>();
			this.hereManager = hereManager;
		}
		
		//println(WeatherStationManager.rssStation[5]);

		public void init(){
//			load the current weather from an xml file. presentation Mode
			lokalRSS = new processing.xml.XMLElement(p, "../data/myCurrentTangibleWeather.xml");
			lokalRSSStation = lokalRSS.getChildren();
			
			XMLElement xmlLat, xmlLon, xmlTemp_f, xmlTemp_c, xmlWind_degrees, xmlWind_mph, xmlWind_string;
		//  public XMLElement xmlRelative_humidity;

			String stringLat, stringLon, stringTemp_f, stringTemp_c, stringWind_degrees, stringWind_mph, stringWind_string;
//			public String stringRelative_humidity;

			float floatLat,  floatLon,  floatTemp_f,  floatTemp_c,  floatWind_degrees,  floatWind_mph;
//			public float floatRelative_humidity;
//			use "hereManager.rssStation" insted of lokalRSSStation if you want a live update from the web
			for (XMLElement oneWeatherStationXML : lokalRSSStation){
				
				//XMLElement currentObs = oneWeatherStationXML.getChild("current_observation");
				xmlLat = oneWeatherStationXML.getChild("latitude");
				xmlLon = oneWeatherStationXML.getChild("longitude");
				xmlTemp_f = oneWeatherStationXML.getChild("temp_f");
				xmlTemp_c = oneWeatherStationXML.getChild("temp_c");
				xmlWind_degrees = oneWeatherStationXML.getChild("wind_degrees");
				xmlWind_mph = oneWeatherStationXML.getChild("wind_mph");
				xmlWind_string = oneWeatherStationXML.getChild("wind_string");

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
				
				try {
					stringWind_string = xmlWind_string.getContent();
				}
				catch (NullPointerException e) {
					stringWind_string= "0";	
				}
				if(stringWind_string !="0"){
					stringWind_string = xmlWind_string.getContent();
					if (stringWind_string.equals ("NA")) { stringWind_string = "Not available!!!";}
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
				
				WindMarker myWindMarker  = new WindMarker(p,oneStationLocation, floatTemp_f, floatTemp_c, stringTemp_c, floatWind_degrees, floatWind_mph, stringWind_string);
				windMarkerList.add(myWindMarker);
				
				//println(floatTemp_f + " " + floatTemp_c + " " + floatWind_degrees + " " + floatWind_mph );
							
			}		
		}	
		
		public void draw(){
			removeSelectedContainer();
			for(int i = 0; i<windMarkerList.size();i++){
					//beim Zeichnen die Container übergeben!
					windMarkerList.get(i).drawArrow(listener);
			}
			

	/*		
			for(int j = 0; j < 20; j++){
				
				p.noStroke();
				p.fill(10,j*23,255);
				p.rect(20, 84-j*4, 20, 4);
			}
			
			for(int k = 0; k < 20; k++){
				
				p.noStroke();
				p.fill(180, k*23, 255);
				p.rect(20, 84 + k * 4, 20, 4);
			}
	*/
		}	
		
		public void drawWriteTheWeather(){
			
			removeSelectedContainer();
			for(int i = 0; i<windMarkerList.size();i++){
				windMarkerList.get(i).writeTheWeather(listener);
			}
		}
		
		public void activeMarker(int x,int y,PMapContainer container){
			if(!listener.contains(container))
				return;
			for(WindMarker marker : windMarkerList){
				marker.checkForSpezial(x, y, container);
			}
		}
}
