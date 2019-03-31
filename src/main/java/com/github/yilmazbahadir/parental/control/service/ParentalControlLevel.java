package com.github.yilmazbahadir.parental.control.service;

import com.github.yilmazbahadir.parental.control.exception.ControlLevelNotFoundException;

public enum ParentalControlLevel {

	U(4), 
	PG(8), 
	A12(12),
	A15(15), 
	A18(18);
	
	int age;
	
	private ParentalControlLevel(int age) {
		this.age = age;
	}
	
	public int getValue() {
		return this.age;
	}
	
	public static ParentalControlLevel fromValue(String value) throws ControlLevelNotFoundException {
		int intValue = Integer.parseInt(value);
		for(ParentalControlLevel level : values()) {
			if(intValue == level.getValue()) {
				return level;
			}
		}
		throw new ControlLevelNotFoundException(String.format("ParentalControlLevel(%d) is not found.", value));
	}
	
	public static ParentalControlLevel fromName(String name) throws ControlLevelNotFoundException {
		ParentalControlLevel level = null;
		try {
			level = ParentalControlLevel.valueOf(name);			
		} catch(IllegalArgumentException e) {
			throw new ControlLevelNotFoundException(String.format("ParentalControlLevel name %s is not found.", name));
		}
		return level;				
	}
} 
