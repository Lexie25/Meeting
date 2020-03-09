package com.br.Meeting.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.Meeting.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{

}
