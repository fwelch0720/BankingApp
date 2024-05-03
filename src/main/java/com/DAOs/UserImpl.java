package com.DAOs;
import com.Utilities.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import com.Entities.User;

public class UserImpl implements UserDAO {
	

	@Override
	public void register() {

		Scanner in = new Scanner(System.in);
		User user = new User();
		Connection conn = ConnectionFactory.createConnection();

		System.out.println("Full Name: ");
		user.setFull_Name(in.nextLine());
		System.out.println("Email: ");
		user.setEmail(in.nextLine());
		System.out.println("Password: ");
		user.setPassword(in.nextLine());

		if (user_exsist(user.getEmail())) {
			System.out.println("User already exsist");
			return;
		}
		try {
			String query = "insert into user(full_name, email, password) values(?,?,?)";
			PreparedStatement stm = conn.prepareStatement(query);
			stm.setString(1, user.getFull_Name());
			stm.setString(2, user.getEmail());
			stm.setString(3, user.getPassword());

			int status = stm.executeUpdate();
			if (status > 0) {
				System.out.println("Registration Sucessful");
			} else {
				System.out.println("Registration Failed");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public String login() {

		Scanner in = new Scanner(System.in);
		Connection conn = ConnectionFactory.createConnection();
        //in.nextLine();
		System.out.println("Email: ");
		String email = in.nextLine();
		System.out.println("Password: ");
		String password = in.nextLine();

		try {
			String query = "select * from user WHERE email = ? AND password = ?";

			PreparedStatement stm = conn.prepareStatement(query);
			stm.setString(1, email);
			stm.setString(2, password);

			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return email;
			} else {
				return null;
				//System.out.println("User does not exist");;
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public boolean user_exsist(String email) {
		Connection conn = ConnectionFactory.createConnection();
		
		try {
			String query = "select * from user WHERE email = ?";
			PreparedStatement stm = conn.prepareStatement(query);
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

}
