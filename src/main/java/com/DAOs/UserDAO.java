package com.DAOs;

public interface UserDAO {

public void register();
	
	public String login();
	
	public boolean user_exsist(String email);
}
