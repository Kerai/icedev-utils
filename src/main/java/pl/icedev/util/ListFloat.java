package pl.icedev.util;

import java.util.AbstractList;
import java.util.Arrays;

public class ListFloat extends AbstractList<Float> {
	protected float[] array;
	protected int size;
	
	public ListFloat() {
		this(10);
	}
	
	public ListFloat(int capacity) {
		array = new float[capacity];
	}

	@Override
	public Float get(int index) {
		if(index >= size)
			throw new IndexOutOfBoundsException(index);
		return array[index];
	}
	
	@Override
	public boolean add(Float e) {
		ensureCapacity(size+1);
		array[size++] = e;
		return true;
	}

	private void ensureCapacity(int size) {
		if(array.length < size) {
			array = Arrays.copyOf(array, (int) (array.length * 1.3f) + 1);
		}
	}
	
	/** only use this if you know what you are doing */
	public float[] getInternalArray() {
		return array;
	}
	
	public void trim() {
		if(size != array.length) {
			array = Arrays.copyOf(array, size);
		}
	}

	@Override
	public int size() {
		return size;
	}

}
