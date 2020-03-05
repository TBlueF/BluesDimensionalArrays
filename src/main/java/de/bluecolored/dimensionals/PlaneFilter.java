package de.bluecolored.dimensionals;

@FunctionalInterface
public interface PlaneFilter<T> {

	T apply(int x, int y, T currentValue);
	
}
