package com.datetimeapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateTimeRepository {
	
	ArrayList<Tablet> tabletdata = new ArrayList<Tablet>();

	public DateTimeRepository(String path) {
		Path filePath = Paths.get(path);
	
		try(Stream<String> lines = Files.newBufferedReader(filePath).lines()){
			
			tabletdata = lines.map(TabletUtils::parseTablet).collect(Collectors.toCollection(ArrayList::new));
			List<String> tabNames = tabletdata.stream().
					filter(p->getExpireinMonths(p) <= 3 && getExpireinMonths(p)>=0).
					map(p->p.getName()).collect(Collectors.toList());
			System.out.println(tabNames);
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}	}

	
	public LocalDate getMaturityDate(String dateStr) {
			System.out.println(dateStr);
		LocalDate userDate = null;
		try {
			userDate= LocalDate.parse(dateStr.trim(), DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd-MM-yyyy]"));
		} catch (Exception e) {
			System.out.println("Invalid Date. Please enter date in [dd/MM/yyyy][dd-MM-yyyy] format");
		}
		
		LocalDate dueDate = userDate.plusYears(4).plusMonths(11);
		return dueDate;
	}
	
	public List<String> getTabletList() {
		Path filePath = Paths.get("tablet.txt");
		ArrayList<Tablet> tabletdata = new ArrayList<Tablet>();
		try(Stream<String> lines = Files.newBufferedReader(filePath).lines()){
			
			tabletdata = lines.map(TabletUtils::parseTablet).collect(Collectors.toCollection(ArrayList::new));
			List<String> tabNames = tabletdata.stream().
					filter(p->getExpireinMonths(p) <= 3 && getExpireinMonths(p)>=0).
					map(p->p.getName()).collect(Collectors.toList());
			return tabNames;
			//System.out.println(tabNames);
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getExpireinMonths(Tablet tabinfo){
		return (int) Period.between(LocalDate.now(),tabinfo.getExpireDate()).toTotalMonths();
	}
	public static String getDetailDate(Tablet tabinfo){
		Period detailDate= Period.between(tabinfo.getManufactureDate(),tabinfo.getExpireDate());	
		return detailDate.getYears() + "year/s" + detailDate.getMonths() + "month/s" + detailDate.getDays()+"day/s";
	}
	
	public ArrayList<Tablet> getTabletSortedByExpiredate() {
		ArrayList<Tablet> SortedbynExpDate = tabletdata.stream().sorted(Comparator.comparing(Tablet::getExpireDate))
		.collect(Collectors.toCollection(ArrayList::new));
		return SortedbynExpDate;
	}
	
	public Map<String, Tablet> getTabletManufacturedInCurrentyear() {
		Map<String, Tablet> collect2 = tabletdata.stream().filter(p-> {
			return p.getManufactureDate().getYear() == LocalDate.now().getYear() 
					&& ChronoUnit.DAYS.between(LocalDate.now(), p.getExpireDate()) < 0;
					
		}).collect(Collectors.toMap(Tablet::getManufacturer, p->p));
		String S = collect2.toString();
		return collect2;
	}
	
}
