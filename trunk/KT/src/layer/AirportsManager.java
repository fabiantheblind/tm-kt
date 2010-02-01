package layer;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.xml.XMLElement;
import utils.Container;

import com.modestmaps.geo.Location;

public class AirportsManager extends AbstractLayer implements Layer {
	
	public processing.xml.XMLElement kmlAirportsWorld;
	public ArrayList<Airport> airportList;
	public ArrayList<Airport> airportCAList;

	
	public AirportsManager(PApplet p){
		super(p);
		airportList = new ArrayList<Airport>();
		airportCAList = new ArrayList<Airport>();

	}
	
	

	
	public void init(){
		kmlAirportsWorld =  new processing.xml.XMLElement(p, "../data/AirportsOfTheWorld.kml");

		
		XMLElement document;
		XMLElement [] folder;
//		XMLElement [] fires;
		XMLElement [] placemarks;
		XMLElement [] name;
		XMLElement [] points;
		XMLElement [] coordinates;
//		String coordinateRaw;
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
		        			coordinateRawSplitter=PApplet.split(latLonStringCoordinates,",");
						Float lat = Float.parseFloat(coordinateRawSplitter[1]);
						Float lon = Float.parseFloat(coordinateRawSplitter[0]);
					//separate California by lat/lon values
					if((lat>41f||lat<31f )|| (lon< -125f ||lon > -114f)){
						Location myAirportLoc = new Location(lat, lon);
						Airport myAirport  = new Airport(p,myAirportLoc,nameStr[m]);
						airportList.add(myAirport);
					}else{
//						p.println(nameStr[m]);
						Location myCAAirportLoc = new Location(lat, lon);
				Airport myCAAirport  = new Airport(p,myCAAirportLoc,nameStr[m]);
					airportCAList.add(myCAAirport);
					}
		    }
		   }
		  }
		 }
		}

	public void draw(){
	for(int i = 0; i<airportCAList.size();i++){
// 		System.out.println(airportCAList.get(i));
 		airportCAList.get(i).drawAirport(listener);
	  
	}	
}
	
	public void update(){
	}
}
