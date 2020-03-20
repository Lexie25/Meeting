package com.br.Meeting.Service;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.Meeting.DTO.UserDTO;
import com.br.Meeting.Repositories.UserRepository;
import com.br.Meeting.exceptions.MessageNotFound;
import com.br.Meeting.model.UserMix;
import com.br.Meeting.util.MapOperations;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Iterable<UserMix> getUser() {
		return userRepository.findAll();
	}

	public UserDTO getUserById(long id) throws Exception {
		Optional<UserMix> opt =userRepository.findById(id);	
		if (opt.isPresent()) {
			return convertToDto(opt.get());
		}
		else {
			throw  new Exception("User not found");

		}
	}
	public UserDTO saveUser  ( @Valid UserDTO userDto) throws Exception {

		Optional<UserMix> user = userRepository.findByCpfAndEmail( userDto.getCpf(),userDto.getEmail());
		if(user.isPresent()) {
			throw new Exception(" User already registered.");
		}

		return  convertToDto(userRepository.save(convertToEntity(userDto)));

	}

	public Map<String, Object> loginUser(String email, String password) throws Exception{
		Optional<UserMix> optLogin = userRepository.findByEmailAndPassword(email, password);
		if(optLogin.isPresent()) {
			Map<String, Object> login = MapOperations.convertToMap(optLogin.get());
			login.remove("password");
			return login;
		}
		 throw new Exception(" Invalid login"); 
		
	}

	public UserMix convertToEntity (UserDTO userDto) {
		UserMix user = new UserMix();
		user.setName(userDto.getName());
		user.setNick(userDto.getNick());
		user.setCpf(userDto.getCpf());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());

		return user;
	}
	public UserDTO convertToDto (UserMix user) {
		UserDTO userDto = new UserDTO();
		userDto.setName(user.getName());
		userDto.setNick(user.getNick());
		userDto.setCpf(user.getCpf());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());

		return userDto;
	}


	public UserDTO updateUser( UserDTO userDto) throws Exception {
		Optional<UserMix> optionalUser = userRepository.findByCpfAndEmail(userDto.getCpf(), userDto.getEmail());

		if (optionalUser.isPresent() ) {

			UserMix userEncontrada = convertToEntity(userDto);	
			return convertToDto(userRepository.save(userEncontrada));

		}
		throw  new Exception("User not found");

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

