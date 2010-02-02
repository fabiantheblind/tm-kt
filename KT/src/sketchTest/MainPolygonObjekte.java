//package sketchTest;
//
//public class MainPolygonObjekte {
//
//}
//
//import com.modestmaps.*;
//
//import com.modestmaps.core.*;
//import com.modestmaps.overlays.*;
//import com.modestmaps.geo.*;
////import com.modestmaps.gestalt.*;
//import com.modestmaps.processing.*;
//import com.modestmaps.providers.*;
//
//import processing.opengl.*;
//
//
///*
// *
// * shows how to use your own cloudmade styles within processing
// * and put markers for specific locations onto the map
// *
// * this is heavily based on tom cardens port of modestmaps to processing
// * http://www.tom-carden.co.uk/2008/02/18/modest-maps-vs-processing/
// * 
// *
// * NOTE: for this version, you want to set your available memory in processing settings
// * to something higher than 256MB, because tiles are not unloaded for now.
// * otherwise you will get an "OutOfMemoryException" after enough scrolling aroundÉ
// *
// */
//
//String CLOUDMADE_API_KEY = "65963b5e0821429da9f583d6f99f1da2";
//int CLOUDMADE_STYLE_ID = 11786; // your style ID
//
//InteractiveMap map; // your map
//
//// important PETE stuff	
//
//public boolean progressPolygonObjekt = false;
//
//public ArrayList polygonObjektManagerList;
//public int polygonObjektManagerCounter;
//
//public char kindOfPolygonObjekt;
//
//
//public void setup(){
//		
//	size(600, 700, OPENGL);
//
//  // create a new map and specify the cloudmade provider with api key and style id
//	map = new InteractiveMap(this, new OpenStreetMap.CloudmadeProvider(CLOUDMADE_API_KEY, CLOUDMADE_STYLE_ID));
//
//  // other providers are (some might me more of a bumpy rideÉ):
//  // map = new InteractiveMap(this, new Microsoft.HybridProvider());
//  // map = new InteractiveMap(this, new Microsoft.AerialProvider());
//  // map = new InteractiveMap(this, new Microsoft.RoadProvider());
//  // map = new InteractiveMap(this, new Yahoo.HybridProvider());
//  // map = new InteractiveMap(this, new Yahoo.AerialProvider());
//  // map = new InteractiveMap(this, new Yahoo.RoadProvider()); // trouble at the moment
//  // map = new InteractiveMap(this, new Google.GoogleTerrainProvider()); // not 'official'
//  // map = new InteractiveMap(this, new GeoMapApp.TopologicalGeoMapProvider());
//
//
//  // set the initial location and zoom level to London:
//  	map.setCenterZoom(new Location(52.4115, 13.0516), 11);
//  // zoom 0 is the whole world, 19 is street level
//  // (try some out, or use getlatlon.com to search for more)
//
//  // enable the mouse wheel, for zooming
//  	addMouseWheelListener(new java.awt.event.MouseWheelListener() {
//	
//    	public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt){
//      		mouseWheel(evt.getWheelRotation());
//    	}
//  	}
//  	);
//	
//// important PETE stuff	
//
//	polygonObjektManagerList = new ArrayList();
//	polygonObjektManagerCounter = 0;
//}
//
//public void draw(){
//	
//	background(0);
//
//// draw the map and update the marker's location
//	map.draw();
//
//    smooth();
//
//// important PETE stuff	
//
//  	if(polygonObjektManagerList.size() > -1){
//  	
//  		for (int i = 0; i < polygonObjektManagerList.size(); i++){
//  					
//  			((PolygonObjektManager)polygonObjektManagerList.get(i)).draw();
//   		}
//  	}
//}
//
//// map stuff
//void keyPressed(){
//  if (key == CODED)
//  {
//    if (keyCode == LEFT){
//      map.tx += 5.0 / map.sc;
//    }
//    else if (keyCode == RIGHT){
//      map.tx -= 5.0 / map.sc;
//    }
//    else if (keyCode == UP){
//      map.ty += 5.0 / map.sc;
//    }
//    else if (keyCode == DOWN){
//      map.ty -= 5.0 / map.sc;
//    }
//  }
//  else if (key == '+' || key == '='){
//    map.sc *= 1.05;
//  }
//  else if (key == '_' || key == '-' && map.sc > 2){
//    map.sc *= 1.0 / 1.05;
//  }
//}
//
//
//void keyReleased(){
//	if (key == 'z' || key == 'Z'){
//    	map.sc = pow(2, map.getZoom());
//  	}
//  	else if (key == ' '){
//	  	map.sc = 2.0;
//	  	map.tx = -128;
//	  	map.ty = -128;
//  	}
//	
//// important PETE stuff	
//	
//	if (key == 'p' || key == 'P') {
//		
//		kindOfPolygonObjekt = 'p';
//		progressPolygonObjekt = true;	
//		polygonObjektManagerList.add(new PolygonObjektManager(kindOfPolygonObjekt));
//	}
//	
//	if (key == 'l' || key == 'L') {
//
//		kindOfPolygonObjekt = 'l';
//		progressPolygonObjekt = true;	
//		polygonObjektManagerList.add(new PolygonObjektManager(kindOfPolygonObjekt));
//	}
//	
//	if (key == 's' || key == 'S') {
//		
//		kindOfPolygonObjekt = 's';
//		progressPolygonObjekt = true;	
//		polygonObjektManagerList.add(new PolygonObjektManager(kindOfPolygonObjekt));
//	}
//	
//	if (key == 'r' || key == 'R') {
//		
//		kindOfPolygonObjekt = 'r';
//		progressPolygonObjekt = true;	
//		polygonObjektManagerList.add(new PolygonObjektManager(kindOfPolygonObjekt));
//	}
//	if (key == 'e' || key == 'E') {
//		
//		progressPolygonObjekt = false;
//		polygonObjektManagerCounter++;	
//		
//	}		
//}
//
//// mapstuff
//// see if we're over any buttons, otherwise tell the map to drag
//void mouseDragged(){
//  	map.mouseDragged();
//}
//
//// mapstuff
//// zoom in or out:
//void mouseWheel(int delta)
//{
//  	if (delta > 0){
//    	map.sc *= 1.05;
//  	}
//  	else if (delta < 0){
//    	map.sc *= 1.0 / 1.05;
//  	}
//}
//
//
//public void mousePressed(){
//	
//// important PETE stuff	
//	if (progressPolygonObjekt){
//		
//		int theMouseX = mouseX;
//		int theMouseY = mouseY;
//				
//		((PolygonObjektManager)polygonObjektManagerList.get(polygonObjektManagerCounter)).addObjekt();		
//	}
//}
