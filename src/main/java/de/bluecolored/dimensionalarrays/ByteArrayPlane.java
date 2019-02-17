package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector2i;

public class ByteArrayPlane extends ArrayPlane<Byte> {

	private byte[] data;
	
	public ByteArrayPlane(Vector2i min, Vector2i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY());
		data = new byte[maxArrayIndex + 1];
	}
	
	@Override
	public Byte get(int x, int y) {
		return getByte(x, y);
	}
	
	public byte getByte(Vector2i pos) {
		return getByte(pos.getX(), pos.getY());
	}

	public byte getByte(int x, int y) {
		return data[arrayIndex(x, y)];
	}

	@Override
	public void set(int x, int y, Byte value) {
		set(x, y, value.byteValue());
	}

	public void set(Vector2i pos, byte value) {
		set(pos.getX(), pos.getY(), value);
	}
	
	public void set(int x, int y, byte value) {
		data[arrayIndex(x, y)] = value;
	}
	
}
