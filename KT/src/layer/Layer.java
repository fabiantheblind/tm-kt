package layer;

import utils.Container;


public interface Layer {
	public void init();
	public void draw();
	public void addContainer(Container c);
	public void removeContainer(Container container);
	public void removeSelectedContainer();
}
