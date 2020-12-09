package pl.icedev.util;

import java.util.AbstractList;
import java.util.Arrays;

public class ListInt extends AbstractList<Integer> {
	protected int[] array;
	protected int size;
	
	public ListInt() {
		this(10);
	}
	
	public ListInt(int capacity) {
		array = new int[capacity];
	}

	@Override
	public Integer get(int index) {
		if(index >= size)
			throw new IndexOutOfBoundsException(index);
		return array[index];
	}
	
	@Override
	public boolean add(Integer e) {
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
	public int[] getInternalArray() {
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
