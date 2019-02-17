package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public class ByteArrayVolume extends ArrayVolume<Byte> {

	private byte[] data;
	
	public ByteArrayVolume(Vector3i min, Vector3i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY(), getMaxZ());
		data = new byte[maxArrayIndex + 1];
	}
	
	@Override
	public Byte get(int x, int y, int z) {
		return getByte(x, y, z);
	}

	public byte getByte(Vector3i pos) {
		return getByte(pos.getX(), pos.getY(), pos.getZ());
	}

	public byte getByte(int x, int y, int z) {
		return data[arrayIndex(x, y, z)];
	}

	@Override
	public void set(int x, int y, int z, Byte value) {
		set(x, y, z, value.byteValue());
	}

	public void set(Vector3i pos, byte value) {
		set(pos.getX(), pos.getY(), pos.getZ(), value);
	}
	
	public void set(int x, int y, int z, byte value) {
		data[arrayIndex(x, y, z)] = value;
	}
	
}
