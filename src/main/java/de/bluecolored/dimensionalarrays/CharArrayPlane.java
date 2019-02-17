package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector2i;

public class CharArrayPlane extends ArrayPlane<Character> {

	private char[] data;
	
	public CharArrayPlane(Vector2i min, Vector2i max) {
		super(min, max);
		
		int maxArrayIndex = arrayIndex(getMaxX(), getMaxY());
		data = new char[maxArrayIndex + 1];
	}
	
	@Override
	public Character get(int x, int y) {
		return getCharacter(x, y);
	}
	
	public char getCharacter(Vector2i pos) {
		return getCharacter(pos.getX(), pos.getY());
	}

	public char getCharacter(int x, int y) {
		return data[arrayIndex(x, y)];
	}

	@Override
	public void set(int x, int y, Character value) {
		set(x, y, value.charValue());
	}

	public void set(Vector2i pos, char value) {
		set(pos.getX(), pos.getY(), value);
	}
	
	public void set(int x, int y, char value) {
		data[arrayIndex(x, y)] = value;
	}
	
}
