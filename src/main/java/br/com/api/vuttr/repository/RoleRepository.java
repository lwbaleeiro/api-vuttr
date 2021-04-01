package br.com.api.vuttr.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.api.vuttr.model.ERole;
import br.com.api.vuttr.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Optional<Role> findByName(ERole name);
	
}
