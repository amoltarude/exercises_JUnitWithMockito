package com.datetimeapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeApp {

	DateTimeRepository repo;

	public boolean maturityDate(String dateStr) {
		 repo.getMaturityDate(dateStr);
		 return true;
	}
	
}
