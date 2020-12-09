package pl.icedev.util;

import java.lang.reflect.*;

import sun.misc.Unsafe;

public class Unsupported {
	
	public static final Unsafe unsafe = getUnsafe();
	
	private static Unsafe getUnsafe() {
	    try {
	        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
	        theUnsafe.setAccessible(true);
	        return (Unsafe) theUnsafe.get(null);
	    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	/**
	 * Disabling Integer object cache may increase performance of code that relies on Escape Analysis to work.
	 * */
	public static void disableIntegerCache() {
	    try {
	        Class<?> clazz = Class.forName("java.lang.Integer$IntegerCache");
	        Field cache = clazz.getDeclaredField("cache");
	        Field low = clazz.getDeclaredField("low");
	        Field high = clazz.getDeclaredField("high");

	        Object base = unsafe.staticFieldBase(low);
	        long lowOffset = unsafe.staticFieldOffset(low);
	        long highOffset = unsafe.staticFieldOffset(high);
	        long cacheOffset = unsafe.staticFieldOffset(cache);

	        unsafe.putObjectVolatile(base, cacheOffset, new Integer[0]);
	        unsafe.putIntVolatile(base, lowOffset, Integer.MAX_VALUE);
	        unsafe.putIntVolatile(base, highOffset, Integer.MIN_VALUE);
	    } catch (ClassNotFoundException | NoSuchFieldException | IllegalArgumentException e) {
	        e.printStackTrace();
	    }
	}
	
    public static void setStaticField(Class<?> clazz, String fieldName, Object newValue) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        Object base = unsafe.staticFieldBase(field);
        long offset = unsafe.staticFieldOffset(field);
        unsafe.putObject(base, offset, newValue);
    }
    
    private static long offsetOverrideAccessible = -1;
    
    public static void makeAccessible(AccessibleObject accessible) {
    	try {
	    	if(offsetOverrideAccessible == -1) {
	    		Field overrideField = AccessibleObject.class.getDeclaredField("override");
	    		offsetOverrideAccessible = unsafe.objectFieldOffset(overrideField);
	    	}

			unsafe.putBoolean(accessible, offsetOverrideAccessible, true);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
