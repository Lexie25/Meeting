package com.br.Meeting.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.Meeting.DTO.UserDTO;
import com.br.Meeting.Service.UserService;
import com.br.Meeting.model.User;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
		

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) {
		try {
			User user = userService.getUserById(id);
			return ResponseEntity.ok(user);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO user) {
		try {
			userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);	
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable long id,
												@Valid @RequestBody User user) {
		userService.updateUser(id, user);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}


}
