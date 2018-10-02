package com.study.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		
		List<User> users= service.findAll();
		
		System.out.println("-------------------------------------"+users.size());
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retriveuser(@PathVariable int id){
		
		User user = service.findOne(id);
		
		if (user==null)
		{
			throw new UserNotFoundException("id - "+id );
		}
		return user;
	}
	
	// return CREATED and return the URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequestUri()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
//		return savedUser;
	}

}
