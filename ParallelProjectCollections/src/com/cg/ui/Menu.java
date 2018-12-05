package com.cg.ui;

import java.util.Scanner;
import com.cg.dto.Customer;
import com.cg.exception.InvalidAmount;
import com.cg.exception.InvalidPhoneNumber;
import com.cg.exception.NameException;
import com.cg.exception.WalletException;
import com.cg.service.WalletService;
import com.cg.service.WalletServiceImpl;

public class Menu 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		WalletService service = new WalletServiceImpl();

		int choice;
		do {
			
			//Menu to display user interface
			System.out.println("Enter your choice");
			System.out.println("1. Create the new customer account");
			System.out.println("2. Show balance of user");
			System.out.println("3. Fund transfer");
			System.out.println("4. Deposit amount into your account");
			System.out.println("5. Withdraw amount from your account");
			System.out.println("6. Exit");
			choice = sc.nextInt();

			switch (choice) 
			{
			case 1:

				String name;
				String mobNo;
				double amt;
				
				do{
					//To enter customer name
					System.out.println("Enter Customer Name: ");
					name = sc.next();
					try 
					{
						if (service.validateUserName(name))
							break;
					} 
					catch (NameException e1) {
						e1.printStackTrace();
					}
				}while(true);

				do{
					//To enter customer mobile number
					System.out.println("Enter Mobile Number: ");
					mobNo = sc.next();
					try 
					{
						if (service.validatePhoneNumber(mobNo))
							break;
					} 
					catch (InvalidPhoneNumber e1) 
					{
						e1.printStackTrace();
					}
				}while(true);

				do{
					//To enter customer initial amount
					System.out.println("Enter Initial Amount: ");
					amt = sc.nextDouble();
					try
					{
						if (service.validateAmount(amt))
							break;
					}
					catch (InvalidAmount e1) 
					{
						e1.printStackTrace();
					}
				}while(true);

				//Creating object for customer
				Customer c = new Customer(name, mobNo, amt);
				Customer c1 = null;
				try 
				{
					try 
					{
						if (service.validateAll(c))
							//Sending controller to service
							c1 = service.createAccount(c);
					} 
					catch (NameException | InvalidAmount | InvalidPhoneNumber e) 
					{
						e.printStackTrace();
					}
				} 
				catch (WalletException e) 
				{
					e.printStackTrace();
				}
				System.out.println("Successfully created new account for " + c1.getCustomerName() + " with "
						+ "Mobile Number " + c1.getMobileNumber());
				break;



			case 2:

				String mobNoShow;

				//To show details ----- Enter mobile number
				System.out.println("Enter an existing mobile number: ");
				mobNoShow = sc.next();
				double bal = 0;
				try
				{
					if (service.validatePhoneNumber(mobNoShow)){
						//Sending controller to service
						bal = service.showBalance(mobNoShow);
					}
					System.out.println("Available balance for the mobile number "+ mobNoShow + " is " + bal);
				} 
				catch (InvalidPhoneNumber | WalletException e) {
					System.err.println(e.getMessage());
				}
				break;

			case 3:

				String sourceMobileNo;
				String targetMobileNo;
				double amount;
				
				Customer funds = null;
				do{
					//To enter source mobile number
					System.out.println("Enter your mobile number: ");
					sourceMobileNo = sc.next();
					try 
					{
						if (service.validatePhoneNumber(sourceMobileNo))
							break;
					} 
					catch (InvalidPhoneNumber e) {
						System.err.println(e.getMessage());
					}
				}while(true);

				do{
					//To enter recipients mobile number
					System.out.println("Enter recipient's mobile number: ");
					targetMobileNo = sc.next();
					try 
					{
						if (service.validatePhoneNumber(sourceMobileNo))
							break;
					} 
					catch (InvalidPhoneNumber e) 
					{
						System.err.println(e.getMessage());
					}
				}while(true);

				do{
					//To enter the amount to be transfered
					System.out.println("Enter the amount that to be transfered: ");
					amount = sc.nextDouble();
					try
					{
						if (service.validateAmount(amount))
							break;
					} 
					catch (InvalidAmount e) {

						System.err.println(e.getMessage());
					}
				}while(true);

				try 
				{
					if (service.validatePhoneNumber(sourceMobileNo) && service.validateTargetMobNo(targetMobileNo) && service.validateAmount(amount))
						//Sending controller to service
						funds = service.fundTransfer(sourceMobileNo, targetMobileNo, amount);
					System.out.println("Successfully transfered Rs." + amount + " to " + targetMobileNo + ".\n" + "Available balance is Rs. " + funds.getAmount());

				}
				catch (InvalidPhoneNumber | InvalidAmount | WalletException e) 
				{
					System.err.println(e.getMessage());
				}
				break;


			case 4:

				String mobNoDep;
				double amtDep;
				Customer cDep = null;

				do{
					//To enter your mobile number
					System.out.println("Enter your mobile number: ");
					mobNoDep = sc.next();
					try 
					{
						if (service.validatePhoneNumber(mobNoDep))
							break;
					} 
					catch (InvalidPhoneNumber e)
					{
						System.err.println(e.getMessage());
					}
				}while(true);

				do{
					//Enter amount to deposit
					System.out.println("Enter amount that to be deposited: ");
					amtDep = sc.nextDouble();
					try 
					{
						if (service.validateAmount(amtDep))
							break;
					} 
					catch (InvalidAmount e)
					{
						System.err.println(e.getMessage());
					}
				}while(true);


				try 
				{
					if (service.validatePhoneNumber(mobNoDep) && service.validateAmount(amtDep))
						//Sending controller to service
						cDep = service.depositAmount(mobNoDep, amtDep);
					System.out.println("Your current balance is Rs."
							+ cDep.getAmount());
				} 
				catch (InvalidAmount | InvalidPhoneNumber | WalletException e) 
				{
					System.err.println(e.getMessage());
				}
				break;


			case 5:

				String mobNoWiDraw;
				double amtWiDraw;

				do{
					//To enter your mobile number
					System.out.println("Enter your mobile number: ");
					mobNoWiDraw = sc.next();
					try 
					{
						if (service.validatePhoneNumber(mobNoWiDraw))
							break;
					} 
					catch (InvalidPhoneNumber e)
					{
						System.err.println(e.getMessage());
					}
				}while(true);

				do{
					//Enter Amount to withdraw
					System.out.println("Enter amount that to be withdrawn: ");
					amtWiDraw = sc.nextDouble();
					try 
					{
						if (service.validateAmount(amtWiDraw))
							break;
					} 
					catch (InvalidAmount e) 
					{
						e.printStackTrace();
					}
				}while(true);

				Customer cWD = null;
				try {
					if (service.validatePhoneNumber(mobNoWiDraw) && service.validateAmount(amtWiDraw))
						//Sending controller to service
						cWD = service.withdrawAmount(mobNoWiDraw, amtWiDraw);
					System.out.println("Your current balance is Rs. " + cWD.getAmount());
				} 
				catch (InvalidAmount | WalletException | InvalidPhoneNumber e) 
				{
					System.err.println(e.getMessage());
				}
				break;


			case 6:
                //To exit from the application
				System.exit(0);

			}

		} while (choice != 7);

		sc.close();
	}

}
