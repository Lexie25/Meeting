package com.br.Meeting.Service;

import java.util.Optional;

import javax.persistence.Id;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.Meeting.DTO.UserDTO;
import com.br.Meeting.Repositories.UserRepository;
import com.br.Meeting.exceptions.MessageNotFound;
import com.br.Meeting.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Iterable<User> getUser() {
		return userRepository.findAll();
	}
	
	public User getUserById(long id) {
		return userRepository.findById(id).get();	
	}
	
	public void saveUser( @Valid UserDTO userDto) {
		User entity = convertToEntity(userDto);
		userRepository.save(entity);
	}
	
	
	public User convertToEntity (UserDTO userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setNick(userDto.getNick());
		user.setCpf(userDto.getCpf());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		
		return user;
	}
	public User updateUser( User user) {
		Optional<User> optionalUser = userRepository.findById(user.getId());
		
		if (optionalUser.isPresent()) {
			User userEncontrada = optionalUser.get();
			user.setName(user.getName());
			userRepository.save(user);
		
			return user;
		}
		return null;
		
	}
	
	public void deleteUser(long id) {
		try {
			userRepository.deleteById(id);	
		}
		catch(EmptyResultDataAccessException e) {
			throw new MessageNotFound("Not found");
		}
	}
	
}
