package com.github.yilmazbahadir.parental.control.service;

import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;

public interface ParentalControlService {

	public boolean controlAccess(String itemId, String controlLevel) throws TitleNotFoundException, TechnicalFailureException;
	
}
