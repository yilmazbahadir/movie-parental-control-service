package com.github.yilmazbahadir.parental.control.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParentalControlController {

	@RequestMapping(path = "/parentalcontrol/movies/{movieId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String controlAccess(@PathVariable("movieId") String movieId, @RequestParam String controlLevel) {
		return "OK";
	}
}
