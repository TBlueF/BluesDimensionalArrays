package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public abstract class ArrayVolume<T> implements Volume<T> {

	private Vector3i min;
	private Vector3i max;
	
	public ArrayVolume(Vector3i min, Vector3i max) {
		this.min = min;
		this.max = max;
	}
	
	int arrayIndex(int x, int y, int z) {
		x -= getMinX();
		y -= getMinY();
		z -= getMinZ();
		return x + getWidth() * y + getWidth() * getHeight() * z;
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
	public int getMinZ() {
		return min.getZ();
	}

	@Override
	public int getMaxZ() {
		return max.getZ();
	}
	
	@Override
	public Vector3i getMin() {
		return min;
	}
	
	@Override
	public Vector3i getMax() {
		return max;
	}
	
}
