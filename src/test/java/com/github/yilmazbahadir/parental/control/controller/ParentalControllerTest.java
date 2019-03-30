package com.github.yilmazbahadir.parental.control.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ParentalControlController.class)
public class ParentalControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void checkParentalControlServiceResultSuccess() throws Exception {
		String movieId = "3idiots";
		String controlLevel = "PG";
		this.mockMvc.perform(get(String.format("/parentalcontrol/movies/%s?controlLevel=%s", movieId, controlLevel)))
				.andExpect(status().isOk())
				.andExpect(content().string(is(equalTo("OK"))))
				.andReturn();
	}

}
