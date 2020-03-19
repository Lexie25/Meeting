package com.br.Meeting.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.Meeting.DTO.UserDTO;
import com.br.Meeting.model.UserMix;

@Repository
public interface UserRepository extends JpaRepository<UserMix, Long>{

	public Optional<UserMix> findByCpfAndEmail(String cpf, String email);

}
