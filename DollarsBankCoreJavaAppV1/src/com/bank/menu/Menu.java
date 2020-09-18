package com.bank.menu;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import com.bank.customer.Customer;
import com.bank.account.Account;
public class Menu {
	static Scanner scanner = new Scanner(System.in);
	static boolean exit = true;

	public static void main(String[] args) {
		while(exit) {
			System.out.println("Welcome to DollarsBank!");
			System.out.println("1. Login");
			System.out.println("2. Create an Account");
			System.out.println("3. Exit");
			
			String option1 = scanner.next();
			
			switch(option1) {
				case "1":
					Login();
					break;
				case "2":
					createAccount();
					break;
				default:
					break;
			}
			if(option1.equals("Exit"))
				break;
		}
	
	}
	
	public static void Login() {
		while(true) {
			System.out.println("Enter Username:");
			String userName = scanner.next();
			System.out.println("Enter Password:");
			String password = scanner.next();
			Customer customer = null;
			for(int i = 0; i < Customer.customers.size(); i++) {
				if(Customer.customers.get(i).getUserName().equals(userName) && Customer.customers.get(i).getPassword().equals(password)){
					customer = Customer.customers.get(i);
					customerMenu(customer);			
				}	
			}
			String response1 = null;
			if(customer == null) {
				while(true) {
					System.out.println("Login Error. No such user exists. Would you like to login as a different customer? Yes or No? ");
					response1 = scanner.next();
					if(response1.equals("Yes")||response1.equals("No")) {
						break;
					}
					else {
						System.out.println("Invalid. Please choose either Yes or No!");
						continue;
					}
					
				}
				
			}
			else break;
			if(response1.equals("Yes")) {
				continue;
			}
			if(response1.equals("No")) {
				break;
			}
		}
	}
	
	public static void customerMenu(Customer customer) {
		while(true) {
			System.out.println("Welcome " + customer.getCustomerName());
			System.out.println("1. Deposit");
			System.out.println("2. Withdrawal");
			System.out.println("3. Transfer");
			System.out.println("4. View recent transactions");
			System.out.println("5. View Customer Information");
			System.out.println("6. SignOut");

			String option2 = scanner.next();
			
			switch(option2) {
			case "1":
				Deposit(customer);
			break;
			case "2":
				Withdrawal(customer);
			break;
			case "3":
				Transfer(customer);
			break;
			case "4":
				recentTransactions(customer);
			break;
			case "5":
				displayCustomerInfo(customer);
			break;
			}
			if(option2.equals("6")) 
				break;
			}
	}
	
	public static void Deposit(Customer customer) {
		while(true) {
			System.out.println("Enter deposit amount: ");
			Double amount = Double.valueOf(scanner.next());
			String type = null;
			while(true) {
				System.out.println("Enter account type: (Checking Or Savings)");
				type = scanner.next();
				if(type.equals("Checking")||type.equals("Savings"))
					break;
				else {System.out.println("Invalid. Please choose either a Checking or Savings account!");
						continue;}
			}
			String accountName = customer.getCustomerName() + "'s " + type + " account";
			for(int i = 0; i < customer.getCustomerAccounts().size(); i++) {
				if(customer.getCustomerAccounts().get(i).getName().equals(accountName)&&customer.getCustomerAccounts().get(i).getId()==(customer.getCustomerId())) {
					customer.getCustomerAccounts().get(i).Deposit(amount);
				}
			}
			String answer = null;
			while(true) {
				System.out.println("Deposit to another account? ");
				answer = scanner.next();
				if(answer.equals("Yes")||answer.equals("No")) {
					break;
				}
				else {System.out.println("Invalid. Please choose either Yes or No!");}
			}
			if(answer.equals("Yes")) {
				continue;
			}
			else if(answer.equals("No")) {
				break;
			}
		
		}
		
	}
	
	public static void Withdrawal(Customer customer) {
		while(true) {
			System.out.println("Enter withdrawal amount: ");
			Double amount = Double.valueOf(scanner.next());
			String type = null;
			while(true) {
				System.out.println("Enter account type: (Checking Or Savings)");
				type = scanner.next();
				if(type.equals("Checking")||type.equals("Savings")) {
					break;
				}
				else {
					System.out.println("Invalid. Please choose either a Checking or Savings account!");
					continue;
				}
			}
			String accountName = customer.getCustomerName() + "'s " + type + " account";
			for(int i = 0; i < customer.getCustomerAccounts().size(); i++) {
				if(customer.getCustomerAccounts().get(i).getName().equals(accountName)&&customer.getCustomerAccounts().get(i).getId()==(customer.getCustomerId())) {
					while(!customer.getCustomerAccounts().get(i).Withdrawal(amount)) {
						amount = Double.valueOf(scanner.next());
					};
				}
			}
			String answer = null;
			while(true) {
				System.out.println("Withdraw from another account? ");
				answer = scanner.nextLine();
				if(answer.equals("Yes")||answer.equals("No"))
					break;
			}
			if(answer.equals("Yes")) {
				continue;
			}
			else break;
		}
		
	}
	
	public static void Transfer(Customer customer) {
		while(true) {
			String from = null;
			String to = null;
			Double amount = 0.0;
			while(true) {
				System.out.println("Enter transfer amount: ");
				amount = scanner.nextDouble();
				System.out.println("Transfer from: (Checking Or Savings)");
				from = scanner.next();
				System.out.println("Transfer to: (Checking or Savings");
				to = scanner.next();
				if(to.equals(from))
					continue;
				if((from.equals("Checking")||from.equals("Saving"))&&(to.equals("Checking")||to.equals("Saving"))) 
					break;
				
			}
			String accountFrom = customer.getCustomerName() + "'s " + from + " account";
			String accountTo = customer.getCustomerName() + "'s " + to + " account";
			for(int i = 0; i < customer.getCustomerAccounts().size(); i++) {
				if(customer.getCustomerAccounts().get(i).getName().equals(accountTo)&&customer.getCustomerAccounts().get(i).getId()==customer.getCustomerId())
					for(int j = 0; j < customer.getCustomerAccounts().size(); j++) {
					if(customer.getCustomerAccounts().get(j).getName().equals(accountFrom)&&customer.getCustomerAccounts().get(j).getId()==customer.getCustomerId()) {
					   customer.getCustomerAccounts().get(j).transfer(customer.getCustomerAccounts().get(i), amount);
					}
				}
			}
			String answer = null;
			while(true) {
				System.out.println("Transfer from another account? ");
				answer = scanner.next();
				if(answer.equals("Yes")||answer.equals("No"))
					break;
			}
			if(answer.equals("Yes")) {
				continue;
			}
			else break;
		}
		
	}
	
	public static void recentTransactions(Customer customer) {
		while(true) {
			String type = null;
			while(true) {
				System.out.println("Enter account type: (Checking Or Savings)");
				type = scanner.next();
				if(type.equals("Checking")||type.equals("Savings"))
					break;
				else {
					System.out.println("Invalid. Please choose either a Checking or Savings account!");
					continue;
				}
			}
			String accountName = customer.getCustomerName() + "'s " + type + " account";
			for(int i = 0; i< customer.getCustomerAccounts().size(); i++) {
				if(customer.getCustomerAccounts().get(i).getName().equals(accountName)) {
					ArrayList<String> history = customer.getCustomerAccounts().get(i).getTransactionHistory();
					for(int j = 0; j< history.size(); j++) {
						System.out.println(history.get(j));
					}
				}
			}
			String answer = null;
			while(true) {
				System.out.println("Transaction History from another account? ");
				answer = scanner.next();
				if(answer.equals("Yes")||answer.equals("No")) 
					break;
				else {
					System.out.println("Invalid. Please choose either Yes or No!");
					continue;
				}
				
			}
			if(answer.equals("Yes")) {
				continue;
			}
			else break;
		}
	}
	
	public static void displayCustomerInfo(Customer customer) {
		while(true) {
			System.out.println("ID: " + customer.getCustomerId());
			System.out.println("Username: " + customer.getUserName());
			System.out.println("Name: " + customer.getCustomerName());
			System.out.println("Address: " + customer.getCustomerAddress());
			System.out.println("Phone Number: " + customer.getPhoneNumber());
			System.out.println("Accounts: ");
			for(int i = 0; i< customer.getCustomerAccounts().size();i++) {
				System.out.println(customer.getCustomerAccounts().get(i).getId());
				System.out.println(customer.getCustomerAccounts().get(i).getName());
				System.out.println(customer.getCustomerAccounts().get(i).getBalance());

			}
			String answer = null;
			while(true) {
				System.out.println("Exit? ");
				answer = scanner.next();
				if(answer.equals("Yes")||answer.equals("No")) {
					break;
				}
				else {
					System.out.println("Invalid. Please choose either Yes or No!");
					continue;
				}
			}
			if(answer.equals("Yes")) 
				break;
			

		}
	}
	
	public static void createAccount() {
		
		while(true) {
			System.out.println("Enter your first Name: ");
			String first = scanner.next();
			System.out.println("Enter your last Name: ");
			String last = scanner.next();
			System.out.println("Enter your address: ");
			String address = scanner.next();
			System.out.println("Enter a phoneNumber: ");
			String phoneNumber = scanner.next();
			System.out.println("Enter a Username: ");
			String userName = scanner.next();
			System.out.println("Enter a password : ");
			String password = scanner.next();
			
			String name = first + " " + last;
			Customer customer = new Customer(name, address, phoneNumber, userName, password);
			Customer.customers.add(customer);
			String accountType = null;
			while(true) {
				System.out.println("Account you would like to open? (Checking or Savings)");
				accountType = scanner.next();
				
				if(accountType.equals("Checking")||accountType.equals("Savings")) {						
					Double deposit = 0.0;
					if(customer.accountNames.contains(accountType)) {
						System.out.println("Account already exists! ");
						continue;
					}
					while(true) {
						System.out.println("Initial Deposit: ");
						deposit = Double.valueOf(scanner.next());
						if(deposit > 100.00) {
							break;
						}
						else {
							System.out.println("Initial Deposit must be over $100.00!");
							continue;
						}
					}
					customer.createAccount(accountType, deposit);
					customer.increaseNumAccounts();
					customer.accountNames.add(accountType);
				}
				else {
					System.out.println("Invalid. Please enter either a Checking or Savings account!");
					continue;
				}
				String answer = null;
				while(true) {
					System.out.println("Would you like to add another account? (Yes or No) ");
					answer = scanner.next();
					if(answer.equals("Yes")||answer.equals("No")){
						break;
					}
					else {
						System.out.println("Invalid. Please choose either Yes or No!");
					}
				}
			
				if(answer.equals("Yes")&&customer.getNumAccounts()==2) {
					System.out.println("No more than 2 accounts per Customer!");
					break;
				}
				if(answer.equals("Yes")) {
					continue;
				}
				else if(answer.equals("No")) {
					break;
				};
			}
			break;
		}
	}
}
