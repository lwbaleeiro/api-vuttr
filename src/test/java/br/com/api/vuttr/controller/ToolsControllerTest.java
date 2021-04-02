package br.com.api.vuttr.controller;

import java.net.URI;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.api.vuttr.model.Tool;
import br.com.api.vuttr.repository.ToolRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ToolsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Test
	public void mustReturn201WhenSaveNewTool() throws Exception {
		
		URI uri = new URI("/tools");
		String json = "{\"title\": \"test 1\", \"link\": \"www.test.org\", \"description\": \"some test.\", \"tags\": [\"test_1\", \"test_2\"]}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
					.status()
					.is(403));
		
	}
	
	@Test
	public void mustReturn204WhenDeleteTool() throws Exception {
		
		List<Tool> tools = toolRepository.findAll();
		
		String id = tools.get(0).getId();
		
		URI uri = new URI("/tools/" + id);
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.delete(uri)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
					.status()
					.is(403));
		
	}
	
	
}
