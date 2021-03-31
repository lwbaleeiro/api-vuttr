package br.com.api.vuttr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.api.vuttr.model.Tool;

public interface ToolRepository extends MongoRepository<Tool, String>{

	List<Tool> findByTags(String tag);
	
}
