package layer;

import java.util.ArrayList;

import processing.core.PApplet;
import utils.Container;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

public class WeatherStation{
	
	public PApplet p;
	public String xml, name, state;
	public Location location;
	
	
	WeatherStation(PApplet p,float cLat,float cLon, String cName,String cXml,String cState){
		this(p,new Location(cLat,cLon),cName,cXml,cState);
	}

	WeatherStation(PApplet p,Location cLocation, String cName,String cXml, String cState){
		location = cLocation;
		xml = cXml;
		name = cName;
		state = cState;
		this.p = p;
	}

    public void drawStation(ArrayList<Container> listener){
		//jede Station würd für jeden Container(Listener) extra gezeichnet
		//aber nur wenn sie sich gerade in der BoundingBox befindet.
    	for(Container container : listener){
    		Point2f point = container.locationPoint(location);
    		if(!container.isInside((int)point.x, (int)point.y)){
    			continue;
    		}
        	p.smooth();
    		p.fill(0);
    		p.stroke(0);
    		p.ellipse(point.x, point.y, 4, 4);
//    		float tw = p.textWidth(name);
//    		p.rect(point.x + 9, point.y - 21, tw + 6 , 18);
    		p.line(point.x, point.y, point.x + 8, point.y - 3);
    		p.fill(255);
//    		p.text(name, point.x + 11, point.y - 8);
    	}
		


	}	
}
