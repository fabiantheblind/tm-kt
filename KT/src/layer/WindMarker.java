package layer;

import java.util.ArrayList;

import processing.core.PApplet;
import utils.Container;
import utils.PMapContainer;
import utils.Styles;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

import de.fhpotsdam.pmaps.PMap;

public class WindMarker {
	
	private PApplet p;
	public Location theLocation;
	public float temp_f;
	public float temp_c;
	public String stringTemp_c;
	public float wind_degrees;
	public float wind_mph;
	public String stringWind_string;
	public boolean isSpezial = false;
	
	public int dropShadow_01 = 2;

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
		//jede Pfeil w�rd f�r jeden Container(Listener) extra gezeichnet
		//aber nur wenn er sich gerade in der BoundingBox befindet(isInside).
		for(Container container : listener ){
			Point2f arrowPoint = container.locationPoint(theLocation);
    		if(!container.isInside((int)arrowPoint.x, (int)arrowPoint.y)){
    			continue;
    		}
    		
			p.smooth();
			p.noStroke();

			
			float arrowThickness = wind_mph/5;
			
			
				
//				if(p.mouseX > arrowPoint.x - 10 && p.mouseX < arrowPoint.x + 10 && p.mouseY > arrowPoint.y - 10 && p.mouseY < arrowPoint.y + 10){
				if(isSpezial){	
					
						
						p.pushMatrix(); 
						p.translate(arrowPoint.x, arrowPoint.y);
						
						p.fill(Styles.colShadow);
						p.rect((-5 + Styles.shadowOffset ),(-41 + Styles.shadowOffset),75,106);
						tempColour_01();								
						p.rect(-5,-41,75,106);

						p.fill(33);
						p.textFont(Styles.calibri24RegBld, 24);
						
						String temp = stringTemp_c +"�C";
						p.text(temp, 0, -7);

						p.textFont(Styles.calibri14Reg, 14);	
						p.text(stringWind_string,0,0,60,60);	
						
						p.popMatrix();
					
				}
				
				else{
					
												
					
				
					if (wind_mph > 0){
						
						
						for(int k = 0; k < 2; k++){
							
							p.pushMatrix(); 
							
							p.translate(arrowPoint.x -(k*Styles.shadowOffset), arrowPoint.y -(k*Styles.shadowOffset));
							arrowRotate();

			
							if(k<1){
								
								p.fill(Styles.colShadow);

							}
							else{
								tempColour_01();

							}

							p.beginShape();
	
							p.vertex(-10 -arrowThickness, -10 -arrowThickness);
							p.vertex(0, -5);
							p.vertex(10 +arrowThickness, -10 -arrowThickness);
							p.vertex(0 , 15 +arrowThickness);
							p.vertex(-10 -arrowThickness, -10 -arrowThickness);
	
							p.endShape();
							
							p.popMatrix();
						}
					}
					else{
						p.pushMatrix(); 
						p.translate(arrowPoint.x, arrowPoint.y);
						p.fill(Styles.colShadow);				
						p.ellipse(0 + Styles.shadowOffset , 0 + Styles.shadowOffset , 15, 15);
						
						tempColour_01();
						p.ellipse(0, 0, 15, 15);
						p.popMatrix();
					}
					
				
				
			}						
		}
	}

	public void writeTheWeather(ArrayList<Container> listener){		

// wird nicht mehr gebraucht, da sie jetzt in der drawArrow() integriert ist.
//		for(Container container : listener ){
//			
//			Point2f arrowPoint = container.locationPoint(theLocation);
//			
//			if(!container.isInside((int)arrowPoint.x, (int)arrowPoint.y)){
//				continue;
//			}
//
//			p.pushMatrix(); 
//			p.translate(arrowPoint.x, arrowPoint.y);
//			p.smooth();
//			
//			p.noStroke();
//			p.fill(0,100);
//			p.rect(dropShadow_01-5,dropShadow_01-41,75,106);
//			p.fill(0);
//			p.rect(-5,-41,75,106);
//
//			p.fill(Styles.textColor);
//			p.textFont(Styles.calibri24RegBld, 24);
//			
//			String temp = stringTemp_c +"�C";
//			p.text(temp, 0, -7);
//
//			p.textFont(Styles.calibri14Reg, 14);	
//			p.text(stringWind_string,0,0,60,60);	
//			
//			p.popMatrix();
//		 
//		}
	}	 



	public void tempColour_01(){


		p.colorMode(PApplet.RGB);
		
		float myR = 255;
		float myG = 255;
		float myB = 150;
		
		if(temp_c < 0){	
			myR += temp_c *7;
			myG += temp_c *7;
			myB -= temp_c*2;
			 
		}else{
			myG -= temp_c *7;
			myB -= temp_c * 5;
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
	
	public void checkForSpezial(int x,int y,PMapContainer container){
		Point2f arrowPoint = container.locationPoint(theLocation);
	
		if(x > arrowPoint.x - 10 && x < arrowPoint.x + 10 && y > arrowPoint.y - 10 && y < arrowPoint.y + 10){
			isSpezial = !isSpezial;
		}
			
	}
}