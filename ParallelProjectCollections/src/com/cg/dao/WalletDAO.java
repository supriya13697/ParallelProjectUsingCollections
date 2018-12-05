package com.cg.dao;

import com.cg.dto.Customer;

public interface WalletDAO {
	
	public Customer createAccount(Customer c);
	public Customer getAccount(String mobileno);
	public Customer setAccount(String mobileNo, double amount);
	
}
