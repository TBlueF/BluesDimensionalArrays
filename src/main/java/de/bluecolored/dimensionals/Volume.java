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

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.flowpowered.math.vector.Vector3i;

public interface Volume<T> extends Iterable<T> {

	T get(int x, int y, int z);
	
	default T get(Vector3i pos) {
		return get(pos.getX(), pos.getY(), pos.getZ());
	}
	
	void set(int x, int y, int z, T value);
	
	default void set(Vector3i pos, T value) {
		set(pos.getX(), pos.getY(), pos.getZ(), value);
	}
	
	int getMinX();
	
	int getMaxX();
	
	int getMinY();
	
	int getMaxY();
	
	int getMinZ();
	
	int getMaxZ();
	
	default Vector3i getMin() {
		return new Vector3i(getMinX(), getMinY(), getMinZ());
	}
	
	default Vector3i getMax() {
		return new Vector3i(getMaxX(), getMaxY(), getMaxZ());
	}
	
	/**
	 * Size in x direction
	 */
	default int getWidth() {
		return getMaxX() - getMinX() + 1;
	}

	/**
	 * Size in y direction
	 */
	default int getHeight() {
		return getMaxY() - getMinY() + 1;
	}

	/**
	 * Size in z direction
	 */
	default int getDepth() {
		return getMaxZ() - getMinZ() + 1;
	}
	
	default Vector3i getSize() {
		return new Vector3i(getWidth(), getHeight(), getDepth());
	}
	
	default boolean contains(int x, int y, int z) {
		return 
				x >= getMinX() &&
				x <= getMaxX() &&
				y >= getMinY() &&
				y <= getMaxY() &&
				z >= getMinZ() &&
				z <= getMaxZ();
	}
	
	default boolean contains(Vector3i pos) {
		return contains(pos.getX(), pos.getY(), pos.getZ());
	}
	
	default Volume<T> getRelativeView(){
		return getTranslatedView(getMin());
	}
	
	default Volume<T> getTranslatedView(Vector3i translation){
		if (translation.equals(Vector3i.ZERO)) return this;
		return new TranslatedView<>(this, getMin());
	}
	
	/**
	 * Returns an iterator that iterates over all elements in this {@link Plane} in xyz-order
	 */
	@Override
	default Iterator<T> iterator() {
		return new Iterator<T>() {
			private int x = getMinX(), y = getMinY(), z = getMinZ();
			
			@Override
			public boolean hasNext() {
				return z <= getMaxZ() && y <= getMaxY() && x <= getMaxX();
			}

			@Override
			public T next() {
				try {
					T next = get(x, y, z);
					
					x++;
					if (x > getMaxX()) {
						y++;
						x = getMinX();
						
						if (y > getMaxY()) {
							z++;
							y = getMinY();
						}
					}
					
					return next;
				} catch (IndexOutOfBoundsException ex) {
					throw new NoSuchElementException();
				}
			}
		};
	}
	
	static class TranslatedView<T> implements Volume<T> {

		private Volume<T> viewOrigin;
		private Vector3i translation;
		
		private TranslatedView(Volume<T> viewOrigin, Vector3i translation) {
			this.viewOrigin = viewOrigin;
			this.translation = translation;
		}
		
		@Override
		public T get(int x, int y, int z) {
			x -= translation.getX();
			y -= translation.getY();
			z -= translation.getZ();
			return viewOrigin.get(x, y, z);
		}

		@Override
		public void set(int x, int y, int z, T value) {
			viewOrigin.set(x, y, z, value);
		}

		@Override
		public int getMinX() {
			return viewOrigin.getMinX() - translation.getX();
		}

		@Override
		public int getMaxX() {
			return viewOrigin.getMaxX() - translation.getX();
		}
		
		@Override
		public int getMinY() {
			return viewOrigin.getMinY() - translation.getY();
		}

		@Override
		public int getMaxY() {
			return viewOrigin.getMaxY() - translation.getY();
		}

		@Override
		public int getMinZ() {
			return viewOrigin.getMinZ() - translation.getZ();
		}

		@Override
		public int getMaxZ() {
			return viewOrigin.getMaxZ() - translation.getZ();
		}
		
		@Override
		public Volume<T> getTranslatedView(Vector3i translation) {
			if (translation.equals(Vector3i.ZERO)) return this;
			
			Vector3i newTranslation = this.translation.add(translation);
			return new TranslatedView<>(viewOrigin, newTranslation);
		}
		
	}
	
}
