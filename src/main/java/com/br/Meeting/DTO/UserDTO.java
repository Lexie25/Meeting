package com.br.Meeting.DTO;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

public class UserDTO implements Serializable {
	private final static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "mandatory name field")
	private String name;
	
	@NotEmpty(message = "mandatory password field")
	private String password;

	@CPF
	@NotEmpty(message = "mandatory CPF field")
	private String cpf;
	
	@NotEmpty(message = "mandatory nick field")
	private String nick;
	
	@Email
	@NotEmpty(message = "mandatory E-mal field")
	private String email;

	public UserDTO(Long id, @NotEmpty(message = "mandatory name field") String name,
			@NotEmpty(message = "mandatory password field") String password,
			@CPF @NotEmpty(message = "mandatory CPF field") String cpf,
			@NotEmpty(message = "mandatory nick field") String nick,
			@Email @NotEmpty(message = "mandatory E-mal field") String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.cpf = cpf;
		this.nick = nick;
		this.email = email;
	}

	public UserDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "UserDTO [id=" + id + ", name=" + name + ", password=" + password + ", cpf=" + cpf + ", nick=" + nick
				+ ", email=" + email + "]";
	}
	
	
	
	
}


