package utils;

import java.util.ArrayList;

import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

import layer.Layer;

import processing.core.PApplet;
import de.fhpotsdam.pmaps.PMap;
import de.fhpotsdam.pmaps.interactions.MapInteractionsHandler;
import de.fhpotsdam.pmaps.interactions.MouseMapInteractionsHandler;

public class PMapContainer implements Container, TuioListener{
	
	protected float singleFingerOldX;
	protected float singleFingerOldY,zoomMarkerOldY;
	private boolean down = false;
	protected int canvasWidth;
	protected int canvasHeight;

//	public static final String OSM_API_KEY = "88563529326447208964bf5be834f46c";
//	public static final int OSM_STYLE_ID = 15503;
	
	protected Seperator t,l,b,r;

	public PMap pmap;
	
	protected ArrayList<Layer> layers;
	
	protected PApplet p;
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	public boolean isActive = true;
	
	/**
	 * seperator[0] = t
	 * seperator[1] = l
	 * seperator[2] = b
	 * seperator[3] = r
	 * 
	 * */
	
	public PMapContainer(PApplet p, Seperator[] seperators, TuioClient client) {
		t = seperators[0];
		l = seperators[1];
		b = seperators[2];
		r = seperators[3];
		this.p = p;
		
		client.addTuioListener(this);
		
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
		if(!isActive)
			return;
		if(hasChanged()){
			x = l.getX();
			y = t.getY();
			width = r.getX() - x;
			height = b.getY() - y;
			if(width>4&&height>4){
				pmap.setMask(x, y, width, height);
				pmap.setBoundingBox((int)x, (int)y, (int)width, (int)height);
			}
		}
		if(width>4&&height>4)
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
	
	public void addTuioCursor(TuioCursor tuioCursor) {

		float x = tuioCursor.getScreenX(p.width);
		float y = tuioCursor.getScreenY(p.height);
		if(!isInside((int)x, (int)y)){
			return;
		}
		down = true;
		singleFingerOldX = x;
		singleFingerOldY = y;
	}

	public void updateTuioCursor(TuioCursor tuioCursor) {
		float x = tuioCursor.getScreenX(p.width);
		float y = tuioCursor.getScreenY(p.height);
		System.out.println(x+"/"+y);
		if(!isInside((int)x, (int)y)){
			return;
		}

		if (down) {
			pmap.mapManipulation.pan(singleFingerOldX, singleFingerOldY, x, y);
		}
		singleFingerOldX = x;
		singleFingerOldY = y;
	}
	
	public void removeTuioCursor(TuioCursor tuioCursor) {
		down = false;
	}

	public void addTuioObject(TuioObject tuioObject) {
		float x = tuioObject.getScreenX(p.width);
		float y = tuioObject.getScreenY(p.height);
//		System.out.println(x+"/"+y);
		if(!isInside((int)x, (int)y) || tuioObject.getSymbolID()!= 4){
			return;
		}
		zoomMarkerOldY = y;
		 
	}

	public void updateTuioObject(TuioObject tuioObject) {
		float x = tuioObject.getScreenX(p.width);
		float y = tuioObject.getScreenY(p.height);
//		System.out.println(x+"/"+y);
		if(!isInside((int)x, (int)y) || tuioObject.getSymbolID()!= 4){
			return;
		}
		if(y < zoomMarkerOldY-15 ){
			pmap.mapManipulation.zoomIn();
			zoomMarkerOldY = y;
		}else if(y > zoomMarkerOldY+15){
			pmap.mapManipulation.zoomOut();
			zoomMarkerOldY = y;
		}	
	}

	public void removeTuioObject(TuioObject tuioObject) {

	}
	
	public void refresh(TuioTime time){
		
	}
}
