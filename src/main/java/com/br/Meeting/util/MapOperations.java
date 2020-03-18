package com.br.Meeting.util;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapOperations {
	
	public static final Map<String, Object> convertToMap (Object instanceClass) {
		Map<String, Object> mapReturn = new LinkedHashMap<>();
		Field[] fields = instanceClass.getClass().getDeclaredFields();
		
		for(Field f : fields) {
			f.setAccessible(true);
			Object value = null;
			try {
				value = f.get(instanceClass);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			mapReturn.put(f.getName(), value);
		}
		
		return mapReturn;
	}
}
