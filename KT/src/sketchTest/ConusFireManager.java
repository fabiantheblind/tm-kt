package sketchTest;

public class ConusFireManager {
	
	ArrayList firesList;

	
	ConusFireManager(){
		firesList = new ArrayList();
	}
	
	public void init(){
		
		XMLElement document;
		XMLElement [] folder;
		XMLElement [] fires;
		XMLElement [] placemarks;
		XMLElement [] points;
		XMLElement [] coordinates;
	//	XMLElement discriptions;
	//	XMLElement styleUrls;
		
		 
		String coordinateRaw;
		String[] coordinateRawSplitter;
		String latLonStringCoordinates;
		
		
			
		document = kmlConusFires.getChildAtIndex(0);
		folder = document.getChildren("Folder");
		for(int j = 0; j<folder.length;j++){
			placemarks=folder[j].getChildren("Placemark");
			XMLElement [] discription = new XMLElement [placemarks.length];
			XMLElement [] styleUrls = new XMLElement [placemarks.length];
			for (int l =0;l<=placemarks.length-1;l++){
				discription=placemarks[l].getChildren("description");
				styleUrls = placemarks[l].getChildren("styleUrl");
				points=placemarks[l].getChildren("Point");
				for (int m =0;m<=points.length-1;m++){
					String discriptionsStr = discription[m].getContent();
					String styleUrlString = styleUrls[m].getContent();
					coordinates=points[m].getChildren("coordinates");
					for (int n =0;n<=coordinates.length-1;n++){
		        			latLonStringCoordinates= coordinates[n].getContent();
		        			coordinateRawSplitter=split(latLonStringCoordinates,",");
						Location myConusFireLoc = new Location(Float.parseFloat(coordinateRawSplitter[1]), Float.parseFloat(coordinateRawSplitter[0]));
				ConusFire myConusFire  = new ConusFire(myConusFireLoc,discriptionsStr,styleUrlString);
					firesList.add(myConusFire);
		    }
		   }
		  }
		 }
		}


public void drawFires(){
	
	for(int i = 0; i<firesList.size();i++){
 		((ConusFire)firesList.get(i)).drawFire();
	}	
}

	
}

