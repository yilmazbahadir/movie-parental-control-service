package com.github.yilmazbahadir.parental.control.service;

import com.github.yilmazbahadir.parental.control.exception.ControlLevelNotFoundException;

public enum ParentalControlLevel {

	LEVEL_U(4), 
	LEVEL_PG(8), 
	LEVEL_12(12),
	LEVEL_15(15), 
	LEVEL_18(18);
	
	int age;
	
	private ParentalControlLevel(int age) {
		this.age = age;
	}
	
	public int getValue() {
		return this.age;
	}
	
	public static ParentalControlLevel fromName(String name) throws ControlLevelNotFoundException {
		ParentalControlLevel level = null;
		try {
			if(!name.startsWith("LEVEL_")) {
				name = "LEVEL_" + name;
			}
			level = ParentalControlLevel.valueOf(name);			
		} catch(IllegalArgumentException e) {
			throw new ControlLevelNotFoundException(String.format("ParentalControlLevel name %s is not found.", name));
		}
		return level;				
	}
} 
