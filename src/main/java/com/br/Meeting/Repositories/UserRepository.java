package com.br.Meeting.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.Meeting.DTO.UserDTO;
import com.br.Meeting.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public Optional<User> findByCpfAndEmail(String cpf, String email);

}
