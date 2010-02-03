package layer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import main.Main;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import processing.core.PApplet;
import utils.Container;
import utils.Styles;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

public class StationsManager extends AbstractLayer implements Layer{

	ArrayList<Location> locations;
	String typeStr;
	int typeNr;
	
	boolean type[];
	
	public StationsManager(PApplet p,int typeNr) {
		super(p);
		switch(typeNr){
		case 0:
			typeStr = "Police Station";
			break;
		case 1:
			typeStr = "Fire Station";
			break;
		case 2:
			typeStr = "Hospital";
			break;
		}
		this.typeNr = typeNr;
		
	}
	
	public void init(){
		try{
			InputStream fileStream = new FileInputStream("../data/ausschnitt.xls");//Main.class.getResourceAsStream("/data/ausschnitt.xls");
			POIFSFileSystem fs = new POIFSFileSystem(fileStream);
			locations = new ArrayList<Location>();
		
		    HSSFWorkbook wb = new HSSFWorkbook(fs);
		    HSSFSheet sheet = wb.getSheetAt(0);
	
		    Iterator<HSSFRow> rowsIt = sheet.rowIterator();
		    while(rowsIt.hasNext()){
		    	String name=null;
		    	HSSFRow row = rowsIt.next();
		    	HSSFCell cell = row.getCell(2);
		    	name = cell.getStringCellValue();
		    	if(name.contains(typeStr)){
		    		float lon = (float)row.getCell(7).getNumericCellValue();
		    		float lat = (float)row.getCell(6).getNumericCellValue();
		    		Location newLocation = new Location(lat, lon); 
			    	locations.add(newLocation);
		    	}	   	
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void draw(){
		removeSelectedContainer();
		Iterator<Location> locIt = locations.iterator();
		while (locIt.hasNext()) {
			Location location = (Location) locIt.next();
	    	for(Container container : listener){
	    		Point2f point = container.locationPoint(location);
	    		if(!container.isInside((int)point.x, (int)point.y)){
	    			continue;
	    		}
				float x = point.x;
				float y = point.y;
	
				switch (typeNr){
					case 0:
//						p.stroke(0,255,0);
//						p.fill(0,255,0);
			
						break;
					case 1:
//						p.stroke(255,0,0);
//						p.fill(255,0,0);

						break;
					case 2:
//						p.stroke(0,0,255);
//						p.fill(0,0,255);
			
						break;
					}
				p.shapeMode(p.CENTER);
				Styles.policeStation.disableStyle();
				Styles.fireStation.disableStyle();
				Styles.firstAid.disableStyle();


				if (typeNr==0){

					p.fill(Styles.colShadow);
//					p.stroke(Styles.colShadow);
					p.noStroke();
					p.shape(Styles.policeStation,point.x+Styles.shadowOffset,point.y+Styles.shadowOffset,Styles.iconSize,Styles.iconSize);
					p.stroke(Styles.kontur);
					p.strokeWeight(Styles.strokeW);
					p.fill(Styles.policeCol);
					p.shape(Styles.policeStation,x,y,Styles.iconSize,Styles.iconSize);
				}
				if (typeNr==1){
					
					p.fill(Styles.colShadow);
//					p.stroke(Styles.colShadow);
					p.noStroke();
					p.shape(Styles.fireStation,x+Styles.shadowOffset,y+Styles.shadowOffset,Styles.iconSize,Styles.iconSize);
					p.stroke(Styles.kontur);
					p.strokeWeight(Styles.strokeW);
					p.fill(Styles.fireStCol);
					p.shape(Styles.fireStation,x,y,Styles.iconSize,Styles.iconSize);
				}
				if (typeNr==2){
					p.fill(Styles.colShadow);
//					p.stroke(Styles.colShadow);
					p.noStroke();
					p.shape(Styles.firstAid,x+Styles.shadowOffset,y+Styles.shadowOffset,Styles.iconSize,Styles.iconSize);
					p.stroke(Styles.kontur);
					p.strokeWeight(Styles.strokeW);
					
					p.fill(Styles.medicCol);
					p.shape(Styles.firstAid,x,y,Styles.iconSize,Styles.iconSize);
				}
				
	    	}
		}	
	}
}
