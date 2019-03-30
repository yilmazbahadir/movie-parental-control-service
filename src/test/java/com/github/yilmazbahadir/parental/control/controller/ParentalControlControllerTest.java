package com.github.yilmazbahadir.parental.control.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.yilmazbahadir.parental.control.service.MovieParentalControlService;

@RunWith(SpringRunner.class)
@WebMvcTest(ParentalControlController.class)
public class ParentalControlControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	MovieParentalControlService parentalControlService;
	
	@Test
	public void checkParentalControlServiceResultSuccess() throws Exception {
		String movieId = "3idiots";
		String controlLevel = "PG";
		
		when(parentalControlService.controlAccess(movieId, controlLevel)).thenReturn(true);
		
		this.mockMvc.perform(get(String.format("/parentalcontrol/movies/%s?controlLevel=%s", movieId, controlLevel)))
				.andExpect(status().isOk())
				.andExpect(content().string(is(equalTo("true"))))
				.andReturn();
	}

}
