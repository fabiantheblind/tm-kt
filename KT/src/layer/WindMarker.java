package layer;

import java.util.ArrayList;

import processing.core.PApplet;
import utils.Container;
import utils.Styles;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

public class WindMarker {
	
	private PApplet p;
	public Location theLocation;
	public float temp_f;
	public float temp_c;
	public String stringTemp_c;
	public float wind_degrees;
	public float wind_mph;
	public String stringWind_string;
	
	public int dropShadow_01 = 4;

	public WindMarker(PApplet p,Location wmTheLocation, float wmTemp_f, float wmTemp_c, String wmStringTemp_c, float wmWind_degrees, float wmWind_mph, String wmStringWind_string){
		this.p = p;
		theLocation = wmTheLocation;	
		temp_f = wmTemp_f;
		temp_c = wmTemp_c;
		stringTemp_c = wmStringTemp_c;
		wind_degrees = wmWind_degrees;
		wind_mph = wmWind_mph;
		stringWind_string = wmStringWind_string;
	
	}
	
	public void drawArrow(ArrayList<Container> listener) {
		//jede Pfeil würd für jeden Container(Listener) extra gezeichnet
		//aber nur wenn er sich gerade in der BoundingBox befindet(isInside).
		for(Container container : listener ){
			Point2f arrowPoint = container.locationPoint(theLocation);
    		if(!container.isInside((int)arrowPoint.x, (int)arrowPoint.y)){
    			continue;
    		}
			float arrowThickness = wind_mph/5;
			p.smooth();
			
			for(int m = 0; m < dropShadow_01; m++){
				
				p.pushMatrix(); 
				p.translate(arrowPoint.x -m, arrowPoint.y -m);
	
				arrowRotate();
	
				if(m < dropShadow_01 -1){
					p.noStroke();				
					p.fill(0,50);				
				}
				else{
					tempColour_02();								
				}
				
				if (wind_mph > 0){
	
	
					p.beginShape();
	
					p.vertex(-10 -arrowThickness, -10 -arrowThickness);
					p.vertex(0, -5);
					p.vertex(10 +arrowThickness, -10 -arrowThickness);
					p.vertex(0 , 15 +arrowThickness);
					p.vertex(-10 -arrowThickness, -10 -arrowThickness);
	
					p.endShape();
				}
				else{
					p.ellipse(0, 0, 15, 15);
				}				
				p.popMatrix();
			}						
		}
	}

	public void writeTheWeather(ArrayList<Container> listener){		

		for(Container container : listener ){
			
			Point2f arrowPoint = container.locationPoint(theLocation);
			
			if(!container.isInside((int)arrowPoint.x, (int)arrowPoint.y)){
				continue;
			}

			p.pushMatrix(); 
			p.translate(arrowPoint.x, arrowPoint.y);
			p.smooth();
			
			p.fill(0,100);
			p.rect(dropShadow_01-5,dropShadow_01-41,75,106);
			p.fill(0);
			p.rect(-5,-41,75,106);

			p.fill(Styles.textColor);
			p.textFont(Styles.calibri24RegBld, 24);
			
			String temp = stringTemp_c +"¡C";
			p.text(temp, 0, -7);

			p.textFont(Styles.calibri14Reg, 14);	
			p.text(stringWind_string,0,0,60,60);	
			
			p.popMatrix();
		 
		}
	}	 



	public void tempColour_01(){

		p.colorMode(PApplet.RGB);

		float myR = 255;
		float myG = 255;
		float myB = 255;

		if(temp_c < 0){	
			myR += temp_c * 4; 
		}else{
			myB -= temp_c * 4;
		}

		p.fill(myR,myG,myB);


	}
	
	public void tempColour_02(){

		p.colorMode(PApplet.HSB);

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

		p.fill(myH,myS,myB);
		// p.stroke(0);		
	}
	
	public void tempColour_03(){

		p.colorMode(PApplet.HSB);

		p.fill(0,0,150);
		p.stroke(0);	
	}

	public void arrowRotate(){

		float rad = PApplet.radians(wind_degrees);
		p.rotate(rad);
	}	 
}