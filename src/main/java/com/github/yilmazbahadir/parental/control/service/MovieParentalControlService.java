package com.github.yilmazbahadir.parental.control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yilmazbahadir.parental.control.exception.ControlLevelNotFoundException;
import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;

@Service
public class MovieParentalControlService implements ParentalControlService {

	private MovieService movieService;

	@Autowired
	public MovieParentalControlService(MovieService movieService) {
		this.movieService = movieService;
	}

	@Override
	public boolean checkAccessAllowed(String movieId, String accessControlLevel)
			throws TitleNotFoundException, TechnicalFailureException, ControlLevelNotFoundException {
		ParentalControlLevel accountAccessControlLevel = ParentalControlLevel.fromName(accessControlLevel);
		ParentalControlLevel movieControlLevel = ParentalControlLevel
				.fromName(this.movieService.getParentalControlLevel(movieId));
		return new ParentalControlLevelComparator().compare(accountAccessControlLevel, movieControlLevel) >= 0;
	}

}
