///* 
// *   create a custom map provider by extending AbstractMapProvider
// *   (which can be thought of as a provider 'template')
// *
// *   this provider tells modestmaps, where to get map 
// *   tiles (images) for each individual location and zoom
// *
// *   thus, we need to assemble the right urls 
// *   (or file paths if you've created map tiles with zoomify express)
// *
// */
////package sketchTest;
//
//class MyMapProvider 
//extends AbstractMapProvider {
//
//  // most likely you will have mercator projection in your map and you don't want to change these
//  MyMapProvider() {
//    super(new MercatorProjection(14, new Transformation(51881.88208847385, -199.2426817165028, 113564.85550152554,
//	-41.71606638883115, -51997.60909842081, 43302.83017095159)));
//  }
//
//  /* this is where the tile urls/path are assembled, modest maps calls this automatically
//   * you'll get a coordinate for which you should provide the tile url/path with column, row and zoom
//   * as an example, we'll stitch together the tile url for the google terrain provider
//   * it get's a lot trickier when you need to do a more complex translation to get the right tiles
//   */
//  String[] getTileUrls(Coordinate coordinate) {
//    String url = "/data/TopoMapCalifornia_SW/" + (int)coordinate.zoom +"-"+ (int)coordinate.column + "-" + (int)coordinate.row  + ".jpg";
//    return new String[] { 
//      url     };
//  }
//  
//  // unless you doing something fancy, these won't change but need to be there
//  int tileWidth() {
//    return 256;
//  }
//  
//  // unless you doing something fancy, these won't change but need to be there
//  int tileHeight() {
//    return 256;
//  }
//}
//
//
//
//
//

