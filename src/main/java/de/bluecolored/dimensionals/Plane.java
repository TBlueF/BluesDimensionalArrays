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

import com.flowpowered.math.vector.Vector2i;

public interface Plane<T> extends Iterable<T> {
	
	T get(int x, int y);
	
	default T get(Vector2i pos) {
		return get(pos.getX(), pos.getY()); 
	}
	
	void set(int x, int y, T value);
	
	default void set(Vector2i pos, T value) {
		set(pos.getX(), pos.getY(), value);
	}
	
	int getMinX();
	
	int getMaxX();
	
	int getMinY();
	
	int getMaxY();
	
	default Vector2i getMin() {
		return new Vector2i(getMinX(), getMinY());
	}
	
	default Vector2i getMax() {
		return new Vector2i(getMaxX(), getMaxY());
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
	
	default Vector2i getSize() {
		return new Vector2i(getWidth(), getHeight());
	}
	
	default boolean contains(int x, int y) {
		return 
				x >= getMinX() &&
				x <= getMaxX() &&
				y >= getMinY() &&
				y <= getMaxY();
	}
	
	default boolean contains(Vector2i pos) {
		return contains(pos.getX(), pos.getY());
	}
	
	default Plane<T> getRelativeView(){
		return getTranslatedView(getMin());
	}
	
	default Plane<T> getTranslatedView(Vector2i translation){
		if (translation.equals(Vector2i.ZERO)) return this;
		return new TranslatedView<>(this, getMin());
	}
	
	/**
	 * Returns an iterator that iterates over all elements in this {@link Plane} in xy-order
	 */
	@Override
	default Iterator<T> iterator() {
		return new Iterator<T>() {
			private int x = getMinX(), y = getMinY();
			
			@Override
			public boolean hasNext() {
				return y <= getMaxY() && x <= getMaxX();
			}

			@Override
			public T next() {
				try {
					T next = get(x, y);
					
					x++;
					if (x > getMaxX()) {
						y++;
						x = getMinX();
					}
					
					return next;
				} catch (IndexOutOfBoundsException ex) {
					throw new NoSuchElementException();
				}
			}
		};
	}
	
	default void filter(PlaneFilter<T> filter) {
		for (int x = getMinX(); x <= getMaxX(); x++) {
			for (int y = getMinY(); y <= getMaxY(); y++) {
					set(x, y, filter.apply(x, y, get(x, y)));
			}
		}
	}
	
	static class TranslatedView<T> implements Plane<T> {

		private Plane<T> viewOrigin;
		private Vector2i translation;
		
		private TranslatedView(Plane<T> viewOrigin, Vector2i translation) {
			this.viewOrigin = viewOrigin;
			this.translation = translation;
		}
		
		@Override
		public T get(int x, int z) {
			x -= translation.getX();
			z -= translation.getY();
			return viewOrigin.get(x, z);
		}

		@Override
		public void set(int x, int z, T value) {
			viewOrigin.set(x, z, value);
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
		public Plane<T> getTranslatedView(Vector2i translation) {
			if (translation.equals(Vector2i.ZERO)) return this;
			
			Vector2i newTranslation = this.translation.add(translation);
			return new TranslatedView<>(viewOrigin, newTranslation);
		}
		
	}
	
}
