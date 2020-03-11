package com.br.Meeting.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.Meeting.DTO.RoomDto;
import com.br.Meeting.Repositories.RoomRepository;
import com.br.Meeting.exceptions.MessageNotFound;
import com.br.Meeting.model.Room;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	

	public Iterable<Room> getRoom() {
		return roomRepository.findAll();
	}
	
	public Room getRoomById(long id) {
		return roomRepository.findById(id).get();	
	}
	
	public void saveRoom(RoomDto roomDto) {
		Room entity = convertToEntity(roomDto);
		roomRepository.save(entity);
	}
	
	private Room convertToEntity(RoomDto roomDto) {
		Room room = new Room();
		room.setDescription(roomDto.getDescription());
		return room;
	}
	
	public void updateRoom(long id, Room room) {
		Optional<Room> optionalMensagem = roomRepository.findById(id);
		if (!optionalMensagem.isPresent()) {
			throw new MessageNotFound("Not found");
		}
		
		room.setIdRoom(id);
		roomRepository.save(room);
	}
	
	public void deleteRoom(long id) {
		try {
			roomRepository.deleteById(id);	
		}
		catch(EmptyResultDataAccessException e) {
			throw new MessageNotFound("Not found");
		}
	}
	

}
