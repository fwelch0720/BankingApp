package com.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.Utilities.ConnectionFactory;
import com.mysql.cj.xdevapi.Result;

public class AccountManagerImpl implements AccountManagerDAO{

	@Override
	public void withdrawMoney(int account_num) throws SQLException {
		Scanner in = new Scanner(System.in);
		Connection con = ConnectionFactory.createConnection();
		
		in.nextLine();
		System.out.println("How much would you like to withdraw?");
		double withdrawalAmount = in.nextDouble();
		in.nextLine();
		System.out.println("What is you pin?");
		String pin_num = in.nextLine();
		
		try {
		 con.setAutoCommit(false);
			if(account_num != 0) {
				String query = "select * from accounts where account_num = ? and pin_num =?";
				PreparedStatement stm = con.prepareStatement(query);
				stm.setInt(1, account_num);
				stm.setString(2, pin_num);
				ResultSet rs = stm.executeQuery();
				
				if(rs.next()) {
					double current_balance = rs.getDouble("balance");
					
					if(withdrawalAmount<= current_balance){
						String withdrawQuery = "update accounts set balance = balance - ? where account_num = ?";
						PreparedStatement stm2 = con.prepareStatement(withdrawQuery);
						stm2.setDouble(1, withdrawalAmount);
						stm2.setInt(2, account_num);
						int status = stm2.executeUpdate();
						
						if(status > 0) {
							System.out.println("$"+withdrawalAmount+ " withdrawn sucessfully");
							con.commit();
							con.setAutoCommit(true);
							return;
						}else {
							System.out.println("Transaction Failed");
							con.rollback();
						}
						
					}else {
						System.out.println("Insuffcient funds ");
					}
				}else {
					System.out.println("Invalid pin or account number");
				}
				
				
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		con.setAutoCommit(true);
	}

	@Override
	public void depositMoney(int account_num) throws SQLException {
		Scanner in = new Scanner(System.in);
		Connection con = ConnectionFactory.createConnection();
		in.nextLine();
		
		try {
			con.setAutoCommit(false);
		String accQuery = "select * from accounts where account_num = ?";
		PreparedStatement stm = con.prepareStatement(accQuery);
		stm.setInt(1, account_num);
		ResultSet rs = stm.executeQuery();
		
		if(rs.next()) {
			System.out.println("How much would you like to deposit?");
			double depositAmount = in.nextDouble();
			
			String depositQuery = "update accounts set balance = balance + ? where account_num = ?";
			PreparedStatement stm2 = con.prepareStatement(depositQuery);
			stm2.setDouble(1, depositAmount);
			stm2.setInt(2, account_num);
			int status = stm2.executeUpdate();
			if(status > 0 ) {
				System.out.println("$"+depositAmount+ " deposited sucessfully");
				con.commit();
				con.setAutoCommit(true);
			}else {
				System.out.println("Transaction failed");
			}
		}else {
			System.out.println("Account does not exsist");
		}
		
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void getBalance(int account_num) {
		
		Connection con = ConnectionFactory.createConnection();
		Scanner in = new Scanner(System.in);
		//in.nextLine();
		
		try {
			String accQuery = "select * from accounts where account_num = ? ";
			PreparedStatement stm = con.prepareStatement(accQuery);
			stm.setInt(1, account_num);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				System.out.println("What is your pin ");
				String pin_num = in.nextLine();
				
				String balanceQuery = "select balance from accounts where account_num = ? and pin_num = ?";
				PreparedStatement stm2 = con.prepareStatement(balanceQuery);
				stm2.setInt(1, account_num);
				stm2.setString(2, pin_num);
				
				ResultSet rs2 = stm2.executeQuery();
				if(rs2.next()) {
					double balance = rs2.getDouble("balance");
					System.out.println("Balance: "+balance);
					
				}else {
					System.out.println("Invalid Pin");
				}
			}else {
				System.out.println("Account doesnt exist");
			}
			
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void transferMoney(int senderAccountNum) {
		
		Connection con = ConnectionFactory.createConnection();
		Scanner in = new Scanner(System.in);
		in.nextLine();
		
		try {
			
			String senderAccQuery = "select * from accounts where account_num = ?";
			PreparedStatement stm = con.prepareStatement(senderAccQuery);
			stm.setInt(1, senderAccountNum);
			ResultSet rs = stm.executeQuery();
			con.setAutoCommit(false);
			if(rs.next()) {
				System.out.println("What is the account number of the user you would like to transfer funds to?");
				int receiverAccountNum = in.nextInt();
				in.nextLine();
				String receiverAccQuery = "select * from accounts where account_num = ?";
				PreparedStatement stm2 = con.prepareStatement(receiverAccQuery);
				stm2.setInt(1, receiverAccountNum);
				ResultSet rs2 = stm2.executeQuery();
				
				if(rs2.next()) {
					System.out.println("How much would you like to transfer?");
					double transferAmount = in.nextDouble();
					in.nextLine();
					double senderCurrentBalance = rs.getDouble("balance");
					
					if(transferAmount<=senderCurrentBalance) {
						
						String senderTransferQuery = "update accounts set balance = balance - ? where account_num = ?";
						String receiverTransferQuery = "update accounts set balance = balance + ? where account_num =?";
						
						PreparedStatement senderPS = con.prepareStatement(senderTransferQuery);
						PreparedStatement receiverPS = con.prepareStatement(receiverTransferQuery);
						senderPS.setDouble(1, transferAmount);
						senderPS.setInt(2, senderAccountNum);
						
						receiverPS.setDouble(1, transferAmount);
						receiverPS.setInt(2, receiverAccountNum);
						
						int senderStatus = senderPS.executeUpdate();
						int receiverStatus = receiverPS.executeUpdate();
						if(senderStatus > 0 && receiverStatus > 0) {
						String receiverName = rs2.getString("full_name");
							
							System.out.println("Transferd "+ transferAmount+ " to " + receiverName + " Successful");
							con.commit();
							con.setAutoCommit(true);
						}else {
							System.out.println("Transfer Unsucessful");
							con.rollback();
						}
						
						
		
					}else {
						System.out.println("Insufficent Fund!");
					}
			
					
				}else {
					System.out.println("Accoount doesnt exsist");
				}
			}else {
				System.out.println("Account does not exist");
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void deleteAccount(int account_num) {
		Scanner in = new Scanner(System.in);
		int menu = 0;
		Connection con = ConnectionFactory.createConnection();
		
	try {
		System.out.println("Are you sure you would like to delete?");
		System.out.println("1. Yes ");
		System.out.println("2. No");
		menu = in.nextInt();
		switch (menu) {
		case 1: 
			String query = "delete from accounts where account_num = ?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, account_num);
			int status  = stm.executeUpdate();
			if(status > 0) {
				System.out.println("Your accout has been deleted");
			}else {
				System.out.println("Something went wrong");
			}
			break;
			
		case 2:
			System.out.println("Thank you for continuing to bank with us");
			break;

		default:
			break;
		}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	}
	

}
