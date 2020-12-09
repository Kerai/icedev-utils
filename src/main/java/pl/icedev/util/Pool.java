package pl.icedev.util;

import java.util.Arrays;
import java.util.function.Supplier;

public class Pool<T> {
	private T[] array;
	private int size;
	private Supplier<T> supplier;
	
	@SuppressWarnings("unchecked")
	public Pool(Supplier<T> supplier) {
		this.supplier = supplier;
		array = (T[]) new Object[64];
	}
	
	public T obtain() {
		if(size <= 0) {
			return supplier.get();
		}
		return array[size--];
	}
	
	public void release(T obj) {
		if(array.length < size+1) {
			array = Arrays.copyOf(array, (int) (size * 1.3) + 1);
		}
		
		array[size++] = obj;
	}
}
