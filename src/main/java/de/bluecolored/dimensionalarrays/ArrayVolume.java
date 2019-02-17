/*
 * The MIT License (MIT)
 * 
 * Copyright (c) Blue <https://www.bluecolored.de>
 * Copyright (c) contributors
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public abstract class ArrayVolume<T> implements Volume<T> {

	private Vector3i min;
	private Vector3i max;
	
	public ArrayVolume(Vector3i min, Vector3i max) {
		this.min = min;
		this.max = max;
	}
	
	int arrayIndex(int x, int y, int z) {
		x -= getMinX();
		y -= getMinY();
		z -= getMinZ();
		return x + getWidth() * y + getWidth() * getHeight() * z;
	}

	@Override
	public int getMinX() {
		return min.getX();
	}

	@Override
	public int getMaxX() {
		return max.getX();
	}

	@Override
	public int getMinY() {
		return min.getY();
	}

	@Override
	public int getMaxY() {
		return max.getY();
	}

	@Override
	public int getMinZ() {
		return min.getZ();
	}

	@Override
	public int getMaxZ() {
		return max.getZ();
	}
	
	@Override
	public Vector3i getMin() {
		return min;
	}
	
	@Override
	public Vector3i getMax() {
		return max;
	}
	
}
