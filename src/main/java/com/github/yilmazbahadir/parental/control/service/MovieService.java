package com.github.yilmazbahadir.parental.control.service;

import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;

public interface MovieService {

	String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;

}
