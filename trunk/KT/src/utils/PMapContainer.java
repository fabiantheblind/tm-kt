package utils;

import java.util.ArrayList;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

import layer.Layer;

import processing.core.PApplet;
import de.fhpotsdam.pmaps.PMap;
import de.fhpotsdam.pmaps.interactions.MapInteractionsHandler;
import de.fhpotsdam.pmaps.interactions.MouseMapInteractionsHandler;

public class PMapContainer implements Container {

	public static final String OSM_API_KEY = "607e6483654b5c47b9056791d607ab74";
	public static final int OSM_STYLE_ID = 998;
	protected Seperator t,l,b,r;

	public PMap pmap;
	
	protected ArrayList<Layer> layers;
	
	protected PApplet p;
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	/**
	 * seperator[0] = t
	 * seperator[1] = l
	 * seperator[2] = b
	 * seperator[3] = r
	 * 
	 * */
	
	public PMapContainer(PApplet p, Seperator[] seperators) {
		t = seperators[0];
		l = seperators[1];
		b = seperators[2];
		r = seperators[3];
		this.p = p;
		
		x = l.getX();
		y = t.getY();
		width = r.getX() - x;
		height = b.getY() - y;
		//
		this.pmap =  new PMap(p,x,y,width,height);

		pmap.addInteractionsHandler(new MouseMapInteractionsHandler(p));
		pmap.setBoundingBox((int)x, (int)y, (int)width, (int)height);
		layers = new ArrayList<Layer>();
	}
	
	public void draw() {
		if(hasChanged()){
			x = l.getX();
			y = t.getY();
			width = r.getX() - x;
			height = b.getY() - y;
			
			pmap.setMask(x, y, width, height);
			pmap.setBoundingBox((int)x, (int)y, (int)width, (int)height);
		}
		pmap.draw();
	}
	
	public void update(){
		
	}
	
	public void setSeperator(Seperator seperator, char direction) {
		switch(direction){
		case 't':
			t = seperator;
			break;
		case 'l':
			l = seperator;
			break;
		case 'b':
			b = seperator;
			break;
		case 'r':
			r = seperator;
			break;
		}
	}
	
	public boolean isInside(int x,int y){
		return pmap.isInsideBoundingBox(x, y);
	}
	
	public Point2f locationPoint(Location loc){
		return pmap.map.locationPoint(loc);
	}
	
	public Location pointLocation(Point2f point){
		return pmap.map.pointLocation(point);
	}
	
	public void addInteractionsHandler(MapInteractionsHandler handler) {
		pmap.addInteractionsHandler(handler);
	}
	
	private boolean hasChanged(){
		if(		x != l.getX() || y != t.getY() 
				|| width != r.getX() - l.getX() 
				|| height != b.getY() - t.getY()){
			return true;
		}else{
			return false;
		}	
	}
}
