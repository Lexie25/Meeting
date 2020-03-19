package com.br.Meeting.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.br.Meeting.DTO.Status;

@Entity
public class Room implements Serializable {
	private final static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRoom;
	
	private String description;

	private short numberRoom;

	private short floor;

	@Enumerated(EnumType.ORDINAL)
	private Status status = Status.AVAILABLE;
	
	@OneToMany(mappedBy = "room")
	private List<Meeting> meetings;

	public Room() {
	}

	public Room(Long idRoom, String description, short numberRoom, short floor, Status status) {
		this.idRoom = idRoom;
		this.description = description;
		this.numberRoom = numberRoom;
		this.floor = floor;
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Long idRoom) {
		this.idRoom = idRoom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getNumberRoom() {
		return numberRoom;
	}

	public void setNumberRoom(short numberRoom) {
		this.numberRoom = numberRoom;
	}

	public short getFloor() {
		return floor;
	}

	public void setFloor(short floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "Room [idRoom=" + idRoom + ", description=" + description + ", numberRoom=" + numberRoom + ", floor="
				+ floor + "]";
	}

	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}
}
