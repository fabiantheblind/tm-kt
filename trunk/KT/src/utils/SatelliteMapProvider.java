package utils;

import com.modestmaps.core.Coordinate;
import com.modestmaps.geo.MercatorProjection;
import com.modestmaps.geo.Transformation;
import com.modestmaps.providers.AbstractMapProvider;

public class SatelliteMapProvider extends AbstractMapProvider{
	
	 public SatelliteMapProvider() {
		    super(new MercatorProjection(14, new Transformation(180594.22077720915f, 3774.453801835696f, 375166.17737171974f, 8841.17878024707f, -178558.8702979821f, 136582.11040239647f)));
		  }

		  /* this is where the tile urls/path are assembled, modest maps calls this automatically
		   * you'll get a coordinate for which you should provide the tile url/path with column, row and zoom
		   * as an example, we'll stitch together the tile url for the google terrain provider
		   * it get's a lot trickier when you need to do a more complex translation to get the right tiles
		   */
	public String[] getTileUrls(Coordinate coordinate) {
		   	String url = "../data/Satellite_basemap/" + (int)coordinate.zoom +"-"+ (int)coordinate.column + "-" + (int)coordinate.row  + ".jpg";
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
