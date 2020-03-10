package com.br.Meeting.Controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.Meeting.Service.RoomService;
import com.br.Meeting.model.Room;

@RestController
@RequestMapping("room")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getRoom(@PathVariable long id) {
		try {
			Room room = roomService.getRoomById(id);
			return ResponseEntity.ok(room);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
	}
	
	@GetMapping
	public ResponseEntity<?> findAllRoom() {
		try {
			Iterable<Room> room = roomService.getRoom();
			return ResponseEntity.ok(room);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}	
	
	@PostMapping
	public ResponseEntity<?> Save(@Valid @RequestBody Room room) {
		try {
			roomService.saveRoom(room);
			return ResponseEntity.status(HttpStatus.CREATED).body(room);	
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id,
												@Valid @RequestBody Room room) {
		roomService.updateRoom(id, room);
		return ResponseEntity.ok(room);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		roomService.deleteRoom(id);
		return ResponseEntity.ok().build();
	}
	
}
