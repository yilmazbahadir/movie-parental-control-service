package com.github.yilmazbahadir.parental.control.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.yilmazbahadir.parental.control.exception.ControlLevelNotFoundException;
import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {

	@Mock
	private MovieService movieService;

	@InjectMocks
	private MovieParentalControlService movieParentalControlService;

	// Parental Control Levels: {U, PG, 12, 15, 18}

	@Test
	public void checkAccessAllowed_MovieHasSameControlLevelWithCustomersPref_ShouldReturnTrue()
			throws TitleNotFoundException, TechnicalFailureException, ControlLevelNotFoundException {
		String movieControlLevel = ParentalControlLevel.LEVEL_PG.toString();
		String customerControlLevel = ParentalControlLevel.LEVEL_PG.toString();
		when(movieService.getParentalControlLevel(anyString())).thenReturn(movieControlLevel);

		boolean controlAccess = movieParentalControlService.checkAccessAllowed("anyString", customerControlLevel);
		assertThat(controlAccess, is(equalTo(Boolean.TRUE)));
	}

	@Test
	public void checkAccessAllowed_MovieHasLowerControlLevelThanCustomersPref_ShouldReturnTrue()
			throws TitleNotFoundException, TechnicalFailureException, ControlLevelNotFoundException {
		String movieControlLevel = ParentalControlLevel.LEVEL_12.toString();
		String customerControlLevel = ParentalControlLevel.LEVEL_15.toString();
		when(movieService.getParentalControlLevel(anyString())).thenReturn(movieControlLevel);

		boolean controlAccess = movieParentalControlService.checkAccessAllowed("anyString", customerControlLevel);
		assertThat(controlAccess, is(equalTo(Boolean.TRUE)));

	}

	@Test
	public void checkAccessAllowed_MovieHasHigherControlLevelThanCustomersPref_ShouldReturnFalse()
			throws TitleNotFoundException, TechnicalFailureException, ControlLevelNotFoundException {
		String movieControlLevel = ParentalControlLevel.LEVEL_18.toString();
		String customerControlLevel = ParentalControlLevel.LEVEL_12.toString();
		when(movieService.getParentalControlLevel(anyString())).thenReturn(movieControlLevel);

		boolean controlAccess = movieParentalControlService.checkAccessAllowed("anyString", customerControlLevel);
		assertThat(controlAccess, is(equalTo(Boolean.FALSE)));

	}

	@Test(expected = TitleNotFoundException.class)
	public void checkAccessAllowed_MovieHasHigherControlLevelThanCustomersPref_ExpectTitleNotFoundException()
			throws TitleNotFoundException, TechnicalFailureException, ControlLevelNotFoundException {
		String movieId = "moviewithnotitle";
		String customerControlLevel = ParentalControlLevel.LEVEL_12.toString();
		when(movieService.getParentalControlLevel(movieId)).thenThrow(TitleNotFoundException.class);

		movieParentalControlService.checkAccessAllowed(movieId, customerControlLevel);
	}

	@Test(expected = TechnicalFailureException.class)
	public void checkAccessAllowed_MovieServiceThrowsTechnicalFailure_ExpectTechnicalFailure()
			throws TitleNotFoundException, TechnicalFailureException, ControlLevelNotFoundException {
		String movieId = "moviewithtechnicalfail";
		String customerControlLevel = ParentalControlLevel.LEVEL_12.toString();
		when(movieService.getParentalControlLevel(movieId)).thenThrow(TechnicalFailureException.class);

		movieParentalControlService.checkAccessAllowed(movieId, customerControlLevel);

	}
	@Test(expected = ControlLevelNotFoundException.class)
	public void checkAccessAllowed_ControlLevelNotFound_ExpectControlLevelNotFoundException()
			throws TitleNotFoundException, TechnicalFailureException, ControlLevelNotFoundException {
		String movieId = "controlleveldoesnotexist";
		String customerControlLevel = "A21";
		
		movieParentalControlService.checkAccessAllowed(movieId, customerControlLevel);

	}
}
