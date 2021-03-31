package br.com.api.vuttr.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.vuttr.controller.dto.DetailToolDto;
import br.com.api.vuttr.controller.form.ToolForm;
import br.com.api.vuttr.model.Tool;
import br.com.api.vuttr.repository.ToolRepository;

@RestController
@RequestMapping("/tools")
public class ToolsController {

	@Autowired
	private ToolRepository toolRepository;

	@GetMapping
	public List<DetailToolDto> find(@RequestParam(required = false) String tag) {
		
		if (tag == null) {
			List<Tool> tools = toolRepository.findAll();
			return DetailToolDto.toDto(tools);
		} else {
			List<Tool> tools = toolRepository.findByTags(tag);
			return DetailToolDto.toDto(tools);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DetailToolDto> save(@RequestBody @Valid ToolForm toolForm, UriComponentsBuilder uriBuilder) {
		Tool tool = toolForm.ToTool();
		toolRepository.save(tool);
		
		URI uri = uriBuilder.path("/tools/{id}").buildAndExpand(tool.getId()).toUri();
		return ResponseEntity.created(uri).body(new DetailToolDto(tool));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailToolDto> detail(@PathVariable String id) {
		
		Optional<Tool> tool = toolRepository.findById(id);
		
		if (tool.isPresent()) {
			return ResponseEntity.ok(new DetailToolDto(tool.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) {
		Optional<Tool> tool = toolRepository.findById(id);
		
		if (tool.isPresent()) {
			toolRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
