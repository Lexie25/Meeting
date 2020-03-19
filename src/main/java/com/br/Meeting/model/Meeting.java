package com.br.Meeting.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.br.Meeting.DTO.Status;

@Entity
public class Meeting implements Serializable {
	private final static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long idMeeting;
	private String date;

	@ManyToOne
	@JoinColumn(name = "start_time_hour_id")
	private Hour startTime;

	@ManyToOne
	@JoinColumn(name = "end_time_hour_id")
	private Hour endTime;
//	private String room;
	private String title;
	private String host;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	public Meeting() {
	}

	public Meeting(Long idMeeting, String date, Hour startTime, Hour endTime, String title, String host) {
		this.idMeeting = idMeeting;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.host = host;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getIdMeeting() {
		return idMeeting;
	}

	public void setIdMeeting(Long idMeeting) {
		this.idMeeting = idMeeting;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Hour getStartTime() {
		return startTime;
	}

	public void setStartTime(Hour startTime) {
		this.startTime = startTime;
	}

	public Hour getEndTime() {
		return endTime;
	}

	public void setEndTime(Hour endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "Meeting [idMeeting=" + idMeeting + ", date=" + date + ", startTime=" + startTime + ", endTime="
				+ endTime + ", title=" + title + ", host=" + host + "]";
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
