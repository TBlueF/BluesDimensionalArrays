package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public class IntegerArrayVolume extends ArrayVolume<Integer> {

	private int[] data;
	
	public IntegerArrayVolume(Vector3i min, Vector3i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY(), getMaxZ());
		data = new int[maxArrayIndex + 1];
	}
	
	@Override
	public Integer get(int x, int y, int z) {
		return getInteger(x, y, z);
	}

	public int getInteger(Vector3i pos) {
		return getInteger(pos.getX(), pos.getY(), pos.getZ());
	}

	public int getInteger(int x, int y, int z) {
		return data[arrayIndex(x, y, z)];
	}

	@Override
	public void set(int x, int y, int z, Integer value) {
		set(x, y, z, value.intValue());
	}

	public void set(Vector3i pos, int value) {
		set(pos.getX(), pos.getY(), pos.getZ(), value);
	}
	
	public void set(int x, int y, int z, int value) {
		data[arrayIndex(x, y, z)] = value;
	}
	
}
