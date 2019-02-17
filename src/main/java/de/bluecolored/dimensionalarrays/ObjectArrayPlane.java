package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector2i;

public class ObjectArrayPlane<T> extends ArrayPlane<T> {

	private T[] data;
	
	@SuppressWarnings("unchecked")
	public ObjectArrayPlane(Vector2i min, Vector2i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY());
		data = (T[]) new Object[maxArrayIndex + 1];
	}
	
	@Override
	public T get(int x, int y) {
		return data[arrayIndex(x, y)];
	}

	@Override
	public void set(int x, int y, T value) {
		data[arrayIndex(x, y)] = value;
	}
	
}
