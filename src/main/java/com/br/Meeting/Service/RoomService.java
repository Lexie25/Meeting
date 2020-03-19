package com.br.Meeting.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.Meeting.DTO.RoomDto;
import com.br.Meeting.DTO.Status;
import com.br.Meeting.Repositories.HourRepository;
import com.br.Meeting.Repositories.RoomRepository;
import com.br.Meeting.exceptions.MessageNotFound;
import com.br.Meeting.model.Meeting;
import com.br.Meeting.model.Room;
import com.br.Meeting.util.MapOperations;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private HourRepository hourRepository;

	@PostConstruct
	public void init() {
		roomRepository.save(new Room(0L, "Teste", (short) 13, (short) 13, Status.AVAILABLE));
	}

	public List<Object> getRoomAvaliable(String date, String roomNumber) {
		List<Object> avaliableRooms = new ArrayList<Object>();
		if (roomNumber != null && roomNumber != "") {
			Room room = roomRepository.findByNumberRoom(Short.parseShort(roomNumber));
			if (room != null) {
				avaliableRooms.add(generateMapRoom(room, date));
			}
		}
		else {
			Iterable<Room> rooms = getRoom();
			for (Room room : rooms) {
				avaliableRooms.add(generateMapRoom(room, date));
			}
		}

		return avaliableRooms;

	}

	public Iterable<Room> getRoom() {
		return roomRepository.findAll();
	}

	public Room getRoomById(long id) {
		return roomRepository.findById(id).get();
	}

	public RoomDto saveRoom(RoomDto roomDto) {
		Room entity = convertToEntity(roomDto);
		return convertToDTO(roomRepository.save(entity));
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
		} catch (EmptyResultDataAccessException e) {
			throw new MessageNotFound("Not found");
		}
	}

	private Map<String, Object> generateMapRoom (Room room, String date) {
		List<Meeting> meetings = room.getMeetings().stream()
				.filter(meeting -> meeting.getStatus() == Status.AVAILABLE)
				.collect(Collectors.toList());
		
		List<String> hours = hourRepository.findAllHoursColumn().stream().map(object -> Objects.toString(object, null))
				.collect(Collectors.toList());
		Map<String, Object> roomCustomInformation = MapOperations.convertToMap(convertToDTO(room));
		
		//Room nao tem reunioes agendadas em nenhum horario
		if (meetings.size() == 0) {
			roomCustomInformation.put("hours", hours);
			return roomCustomInformation;
		}
		
		meetings = room.getMeetings().stream()
				.filter(meeting -> meeting.getDate().equals(date))
				.collect(Collectors.toList());
		
		List<String> avaliableHours = hours;
		for (Meeting meeting : meetings) {
			if (avaliableHours.size() > 0) {
				avaliableHours = removeNotUsedHours(avaliableHours, meeting.getStartTime().getHour(), meeting.getEndTime().getHour());
			}
		}
		roomCustomInformation.put("hours", avaliableHours);
		
		return roomCustomInformation;
	}
	
	private List<String> removeNotUsedHours(List<String> hours, String startHour, String endHour) {
		List<String> notUsedHours = new ArrayList<>(hours);
		try {
			Date startDateTime = new SimpleDateFormat("HH:mm").parse(startHour);
			Date endDateTime = new SimpleDateFormat("HH:mm").parse(endHour);

			for (String hour : hours) {
				Date currentTime = new SimpleDateFormat("HH:mm").parse(hour);

				if (startDateTime.compareTo(currentTime) * currentTime.compareTo(endDateTime) >= 0) {
					notUsedHours.remove(hour);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return notUsedHours;

	}

	private Room convertToEntity(RoomDto roomDto) {
		Room room = new Room();
		room.setDescription(roomDto.getDescription());
		room.setFloor(roomDto.getFloor());
		room.setNumberRoom(roomDto.getNumberRoom());
		room.setStatus(roomDto.getStatus());
		return room;
	}

	private RoomDto convertToDTO(Room room) {
		RoomDto roomDto = new RoomDto();
		roomDto.setDescription(room.getDescription());
		roomDto.setFloor(room.getFloor());
		roomDto.setId(room.getIdRoom());
		roomDto.setNumberRoom(room.getNumberRoom());
		roomDto.setStatus(room.getStatus());

		return roomDto;
	}

}
