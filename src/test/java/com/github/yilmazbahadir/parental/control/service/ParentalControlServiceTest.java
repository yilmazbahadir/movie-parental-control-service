package com.github.yilmazbahadir.parental.control.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;


@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {

	@Mock
	private MovieService movieService;
	
	@InjectMocks
	private MovieParentalControlService movieParentalControlService;
	
	@Test
	public void movieWithTheLowerControlLevelThanCustomersPrefSuccess() throws TitleNotFoundException, TechnicalFailureException {
		String movieId = "3idiots";
		String controlLevel = "PG";
		when(movieService.getParentalControlLevel(movieId)).thenReturn(controlLevel);
		
		boolean controlAccess = movieParentalControlService.controlAccess(movieId, controlLevel);
		assertThat(controlAccess, is(Boolean.TRUE));
		
	}
}
