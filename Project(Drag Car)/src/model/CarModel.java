package model;

import lib.IRenderableObject;

public interface CarModel extends IRenderableObject {
	public void setZ(int z);
	public void setMoving(boolean moving);
}
