package layer;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.xml.XMLElement;
import utils.Container;

import com.modestmaps.geo.Location;

public class ConusFiresManager extends AbstractLayer implements Layer {

	
	public processing.xml.XMLElement kmlConusFires;
	public ArrayList<ConusFire> firesList;
	
	public ConusFiresManager(PApplet p){
		super(p);
		firesList = new ArrayList<ConusFire>();
	}
	
	public void init(){
		kmlConusFires = new processing.xml.XMLElement(p, "../data/tangibleMapsMyConus.kml");
		processing.xml.XMLElement document;
		processing.xml.XMLElement [] folder;
		processing.xml.XMLElement [] fires;
		processing.xml.XMLElement [] placemarks;
		processing.xml.XMLElement [] points;
		processing.xml.XMLElement [] coordinates;
	
		
		 
		String coordinateRaw;
		String[] coordinateRawSplitter;
		String latLonStringCoordinates;
		
		document = kmlConusFires.getChildAtIndex(0);
		folder = document.getChildren("Folder");
		for(int j = 0; j<folder.length;j++){
			placemarks=folder[j].getChildren("Placemark");
//			processing.xml.XMLElement [] discription = new processing.xml.XMLElement [placemarks.length];
			processing.xml.XMLElement [] styleUrls = new processing.xml.XMLElement [placemarks.length];
			for (int l =0;l<=placemarks.length-1;l++){
//				discription=placemarks[l].getChildren("description");
				styleUrls = placemarks[l].getChildren("styleUrl");
				points=placemarks[l].getChildren("Point");
				for (int m =0;m<=points.length-1;m++){
					//String discriptionsStr = discription[m].getContent();
					String styleUrlString = styleUrls[m].getContent();
					coordinates=points[m].getChildren("coordinates");
					for (int n =0;n<=coordinates.length-1;n++){
		        			latLonStringCoordinates= coordinates[n].getContent();
		        			coordinateRawSplitter=p.split(latLonStringCoordinates,",");
						Location myConusFireLoc = new Location(Float.parseFloat(coordinateRawSplitter[1]), Float.parseFloat(coordinateRawSplitter[0]));
				ConusFire myConusFire  = new ConusFire(p,myConusFireLoc,"N/A",styleUrlString);
					firesList.add(myConusFire);
		    }
		   }
		  }
		 }
		}
	
	
	public void draw(){
		for(int i = 0; i<firesList.size();i++){
 		((ConusFire)firesList.get(i)).drawFire(listener);
	}
	}
	
	public void update(){
	}
	
}
