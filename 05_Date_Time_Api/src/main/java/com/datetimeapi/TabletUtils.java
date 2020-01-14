package com.datetimeapi;
//- Create a class TabletUtils with a static method 
//getExpiredTablets(String filename, String manufacturer). 
//This method should return a Map with key as Tablet name and 
//value as expiry date of tablets, which are already expired and from a given manufacturer. 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TabletUtils {

	public static Map<String, LocalDate> getExpiredTablets(String filename, String manufacturer){
		Path filePath = Paths.get("data","tablet.txt");
		Map<String, LocalDate> collect = new HashMap<String, LocalDate>();
		try(Stream<String> lines = Files.newBufferedReader(filePath).lines()){
		collect = lines.map(TabletUtils::parseTablet).filter(t->t.getExpireDate().compareTo(LocalDate.now())<0 && t.getManufacturer().equals(manufacturer))
		 .collect(Collectors.toMap(Tablet::getName, Tablet::getExpireDate));
			//System.out.println(list);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return collect;
	}
	public static Tablet parseTablet(String line){
		String[] data = line.split(",");
		return new Tablet(data[0].trim(), data[1].trim(),LocalDate.parse(data[2].trim(), DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd-MM-yyyy]")),
				LocalDate.parse(data[3].trim(), DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd-MM-yyyy]")));
	}
}
