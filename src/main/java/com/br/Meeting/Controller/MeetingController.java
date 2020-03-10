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

import com.br.Meeting.DTO.MeetingDTO;
import com.br.Meeting.Service.MeetingService;
import com.br.Meeting.model.Meeting;

@RestController
@RequestMapping("meeting")
public class MeetingController {

	@Autowired 
	private MeetingService meetingService;
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getMeeting(@PathVariable long id) {
		try {
			Meeting meeting = meetingService.getMeetingById(id);
			return ResponseEntity.ok(meeting);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	@GetMapping
	public ResponseEntity<?>  findAll () {
		try {
			Iterable<Meeting> meeting = meetingService.getMeeting();
			return ResponseEntity.ok(meeting);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	@PostMapping
	public ResponseEntity<?> saveMeeting(@Valid @RequestBody MeetingDTO meetingDto) {
		try {
			meetingService.saveMeeting(meetingDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(meetingDto);	
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMeeting(@PathVariable long id,
												@Valid @RequestBody Meeting meeting) {
		meetingService.updateMeeting(id, meeting);
		return ResponseEntity.ok(meeting);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMeeting(@PathVariable long id) {
		meetingService.deleteMeeting(id);
		return ResponseEntity.ok().build();
	}
	
	
}
