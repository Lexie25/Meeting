package com.br.Meeting.DTO;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class RoomDTO implements Serializable {
	private final static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRoom;
	private String description;
	
	@NotNull(message = "The room number cannot be null")
	private short numberRoom;
	
	@NotNull(message = "The floor number cannot be null")
	private short floor;
	
	
	public enum situaction{
		AVAILABLE,UNAVAILABLE;
	}


	public RoomDTO(Long idRoom, String description,
			@NotNull(message = "The room number cannot be null") short numberRoom,
			@NotNull(message = "The floor number cannot be null") short floor) {
		super();
		this.idRoom = idRoom;
		this.description = description;
		this.numberRoom = numberRoom;
		this.floor = floor;
	}


	public RoomDTO() {
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
		return "RoomDTO [idRoom=" + idRoom + ", description=" + description + ", numberRoom=" + numberRoom + ", floor="
				+ floor + "]";
	}

	
}
