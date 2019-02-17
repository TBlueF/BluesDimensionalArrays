package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public class DoubleArrayVolume extends ArrayVolume<Double> {

	private double[] data;
	
	public DoubleArrayVolume(Vector3i min, Vector3i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY(), getMaxZ());
		data = new double[maxArrayIndex + 1];
	}
	
	@Override
	public Double get(int x, int y, int z) {
		return getDouble(x, y, z);
	}

	public double getDouble(Vector3i pos) {
		return getDouble(pos.getX(), pos.getY(), pos.getZ());
	}

	public double getDouble(int x, int y, int z) {
		return data[arrayIndex(x, y, z)];
	}

	@Override
	public void set(int x, int y, int z, Double value) {
		set(x, y, z, value.doubleValue());
	}

	public void set(Vector3i pos, double value) {
		set(pos.getX(), pos.getY(), pos.getZ(), value);
	}
	
	public void set(int x, int y, int z, double value) {
		data[arrayIndex(x, y, z)] = value;
	}
	
}
