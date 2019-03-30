package com.github.yilmazbahadir.parental.control.service;

import org.springframework.stereotype.Service;

import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;

@Service
public class MovieParentalControlService implements ParentalControlService {

	private MovieService movieService;
	
	public MovieParentalControlService(MovieService movieService) {
		this.movieService = movieService;
	}
	
	public boolean controlAccess(String movieId, String controlLevel) throws TitleNotFoundException, TechnicalFailureException {
		return controlLevel.equals(this.movieService.getParentalControlLevel(movieId));
	}
	
}
