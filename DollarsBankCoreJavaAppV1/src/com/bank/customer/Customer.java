package com.bank.customer;
import java.util.ArrayList;
import java.util.HashSet;

import com.bank.account.Account;

public class Customer {
	
	public static long Id = 1;
	
	private int numaccounts;
	
	private long customerId;
	
	public HashSet<String> accountNames;
	
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	private ArrayList<Account> accounts;
	
	private String customerName;
	
	private String address;
	
	private String phoneNumber;
	
	private String password;
	
	
	private String userName;
	

	public Customer(String customerName, String address, String phoneNumber, String userName, String password) {
		this.customerName = customerName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		this.customerId = Id++;
		this.accounts = new ArrayList<Account>();
		this.numaccounts = 0;
		this.accountNames = new HashSet<String>();
	}
	
	public void createAccount(String type, Double deposit) {
		String accountName = this.customerName + "'s " + type + " account";
		Account account = new Account(this.customerId, accountName, deposit);
		accounts.add(account);
	}
	
	public void deleteAccount(String type) {
		String accountName = this.customerName + "'s " + type + " account";
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getName().equals(accountName)) {
				accounts.get(i).withdrawalAll();
				accounts.remove(i);
				
			}
		}

	}
	
	public long getCustomerId() {
		return this.customerId;
	}
	
	public String getPassword() {
		return this.password;
	}
	public int getNumAccounts() {
		return this.numaccounts;
	}
	public void increaseNumAccounts() {
		this.numaccounts++;
	}
	
	public void decreaseNumAccounts() {
		this.numaccounts--;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public String getCustomerAddress() {
		return this.address;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public ArrayList<Account> getCustomerAccounts(){
		return this.accounts;
	}
	
	
	
	
	
	
	

}
