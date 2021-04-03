package br.com.api.vuttr.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.api.vuttr.model.Tool;
import br.com.api.vuttr.model.User;
import br.com.api.vuttr.repository.ToolRepository;
import br.com.api.vuttr.repository.UserRepository;
import br.com.api.vuttr.service.TokenService;
import br.com.api.vuttr.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class ToolsControllerTest {
	
	private String username;
	private String token;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@BeforeAll
	void init() {
		username = "26d681d8c76a4bdfb7bb50863hu2h3353";	
		String password = "$2a$10$WLNsc39W32dJL0GouG9UMuxPW9gyBgO!oko8lxkyypkiLVdZakCQ.";
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		
		UserDetails loadedUser = userService.loadUserByUsername(username);
		token = tokenService.generateToken(loadedUser);
	}
	
	@AfterAll
	void end() {
		User user = userRepository.findByUsername(username);
		userRepository.delete(user);	
	}
	
	@Test
	public void mustReturn201WhenSaveNewTool() throws Exception {
		String json = "{\"title\": \"hotel\", \"link\": \"https://github.com/typicode/hotel\", \"description\":"
				+ " \"Local app manager. Start apps within your browser.\", \"tags\": [\"node\", \"developer\"]}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.post("/tools")
		            .header("authorization", "Bearer " + token)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
					.status()
					.is(201));
		
	}
	
	@Test
	public void mustReturn204WhenDeleteTool() throws Exception {
		
		List<Tool> tools = toolRepository.findAll();
		
		String id = tools.get(0).getId();
		
		URI uri = new URI("/tools/" + id);
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.delete(uri)
		            .header("authorization", "Bearer " + token)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
					.status()
					.is(204));
		
	}
	
	
}
