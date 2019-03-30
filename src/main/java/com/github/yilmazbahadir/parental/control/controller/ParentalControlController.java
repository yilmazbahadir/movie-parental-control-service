package com.github.yilmazbahadir.parental.control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;
import com.github.yilmazbahadir.parental.control.service.ParentalControlService;

@RestController
public class ParentalControlController {

	ParentalControlService parentalControlService;

	@Autowired
	public ParentalControlController(ParentalControlService parentalControlService) {
		this.parentalControlService = parentalControlService;
	}

	@RequestMapping(path = "/parentalcontrol/movies/{movieId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean controlAccess(@PathVariable("movieId") String movieId, @RequestParam String controlLevel) throws TitleNotFoundException, TechnicalFailureException {
		return this.parentalControlService.controlAccess(movieId, controlLevel);
	}
}
