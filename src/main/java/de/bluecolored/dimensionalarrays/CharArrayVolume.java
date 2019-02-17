package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public class CharArrayVolume extends ArrayVolume<Character> {

	private char[] data;
	
	public CharArrayVolume(Vector3i min, Vector3i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY(), getMaxZ());
		data = new char[maxArrayIndex + 1];
	}
	
	@Override
	public Character get(int x, int y, int z) {
		return getCharacter(x, y, z);
	}

	public char getCharacter(Vector3i pos) {
		return getCharacter(pos.getX(), pos.getY(), pos.getZ());
	}

	public char getCharacter(int x, int y, int z) {
		return data[arrayIndex(x, y, z)];
	}

	@Override
	public void set(int x, int y, int z, Character value) {
		set(x, y, z, value.charValue());
	}

	public void set(Vector3i pos, char value) {
		set(pos.getX(), pos.getY(), pos.getZ(), value);
	}
	
	public void set(int x, int y, int z, char value) {
		data[arrayIndex(x, y, z)] = value;
	}
	
}
