package br.com.api.vuttr.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.api.vuttr.model.User;
import br.com.api.vuttr.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User fountedUser = userRepository.findByUsername(username);
		if (fountedUser == null) {
			return null;
		}
		
		String name = fountedUser.getUsername();
		String password = fountedUser.getPassword();
		
		return new org.springframework.security.core.userdetails.User(name, password, new ArrayList<>());
	}
	
	
}
