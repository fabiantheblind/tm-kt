package sketchTest;

public class WeatherStationManager {
	
	public ArrayList stationsList;
	public XMLElement[] rssStation;

	
	WeatherStationManager(){
	stationsList = new ArrayList();
	rssStation = new XMLElement[1000];
	}
	
	public void init(){
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
				WeatherStation myWeatherStation  = new WeatherStation(myStation, stationNameStr,xmlUrlStr,stationStateStr);
				stationsList.add(myWeatherStation);
			}
		}
	}
		
	public void drawStations(){
	
		for(int i = 0; i<stationsList.size();i++){
		//Point2f myPoint = map.locationPoint((Location)((WeatherStation)stationsList.get(i)).location);
			if (((WeatherStation)stationsList.get(i)).state.equals("CA")==true){  
				((WeatherStation)stationsList.get(i)).drawStation();
			}
		}	
	}

	public void drawWeather(){
		//rssStation = stationsList.size();
		XMLElement[] currentObs= new XMLElement[stationsList.size()];
		String [] coords = new String[stationsList.size()];
		//rssStation[0] =new XMLElement(mmKMLDISPLAY06.this,((WeatherStation)stationsList.get(100)).xml);
		//println(rssStation[0]);
	
		for(int i = 0, j = 0; i<stationsList.size();i++){
			//for(int i = 0; i<20;i++){
				
			
			if (((WeatherStation)stationsList.get(i)).state.equals("CA")==true){  
				rssStation[j] = new XMLElement(mmKMLDISPLAY06.this,((WeatherStation)stationsList.get(i)).xml);
				
				// coords[i]= (rssStation[i].getChildren("location")).getContent();
		
				//println(rssStation[j] + String.valueOf(j) + "bla");
				j++;
			}
		}
	}
}
