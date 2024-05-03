package com.Entities;

public class Accounts {
	
	private int account_Num;
	private String full_Name;
	private String email;
	private double balance;
	private String pin_num;
	
	public Accounts() {
		// TODO Auto-generated constructor stub
	}

	public Accounts(int account_Num, String full_Name, String email, double balance, String pin_num) {
		super();
		this.account_Num = account_Num;
		this.full_Name = full_Name;
		this.email = email;
		this.balance = balance;
		this.pin_num = pin_num;
	}

	public int getAccount_Num() {
		return account_Num;
	}

	public void setAccount_Num(int account_Num) {
		this.account_Num = account_Num;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPin_num() {
		return pin_num;
	}

	public void setPin_num(String pin_num) {
		this.pin_num = pin_num;
	}

	@Override
	public String toString() {
		return "Accounts [account_Num=" + account_Num + ", full_Name=" + full_Name + ", email=" + email + ", balance="
				+ balance + ", pin_num=" + pin_num + "]";
	}
	
	

}
