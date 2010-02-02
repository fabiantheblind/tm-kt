//package sketchTest;
//
//public class SatelliteMapProvider extends AbstractMapProvider{
//	
//	 SatelliteMapProvider() {
//		    super(new MercatorProjection(14, new Transformation(180594.22077720915, 3774.453801835696, 375166.17737171974, 8841.17878024707, -178558.8702979821, 136582.11040239647)));
//		  }
//
//		  /* this is where the tile urls/path are assembled, modest maps calls this automatically
//		   * you'll get a coordinate for which you should provide the tile url/path with column, row and zoom
//		   * as an example, we'll stitch together the tile url for the google terrain provider
//		   * it get's a lot trickier when you need to do a more complex translation to get the right tiles
//		   */
//			String[] getTileUrls(Coordinate coordinate) {
//		   	String url = "/data/Satellite_basemap/" + (int)coordinate.zoom +"-"+ (int)coordinate.column + "-" + (int)coordinate.row  + ".jpg";
//		   	return new String[] { 
//		     		url     };
//		 	}
//		  
//		  // unless you doing something fancy, these won't change but need to be there
//		  int tileWidth() {
//		    return 256;
//		  }
//		  
//		  // unless you doing something fancy, these won't change but need to be there
//		  int tileHeight() {
//		    return 256;
//		  }
//
//}
