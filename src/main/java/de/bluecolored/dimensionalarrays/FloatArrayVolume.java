package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public class FloatArrayVolume extends ArrayVolume<Float> {

	private float[] data;
	
	public FloatArrayVolume(Vector3i min, Vector3i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY(), getMaxZ());
		data = new float[maxArrayIndex + 1];
	}
	
	@Override
	public Float get(int x, int y, int z) {
		return getFloat(x, y, z);
	}

	public float getFloat(Vector3i pos) {
		return getFloat(pos.getX(), pos.getY(), pos.getZ());
	}

	public float getFloat(int x, int y, int z) {
		return data[arrayIndex(x, y, z)];
	}

	@Override
	public void set(int x, int y, int z, Float value) {
		set(x, y, z, value.floatValue());
	}

	public void set(Vector3i pos, float value) {
		set(pos.getX(), pos.getY(), pos.getZ(), value);
	}
	
	public void set(int x, int y, int z, float value) {
		data[arrayIndex(x, y, z)] = value;
	}
	
}
