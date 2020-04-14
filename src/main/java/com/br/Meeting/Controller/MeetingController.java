package com.br.Meeting.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.Meeting.DTO.MeetingDTO;
import com.br.Meeting.Service.MeetingService;
import com.br.Meeting.exceptions.ApiError;
import com.br.Meeting.exceptions.ApiErrorRequest;
import com.br.Meeting.model.Meeting;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Api rest meeting")
@RestController
@RequestMapping("meeting")
public class MeetingController {

	@Autowired 
	private MeetingService meetingService;
	

	@ApiOperation(value="get a meeting by Id")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.DELETE,RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
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
	
	
	@ApiOperation(value="take all meeting")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.DELETE,RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
	@GetMapping
	public ResponseEntity<?>  findAll () {
		try {
			return ResponseEntity.ok(meetingService.getMeeting());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@ApiOperation(value="add a meeting")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.DELETE,RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
	@PostMapping
	public ResponseEntity<?> saveMeeting(@Valid @RequestBody MeetingDTO meetingDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(meetingService.saveMeeting(meetingDto));	
		}
		catch (ApiErrorRequest e) {
			ApiError apiError = new ApiError(e.getMessage(), e.getStatus());
			return ResponseEntity.status(e.getStatus()).body(apiError);
		}
	}
	
	@ApiOperation(value="update a meeting")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.DELETE,RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMeeting(@PathVariable long id,
												@Valid @RequestBody MeetingDTO meetingDto) {
		return ResponseEntity.ok(meetingService.updateMeeting(id, meetingDto));
	}
	
	@ApiOperation(value="delete a meeting")
	@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.DELETE,RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMeeting(@PathVariable long id) {
		meetingService.deleteMeeting(id);
		return ResponseEntity.ok().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    
	    if (ex.getBindingResult().getAllErrors().size() > 0) {
	    	
	    	ObjectError error = ex.getBindingResult().getAllErrors().get(0);
	        String errorMessage = error.getDefaultMessage();
	        errors.put("error", errorMessage);
	    }
	    return errors;
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	public Map<String, String> handleJsonSerializationExceptions (InvalidFormatException ex) {
		String field = ex.getPath().get(0).getFieldName();
		Map<String, String> errors = new HashMap<>();
		errors.put("error", "'" + ex.getValue() +"' is invalid for field " + field);
		return errors;
	}
	
}
