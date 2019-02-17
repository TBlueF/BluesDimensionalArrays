package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector2i;

public interface Plane<T> {
	
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
	
	default Plane<T> getRelativeView(){
		return getTranslatedView(getMin());
	}
	
	default Plane<T> getTranslatedView(Vector2i translation){
		if (translation.equals(Vector2i.ZERO)) return this;
		return new TranslatedView<>(this, getMin());
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
