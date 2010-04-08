package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import layer.ConusFiresManager;
import layer.Layer;
import layer.PolygonObjektManager;
import layer.StationsManager;
import layer.WeatherStationManager;
import layer.WindMarkerManager;
import processing.core.PApplet;
import utils.EmptyContainer;
import utils.MyMapProvider;
import utils.PMapContainer;
import utils.SatelliteMapProvider;
import utils.Seperator;
import utils.Styles;
import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;

import codeanticode.glgraphics.GLConstants;

import com.modestmaps.geo.Location;
import com.modestmaps.providers.Microsoft;
import com.modestmaps.providers.OpenStreetMap;
//import com.modestmaps.providers.OpenStreetMap;

import de.fhpotsdam.pmaps.PMap;
import de.fhpotsdam.pmaps.utils.DebugDisplay;

@SuppressWarnings("serial")
public class Main extends PApplet implements TuioListener{

	public final int faktor = 2;
	public final int SIZE_X = 1920/faktor, SIZE_Y = 1020/faktor;
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
	 * 	- Tasten durch TUIO ersetzen ( TUIO Objects )
	 * 	- proper band pass filter
	 * 	- 
	 * 
	 * */
	
	/**
	 * Polygone mit Pete
	 * Straßen
	 * FireArea
	 * Layoutwechsel
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
	
	String CLOUDMADE_API_KEY = "88563529326447208964bf5be834f46c";// Fabs API key
	String CLOUDMADE_API_KEY02 = "ebf32fe920df40e8afd071d75106f7f9";// Petes API KEY

	int CLOUDMADE_STYLE_ID = 15503; // your style ID 
	int CLOUDMADE_STYLE_IDv02 = 15397; // your style ID 

	Layer wsm, wmm, cfm, caam, ps, fs, hos;
	ArrayList<Layer> layers;
	
	
 
	
	
	TuioClient tuioClient;
//	Menue menue;
	PMapContainer c1;
	ArrayList<PMapContainer> containers;
	EmptyContainer empties[];
	ArrayList<PMapContainer> newConainter = new ArrayList<PMapContainer>();
	
	/**POLYGONE*/
	public boolean progressPolygonObjekt = false;
	public ArrayList<PolygonObjektManager> polygonObjektManagerList = new ArrayList<PolygonObjektManager>();
	public int polygonObjektManagerCounter = 0;
	public char kindOfPolygonObjekt;
	public boolean firstPoly =  true;
	public boolean deletePolys = false;
	
	public void setup(){

		Styles.setPApplet(this);
		Styles.create();

		size(SIZE_X, SIZE_Y, P3D);
		tuioClient = new TuioClient();
		tuioClient.addTuioListener(this);
		tuioClient.connect();
		
		/* Initialisierung der Ränder */
		top = new Seperator(this);
		top.setPoints(new Point(0,50/faktor), new Point(width,50/faktor));
		top.vertical = false;
		left = new Seperator(this);
		left.setPoints(new Point(0,20), new Point(0,height-116/faktor));
		left.vertical = true;
		buttom = new Seperator(this);
		buttom.setPoints(new Point(0,height-116/faktor), new Point(width,height-116/faktor));
		buttom.vertical = false;
		right = new Seperator(this);
		right.setPoints(new Point(width,50/faktor), new Point(width,height-116/faktor));
		right.vertical = true;
		
		/* Initialisierung der innteren Trennlinien */
		seperator = new Seperator[4];
		seperator[0] = new Seperator(this,top,buttom);
		seperator[0].vertical = true;
//		seperator[0].setPoints(new Point(640/faktor,50/faktor), new Point(640/faktor,height-116/faktor));
		seperator[0].setPoints(new Point(0,50/faktor), new Point(0,height-116/faktor));
		seperator[1] = new Seperator(this,left,seperator[0]);
		seperator[1].vertical = false;
		seperator[1].setPoints(new Point(0,456/faktor), new Point(0,456/faktor));
		seperator[2] = new Seperator(this,top,buttom);
		seperator[2].vertical = true;
		seperator[2].setPoints(new Point(width,50/faktor), new Point(width,height-116/faktor));
		seperator[3] = new Seperator(this,seperator[2],right);
		seperator[3].vertical = false;
		seperator[3].setPoints(new Point(width,456/faktor), new Point(width,456/faktor));

		Seperator[] s4c1 = {top,seperator[0],buttom,seperator[2]};
		c1 = new PMapContainer(this, s4c1,tuioClient);
		Seperator[] s4c2 = {top,left,seperator[1],seperator[0]};
//		c2 = new PMapContainer(this, s4c2,tuioClient);
		Seperator[] s4c3 = {seperator[1],left,buttom,seperator[0]};
//		c3 = new PMapContainer(this, s4c3,tuioClient);
		Seperator[] s4c4 = {top,seperator[2],seperator[3],right};
		Seperator[] s4c5 = {seperator[3],seperator[2],buttom,right};
		
		empties = new EmptyContainer[4];
		empties[0] = new EmptyContainer(this, s4c2);
		empties[1] = new EmptyContainer(this, s4c3);
		empties[2] = new EmptyContainer(this, s4c4);
		empties[3] = new EmptyContainer(this, s4c5);
		
		
		
		c1.pmap.mapManipulation.panCenterTo(new Location(34.14590795200977f, -118.25546264648438f));
		c1.pmap.mapManipulation.zoomToLevel(3);
		c1.pmap.map.setMapProvider(new Microsoft.RoadProvider());
//		c2.pmap.mapManipulation.panCenterTo(new Location(38.8225909761771f, -101.07421875f));
//		c2.pmap.mapManipulation.zoomToLevel(3);
//		c3.pmap.map.setMapProvider(new MyMapProvider());
//		c3.pmap.mapManipulation.panCenterTo(new Location(38.8225909761771f, -101.07421875f));
//		c3.pmap.mapManipulation.zoomToLevel(3);
		
		
		/**Layer erstellen und ihnen 
		 * die Container in denen sie gezeichnet werden soll hinzufügen.
		 * Das Verwalten der Container steuert der AbstractLayer 
		 * von dem WindMarkerManager und WeatherStationManager erben!
		 * Erben = extends => sie können alles was der AbstractLayer kann.
		 */

		cfm = new ConusFiresManager(this);
		cfm.init();
		wsm = new WeatherStationManager(this);
		wsm.init();
		wmm = new WindMarkerManager(this,(WeatherStationManager)wsm);
		wmm.init();
		
		ps = new StationsManager(this, 0);
		ps.init();
		fs = new StationsManager(this, 1);
		fs.init();
		hos = new StationsManager(this, 2);
		hos.init();
		

//		cfm.addContainer(c1);

		layers = new ArrayList<Layer>();
		containers = new ArrayList<PMapContainer>();
		layers.add(wmm);
		layers.add(ps);
		layers.add(hos);
		layers.add(fs);
		layers.add(cfm);
		containers.add(c1);
	}
	
	public void draw(){
		try{
			background(Styles.colBG);
			fill(Styles.col1);
			stroke(Styles.col1);
			rect( 0, 50/faktor, width, height-116/faktor- 50/faktor);
			
			containers.addAll(newConainter);
			newConainter.clear();
			for(PMapContainer container : containers){
				container.draw();
			}
			
			for(Layer layer : layers){
				layer.draw();
			}
			
			if(deletePolys){
				polygonObjektManagerList.clear();
				polygonObjektManagerCounter = 0;
				deletePolys = false;
			}
	  		for (int i = 0; i < polygonObjektManagerList.size(); i++){
	  			polygonObjektManagerList.get(i).draw();
	   		}
			
			/* Ränder ... */ 
			top.draw();
			left.draw();
			buttom.draw();
			right.draw();
			/* .. und  Trennlinien zeichnen */
			for (int i = 0; i < seperator.length; i++) {
				seperator[i].draw();
			}		
		}catch(ConcurrentModificationException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void mousePressed() {
		pointPressed(mouseX,mouseY);
	}
	
	private void pointPressed(int x,int y){
//		System.out.println(x+"/"+y);
		down = true;
		active = new Seperator[seperator.length];
		activeCount = 0;
		for (int i = 0; i < seperator.length; i++) {
			if(seperator[i].isOver(x,y)){
				active[activeCount++] = seperator[i];
				seperator[i].active = true;
			}
		}
	}
	
	@Override
	public void mouseDragged() {
		pointDragged(mouseX, mouseY);
	}
	
	private void pointDragged(int x,int y){
		if(!down)
			return;
		for (int i = 0; i < activeCount; i++) {
			active[i].move(x, y);
		}
	}
	
	@Override
	public void mouseReleased() {
		pointReleased();
	}
	
	private void pointReleased(){
		for (int i = 0; i < activeCount; i++) {
			active[i].active = false;
		}
		active = null;
		activeCount = 0;
		down = false;
	}
	
	public void keyPressed(){
//		switch(key){
//			case '1':
//				c2.isActive = false;
//				c3.isActive = false;
//				seperator[0].setPoints(new Point(width,20), new Point(width,height));
//				seperator[1].setPoints(new Point(400,height), new Point(800,height));
//				break;
//			case '2':
//				c2.isActive=true;
//				c3.isActive = false;
//				seperator[0].setPoints(new Point((int)(width*0.75),20), new Point((int)(width*0.75),height));
//				seperator[1].setPoints(new Point(400,height), new Point(800,height));
//				break;
//			case '3':
//				c2.isActive = true;
//				c3.isActive = true;
//				seperator[0].setPoints(new Point((int)(width*0.75),20), new Point((int)(width*0.75),height));
//				seperator[1].setPoints(new Point((int)(width*0.75),height/2), new Point(800,height/2));
//				break;
//		}
		if (key == 'p' || key == 'P') {
			
			kindOfPolygonObjekt = 'p';
			progressPolygonObjekt = true;	
			
		}
		
		if (key == 'l' || key == 'L') {

			kindOfPolygonObjekt = 'l';
			progressPolygonObjekt = true;
		}
		
		if (key == 's' || key == 'S') {
			
			kindOfPolygonObjekt = 's';
			progressPolygonObjekt = true;	
		}
		
		if (key == 'r' || key == 'R') {
			
			kindOfPolygonObjekt = 'r';
			progressPolygonObjekt = true;	
		}
		if (key == 'e' || key == 'E') {
			
			progressPolygonObjekt = false;
			polygonObjektManagerCounter++;	
			firstPoly = true;
			
		}
		if (key == 'd' || key == 'D') {
			deletePolys = true;
		}
	}
	
	public void addTuioObject(TuioObject tObj){
//		System.out.println("add obj with id="+tObj.getSymbolID());
		int x = (int)(tObj.getX()*(float)width);
		int y = (int)(tObj.getY()*(float)height);
		int id = tObj.getSymbolID();
		if(id>=5 && id < 10){
			boolean all = false;
			if(y>height-116/faktor/*untern im schwarzen*/){
				all = true;
			}
			for(PMapContainer container : containers){
				if(container.isInside(x, y) || all){
					switch(id){
					case 5:
						cfm.addContainer(container);
						break;
					case 6:
						wmm.addContainer(container);
						break;
					case 7:
						ps.addContainer(container);
						break;
					case 8:
						fs.addContainer(container);
						break;
					case 9:
						hos.addContainer(container);
						break;
					}
				}
			}
		}else if(id>=10 && id < 15){
			for(EmptyContainer empty : empties ){
				if(empty.isInside(x, y)){
					PMapContainer container = 
						new PMapContainer(this, empty.getSeperators(), tuioClient);
					switch(id){
					
					case 14:
						container.pmap.map.setMapProvider(new OpenStreetMap.CloudmadeProvider(CLOUDMADE_API_KEY, CLOUDMADE_STYLE_IDv02));
						container.pmap.mapManipulation.panCenterTo(new Location(34.14590795200977f, -118.25546264648438f));
						container.pmap.mapManipulation.zoomToLevel(5);
						break;
					case 13:
						container.pmap.map.setMapProvider(new Microsoft.RoadProvider());
						container.pmap.mapManipulation.panCenterTo(new Location(34.14590795200977f, -118.25546264648438f));
						container.pmap.mapManipulation.zoomToLevel(5);
						break;
			
					case 12:
						container.pmap.map.setMapProvider(new Microsoft.AerialProvider());
						container.pmap.mapManipulation.panCenterTo(new Location(34.14590795200977f, -118.25546264648438f));
						container.pmap.mapManipulation.zoomToLevel(5);
						break;
					case 11:
						container.pmap.map.setMapProvider(new MyMapProvider());
						container.pmap.mapManipulation.panCenterTo(new Location(34.14590795200977f, -118.25546264648438f));
						container.pmap.mapManipulation.zoomToLevel(3);
						break;
					case 10:
						container.pmap.map.setMapProvider(new OpenStreetMap.CloudmadeProvider(CLOUDMADE_API_KEY, CLOUDMADE_STYLE_ID));
						container.pmap.mapManipulation.panCenterTo(new Location(34.14590795200977f, -118.25546264648438f));
						container.pmap.mapManipulation.zoomToLevel(5);
						break;
					}
					newConainter.add(container);
					empty.isInUse = true;
					
				}
			}
		}
		
	}
	public void removeTuioObject(TuioObject tObj){
//		System.out.println("rem obj with id="+tObj.getSymbolID());
		int x = (int)(tObj.getX()*(float)width);
		int y = (int)(tObj.getY()*(float)height);
		int id = tObj.getSymbolID();
		if(id>4 && id < 10){
			for(PMapContainer container : containers){
				if(container.isInside(x, y)){
					switch(id){
					case 5:
						cfm.removeContainer(container);
						break;
					case 6:
						wmm.removeContainer(container);
						break;
					case 7:
						ps.removeContainer(container);
						break;
					case 8:
						fs.removeContainer(container);
						break;
					case 9:
						hos.removeContainer(container);
						break;
					}
				}
			}
		}
	}
	
	public void updateTuioObject(TuioObject tObj){
//		System.out.println("update obj");
	}
	public void addTuioCursor(TuioCursor tCur){
		System.out.println("add cur");
		int x = (int)(tCur.getX()*(float)width);
		int y = (int)(tCur.getY()*(float)height);
		pointPressed(x, y);
		if (progressPolygonObjekt){
			for(PMapContainer container : containers){
				if(container.isInside(x, y)){
					if(firstPoly){
						polygonObjektManagerList.add(new PolygonObjektManager(this,kindOfPolygonObjekt,container));
						firstPoly = false;
					}
					try{
					polygonObjektManagerList.get(polygonObjektManagerCounter).addObjekt(x,y);	
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}else{
		//WINDMARKER AKTIVIEREN
			for(PMapContainer container: containers){
				if(container.isInside(x, y)){
					((WindMarkerManager)wmm).activeMarker(x, y, container);
				}
			}
		}
	}
	public void removeTuioCursor(TuioCursor tCur){
//		System.out.println("rem cur");
		pointReleased();
	}
	public void updateTuioCursor(TuioCursor tCur){
//		System.out.println("update cur x:"+tCur.getX()+" y:"+tCur.getY());	
		pointDragged((int)(tCur.getX()*(float)width), (int)(tCur.getY()*(float)height));
	}

	public void refresh(TuioTime tuioTime) {
	}
	
}

