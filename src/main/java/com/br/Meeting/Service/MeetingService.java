package com.br.Meeting.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.Meeting.DTO.MeetingDTO;
import com.br.Meeting.Repositories.HourRepository;
import com.br.Meeting.Repositories.MeetingRepository;
import com.br.Meeting.Repositories.RoomRepository;
import com.br.Meeting.exceptions.MessageNotFound;
import com.br.Meeting.model.Hour;
import com.br.Meeting.model.Meeting;
import com.br.Meeting.model.Room;
import com.br.Meeting.util.DateOperations;

@Service
public class MeetingService {

	@Autowired
	private MeetingRepository meetingRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private HourRepository hourRepository;

	
	//TODO Remover ao subir para produção - inserir esses valores no banco manualmente
	@PostConstruct
	public void init () {
		hourRepository.deleteAll();
		List<String> times = DateOperations.generateTimes("00:00", "23:30", 30);
		for (int i = 0; i < times.size(); i++) {
			hourRepository.save(new Hour((long) (i + 1), times.get(i)));
		}
	}
	
	public List<MeetingDTO> getMeeting() {
		List<Meeting> meetings = (List<Meeting>) meetingRepository.findAll();	
		return meetings.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	public Meeting getMeetingById(long id) {
		return meetingRepository.findById(id).get();
	}

	public MeetingDTO saveMeeting(@Valid MeetingDTO meetingDto) {
		Meeting entity = convertEntity(meetingDto);
		Room room = roomRepository.findByNumberRoom(meetingDto.getRoom());
		if(room == null) {
			throw new MessageNotFound("Room not found");
		}
		entity.setRoom(room);
		
		//TODO Validações : Validar se os campos foram preenchidos
		//TODO Validações : Validar se o horário informado é possível criar
		
		return convertToDTO(meetingRepository.save(entity));
	}

	public void updateMeeting(long id, Meeting meeting) {
		Optional<Meeting> optionalMensagem = meetingRepository.findById(id);
		if (!optionalMensagem.isPresent()) {
			throw new MessageNotFound("Not found");
		}
		
		//TODO Validações : Validar se os campos foram preenchidos
		//TODO Validações : Validar se o horário informado é possível alterar
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
	
	private Meeting convertEntity(MeetingDTO meetingDto) {
		Meeting meeting = new Meeting();
		meeting.setDate(meetingDto.getDate());
		
		meeting.setEndTime(hourRepository.findByHour(meetingDto.getEndTime()));
		meeting.setStartTime(hourRepository.findByHour(meetingDto.getStartTime()));
		
		meeting.setTitle(meetingDto.getTitle());
		meeting.setStatus(meetingDto.getStatus());
		meeting.setHost(meetingDto.getHost());
		return meeting;
	}
	
	private MeetingDTO convertToDTO (Meeting meeting) {
		return new MeetingDTO(meeting.getIdMeeting(), meeting.getDate(), meeting.getStartTime().getHour(), meeting.getEndTime().getHour(), meeting.getTitle(), meeting.getHost(), meeting.getStatus(), meeting.getRoom().getNumberRoom());
	}

}
