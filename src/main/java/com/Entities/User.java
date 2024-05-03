package com.Entities;

public class User {

	private String full_Name;
	private String email;
	private String password;
	
	public User(String full_Name, String email, String password) {
		super();
		this.full_Name = full_Name;
		this.email = email;
		this.password = password;
	}
	
public User() {
	
}

public String getFull_Name() {
	return full_Name;
}

public void setFull_Name(String full_Name) {
	this.full_Name = full_Name;
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

@Override
public String toString() {
	return "User [full_Name=" + full_Name + ", email=" + email + ", password=" + password + "]";
}


	
}
