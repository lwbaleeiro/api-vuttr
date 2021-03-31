package br.com.api.vuttr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.vuttr.model.Tool;

public interface ToolRepository extends JpaRepository<Tool, Long> {
	
}
