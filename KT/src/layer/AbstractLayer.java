package layer;

import java.util.ArrayList;

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
