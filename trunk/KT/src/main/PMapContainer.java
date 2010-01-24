package main;

import processing.core.PApplet;

import com.modestmaps.providers.OpenStreetMap;

import de.fhpotsdam.pmaps.MapManipulation;
import de.fhpotsdam.pmaps.interactions.MapInteractionsHandler;
import de.fhpotsdam.pmaps.interactions.MouseMapInteractionsHandler;
import de.fhpotsdam.pmaps.map.MaskedProcessingInteractiveMap;

public class PMapContainer implements Container {

	public static final String OSM_API_KEY = "607e6483654b5c47b9056791d607ab74";
	public static final int OSM_STYLE_ID = 998;
	protected Seperator t,l,b,r;
	public MaskedProcessingInteractiveMap map;
	public MapManipulation mapManipulation;
	protected MouseMapInteractionsHandler mouse;
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
	
	public PMapContainer(PApplet p, Seperator[] seperators, MouseMapInteractionsHandler mouse) {
		t = seperators[0];
		l = seperators[1];
		b = seperators[2];
		r = seperators[3];
		this.p = p;
		this.mouse = mouse;
		
		
		x = l.getX();
		y = t.getY();
		width = r.getX() - x;
		height = b.getY() - y;
		//
		this.map =  new MaskedProcessingInteractiveMap(p, new OpenStreetMap.CloudmadeProvider(
				OSM_API_KEY, OSM_STYLE_ID), x, y, width, height);

		mapManipulation = new MapManipulation(map);
		mouse.setMapManipulation(mapManipulation);
		mouse.setBoundingBox((int)x, (int)y, (int)width, (int)height);
	}
	
	public void draw() {
		if(hasChanged()){
			x = l.getX();
			y = t.getY();
			width = r.getX() - x;
			height = b.getY() - y;
			
			map.setMask(x, y, width, height);
			mouse.setBoundingBox((int)x, (int)y, (int)width, (int)height);
		}
		mapManipulation.updateMap();
		map.draw();
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
	
	public void addInteractionsHandler(MapInteractionsHandler handler) {
		handler.setMapManipulation(mapManipulation);
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
