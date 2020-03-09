package com.br.Meeting.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public void updateUser(long id, User user) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new MessageNotFound("Não há mensagens com esse id");
		}
		
		user.setId(id);
		userRepository.save(user);
	}
	
	public void deleteUser(long id) {
		try {
			userRepository.deleteById(id);	
		}
		catch(EmptyResultDataAccessException e) {
			throw new MessageNotFound("Não há mensagens com esse id");
		}
	}
	
}
