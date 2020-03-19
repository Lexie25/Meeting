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
}
