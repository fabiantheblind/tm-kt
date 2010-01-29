package main;

import java.awt.Point;

import layer.AirportsManager;
import layer.Layer;
import layer.ConusFiresManager;
import layer.WeatherStationManager;
import layer.WindMarkerManager;

import processing.core.PApplet;
//import processing.core.PConstants;
import utils.PMapContainer;
import utils.Seperator;

import com.modestmaps.geo.Location;
import com.modestmaps.providers.Microsoft;
import com.modestmaps.providers.OpenStreetMap;

import de.fhpotsdam.pmaps.PMap;
import de.fhpotsdam.pmaps.utils.DebugDisplay;

@SuppressWarnings("serial")
public class Main extends PApplet {

	/**
	 * Beschreibung:
	 * 
	 * mögliche Einstellungen:
	 * 	- Größe des Sketchs
	 * 	- ...
	 * 
	 * next Steps:
	 * 	- Layout (erstmal mit Tasten (1,2,.... )
	 * 	- ShowableObjects ( bestehende anschauen und Bridge Pattern anwenden )
	 * 		-> Möglichkeiten: draw/Begrenzung durch Seperatoren/isOver(für Interaktion/
	 * 	- Maus durch TUIO erstetzen ( TUIO Finger )
	 * 	- Tasten durch TUIO ersetzen ( TUIO Objects )
	 * 	- proper band pass filter
	 * 	- 
	 * 
	 * */
	
	/** Ränder */
	Seperator top,left,buttom,right;
	/** alle bewegbaren und momentar zur Bewegung aktiven Trennlinien */
	Seperator seperator[],active[];
	
	/** Kontrollattribute für die Steuerung*/
	private int activeCount;
	private boolean down;

	// this is the only bit that's needed to show a map:
	PMap pmap;
	PMap pmap2;
	DebugDisplay debugDisplay;

	boolean gui = false;
	int picNum = 1;
	
	String CLOUDMADE_API_KEY = "65963b5e0821429da9f583d6f99f1da2";
	int CLOUDMADE_STYLE_ID = 11786; // your style ID 
//	cfm = Conus Fire Manager
//	caam = California Airports Manager
	Layer wsm,wmm,cfm, caam;
	

	
	PMapContainer c1,c2,c3;
	
	public void setup(){
		size(960, 540, P3D);

//		pmap = new PMap(this, 50, 50, 600, 400);
//		MouseMapInteractionsHandler mih = new MouseMapInteractionsHandler(this);
//		mih.setBoundingBox(50, 50, 600, 400);
//		pmap.addInteractionsHandler(mih);
//		pmap.addInteractionsHandler(new KeyboardMapInteractionsHandler(this));
//		pmap.map.setMapProvider(new Microsoft.HybridProvider());
//
//		pmap2 = new PMap(this, 600, 100, 100, 100);
//		pmap2.mapManipulation.zoom(2);
//		pmap2.addInteractionsHandler(new MouseMapInteractionsHandler(this));
//		pmap2.map.setMapProvider(new Microsoft.HybridProvider());
//
//		debugDisplay = new DebugDisplay(this, pmap.map, 10, 440, 260, 140);
//
//		// Set start point
//		// Upper left corner of Scotland at map origin (center: "a" in "Wadden Sea")
//		pmap.mapManipulation.panCenterTo(new Location(53.809f, 7.954f));
//		pmap.mapManipulation.zoomToLevel(5);
//		pmap2.mapManipulation.panCenterTo(new Location(53.809f, 7.954f));
//		pmap2.mapManipulation.zoomToLevel(3);
//		  
//		
		
		/* Initialisierung der Ränder */
		top = new Seperator(this);
		top.setPoints(new Point(0,20), new Point(width,20));
		top.vertical = false;
		left = new Seperator(this);
		left.setPoints(new Point(0,0), new Point(0,height-20));
		left.vertical = true;
		buttom = new Seperator(this);
		buttom.setPoints(new Point(0,height-42), new Point(width,height-42));
		buttom.vertical = false;
		right = new Seperator(this);
		right.setPoints(new Point(width,20), new Point(width,height-42));
		right.vertical = true;
		
		/* Initialisierung der inneren Trennlinien */
		seperator = new Seperator[2];
		seperator[0] = new Seperator(this,top,buttom);
		seperator[0].vertical = true;
		seperator[0].setPoints(new Point((width/3)*2,20), new Point((width/3)*2,height-42));
		seperator[1] = new Seperator(this,seperator[0],right);
		seperator[1].vertical = false;
		seperator[1].setPoints(new Point((width/3)*2,300), new Point(width,300));
//		seperator[2] = new Seperator(this,seperator[0],right);
//		seperator[2].vertical = false;
//		seperator[2].setPoints(new Point(400,500), new Point(800,500));
//		seperator[3] = new Seperator(this,left,seperator[0]);
//		seperator[3].vertical = false;
//		seperator[3].setPoints(new Point(0,100), new Point(400,100));

		Seperator[] s4c1 = {top,left,buttom,seperator[0]};
		c1 = new PMapContainer(this, s4c1);
		Seperator[] s4c2 = {top,seperator[0],seperator[1],right};
		c2 = new PMapContainer(this, s4c2);
		Seperator[] s4c3 = {seperator[1],seperator[0],buttom,right};
		c3 = new PMapContainer(this, s4c3);
		
		c1.pmap.mapManipulation.panCenterTo(new Location(38.8225909761771f, -101.07421875f));
		c1.pmap.mapManipulation.zoomToLevel(5);
		c1.pmap.map.setMapProvider(new OpenStreetMap.CloudmadeProvider(CLOUDMADE_API_KEY, CLOUDMADE_STYLE_ID));
		c2.pmap.mapManipulation.panCenterTo(new Location(38.8225909761771f, -101.07421875f));
		c2.pmap.mapManipulation.zoomToLevel(3);
		c3.pmap.map.setMapProvider(new Microsoft.RoadProvider());
		c3.pmap.mapManipulation.panCenterTo(new Location(38.8225909761771f, -101.07421875f));
		c3.pmap.mapManipulation.zoomToLevel(3);
		
		
		/**Layer erstellen und ihnen 
		 * die Container in denen sie gezeichnet werden soll hinzufügen.
		 * Das Verwalten der Container steuert der AbstractLayer 
		 * von dem WindMarkerManager und WeatherStationManager erben!
		 * Erben = extends => sie können alles was der AbstractLayer kann.
		 */
		caam = new AirportsManager(this);
		cfm =new  ConusFiresManager(this);
		wsm = new WeatherStationManager(this);
		cfm.init();
		wsm.init();
		caam.init();
		caam.addContainer(c1);
		wsm.addContainer(c2);
		cfm.addContainer(c3);
		
		
		wmm = new WindMarkerManager(this,(WeatherStationManager)wsm);
		
		wmm.init();
		wmm.addContainer(c1);
		wmm.addContainer(c3);
		
		
	}
	
	public void draw(){
		background(0);
		

//		pmap.draw();
//		pmap.drawGreenBorder();
//
//		pmap2.draw();
//		pmap2.drawGreenBorder();
//
//		debugDisplay.draw();
		
		c1.draw();
		c2.draw();
		c3.draw();
		
		/* Ränder ... */ 
		top.draw();
		left.draw();
		buttom.draw();
		right.draw();
		/* .. und  Trennlinien zeichnen */
		for (int i = 0; i < seperator.length; i++) {
			seperator[i].draw();
		}
		
		wsm.draw();
		wmm.draw();
		cfm.draw();
//		doesnt work dont know why????
//		caam.draw();
		
	}
	
	@Override
	public void mousePressed() {
		down = true;
		active = new Seperator[seperator.length];
		activeCount = 0;
		for (int i = 0; i < seperator.length; i++) {
			if(seperator[i].isOver()){
				active[activeCount++] = seperator[i];
				seperator[i].active = true;
			}
		}
	}
	
	@Override
	
	public void mouseDragged() {
		if(!down)
			return;
		
		for (int i = 0; i < activeCount; i++) {
			active[i].move(mouseX, mouseY);
		}
	}
	
	@Override
	public void mouseReleased() {
		for (int i = 0; i < activeCount; i++) {
			active[i].active = false;
		}
		active = null;
		activeCount = 0;
		down = false;
	}

	
}

