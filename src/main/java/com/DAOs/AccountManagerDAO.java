package com.DAOs;

import java.sql.SQLException;

public interface AccountManagerDAO {
	
	public void withdrawMoney(int account_num) throws SQLException;
	
	public void depositMoney (int account_num) throws SQLException;
	
	public void getBalance (int account_num);
	
	public void transferMoney(int senderAccountNum);
	
	public void deleteAccount(int account_num);

}
