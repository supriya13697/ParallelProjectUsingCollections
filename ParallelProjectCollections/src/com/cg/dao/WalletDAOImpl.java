package com.cg.dao;

import java.util.HashMap;
import java.util.Map;
import com.cg.dto.Customer;

public class WalletDAOImpl implements WalletDAO {

	Map<String, Customer> map;

	public WalletDAOImpl() {
		map = new HashMap<String, Customer>();
		map.put("7702725233", new Customer("Supriya", "7702725233", 500));
		map.put("9666062327", new Customer("Eswar", "9666062327", 1000));
		map.put("9849130686", new Customer("Rama", "9849130686", 1500));
		map.put("9666062327", new Customer("Kamala", "9666062327", 2000));
	}

	@Override
	public Customer createAccount(Customer c) {
		map.put(c.getMobileNumber(), c);
		return c;
	}

	@Override
	public Customer getAccount(String mobileno) {
		Customer cShow = map.get(mobileno);
		return cShow;
	}

	@Override
	public Customer setAccount(String mobileNo, double amount) {
		Customer cSet = map.get(mobileNo);
		cSet.setAmount(amount);
		System.out.println(cSet);
		return map.put(mobileNo, cSet);
	}
}
