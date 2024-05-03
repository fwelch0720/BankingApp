package com.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.Entities.Accounts;
import com.Utilities.ConnectionFactory;

public class AccountImpl implements AccountDAO{

	@Override
	public int openAccount(String email) {
		Scanner in  = new Scanner(System.in);
		Accounts acc = new Accounts();
		Connection con = ConnectionFactory.createConnection();
		
		if(!accountExsist(email)) {
			System.out.println("What is your full name?");
		String fullName = in.nextLine();
		
		try {
			String query = "select full_name from user where email = ?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
			String nameCheck = rs.getString("full_name");
			if(nameCheck.equals(fullName)) {
				System.out.println("What would you starting deposit be?");
				double balance = in.nextDouble();
				in.nextLine();
				System.out.println("Enter desire pin:");
				String pin_num = in.nextLine();
				try {
					String query2 = "insert into accounts (full_name, email, balance, pin_num) values (?,?,?,?)";
					PreparedStatement stm2 = con.prepareStatement(query2);
					stm2.setString(1, fullName);
					stm2.setString(2, email);
					stm2.setDouble(3, balance);
					stm2.setString(4, pin_num);
					int status = stm2.executeUpdate();
					if(status > 0) {
						System.out.println("Account Created Succesfully");
					
						System.out.println("You account number is " + getAccountNum(email));
					}else {
						throw new RuntimeException("Account Creation Failed");
					}
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			}else {
				System.out.println("User doesnt exist");
			}
			}else {
				System.out.println("User doesnt exist");
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
		}else {
			throw new RuntimeException("Account already exist");
		}
		return 0;
	}

	@Override
	public boolean accountExsist(String email) {
		Connection con = ConnectionFactory.createConnection();
		try {
			String query = "select account_num from accounts where email = ?";
			
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, email);
			
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public int getAccountNum(String email) {
		Connection con = ConnectionFactory.createConnection();
		
		try {
			String query = "select account_num from accounts where email = ? ";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				return rs.getInt("account_num");
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		throw new RuntimeException("Account Number Doesnt Exsist");
	}

	@Override
	public int generateAccountNum() {
		// TODO Auto-generated method stub
		return 0;
	}

}
