package com.br.Meeting.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateOperations {
	
	public static final String formatDate (Date date, String format) {
		Format formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	private static final long ONE_MINUTE_IN_MILLIS = 60000;
	public static final List<String> generateTimes (String startTime, String endTime, int minutesBetween) { 
		
		List<String> times = new ArrayList<String>();
		
		try {
			Date startDateTime = new SimpleDateFormat("HH:mm").parse(startTime);
			Date endDateTime = new SimpleDateFormat("HH:mm").parse(endTime);

			if(startDateTime.after(endDateTime)) {
				//TODO Disparar exceção que a data start é maior que a end
			}
			
			Calendar calendar = Calendar.getInstance();
			
			
			while (true) {
				
				calendar.setTime(startDateTime);
				times.add(StringOperations.addZeroToLeft(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + StringOperations.addZeroToLeft(calendar.get(Calendar.MINUTE)));
				if(startDateTime.compareTo(endDateTime) >= 0) {
					break;
				}
				long t = startDateTime.getTime();
				startDateTime = new Date(t + (minutesBetween * ONE_MINUTE_IN_MILLIS));
				
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return times;
	}
}
