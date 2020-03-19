package com.br.Meeting.Controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.Meeting.DTO.RoomDto;
import com.br.Meeting.Service.RoomService;
import com.br.Meeting.model.Room;
import com.br.Meeting.util.DateOperations;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api rest room")
@RestController
@RequestMapping("room")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@ApiOperation(value = "get a room by Id")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.OPTIONS, RequestMethod.POST })
	@GetMapping("/{id}")
	public ResponseEntity<?> getRoom(@PathVariable long id) {
		try {
			Room room = roomService.getRoomById(id);
			return ResponseEntity.ok(room);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@ApiOperation(value = "take all rooms")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.OPTIONS, RequestMethod.POST })
	@GetMapping
	public ResponseEntity<?> findAllRoom() {
		try {
			return ResponseEntity.ok(roomService.getRoom());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@ApiOperation(value = "add a room")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.OPTIONS, RequestMethod.POST })
	@PostMapping
	public ResponseEntity<?> Save(@Valid @RequestBody RoomDto room) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(roomService.saveRoom(room));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@ApiOperation(value = "update a room")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.OPTIONS, RequestMethod.POST })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody Room room) {
		roomService.updateRoom(id, room);
		return ResponseEntity.ok(room);
	}

	@ApiOperation(value = "delete a room")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.OPTIONS, RequestMethod.POST })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		roomService.deleteRoom(id);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "take filter room")
	@CrossOrigin(origins = "*")
	@GetMapping("/filter")
	public ResponseEntity<?> findMeetingFilter(@RequestParam(name = "date") String date,
			@RequestParam(name = "room", required = false) String room) {

		if (date == null || date == "" || (date != null && (date.length() != 10 || !DateOperations.validDate(date)))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date");
		}

		return ResponseEntity.status(HttpStatus.OK).body(roomService.getRoomAvaliable(date, room));
	}
}
