package com.github.yilmazbahadir.parental.control.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;

@Service
public class FakeMovieService implements MovieService {

	private static Map<String, ParentalControlLevel> movieDB = new ConcurrentHashMap<>();

	static {
		movieDB.put("godfather", ParentalControlLevel.A18);
		movieDB.put("schindlerslist", ParentalControlLevel.A15);
		movieDB.put("12angrymen", ParentalControlLevel.A12);
		movieDB.put("thelordoftherings", ParentalControlLevel.PG);
		movieDB.put("pulpfiction", ParentalControlLevel.U);
	}

	@Override
	public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
		ParentalControlLevel controlLevel = movieDB.get(movieId);
		if (controlLevel == null) {
			throw new TitleNotFoundException(movieId + " title is not found in the DB");
		}
		return controlLevel.toString();
	}

}
