package com.br.Meeting.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.br.Meeting.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
