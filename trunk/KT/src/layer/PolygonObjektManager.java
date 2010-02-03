package layer;

import java.util.ArrayList;

import processing.core.PApplet;
import utils.PMapContainer;
import utils.Styles;

public class PolygonObjektManager extends AbstractLayer implements Layer{
	
	public ArrayList<PolygonObjekte> polygonObjektList;
	public PolygonObjekte polygon;
	public PMapContainer pmapc;
	
	public char kind;
	
	
	public int polygonObjektCounter = 0;
	
	public PolygonObjektManager(PApplet p,char thatKind, PMapContainer pmapc){
		super(p);
		this.pmapc = pmapc;
		
		kind = thatKind;
		polygonObjektList = new ArrayList<PolygonObjekte>();
	}
	
	public void init(){
		
	}

	public void draw(){

//		cases
		p.smooth();
		switch(kind){
  
			case 'p':
				
				for (int i = 0; i < polygonObjektList.size(); i++){
					p.noStroke();
					p.fill(Styles.pointCol);
					
					PolygonObjekte po_p = polygonObjektList.get(i);
					po_p.update();
					
					p.ellipse(po_p.nowPoint.x, po_p.nowPoint.y,10,10);	
				}				
			break;

			case 'l':
				p.beginShape();
				
				for (int i = 0; i < polygonObjektList.size(); i++){
					p.stroke(Styles.lineCol);
					p.strokeWeight(Styles.strokeW*2.5f);
					p.noFill();
					
					PolygonObjekte po_l = polygonObjektList.get(i);
					po_l.update();
					
					p.vertex(po_l.nowPoint.x, po_l.nowPoint.y);	
				}				
				p.endShape();
			break;
			
			case 'r':
				p.beginShape();
				
				for (int i = 0; i < polygonObjektList.size(); i++){
					p.stroke(Styles.lineCol);
					p.strokeWeight(Styles.strokeW*2.5f);
					p.noFill();
					
					PolygonObjekte po_r = polygonObjektList.get(i);
					po_r.update();
					
					p.vertex(po_r.nowPoint.x, po_r.nowPoint.y);	
				}				
				p.endShape();
			break;
			
			case 's':
				p.beginShape();
				
				for (int i = 0; i < polygonObjektList.size(); i++){
					p.noStroke();
					p.fill(Styles.polyCol);
					p.stroke(Styles.lineCol);
					p.strokeWeight(Styles.strokeW);

					PolygonObjekte po_s = polygonObjektList.get(i);
					po_s.update();
					
					p.vertex(po_s.nowPoint.x, po_s.nowPoint.y);	
				}				
				p.endShape(p.CLOSE);
			break;
		}
	}

	public void addObjekt(int x,int y){
		
		polygonObjektList.add(new PolygonObjekte(x,y,pmapc));
		polygonObjektCounter++;
	}	

}
