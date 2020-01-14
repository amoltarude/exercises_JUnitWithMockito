package com.datetimeapi;

import java.time.LocalDate;

public class Tablet {
	private String name;
	private String manufacturer;
	private LocalDate manufactureDate;
	private LocalDate expireDate;
	
	public Tablet(){
		
	}
	
	public Tablet(String name, String manufacturer, LocalDate manufactureDate, LocalDate expireDate) {
		//super();
		this.name = name;
		this.manufacturer = manufacturer;
		this.manufactureDate = manufactureDate;
		this.expireDate = expireDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public LocalDate getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(LocalDate manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	public LocalDate getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public String toString() {
		return "Tablet [name=" + name + ", manufacturer=" + manufacturer + ", manufactureDate=" + manufactureDate
				+ ", expireDate=" + expireDate + "]";
	}
	
	
	
}
