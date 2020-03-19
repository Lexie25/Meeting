package com.br.Meeting.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.br.Meeting.DTO.MeetingDTO;
import com.br.Meeting.DTO.Status;
import com.br.Meeting.Repositories.HourRepository;
import com.br.Meeting.Repositories.MeetingRepository;
import com.br.Meeting.Repositories.RoomRepository;
import com.br.Meeting.exceptions.ApiErrorRequest;
import com.br.Meeting.exceptions.MessageNotFound;
import com.br.Meeting.model.Hour;
import com.br.Meeting.model.Meeting;
import com.br.Meeting.model.Room;
import com.br.Meeting.util.DateOperations;
import com.br.Meeting.util.MapOperations;

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
		try {
			hourRepository.deleteAll();
			List<String> times = DateOperations.generateTimes("00:00", "23:30", 30);
			for (int i = 0; i < times.size(); i++) {
				hourRepository.save(new Hour((long) (i + 1), times.get(i)));
			}
			
			roomRepository.save(new Room((long) 0, "Teste", (short)0, (short)0, Status.AVAILABLE));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Object> getMeeting() {
		List<Meeting> meetings = (List<Meeting>) meetingRepository.findAll();	
		List<Object> meetingsDescription = new ArrayList<Object>();
		
		for (Meeting meeting : meetings) {
			Map<String, Object> mapTemp = MapOperations.convertToMap(convertToDTO(meeting));
			mapTemp.put("roomDescription", meeting.getRoom().getDescription());
			meetingsDescription.add(mapTemp);
		}
		return meetingsDescription; 
	}
	public Meeting getMeetingById(long id) {
		return meetingRepository.findById(id).get();
	}

	public MeetingDTO saveMeeting(@Valid MeetingDTO meetingDto) {
		Meeting entity = convertEntity(meetingDto);
		Room room = roomRepository.findByNumberRoom(meetingDto.getRoom());
		if(room == null) {
			throw new ApiErrorRequest("Room not found", HttpStatus.ACCEPTED);
		}
		entity.setRoom(room);
		System.out.println(entity);
		
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
		
		Hour endTime = hourRepository.findByHour(meetingDto.getEndTime());
		if(endTime == null) {
			throw new ApiErrorRequest("End time not found", HttpStatus.ACCEPTED);
		}
		
		Hour startTime = hourRepository.findByHour(meetingDto.getStartTime());
		if(startTime == null) {
			throw new ApiErrorRequest("Start time not found", HttpStatus.ACCEPTED);
		}
		meeting.setEndTime(endTime);
		meeting.setStartTime(startTime);
		
		meeting.setTitle(meetingDto.getTitle());
		meeting.setStatus(meetingDto.getStatus());
		meeting.setHost(meetingDto.getHost());
		return meeting;
	}
	
	private MeetingDTO convertToDTO (Meeting meeting) {
		return new MeetingDTO(meeting.getIdMeeting(), meeting.getDate(), meeting.getStartTime().getHour(), meeting.getEndTime().getHour(), meeting.getTitle(), meeting.getHost(), meeting.getStatus(), meeting.getRoom().getNumberRoom());
	}

}
