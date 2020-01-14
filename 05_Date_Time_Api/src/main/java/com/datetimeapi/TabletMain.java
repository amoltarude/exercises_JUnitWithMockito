package com.datetimeapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TabletMain {

	public static void main(String[] args) {
		System.out.println("====================================================================================");
		System.out.println("Get the names of Tablet in a List, Which are expiring within 3 months from today?");
		System.out.println("====================================================================================");
		Path filePath = Paths.get("tablet.txt");
		ArrayList<Tablet> tabletdata = new ArrayList<Tablet>();
		try(Stream<String> lines = Files.newBufferedReader(filePath).lines()){
			
			tabletdata = lines.map(TabletUtils::parseTablet).collect(Collectors.toCollection(ArrayList::new));
			List<String> tabNames = tabletdata.stream().
					filter(p->getExpireinMonths(p) <= 3 && getExpireinMonths(p)>=0).
					map(p->p.getName()).collect(Collectors.toList());
			System.out.println(tabNames);
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("====================================================================================");
		System.out.println("Create a List of Tablets, in the ascending order of expiry date?");
		System.out.println("=====================================================================================");
		ArrayList<Tablet> SortedbynExpDate = tabletdata.stream().sorted(Comparator.comparing(Tablet::getExpireDate))
		.collect(Collectors.toCollection(ArrayList::new));
		SortedbynExpDate.forEach(System.out::println);
		
		System.out.println("====================================================================================");
		System.out.println("Create a Map with the tablet name and the period between the manufacture date and expiry date. The period should be a string");
		System.out.println("containing years, months and days (ex: 1 year/s 2 month/s 5 day/s , 3 month/s 5 day/s, 3 year/s ..)");
		System.out.println("======================================================================================");
		Map<String, String> collect = tabletdata.stream().collect(Collectors.toMap(Tablet::getName, TabletMain::getDetailDate));
		collect.forEach((k,v) -> System.out.println(k+" : "+v));
		
		System.out.println("=========================================================================================");
		System.out.println("Create a Map with key as manufacturer and value as list of tablet names which are manufactured in the current year and are expired");
		System.out.println("=========================================================================================");
		Map<String, Tablet> collect2 = tabletdata.stream().filter(p-> {
			return p.getManufactureDate().getYear() == LocalDate.now().getYear() 
					&& ChronoUnit.DAYS.between(LocalDate.now(), p.getExpireDate()) < 0;
					
		}).collect(Collectors.toMap(Tablet::getManufacturer, p->p));
		collect2.forEach((k,v) -> System.out.println(k+" : "+v));
		
		System.out.println("=================================================================================================");
		System.out.println("Given todays date, create a List containing all the dates of working days for next month?(considering sat and sun as non-working days)");
		System.out.println("=========================================================================================================");
		
		LocalDate today = LocalDate.now();
		LocalDate nextMonthDate = today.plusMonths(1);
		System.out.println(today.getDayOfWeek());
		System.out.println(nextMonthDate);
		ArrayList<LocalDate> workingDays = new ArrayList<>();
		System.out.println(nextMonthDate.compareTo(today));
		while(nextMonthDate.compareTo(today) != 0){
			today = today.plusDays(1);
			if (today.getDayOfWeek()==DayOfWeek.SATURDAY){
				continue;
			}
			if (today.getDayOfWeek()==DayOfWeek.SUNDAY){
				continue;
			}
			workingDays.add(today);
		}
		workingDays.forEach(System.out::println);
	}
	
	public static int getExpireinMonths(Tablet tabinfo){
		return (int) Period.between(LocalDate.now(),tabinfo.getExpireDate()).toTotalMonths();
	}
	public static String getDetailDate(Tablet tabinfo){
		Period detailDate= Period.between(tabinfo.getManufactureDate(),tabinfo.getExpireDate());	
		return detailDate.getYears() + "year/s" + detailDate.getMonths() + "month/s" + detailDate.getDays()+"day/s";
	}
}
