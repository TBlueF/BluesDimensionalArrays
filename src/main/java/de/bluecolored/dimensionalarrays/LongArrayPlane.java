package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector2i;

public class LongArrayPlane extends ArrayPlane<Long> {

	private long[] data;
	
	public LongArrayPlane(Vector2i min, Vector2i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY());
		data = new long[maxArrayIndex + 1];
	}
	
	@Override
	public Long get(int x, int y) {
		return getLong(x, y);
	}
	
	public long getLong(Vector2i pos) {
		return getLong(pos.getX(), pos.getY());
	}

	public long getLong(int x, int y) {
		return data[arrayIndex(x, y)];
	}

	@Override
	public void set(int x, int y, Long value) {
		set(x, y, value.longValue());
	}

	public void set(Vector2i pos, long value) {
		set(pos.getX(), pos.getY(), value);
	}
	
	public void set(int x, int y, long value) {
		data[arrayIndex(x, y)] = value;
	}
	
}
