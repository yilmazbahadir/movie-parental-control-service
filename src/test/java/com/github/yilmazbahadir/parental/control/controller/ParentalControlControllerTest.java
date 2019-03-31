package com.github.yilmazbahadir.parental.control.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;
import com.github.yilmazbahadir.parental.control.service.MovieParentalControlService;

@RunWith(SpringRunner.class)
@WebMvcTest(ParentalControlController.class)
public class ParentalControlControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	MovieParentalControlService parentalControlService;

	@Test
	public void checkParentalControl_ExpectedInputs_ServiceResultSuccess() throws Exception {
		String movieId = "anyMovie";
		String controlLevel = "anyLevel";

		when(parentalControlService.checkAccessAllowed(anyString(), anyString())).thenReturn(true);

		this.mockMvc.perform(get("/v1/parentalcontrol/movies/" + movieId).param("controlLevel", controlLevel))
				.andExpect(status().isOk()).andExpect(content().string(is(equalTo("true")))).andReturn();
	}

	@Test
	public void checkParentalControl_MovieIsNotFound_ExpectTitleNotFoundException() throws Exception {
		String movieId = "anyMovie";
		String controlLevel = "anyLevel";

		when(parentalControlService.checkAccessAllowed(anyString(), anyString()))
				.thenThrow(new TitleNotFoundException("Title is not found."));

		this.mockMvc.perform(get("/v1/parentalcontrol/movies/" + movieId).param("controlLevel", controlLevel))
				.andDo(print()).andExpect(status().isNotFound())
				.andExpect(content().json(
						"{\"code\":\"404\",\"type\":\"TitleNotFoundException\",\"message\":\"Title is not found.\"}"))
				.andReturn();

	}

	@Test
	public void checkParentalControl_TechnicalFailureOccured_ExpectTechnicalFailureException() throws Exception {
		String movieId = "anyMovie";
		String controlLevel = "anyLevel";

		when(parentalControlService.checkAccessAllowed(anyString(), anyString()))
				.thenThrow(new TechnicalFailureException("A technical error occured."));

		this.mockMvc.perform(get("/v1/parentalcontrol/movies/" + movieId).param("controlLevel", controlLevel))
				.andDo(print()).andExpect(status().is5xxServerError())
				.andExpect(content().json(
						"{\"code\":\"500\",\"type\":\"TechnicalFailureException\",\"message\":\"A technical error occured.\"}"))
				.andReturn();
	}

}
