package com.br.Meeting.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class MeetingDTO implements Serializable {
	private final static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	
	@NotEmpty(message = "mandatory start time field")
	private String  startTime;
	
	@NotEmpty(message = "mandatory end time field")
	private String endTime;
	
	@NotEmpty(message = "mandatory title field")
	private String title;
	
	@NotEmpty(message = "mandatory host field")
	private String host;
	
	public enum Status{
		CHECKED,END,CANCELED;
	}

	public MeetingDTO(Long id, Date date, @NotEmpty(message = "mandatory start time field") String startTime,
			@NotEmpty(message = "mandatory end time field") String endTime,
			@NotEmpty(message = "mandatory title field") String title,
			@NotEmpty(message = "mandatory host field") String host) {
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.host = host;
	}

	public MeetingDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
		return "MeetingDTO [id=" + id + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", title=" + title + ", host=" + host + "]";
	}
	
	

}
