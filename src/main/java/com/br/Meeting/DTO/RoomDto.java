package com.br.Meeting.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoomDto {

	private Long id;
	@NotEmpty(message="The room's description cannot be null")
	private String description;
	private Status status;
	@NotNull(message = "The room's floor number cannot be null")
	private short floor;
	@NotNull(message = "The room's number cannot be null")
	private short numberRoom;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "RoomDTO [idRoom=" + id + ", description=" + description + ", numberRoom=" + numberRoom + ", floor="
				+ floor + "]";
	}
	
}
