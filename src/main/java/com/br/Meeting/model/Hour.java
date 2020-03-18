package com.br.Meeting.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Hour {
	
	public Hour () {}
	public Hour (Long id, String hour) {
		this.id = id;
		this.hour = hour;
	}
	
	@Id
	private Long id;
	//TODO Utilizar Date ou DateTime posteriormente
	private String hour;
	
	public Long getId() {
		return id;
	}
	public String getHour() {
		return hour;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	
}
