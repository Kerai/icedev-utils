package pl.icedev.util;

public class Num {
	private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	
	public static String toHex(long v, int digits) {
		char[] chars = new char[digits];
		for(int i=chars.length-1; i >=0; i--) {
			chars[i] = DIGITS[(int) (v & 0xF)];
			v = v >>> 4;
		}
		
		return new String(chars);
	}

	public static String toBinary(long v, int digits) {
		char[] chars = new char[digits];
		for(int i=chars.length-1; i >=0; i--) {
			chars[i] = DIGITS[(int) (v & 0x1)];
			v = v >>> 1;
		}
		
		return new String(chars);
	}
	
	public static String toHex(int v) {
		return toHex(v & 0xFFFFFFFFL, 8);
	}
	
	public static String toHex(short v) {
		return toHex(v & 0xFFFFL, 4);
	}
	
	public static String toHex(long v) {
		return toHex(v, 16);
	}
	
	public static String toBinary(int v) {
		return toBinary(v & 0xFFFFFFFFL, 32);
	}
	
	public static String toBinary(short v) {
		return toBinary(v & 0xFFFFL, 16);
	}
	
	public static String toBinary(long v) {
		return toBinary(v, 64);
	}

}
