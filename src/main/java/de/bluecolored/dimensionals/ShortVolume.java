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

package de.bluecolored.dimensionals;

import com.flowpowered.math.vector.Vector3i;

public interface ShortVolume extends Volume<Short> {
	
	@Override
	default Short get(int x, int y, int z) {
		return getShort(x, y, z);
	}

	default short getShort(Vector3i pos) {
		return getShort(pos.getX(), pos.getY(), pos.getZ());
	}

	short getShort(int x, int y, int z);

	@Override
	default void set(int x, int y, int z, Short value) {
		set(x, y, z, value.shortValue());
	}

	default void set(Vector3i pos, short value) {
		set(pos.getX(), pos.getY(), pos.getZ(), value);
	}
	
	void set(int x, int y, int z, short value);
	
}
