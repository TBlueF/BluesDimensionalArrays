package de.bluecolored.dimensionals;

@FunctionalInterface
public interface VolumeFilter<T> {

	T apply(int x, int y, int z, T currentValue);
	
}
