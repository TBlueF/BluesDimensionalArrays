package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public class ShortArrayVolume extends ArrayVolume<Short> {

	private short[] data;
	
	public ShortArrayVolume(Vector3i min, Vector3i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY(), getMaxZ());
		data = new short[maxArrayIndex + 1];
	}
	
	@Override
	public Short get(int x, int y, int z) {
		return getShort(x, y, z);
	}

	public short getShort(Vector3i pos) {
		return getShort(pos.getX(), pos.getY(), pos.getZ());
	}

	public short getShort(int x, int y, int z) {
		return data[arrayIndex(x, y, z)];
	}

	@Override
	public void set(int x, int y, int z, Short value) {
		set(x, y, z, value.shortValue());
	}

	public void set(Vector3i pos, short value) {
		set(pos.getX(), pos.getY(), pos.getZ(), value);
	}
	
	public void set(int x, int y, int z, short value) {
		data[arrayIndex(x, y, z)] = value;
	}
	
}
