package com.github.yilmazbahadir.parental.control.service;

import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;

public class FakeMovieService implements MovieService {

	public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
		return "PG";
	}

}
