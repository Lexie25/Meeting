package com.br.Meeting.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
	private final static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	private String name;
	private String password;
	private String cpf;
	private String nick;
	private String email;
	
	public User() {
	}
	public User(Long id, String name, String password, String cpf, String nick, String email) {
		this.idUser = id;
		this.name = name;
		this.password = password;
		this.cpf = cpf;
		this.nick = nick;
		this.email = email;
	}
	public Long getId() {
		return idUser;
	}
	public void setId(Long id) {
		this.idUser = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Room [id=" + idUser + ", name=" + name + ", password=" + password + ", cpf=" + cpf + ", nick=" + nick
				+ ", email=" + email + "]";
	}
}
