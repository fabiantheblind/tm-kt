package layer;

import java.io.PrintWriter;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.xml.XMLElement;

import com.modestmaps.geo.Location;

public class WeatherStationManager extends AbstractLayer implements Layer{
	
	public XMLElement xmlWeathStations;
	public ArrayList<WeatherStation> stationsList;
	public ArrayList<XMLElement> rssStation;
	
//	Create Textfiles
	PrintWriter output;

	
	public WeatherStationManager(PApplet p){
		super(p);
		stationsList = new ArrayList<WeatherStation>();
		rssStation = new ArrayList<XMLElement>();
	}
	
	public void init(){
		
		xmlWeathStations = new XMLElement(p, "../data/CurrentWeatherStationsFeeds.xml");
		XMLElement [] station = xmlWeathStations.getChildren();
		XMLElement [] lonBox; 
		XMLElement [] latBox;
		XMLElement [] xmlUrl;
		XMLElement [] stationName;
		XMLElement [] stationState;
		
		String lonBoxString ; 
		String latBoxString ;
		String xmlUrlStr;
		String stationNameStr;
		String stationStateStr;
		
			//Stations laden
		  for (int l =0;l<station.length;l++){
			    lonBox=station[l].getChildren("longitude");
			    latBox=station[l].getChildren("latitude");
			   	xmlUrl=station[l].getChildren("xml_url");
			   	stationName=station[l].getChildren("station_name");
			   	stationState=station[l].getChildren("state");
			   	
				for(int m = 0; m<= lonBox.length-1;m++){
			   		lonBoxString=lonBox[m].getContent(); 
					latBoxString=latBox[m].getContent();
					xmlUrlStr = xmlUrl[m].getContent();
					stationNameStr = stationName[m].getContent();
					stationStateStr = stationState[m].getContent();
					Location myStation = new Location(Float.parseFloat(latBoxString),Float.parseFloat(lonBoxString));		
					WeatherStation myWeatherStation  = new WeatherStation(p,myStation, stationNameStr,xmlUrlStr,stationStateStr);
					if (myWeatherStation.state.equals("CA")==true){
						stationsList.add(myWeatherStation);
					}
				}
		  }
//		  Here u can write Textfiles
//		  output = p.createWriter("../data/currentWeatherTest.xml");
		  //RSS FEEDS lesen
		  for(int i = 0; i<1 /*stationsList.size()*/;i++){
					rssStation.add(new XMLElement(p,stationsList.get(i).xml));
					
//					p.println(rssStation.get(i));
//					output.println(rssStation.get(i));
//					if(i==stationsList.size()-1){ 
//					output.flush();
//					output.close();
//					}
					// coords[i]= (rssStation[i].getChildren("location")).getContent();
//					System.out.println("Station "+i);
		  }
		 
	}
		
	public void draw(){
		removeSelectedContainer();
		for(int i = 0; i<stationsList.size();i++){
		//Point2f myPoint = map.locationPoint((Location)((WeatherStation)stationsList.get(i)).location);
			if (((WeatherStation)stationsList.get(i)).state.equals("CA")==true){  
				stationsList.get(i).drawStation(listener);
			}
		}	
	}
	
	public void update(){
		
	}
}
