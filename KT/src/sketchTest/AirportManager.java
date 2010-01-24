package sketchTest;

public class AirportManager {
	
	ArrayList airportList;
	ArrayList airportCAList;

	
	AirportManager(){
		airportList = new ArrayList();
		airportCAList = new ArrayList();
		
	}
	
	public void init(){
		
		XMLElement document;
		XMLElement [] folder;
		XMLElement [] fires;
		XMLElement [] placemarks;
		XMLElement [] name;
		XMLElement [] points;
		XMLElement [] coordinates;
		String coordinateRaw;
		String[] coordinateRawSplitter;
		String latLonStringCoordinates;
		
		
			
		document = kmlAirportsWorld.getChildAtIndex(0);
		folder = document.getChildren("Folder");
		for(int j = 0; j<folder.length;j++){
			placemarks=folder[j].getChildren("Placemark");
			for (int l =0;l<=placemarks.length-1;l++){
				name=placemarks[l].getChildren("name");
				points=placemarks[l].getChildren("Point");
				String [] nameStr= new String[placemarks.length];
				
				for (int m =0;m<=points.length-1;m++){
					nameStr[m]= name[m].getContent();
					coordinates=points[m].getChildren("coordinates");
					for (int n =0;n<=coordinates.length-1;n++){
		        			latLonStringCoordinates= coordinates[n].getContent();
		        			coordinateRawSplitter=split(latLonStringCoordinates,",");
						Float lat = Float.parseFloat(coordinateRawSplitter[1]);
						Float lon = Float.parseFloat(coordinateRawSplitter[0]);
					//separate California by lat/lon values
					if((lat>41f||lat<31f )|| (lon< -125f ||lon > -114f)){
						Location myAirportLoc = new Location(lat, lon);
						Airport myAirport  = new Airport(myAirportLoc,nameStr[m]);
						airportList.add(myAirport);
					}else{
						Location myCAAirportLoc = new Location(lat, lon);
				Airport myCAAirport  = new Airport(myCAAirportLoc,nameStr[m]);
					airportCAList.add(myCAAirport);
					}
		    }
		   }
		  }
		 }
		}


public void drawAirports(){
	
	for(int i = 0; i<airportCAList.size();i++){
 		((Airport)airportCAList.get(i)).drawAirport();
	}	
}

	
}
