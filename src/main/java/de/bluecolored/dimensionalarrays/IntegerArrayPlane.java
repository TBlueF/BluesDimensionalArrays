package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector2i;

public class IntegerArrayPlane extends ArrayPlane<Integer> {

	private int[] data;
	
	public IntegerArrayPlane(Vector2i min, Vector2i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY());
		data = new int[maxArrayIndex + 1];
	}
	
	@Override
	public Integer get(int x, int y) {
		return getInteger(x, y);
	}
	
	public int getInteger(Vector2i pos) {
		return getInteger(pos.getX(), pos.getY());
	}

	public int getInteger(int x, int y) {
		return data[arrayIndex(x, y)];
	}

	@Override
	public void set(int x, int y, Integer value) {
		set(x, y, value.intValue());
	}

	public void set(Vector2i pos, int value) {
		set(pos.getX(), pos.getY(), value);
	}
	
	public void set(int x, int y, int value) {
		data[arrayIndex(x, y)] = value;
	}
	
}
