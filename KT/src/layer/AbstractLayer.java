package layer;

import java.util.ArrayList;

/**
 * OberKlasse für die Layer(bisher alles Manager).
 * Steuert das Verwalten der Container(Listener).
 * */

import processing.core.PApplet;

import utils.Container;

public class AbstractLayer {
	protected ArrayList<Container> listener;
	protected PApplet p;
	
	public AbstractLayer(PApplet p){
		this.p = p;
		this.listener = new ArrayList<Container>();
	}
	
	public void addContainer(Container container){
		listener.add(container);
	}
}
