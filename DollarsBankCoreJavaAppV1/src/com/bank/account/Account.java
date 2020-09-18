package com.bank.account;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private Long customerId;
	private String Name;
	private double balance = 0;
	
	private ArrayList<String> transactionHistory;

	public Account(Long customerId, String Name, double initialDeposit) {
		super();
		this.customerId = customerId;
		this.Name = Name;
		this.balance += initialDeposit; 
		this.transactionHistory = new ArrayList<>();
		String statement = "Deposit of " + initialDeposit + "; Balance is now " + this.balance;
		transactionHistory.add(statement);
	}
	
	
	public void Deposit(double amount) {
		this.balance += amount;
		System.out.println( "Successfully Deposited");
		String statement = "Deposit of " + amount + "; Balance is now " + this.balance;
		transactionHistory.add(0, statement);
	}
	
	public void withdrawalAll() {
		System.out.println("Withdrawal of " + this.balance + " dollars");
		this.balance = 0;
		
	}
	
	
	public boolean Withdrawal(double amount) {
		if(this.balance - amount < 0) {
			System.out.println("amount requested exceeds your balance");
			return false;
		}
		else {
			this.balance = this.balance - amount;
			System.out.println("Balance is now " + this.balance);
			String statement = "Withdrawal of " + amount + "; Balance is now " + this.balance;
			transactionHistory.add(0, statement);
			return true;
		}
		
	}
	public String getName() {
		return this.Name;
	}

	public double getBalance() {
		return this.balance;
	}
	
	public Long getId() {
		return this.customerId;
	}
	
	public ArrayList<String> getTransactionHistory(){
		return this.transactionHistory;
	}
	
	
	public void transfer(Account account, double amount) {
		if(account!=null)
			if(this.balance - amount >= 0) {
				this.balance = this.balance - amount;
				account.Deposit(amount);
				System.out.println("Successfully transferred");
				String statement = "Transferred " + amount + " from " + this.Name + " to " + account.getName() + "Balance is now " + this.balance;
				transactionHistory.add(0, statement);
				System.out.println(statement);

			}
			else {
				System.out.println("amount requested for transfer exceeds your balance");
			}
		else {
			System.out.println("account was not found");
		}
		
		
	}

	
	
	
	
	
	

}
