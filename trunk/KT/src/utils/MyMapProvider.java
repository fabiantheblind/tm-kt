package utils;
import com.modestmaps.core.Coordinate;
import com.modestmaps.geo.MercatorProjection;
import com.modestmaps.geo.Transformation;
import com.modestmaps.providers.AbstractMapProvider;

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

public class MyMapProvider extends AbstractMapProvider {
	
	public String TopomapType_01 = "_basemap";	// natuerlich
	public String TopomapType_02 = "_CF";		// bunt
	public String TopomapType_03 = "_SW";		// graustufen


  // most likely you will have mercator projection in your map and you don't want to change these
  public MyMapProvider() {
    super(new MercatorProjection(14, new Transformation(51881.88208847385f, -199.2426817165028f, 113564.85550152554f,
	-41.71606638883115f, -51997.60909842081f, 43302.83017095159f)));
  }

  /* this is where the tile urls/path are assembled, modest maps calls this automatically
   * you'll get a coordinate for which you should provide the tile url/path with column, row and zoom
   * as an example, we'll stitch together the tile url for the google terrain provider
   * it get's a lot trickier when you need to do a more complex translation to get the right tiles
   */
  public String[] getTileUrls(Coordinate coordinate) {
	  
    String url = "../data/TopoMapCalifornia"+TopomapType_03+"/"+(int)coordinate.zoom +"-"+ (int)coordinate.column + "-" + (int)coordinate.row  + ".jpg";
    return new String[] { 
      url     };
  }
  
  // unless you doing something fancy, these won't change but need to be there
  public int tileWidth() {
    return 256;
  }
  
  // unless you doing something fancy, these won't change but need to be there
  public int tileHeight() {
    return 256;
  }
}
//
//
//
//
//

