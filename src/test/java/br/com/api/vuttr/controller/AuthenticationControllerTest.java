package br.com.api.vuttr.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.api.vuttr.model.User;
import br.com.api.vuttr.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AuthenticationControllerTest {
	
	private String username;
	private String jsonContent;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;
	
	@BeforeAll
	void init() {
	    username = "26d681d8c76a4bdfb7bb5086178a5f53";	
	    String password = "$2a$10$WLNsc39W32dJL0GouG9UMuxPW9gyBgOC1XzylxkyypkiLVdZakCQ.";
	    jsonContent = "{\"username\":\""+ username +"\",\"password\":\"" + password + "\"}";
	}
	
	@AfterAll
	void end() {
		User user = userRepository.findByUsername(username);
		userRepository.delete(user);	
	}

	@Test
	public void mustCreateUserAndReturn200() throws Exception {

		mockMvc
			.perform(MockMvcRequestBuilders
				.post("/subs")
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	
	}

	@Test
	public void mustAuthenticateUserAndReturn200() throws Exception {

		mockMvc
			.perform(MockMvcRequestBuilders
				.post("/auth")
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void mustReturn400WhenAuthenticationFails() throws Exception {
		String json = "{\"username\":\"invalid\",\"password\":\"1nv@l!d\"}";

		mockMvc
			.perform(MockMvcRequestBuilders
				.post("/auth")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}


}
