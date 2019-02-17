package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector2i;

public class DoubleArrayPlane extends ArrayPlane<Double> {

	private double[] data;
	
	public DoubleArrayPlane(Vector2i min, Vector2i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY());
		data = new double[maxArrayIndex + 1];
	}
	
	@Override
	public Double get(int x, int y) {
		return getDouble(x, y);
	}
	
	public double getDouble(Vector2i pos) {
		return getDouble(pos.getX(), pos.getY());
	}

	public double getDouble(int x, int y) {
		return data[arrayIndex(x, y)];
	}

	@Override
	public void set(int x, int y, Double value) {
		set(x, y, value.doubleValue());
	}

	public void set(Vector2i pos, double value) {
		set(pos.getX(), pos.getY(), value);
	}
	
	public void set(int x, int y, double value) {
		data[arrayIndex(x, y)] = value;
	}
	
}
