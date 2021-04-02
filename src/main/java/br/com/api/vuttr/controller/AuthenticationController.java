package br.com.api.vuttr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.vuttr.model.AuthenticationRequest;
import br.com.api.vuttr.model.AuthenticationResponse;
import br.com.api.vuttr.model.User;
import br.com.api.vuttr.repository.UserRepository;
import br.com.api.vuttr.service.TokenService;
import br.com.api.vuttr.service.UserService;

@RestController
@RequestMapping
public class AuthenticationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/subs")
	private ResponseEntity<?> subscribleClient(@RequestBody AuthenticationRequest authenticationRequest) {

		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();

		if (!authenticationRequest.isCredentialsValid()) {
			return ResponseEntity.badRequest().body(new AuthenticationResponse("Invalid username or password."));
		}

		User fountedUser = userRepository.findByUsername(username);
		if (fountedUser != null) {
			return ResponseEntity.badRequest().body(new AuthenticationResponse("User name " + username + " already exists, choose another one!"));
		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));

		try {
			userRepository.save(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new AuthenticationResponse("Error during client subscription  " + username));
		}

		return ResponseEntity.ok(new AuthenticationResponse("Succesful subscription for client " + username));
	}

	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {

		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(new AuthenticationResponse("Error during client authentication " + username));
		}

		UserDetails loadedUser = userService.loadUserByUsername(username);
		String generatedToken = tokenService.generateToken(loadedUser);

		return ResponseEntity.ok(new AuthenticationResponse(generatedToken));
	}

}
