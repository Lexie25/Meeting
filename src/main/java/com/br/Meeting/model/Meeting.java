package com.br.Meeting.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Meeting implements Serializable {
	private final static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long idMeeting;
	private String date;
	private String  startTime;
	private String endTime;
//	private String room;
	private String title;
	private String host;
	
	public enum Status{
		CHECKED,END,CANCELED;
	}

	public Meeting() {
	}

	public Meeting(Long idMeeting, String date, String startTime, String endTime, String title, String host) {
		this.idMeeting = idMeeting;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.host = host;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
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
	
}
