package sketchTest;

public class WindMarker {
	
	public Location theLocation;
	public float temp_f;
	public float temp_c;
	public float wind_degrees;
	public float wind_mph;
	
	public int dropShadow_01 = 4;

	WindMarker(Location wmTheLocation, float wmTemp_f, float wmTemp_c, float wmWind_degrees, float wmWind_mph){

		theLocation = wmTheLocation;	
		temp_f = wmTemp_f;
		temp_c = wmTemp_c;
		wind_degrees = wmWind_degrees;
		wind_mph = wmWind_mph;
	
	}
	
	public void drawArrow() {

		Point2f arrowPoint = map.locationPoint(theLocation);
		
		float arrowThickness = wind_mph/5;
		smooth();
		
		for(int m = 0; m < dropShadow_01; m++){
			
			pushMatrix(); 
			translate(arrowPoint.x -m, arrowPoint.y -m);

			arrowRotate();

			if(m < dropShadow_01 -1){
				stroke(0,50);				
				fill(0,50);				
			}
			else{
				tempColour_02();								
			}
			
			if (wind_mph > 0){

				strokeWeight(2);

				beginShape();

				vertex(-10 -arrowThickness, -10 -arrowThickness);
				vertex(0, -5);
				vertex(10 +arrowThickness, -10 -arrowThickness);
				vertex(0 , 15 +arrowThickness);
				vertex(-10 -arrowThickness, -10 -arrowThickness);

				endShape();
			}
			else{
				ellipse(0, 0, 15, 15);
			}				
			popMatrix();
		}						

	}

	public void writeTheWeather(){		
	
		Point2f arrowPoint = map.locationPoint(theLocation);	
			
		textFont(regular_18, 18);
		
		for(int n = 0; n < dropShadow_01; n++){
			
			pushMatrix(); 
			translate(arrowPoint.x -n, arrowPoint.y -n);
			smooth();
			
			if(n < dropShadow_01 -1){
				
				fill(0,50);
								
			}
			else{
			
				tempColour_02();
								
			}

			text(temp_c, 0, 0);	
			
			popMatrix();
		}
		 
	}	 

	public void tempColour_01(){

		colorMode(RGB);

		float myR = 255;
		float myG = 255;
		float myB = 255;

		if(temp_c < 0){	
			myR += temp_c * 4; 
		}else{
			myB -= temp_c * 4;
		}

		fill(myR,myG,myB);


	}
	
	public void tempColour_02(){

		colorMode(HSB);

		float myH = 255;
		float myS = 255;
		float myB = 255;

		if(temp_c < 0){	
			myH = 180; 
			myS = temp_c * 5;
		}else{
			myH = 10;
			myS = temp_c * 5;
			
		}

		fill(myH,myS,myB);
		stroke(0);		
	}

	public void arrowRotate(){

		float rad = radians(wind_degrees);
		rotate(rad);
	}	 
}