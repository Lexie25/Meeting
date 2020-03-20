package com.br.Meeting.util;

public class StringOperations {
	public static final String addZeroToLeft (String n, int maxStringSize) {
		if(n.length() >= maxStringSize)
			return n;
		
		for (int i = n.length(); i < maxStringSize; i++) {
			n = "0" + n;
		}
		return n;
	}
	
	public static final String addZeroToLeft (String n) {
		return addZeroToLeft(n, 2);
	}
	
	public static final String addZeroToLeft (int n) {
		return addZeroToLeft(String.valueOf(n));
	}
	
	public static final String invertString (String value) {
		String inverted = "";
		for (int i = value.length() - 1; i>= 0; i--) {
			inverted = inverted + value.charAt(i);
		}
		
		return inverted;
	}
}
