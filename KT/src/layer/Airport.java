package layer;



import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import utils.Container;
import utils.Styles;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

	public class Airport{
	public PApplet p;

	public float lat, lon;
	public String name;
	public Location location;
	PShape myAirportIcon;// = p.loadShape("../data/AirportIcon.svg");
	

	
	Airport(PApplet p,float cLat,float cLon, String cName){
		this(p,new Location(cLat,cLon),cName);
	}

	Airport(PApplet p,Location cLocation,String cName){
		name = cName;
		location = cLocation;
		this.p = p;

}

public void drawAirport(ArrayList<Container> listener){
         	for(Container container : listener){
	    		Point2f point = container.locationPoint(location);
	    		if(!container.isInside((int)point.x, (int)point.y)){
	    			continue;
	    		}
//	    myAirportIcon = p.loadShape("../data/AirportIcon.svg");
        p.smooth();
		p.shapeMode(p.CENTER);
		myAirportIcon.disableStyle();
		p.fill(Styles.airportCol);
		p.stroke(Styles.kontur);
		p.strokeWeight(Styles.strokeW);
        p.shape(myAirportIcon,point.x,point.y,Styles.iconSize,Styles.iconSize);
        
//      p.ellipse(point.x, point.y, 10, 10);
//		float tw = p.textWidth(name);
//		p.rect(point.x + 9, point.y - 21, tw + 6 , 18);
//		p.line(point.x, point.y, point.x + 8, point.y - 3);
//		p.fill(128,128,128);
//		p.text(name, point.x + 11, point.y - 8);
        }	
	}
}



