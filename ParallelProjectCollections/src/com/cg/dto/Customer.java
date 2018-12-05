package com.cg.dto;
public class Customer {
	
	private String customerName;
	private String mobileNumber;
	private double amount;
		
	public Customer(String name, String mobNo, double amt) 
	{
		this.customerName = name;
		this.mobileNumber = mobNo;
		this.amount = amt;
	}
	
	public Customer() 
	{
	}
	
	public String getCustomerName() 
	{
		return customerName;
	}
	
	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}
	
	public String getMobileNumber() 
	{
		return mobileNumber;
	}
	
	public void setMobileNumber(String mobileNumber) 
	{
		this.mobileNumber = mobileNumber;
	}
	
	public double getAmount() 
	{
		return amount;
	}
	
	public void setAmount(double amount) 
	{
		this.amount = amount;
	}

	public void fundTransfer(double amount) 
	{
		this.amount = this.amount - amount;
	}
	
	
	@Override
	public String toString() 
	{
		return "CustomerName: " + customerName + ", MobileNumber: "+ mobileNumber + ", Amount: " + amount;
	}
	
}
