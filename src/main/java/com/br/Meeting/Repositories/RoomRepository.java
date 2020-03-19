package com.br.Meeting.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.Meeting.DTO.RoomDto;
import com.br.Meeting.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
	
	Room findByNumberRoom (short numberRoom);

	Optional<RoomDto> findByFloorAndNumberRoom(short floor, short numberRoom) ;
}
