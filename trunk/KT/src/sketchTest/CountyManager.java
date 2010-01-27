//package sketchTest;
//
//public class CountyManager {
//	
//	ArrayList countyList;
//	ArrayList countyPoints;
//
//	
//	CountyManager(){
//		countyList = new ArrayList();
//		countyPoints= new ArrayList();
//	}
//	
//	public void init(){
//		
//		XMLElement document;
//		XMLElement folder;
//		XMLElement [] fires;
//		XMLElement [] placemarks;
//		XMLElement [] name;
//		XMLElement [] points;
//		XMLElement [] coordinates;
//		XMLElement [] polygons;
//		XMLElement [] outerBoundaryIs;
//		XMLElement [] linearRing;
//		String coordinateRaw;
//		String[] coordinateRawSplitter;
//		String latLonStringCoordinates;
//		
//		
//		document = kmlCaCountysPoDes.getChildAtIndex(0);
//		folder = document.getChildAtIndex(0);
//		placemarks=folder.getChildren("Placemark");// Fill Array Placemark with all Elements called <Placemark> in Folder[2] 
//	    
//	 //    println("Folder cointains \""+placemarks.length+"\" Placemarks");
//	    for (int k = 0; k<=placemarks.length-1;k++){
//	      polygons=placemarks[k].getChildren("Polygon");// Fill Array Placemark with all Elements called <Placemark> in Folder[2]  
//	      for(int m = 0; m<=polygons.length-1;m++){
//	        outerBoundaryIs=polygons[m].getChildren("outerBoundaryIs");
//	        for(int n = 0; n<=outerBoundaryIs.length-1;n++){
//	          linearRing=outerBoundaryIs[n].getChildren("LinearRing");
//	          for(int o=0;o<=linearRing.length-1;o++){
//	            coordinates=linearRing[0].getChildren("coordinates");
//	            String [] strCoordinats=new String [coordinates.length];
//	            String [] strCoordinatsSplit;
//	            //float[] lonLatCoordtoXY = new float[(Coordinates.length)*2];
//	            for (int p=0;p<=coordinates.length-1;p++){
//	              strCoordinats[p]=coordinates[p].getContent();
//	              strCoordinats[p] = strCoordinats[p].replaceAll("\n", "");
//	              strCoordinats[p] = strCoordinats[p].replaceAll(",0", ",");
//	              strCoordinats[p]=trim(strCoordinats[p]);
//	    strCoordinatsSplit=split(strCoordinats[p],",");
//	Location [] myCountyLoc = new Location [strCoordinatsSplit.length];
//	              for (int q = 0; q<=(strCoordinatsSplit.length-4);q=q+2){       
//	                  //float lon = CoordStrToLon(strCoordinatsSplit[q]);
//	                    //  float lat = CoordStrToLat(strCoordinatsSplit[q]);
// myCountyLoc[q] = new Location(Float.parseFloat(strCoordinatsSplit[q+1]), Float.parseFloat(strCoordinatsSplit[q]));
//				countyPoints.add(myCountyLoc[q]);
//	             }
//			County myCounty  = new County((ArrayList)countyPoints);
//			countyList.add(myCounty);
//	
//	            }
//	          }
//	        }
//	      }
//	    }
//	  
//	}
//
//
//public void drawCountys(){
//        
//	for(int i = 0; i<countyList.size();i++){
// 		((County)countyList.get(i)).drawCounty();
//	}	
//}
//
//	
//}

