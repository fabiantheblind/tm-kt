//package sketchTest;
//
//public class PolygonObjektManager {
//	
//	public ArrayList polygonObjektList;
//	public PolygonObjekt polygon;
//	
//	public char kind;
//	
//	
//	public int polygonObjektCounter = 0;
//	
//	PolygonObjektManager(char thatKind){
//		
//		kind = thatKind;
//		polygonObjektList = new ArrayList();
//	}
//
//	public void draw(){
//
////		cases
//		
//		switch(kind){
//  
//			p.case 'p':
//				
//				for (int i = 0; i < polygonObjektList.size(); i++){
//					p.noStroke();
//					p.fill(0,0,255,50);
//					
//					PolygonObjekt po_p = ((PolygonObjekt)polygonObjektList.get(i));
//					po_p.update();
//					
//					p.ellipse(po_p.nowPoint.x, po_p.nowPoint.y,10,10);	
//				}				
//			break;
//
//			case 'l':
//				beginShape();
//				
//				for (int i = 0; i < polygonObjektList.size(); i++){
//					p.stroke(255, 0, 0,50);
//					p.strokeWeight(5);
//					p.noFill();
//					
//					PolygonObjekt po_l = ((PolygonObjekt)polygonObjektList.get(i));
//					po_l.update();
//					
//					p.vertex(po_l.nowPoint.x, po_l.nowPoint.y);	
//				}				
//				endShape();
//			break;
//			
//			case 'r':
//				beginShape();
//				
//				for (int i = 0; i < polygonObjektList.size(); i++){
//					p.stroke(0, 0, 255, 50);
//					p.strokeWeight(5);
//					p.noFill();
//					
//					PolygonObjekt po_r = ((PolygonObjekt)polygonObjektList.get(i));
//					po_r.update();
//					
//					p.vertex(po_r.nowPoint.x, po_r.nowPoint.y);	
//				}				
//				endShape();
//			break;
//			
//			case 's':
//				beginShape();
//				
//				for (int i = 0; i < polygonObjektList.size(); i++){
//					p.noStroke();
//					p.fill(255, 0, 0, 20);
//					
//					PolygonObjekt po_s = ((PolygonObjekt)polygonObjektList.get(i));
//					po_s.update();
//					
//					p.vertex(po_s.nowPoint.x, po_s.nowPoint.y);	
//				}				
//				endShape();
//			break;
//		}
//	}
//
//	public void addObjekt(){
//		
//		polygonObjektList.add(new PolygonObjekt(mouseX,mouseY));
//		polygonObjektCounter++;
//	}	
//
//}
