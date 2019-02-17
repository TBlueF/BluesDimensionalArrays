package de.bluecolored.dimensionalarrays;

import com.flowpowered.math.vector.Vector3i;

public interface Volume<T> {

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
	
	default Volume<T> getRelativeView(){
		return getTranslatedView(getMin());
	}
	
	default Volume<T> getTranslatedView(Vector3i translation){
		if (translation.equals(Vector3i.ZERO)) return this;
		return new TranslatedView<>(this, getMin());
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