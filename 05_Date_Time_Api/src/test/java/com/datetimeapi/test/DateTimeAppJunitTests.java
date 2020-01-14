package com.datetimeapi.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datetimeapi.DateTimeApp;
import com.datetimeapi.DateTimeRepository;
import com.datetimeapi.Tablet;

public class DateTimeAppJunitTests {

	static DateTimeRepository dateTimeApp;
	static LocalDate todaydate;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Beginning of Test execution"); 
		todaydate = LocalDate.now();
		dateTimeApp = new DateTimeRepository("tablet.txt");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("End of Test Execution");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Beginning of new test method");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("End of new test method");	
	}
	
	@Test
	public void testmaturityDate() {
		
		LocalDate maturityDate = dateTimeApp.getMaturityDate("23/12/2019");
		
		assertEquals(LocalDate.parse("23/12/2019", DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd-MM-yyyy]")).plusYears(4).plusMonths(11), maturityDate);
	}
	
	@Test
	public void testTabletList() {
		List<String> tabletList = dateTimeApp.getTabletList();
		assertEquals("[Saradon]", tabletList.toString());
	}
	
	@Test
	public void testTabletSortedByExpiredate() {
		ArrayList<Tablet> tabletSortedByExpiredate = dateTimeApp.getTabletSortedByExpiredate();
		assertEquals("Tablet [name=nice, manufacturer=gsk, manufactureDate=2014-04-11, expireDate=2018-03-12]",
				tabletSortedByExpiredate.get(0).toString());
	}
	
	@Test
	public void testTabletManufacturedInCurrentyear() {
		Map<String, Tablet> tabletManufacturedInCurrentyear = dateTimeApp.getTabletManufacturedInCurrentyear();
		assertEquals("{gsk=Tablet [name=nice 250, manufacturer=gsk, manufactureDate=2019-04-11, expireDate=2019-08-12]}",
				tabletManufacturedInCurrentyear.toString());
	}

}
