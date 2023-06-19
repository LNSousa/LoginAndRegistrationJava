package com.lsousa.logreg.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Username is required")
	@Pattern(regexp = "[A-Za-z]+", message="Please use only letters for your username")
	@Size(min=3, message="Your username must be longer than 3 characters")
	private String username;
	
	@NotEmpty(message="Email is required")
	@Email(message="Please enter a valid email address")
	private String email;
	
	@NotEmpty(message="Password is required")
	@Size(min=8, message="Password must be at least 8 characters long")
	private String password;

	@Transient
	@NotEmpty(message="Confirm password is required")
	private String confirm;
	
	public User () {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirm() {
		return confirm;
	}


	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	
}
