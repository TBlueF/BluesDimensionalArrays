package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector2i;

public class ShortArrayPlane extends ArrayPlane<Short> {

	private short[] data;
	
	public ShortArrayPlane(Vector2i min, Vector2i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY());
		data = new short[maxArrayIndex + 1];
	}
	
	@Override
	public Short get(int x, int y) {
		return getShort(x, y);
	}
	
	public short getShort(Vector2i pos) {
		return getShort(pos.getX(), pos.getY());
	}

	public short getShort(int x, int y) {
		return data[arrayIndex(x, y)];
	}

	@Override
	public void set(int x, int y, Short value) {
		set(x, y, value.shortValue());
	}

	public void set(Vector2i pos, short value) {
		set(pos.getX(), pos.getY(), value);
	}
	
	public void set(int x, int y, short value) {
		data[arrayIndex(x, y)] = value;
	}
	
}
