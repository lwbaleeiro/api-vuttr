package br.com.api.vuttr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.api.vuttr.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{

	UserModel findByUsername(String username);
	
}
