//package sketchTest;
//
//public class Main2 extends PApplet{
//	
//	import com.modestmaps.*;
//	import com.modestmaps.core.*;
//	import com.modestmaps.overlays.*;
//	import com.modestmaps.geo.*;
//	import com.modestmaps.gestalt.*;
//	import com.modestmaps.processing.*;
//	import com.modestmaps.providers*;
//	import processing.core.PApplet;
//
//
//	import processing.opengl.*;
//	/*
//	 * this is heavily based on tom cardens port of modestmaps to processing
//	 * http://www.tom-carden.co.uk/2008/02/18/modest-maps-vs-processing/
//	 * NOTE: for this version, you want to set your available memory in processing settings
//	 * to something higher than 256MB, because tiles are not unloaded for now.
//	 * otherwise you will get an "OutOfMemoryException" after enough scrolling aroundÉ
//	 */
//	String CLOUDMADE_API_KEY = "65963b5e0821429da9f583d6f99f1da2";
//	int CLOUDMADE_STYLE_ID = 11786; // your style ID 
//	InteractiveMap map; // your map
//	Marker marker; // let's place a marker on the map
//
//	PFont font;
//
//	PFont regular_14;
//	PFont regular_18;
//	PFont regular_24;
//	PFont regular_36;
//	PFont bold_14;
//	PFont bold_18;
//	PFont bold_24;
//	PFont bold_36;
//
//	
//	XMLElement kmlConusFires;
//	XMLElement kmlAirportsWorld;
//	XMLElement kmlCaCountysPoDes;
//
//	boolean weatherSwitch = true;
//
//
//	//ArrayList locations = new ArrayList();
//	WeatherStationManager weatherManager = new WeatherStationManager();
//	ConusFireManager conusFireManager = new ConusFireManager();
//	AirportManager airportManager = new AirportManager();
//	CountyManager countyManager = new CountyManager();
//	WindMarkerManager windMarkerManger = new WindMarkerManager();
//
//	public void setup(){
//		size(1200, 700);
//		
//		
//			
//	// create a new map and specify the cloudmade provider with api key and style id
//		map = new InteractiveMap(this, new OpenStreetMap.CloudmadeProvider(CLOUDMADE_API_KEY, CLOUDMADE_STYLE_ID));
////		map = new InteractiveMap(this, new MyMapProvider());
//	// other providers are
//	// map = new InteractiveMap(this, new Microsoft.HybridProvider());
//	//map = new InteractiveMap(this, new Microsoft.AerialProvider());
//	// map = new InteractiveMap(this, new Microsoft.RoadProvider());
//	// map = new InteractiveMap(this, new Yahoo.HybridProvider());
//	//map = new InteractiveMap(this, new Yahoo.AerialProvider());
//	// map = new InteractiveMap(this, new Yahoo.RoadProvider()); // trouble at the moment
//	// map = new InteractiveMap(this, new Google.GoogleTerrainProvider()); // not 'official'
//
//	// set the initial location and zoom level to San Dimas:
//		map.setCenterZoom(new Location(34.1066, -117.8067), 5);
//	// zoom 0 is the whole world, 19 is street level
//	// (try some out, or use getlatlon.com to search for more)
//
//	// enable the mouse wheel, for zooming
//		addMouseWheelListener(new java.awt.event.MouseWheelListener(){
//		 	public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt){
//				mouseWheel(evt.getWheelRotation());
//		 	}
//		}
//	);
//
//	// Get the weatherstation .xml
//		xmlWeathStations = new XMLElement(this, "CurrentWeatherStationsFeeds.xml");
//		kmlConusFires = new XMLElement(this, "conus.kml");
//		kmlAirportsWorld =  new XMLElement(this, "AirportsOfTheWorld.kml");
//		kmlCaCountysPoDes = new XMLElement(this, "caCountysPopDens.kml");
//
//		weatherManager.init();
//		
//		println("weatherManager");
//		
//		windMarkerManger.init(weatherManager);
//		
//	//conusFireManager.init();
//	//airportManager.init();
//	//countyManager.init();
//	//This is for single markers
//	//marker = new Marker(34.1066, -117.8067, "San Dimas");
//	//map.addMarker(marker);
//
//		font = loadFont("MyriadPro-Semibold-14.vlw");
//		textFont(font, 14);
//		
//		regular_14 = loadFont("Calibri-14.vlw");
//		regular_18 = loadFont("Calibri-18.vlw");
//		regular_24 = loadFont("Calibri-24.vlw");
//		regular_36 = loadFont("Calibri-36.vlw");
//		bold_14 = loadFont("Calibri-Bold-14.vlw");
//		bold_18 = loadFont("Calibri-Bold-18.vlw");
//		bold_24 = loadFont("Calibri-Bold-24.vlw");
//		bold_36 = loadFont("Calibri-Bold-36.vlw");
//		
//	}
//
//	public void draw(){
//		background(0);
////	 	draw the map and update the marker's location
//		map.draw();
//
////		Let the manager tell the stations that they should show
//		//weatherManager.drawStations();
////		weatherManager.drawWeather();
////		conusFireManager.drawFires();
////		airportManager.drawAirports();
////		countyManager.drawCountys();
//
//
//
////		marker.updateLocation();
////	 	smooth is disabled everytime the map is drawn (p5 stuffÉ)
////	 	so we just re-enable it, although this might work in all cases
////	 	we're working on this
//	/* 
//		smooth();
//		fill(0);
//		stroke(0);
//		float tw = textWidth(marker.name);
//		rect(marker.x + 9, marker.y - 21, tw + 6 , 18);
//		line(marker.x, marker.y, marker.x + 8, marker.y - 3);
//		ellipse(marker.x, marker.y, 4, 4);
//
//		fill(255);
//		text(marker.name, marker.x + 11, marker.y - 8);
//		*/
//		
//
//		if(weatherSwitch){
//			
//			windMarkerManger.drawWindMarkers();
//			
//			
//		} else{
//			
//			windMarkerManger.drawWriteTheWeather();
//			
//		}
//
//		
//	}
//
//	void keyPressed(){
//		if (key == CODED){
//			
//		 	if (keyCode == LEFT){
//			
//				map.tx += 5.0 / map.sc;
//		 	}
//		 	else if (keyCode == RIGHT){
//			
//				map.tx -= 5.0 / map.sc;
//		 	}
//		 	else if (keyCode == UP){
//			
//				map.ty += 5.0 / map.sc;
//		 	}
//		 	else if (keyCode == DOWN){
//			
//				map.ty -= 5.0 / map.sc;
//		 	}
//		}
//		else if (key == '+' || key == '='){
//			
//		 	map.sc *= 1.05;
//		}
//		else if (key == '_' || key == '-' && map.sc > 2){
//			
//		 	map.sc *= 1.0 / 1.05;
//		}
//		else if (key == 'w' ){
//			
//			weatherSwitch = true;
//			
//		}
//		else if (key == 'W' ){
//			
//			weatherSwitch = false;
//			
//		}
//	}
//
//	void keyReleased(){
//
//		if (key == 'z' || key == 'Z'){
//			
//		 	map.sc = pow(2, map.getZoom());
//		}
//		else if (key == ' '){
//			
//		 	map.sc = 2.0;
//		 	map.tx = -128;
//		 	map.ty = -128;
//		}
//	}
//
//	// see if we're over any buttons, otherwise tell the map to drag
//	void mouseDragged(){
//		
//		map.mouseDragged();
//	}
//
//	// zoom in or out:
//	void mouseWheel(int delta){
//		
//		if (delta > 0){
//		 	map.sc *= 1.05;
//		}
//		
//		else if (delta < 0){
//		 	map.sc *= 1.0 / 1.05;
//		}
//	}
//
//
//
//
//}
