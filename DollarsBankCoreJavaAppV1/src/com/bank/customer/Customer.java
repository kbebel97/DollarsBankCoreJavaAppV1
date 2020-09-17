package com.bank.customer;
import java.util.ArrayList;

import com.bank.account.Account;

public class Customer {
	
	public static long Id = 1;
	
	private long customerId;
	
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	private ArrayList<Account> accounts;
	
	private String customerName;
	
	private String address;
	
	private String phoneNumber;
	
	private String password;
	
	private Double initialDeposit;
	

	public Customer(String customerName, String address, String phoneNumber, String password) {
		this.customerName = customerName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.customerId = Id++;
		this.accounts = new ArrayList<Account>();
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
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public String getCustomerAddress() {
		return this.address;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public ArrayList<Account> getCustomerAccounts(){
		return this.accounts;
	}
	
	
	
	
	
	
	

}
