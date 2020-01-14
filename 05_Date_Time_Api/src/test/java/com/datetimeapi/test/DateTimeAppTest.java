package com.datetimeapi.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.datetimeapi.DateTimeApp;
import com.datetimeapi.DateTimeRepository;

@RunWith(MockitoJUnitRunner.class)
public class DateTimeAppTest {

	
    @Mock
    DateTimeRepository repoMock;
     
    
	@InjectMocks
    DateTimeApp dateTimeApp; 

	@Test
	public void testAdd() {
		
		boolean maturityDate = dateTimeApp.maturityDate("23/12/2019");
		
		assertEquals(true, maturityDate);
		 verify(repoMock, times(1)).getMaturityDate("23/12/2019");
	}
	

}
