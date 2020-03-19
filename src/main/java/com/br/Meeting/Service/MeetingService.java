package com.br.Meeting.Service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.Meeting.DTO.MeetingDTO;
import com.br.Meeting.Repositories.MeetingRepository;
import com.br.Meeting.exceptions.MessageNotFound;
import com.br.Meeting.model.Meeting;

@Service
public class MeetingService {

	@Autowired
	private MeetingRepository meetingRepository;

	public Iterable<Meeting> getMeeting() {
		return meetingRepository.findAll();
	}

	public Meeting getMeetingById(long id) {
		return meetingRepository.findById(id).get();
	}

	public void saveMeeting(@Valid MeetingDTO meetingDto) {
		
		Meeting entity = convertEntity(meetingDto);
		meetingRepository.save(entity);
	}

	public Meeting convertEntity(MeetingDTO meetingDto) {
		Meeting meeting = new Meeting();
		meeting.setDate(meetingDto.getDate());
		meeting.setEndTime(meetingDto.getEndTime());
		meeting.setStartTime(meetingDto.getStartTime());
		meeting.setTitle(meetingDto.getTitle());
		meeting.setStatus(meetingDto.getStatus());
		return meeting;
	}
	

	public void updateMeeting(long id, Meeting meeting) {
		Optional<Meeting> optionalMensagem = meetingRepository.findById(id);
		if (!optionalMensagem.isPresent()) {
			throw new MessageNotFound("Not found");
		}

		meeting.setIdMeeting(id);
		meetingRepository.save(meeting);
	}

	public void deleteMeeting(long id) {
		try {
			meetingRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new MessageNotFound("Not found");
		}
	}

}
