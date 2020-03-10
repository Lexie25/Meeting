package com.br.Meeting.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
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

	public void saveMeeting(Meeting meeting) {
		meetingRepository.save(meeting);
	}
	public void updateMeeting(long id, Meeting meeting) {
		Optional<Meeting> optionalMensagem = meetingRepository.findById(id);
		if (!optionalMensagem.isPresent()) {
			throw new MessageNotFound("Não há mensagens com esse id");
		}

		meeting.setIdMeeting(id);
		meetingRepository.save(meeting);
	}

	public void deleteMeeting(long id) {
		try {
			meetingRepository.deleteById(id);	
		}
		catch(EmptyResultDataAccessException e) {
			throw new MessageNotFound("Não há mensagens com esse id");
		}
	}

}
