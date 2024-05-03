package com.Controllers;

import java.util.Scanner;

import com.DAOs.AccountImpl;
import com.DAOs.AccountManagerImpl;
import com.DAOs.UserImpl;

public class MainController {

	public static void bankingApp() {

		UserImpl userImpl = new UserImpl();
		AccountImpl accImpl = new AccountImpl();
		AccountManagerImpl accManagerImpl = new AccountManagerImpl();
		Scanner in = new Scanner(System.in);
		int menu = 0;
		String email = "";
		int account_num = 0;
		try {
			while (menu != 3) {
				System.out.println("*********************");
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				System.out.println("Select an option: ");
				
				menu = in.nextInt();
				

				switch (menu) {
				case 1:
					userImpl.register();
					break;
				case 2:
					
					email = userImpl.login();
					if (email != null) {
						System.out.println("User Logged in");
						if (!accImpl.accountExsist(email)) {
							System.out.println();
							System.out.println("1. Open a new account");
							System.out.println("2. exit");
							if (in.nextInt() == 1) {
								account_num = accImpl.openAccount(email);
								in.nextLine();
							} else {
								break;
							}
						}else {
							System.out.println();
						}
						int menu2 = 0;
						int accountNum = accImpl.getAccountNum(email);
						while (menu2 != 6) {
							System.out.println();
							System.out.println("1. Deposit Money");
							System.out.println("2. Withdraw Money");
							System.out.println("3. Transfer Money");
							System.out.println("4. View Balance");
							System.out.println("5. Delete Account");
							System.out.println("6. Exit");
							System.out.println("Enter you choice: ");
							menu2 = in.nextInt();
							switch (menu2) {
							case 1:
								accManagerImpl.depositMoney(accountNum);
								break;
							case 2:
								accManagerImpl.withdrawMoney(accountNum);
								break;
							case  3:
								accManagerImpl.transferMoney(accountNum);
								break;
							case 4:
								accManagerImpl.getBalance(accountNum);
								break;
							case 5: 
								accManagerImpl.deleteAccount(accountNum);
								
								break;
							case 6:
								System.out.println("Goodbye");
							default:
								break;
							}
						}
					}else {
						System.out.println("Invalid email or password");
						break;
					}
				case 3:
					in.nextLine();
					System.out.println("Thank you for coming in! Goodbye!");
				default:
					System.out.println("Choose a valid option");
					break;
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
