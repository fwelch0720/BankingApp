package com.DAOs;

public interface AccountDAO {
	
	public int openAccount(String email);
	
	public boolean accountExsist(String Email);
	
	public int getAccountNum(String email);
	
	public int generateAccountNum();
	
	

}
