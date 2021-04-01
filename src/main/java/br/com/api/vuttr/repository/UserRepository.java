package br.com.api.vuttr.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.api.vuttr.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	Optional<User> findByUsername(String userName);
	Optional<User> findByEmail(String email);

}
