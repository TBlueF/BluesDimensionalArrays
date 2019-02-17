package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector2i;

public class FloatArrayPlane extends ArrayPlane<Float> {

	private float[] data;
	
	public FloatArrayPlane(Vector2i min, Vector2i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY());
		data = new float[maxArrayIndex + 1];
	}
	
	@Override
	public Float get(int x, int y) {
		return getFloat(x, y);
	}
	
	public float getFloat(Vector2i pos) {
		return getFloat(pos.getX(), pos.getY());
	}

	public float getFloat(int x, int y) {
		return data[arrayIndex(x, y)];
	}

	@Override
	public void set(int x, int y, Float value) {
		set(x, y, value.floatValue());
	}

	public void set(Vector2i pos, float value) {
		set(pos.getX(), pos.getY(), value);
	}
	
	public void set(int x, int y, float value) {
		data[arrayIndex(x, y)] = value;
	}
	
}
