package com.cg.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.dao.WalletDAO;
import com.cg.dao.WalletDAOImpl;
import com.cg.dto.Customer;
import com.cg.exception.WalletException;
import com.cg.exception.InvalidAmount;
import com.cg.exception.InvalidPhoneNumber;
import com.cg.exception.NameException;

public class WalletServiceImpl implements WalletService{

	WalletDAO dao;
	
	public WalletServiceImpl(){
		dao= new WalletDAOImpl();
	}
	
	@Override
	public Customer createAccount(Customer c) 
	{
		return dao.createAccount(c);
	}

	@Override
	public double showBalance(String mobileno) throws InvalidPhoneNumber,WalletException 
	{
		Customer bal = dao.getAccount(mobileno);
		if (bal == null)
			throw new WalletException("Mobile number doesn't exist");
		return bal.getAmount();
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,double amount) throws WalletException {
		Customer sbal = dao.getAccount(sourceMobileNo);
		Customer tbal = dao.getAccount(targetMobileNo);
		if (sbal == null)
			throw new WalletException("Mobile number doesn't exist");
		if (tbal == null)
			throw new WalletException("Mobile number doesn't exist");
		double tmp = (sbal.getAmount() - amount);
		if (tmp >= 0) {
			dao.setAccount(targetMobileNo, tbal.getAmount() + amount);
			dao.setAccount(sourceMobileNo, sbal.getAmount() - amount);
		} else {
			throw new WalletException("Minimum balance of Rupees greater than zero should be available...");
		}
		System.out.println(dao.getAccount(sourceMobileNo));
		return dao.getAccount(sourceMobileNo);
	}

	@Override
	public Customer depositAmount(String mobileNo, double amount) throws WalletException {
		Customer cDep1 = dao.getAccount(mobileNo);
		if (cDep1 == null)
			throw new WalletException("Mobile number doesn't exist");
		Customer c = dao.setAccount(mobileNo, cDep1.getAmount() + amount);
		System.out.println(c);
		Customer cDep = dao.getAccount(mobileNo);
		return cDep;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, double amount)throws WalletException {
		Customer c;
		Customer cDep1 = dao.getAccount(mobileNo);
		if (cDep1 == null)
			throw new WalletException("Mobile number doesn't exist");
		try {
			if ((cDep1.getAmount() - amount) >= 0)
				c = dao.setAccount(mobileNo, cDep1.getAmount() - amount);
			else{
				throw new WalletException("Invalid");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Amount should be greater than existing account");
		}
		Customer cDep = dao.getAccount(mobileNo);
		return cDep;
	}

	@Override
	public boolean validateAll(Customer c) throws WalletException, NameException, InvalidAmount, InvalidPhoneNumber {

		boolean b = false;

		if (validateUserName(c.getCustomerName()) == true && validatePhoneNumber(c.getMobileNumber()) == true && validateAmount(c.getAmount()) == true)
			b = true;
		
		if (!b)
			throw new WalletException("Invalid details");
		
		return b;
	}

	@Override
	public boolean validateUserName(String name) throws NameException {

		Pattern p = Pattern.compile("[A-Z]{1}[a-z]{2,30}");
		Matcher mat = p.matcher(name);
		boolean b = mat.matches();

		if (!b)
			throw new NameException();

		return b;
	}

	@Override
	public boolean validatePhoneNumber(String mobileNo) throws InvalidPhoneNumber {

		Pattern pat = Pattern.compile("[6-9]{1}[0-9]{9}");
		Matcher mat = pat.matcher(mobileNo);
		boolean b = mat.matches();

		if (!b)
			throw new InvalidPhoneNumber();

		return b;
	}

	@Override
	public boolean validateAmount(double amt) throws InvalidAmount {

		Pattern pat = Pattern.compile("[1-9]{1}[0-9.]{0,9}");
		Matcher mat = pat.matcher(String.valueOf(amt));
		boolean b = mat.matches();

		if (!b)
			throw new InvalidAmount();

		return b;
	}

	@Override
	public boolean validateTargetMobNo(String targetMobNo) throws InvalidPhoneNumber {

		Pattern pat = Pattern.compile("[6-9]{1}[0-9]{9}");
		Matcher mat = pat.matcher(targetMobNo);
		boolean b = mat.matches();

		if (!b)
			throw new InvalidPhoneNumber();

		return b;
	}
}
