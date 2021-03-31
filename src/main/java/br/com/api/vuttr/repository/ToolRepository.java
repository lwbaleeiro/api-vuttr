package br.com.api.vuttr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.api.vuttr.model.Tool;

public interface ToolRepository extends MongoRepository<Tool, String>{
	
}
