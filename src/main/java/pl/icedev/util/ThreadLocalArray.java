package pl.icedev.util;

import java.util.*;
import java.util.function.*;

/**
 * Fast thread-local backed by array, indexed with thread ID.
 * It is not safe to use if you create/discard a lot of threads, because of thread ID inflation.
 * 
 * @author Keray
 */
public class ThreadLocalArray<T> {
	public T[] array;
	
	public ThreadLocalArray(Function<Integer, T[]> arrayConstructor) {
		this.array = arrayConstructor.apply(64);
	}
	
	public ThreadLocalArray(T[] initialArray) {
		this.array = initialArray;
	}
	
	public void initForAllExistingThreads(Supplier<T> func) {
		Set<Thread> allThreads = Thread.getAllStackTraces().keySet();
		
		for(Thread t : allThreads ) {
			int index = (int) t.getId();
			if(index >= array.length) {
				synchronized(this) {
					array = Arrays.copyOf(array, index+1);
				}
			}
			array[index] = func.get();
		}
	}
	
	public T get() {
		int index = (int) Thread.currentThread().getId();
		if(index >= array.length) {
			synchronized(this) {
				array = Arrays.copyOf(array, index+1);
			}
		}
		return array[index];
	}
	
	public T getFast() {
		return array[(int) Thread.currentThread().getId()];
	}
	
	public void set(T v) {
		int index = (int) Thread.currentThread().getId();
		if(index >= array.length) {
			synchronized(this) {
				array = Arrays.copyOf(array, index+1);
			}
		}
		array[index] = v;
	}
}
