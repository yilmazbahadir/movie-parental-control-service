package com.github.yilmazbahadir.parental.control.service;

import java.util.Comparator;

public class ParentalControlLevelComparator implements Comparator<ParentalControlLevel>{

	@Override
	public int compare(ParentalControlLevel o1, ParentalControlLevel o2) {
		return o1.getValue() - o2.getValue();
	}
	
}
