package com.github.yilmazbahadir.parental.control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.yilmazbahadir.parental.control.exception.ControlLevelNotFoundException;
import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;
import com.github.yilmazbahadir.parental.control.service.ParentalControlService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "v1/parentalcontrol")
@RequestMapping(path = "v1/parentalcontrol")
public class ParentalControlController {

	ParentalControlService parentalControlService;

	@Autowired
	public ParentalControlController(ParentalControlService parentalControlService) {
		this.parentalControlService = parentalControlService;
	}

	@ApiOperation("Controls access on the given movieId according to users parental control level preference")
	@RequestMapping(path = "/movies/{movieId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> movieAccessControl(@PathVariable("movieId") String movieId,
			@RequestParam("controlLevel") String controlLevel)
			throws TitleNotFoundException, TechnicalFailureException, ControlLevelNotFoundException {
		return ResponseEntity.ok(this.parentalControlService.checkAccessAllowed(movieId, controlLevel));
	}
	
	/*
		@RequestMapping(path = "/series/{serieId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
		public void serieAccessControl(@PathVariable("serieId") String movieId,
				@RequestParam String controlLevel)
				throws TitleNotFoundException, TechnicalFailureException, ControlLevelNotFoundException {
			// Can be implemented
		}
	*/
}
