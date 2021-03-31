package br.com.api.vuttr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.vuttr.model.Tool;
import br.com.api.vuttr.repository.ToolRepository;

@RestController
@RequestMapping("/tools")
public class ToolsController {

	@Autowired
	private ToolRepository toolRepository;

	@GetMapping
	public List<Tool> find() {

		return toolRepository.findAll();
	}

}
