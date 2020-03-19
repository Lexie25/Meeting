package com.br.Meeting.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.Meeting.DTO.UserDTO;
import com.br.Meeting.Service.UserService;
import com.br.Meeting.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api rest ")
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "take all user")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.OPTIONS, RequestMethod.POST })
	@GetMapping
	public ResponseEntity<?> findAll() {
		try {
			Iterable<User> user = userService.getUser();
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@ApiOperation(value = "get a user by Id")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.OPTIONS, RequestMethod.POST })
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable long id) {
		try {
			UserDTO user = userService.getUserById(id);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@ApiOperation(value = "add a user")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.OPTIONS, RequestMethod.POST })
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
		try {
			userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@ApiOperation(value = "update a user")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.OPTIONS, RequestMethod.POST })
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDto) throws Exception {

		UserDTO personUpdate = userService.updateUser(userDto);
		if (personUpdate == null) {
			return new ResponseEntity<String>("Usuario não encontrado", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Atualizada", HttpStatus.OK);

	}

	@ApiOperation(value = "delete a user")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.OPTIONS, RequestMethod.POST })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}

}
