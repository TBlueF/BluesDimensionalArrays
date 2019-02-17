package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public class ObjectArrayVolume<T> extends ArrayVolume<T> {

	private T[] data;
	
	@SuppressWarnings("unchecked")
	public ObjectArrayVolume(Vector3i min, Vector3i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY(), getMaxZ());
		data = (T[]) new Object[maxArrayIndex + 1];
	}
	
	@Override
	public T get(int x, int y, int z) {
		return data[arrayIndex(x, y, z)];
	}
	
	@Override
	public void set(int x, int y, int z, T value) {
		data[arrayIndex(x, y, z)] = value;
	}
	
}
