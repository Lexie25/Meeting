package com.br.Meeting.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RoomId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private short numberRoom;

	private short floor;

	public short getRoomNumber() {
		return numberRoom;
	}

	public void setRoomNumber(short numberRoom) {
		this.numberRoom = numberRoom;
	}

	public short getFloor() {
		return floor;
	}

	public void setFloor(short floor) {
		this.floor = floor;
	}

}
