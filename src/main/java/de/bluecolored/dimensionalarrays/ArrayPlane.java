package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector2i;

public abstract class ArrayPlane<T> implements Plane<T> {

	private Vector2i min;
	private Vector2i max;
	
	public ArrayPlane(Vector2i min, Vector2i max) {
		this.min = min;
		this.max = max;
	}
	
	int arrayIndex(int x, int y) {
		x -= getMinX();
		y -= getMinY();
		return x + getWidth() * y;
	}

	@Override
	public int getMinX() {
		return min.getX();
	}

	@Override
	public int getMaxX() {
		return max.getX();
	}

	@Override
	public int getMinY() {
		return min.getY();
	}

	@Override
	public int getMaxY() {
		return max.getY();
	}
	
	@Override
	public Vector2i getMin() {
		return min;
	}
	
	@Override
	public Vector2i getMax() {
		return max;
	}
	
}
