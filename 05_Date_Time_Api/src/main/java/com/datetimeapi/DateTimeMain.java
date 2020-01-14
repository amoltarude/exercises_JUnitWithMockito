package com.datetimeapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateTimeMain {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);	
		System.out.println("Print the maturity Date in format <dd-mmm-yyyy>");
			System.out.println("Please enter the date");
			String Date = myObj.nextLine();
			LocalDate userDate = null;
			try {
				userDate= LocalDate.parse(Date.trim(), DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd-MM-yyyy]"));
			} catch (Exception e) {
				System.out.println("Invalid Date. Please enter date in [dd/MM/yyyy][dd-MM-yyyy] format");
			}
			
			LocalDate dueDate = userDate.plusYears(4).plusMonths(11);
			
			System.out.println("the maturity Date :" + dueDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
			
	}

}
