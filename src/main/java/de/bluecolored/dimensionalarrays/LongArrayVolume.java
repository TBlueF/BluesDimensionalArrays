package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public class LongArrayVolume extends ArrayVolume<Long> {

	private long[] data;
	
	public LongArrayVolume(Vector3i min, Vector3i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY(), getMaxZ());
		data = new long[maxArrayIndex + 1];
	}
	
	@Override
	public Long get(int x, int y, int z) {
		return getLong(x, y, z);
	}

	public long getLong(Vector3i pos) {
		return getLong(pos.getX(), pos.getY(), pos.getZ());
	}

	public long getLong(int x, int y, int z) {
		return data[arrayIndex(x, y, z)];
	}

	@Override
	public void set(int x, int y, int z, Long value) {
		set(x, y, z, value.longValue());
	}

	public void set(Vector3i pos, long value) {
		set(pos.getX(), pos.getY(), pos.getZ(), value);
	}
	
	public void set(int x, int y, int z, long value) {
		data[arrayIndex(x, y, z)] = value;
	}
	
}
