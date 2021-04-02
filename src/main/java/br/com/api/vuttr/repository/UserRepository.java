package br.com.api.vuttr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.api.vuttr.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	User findByUsername(String username);
	
}
