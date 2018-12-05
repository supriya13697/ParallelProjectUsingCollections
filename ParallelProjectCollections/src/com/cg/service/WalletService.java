package com.cg.service;

import com.cg.dto.Customer;
import com.cg.exception.InvalidAmount;
import com.cg.exception.InvalidPhoneNumber;
import com.cg.exception.NameException;
import com.cg.exception.WalletException;

public interface WalletService {
	
	public Customer createAccount(Customer c) throws WalletException;
	public double showBalance (String mobileno) throws InvalidPhoneNumber, WalletException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, double amount) throws WalletException;
	public Customer depositAmount (String mobileNo, double amount ) throws WalletException;
	public Customer withdrawAmount(String mobileNo, double amount) throws WalletException;
	
	public boolean validateUserName(String name) throws NameException;
	public boolean validatePhoneNumber(String mobNo) throws InvalidPhoneNumber;
	public boolean validateTargetMobNo(String targetMobNo) throws InvalidPhoneNumber;
	public boolean validateAmount(double amt) throws InvalidAmount;
	public boolean validateAll(Customer c) throws WalletException, NameException, InvalidAmount, InvalidPhoneNumber;
}
