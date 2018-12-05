package com.cg.exception;

public class InvalidPhoneNumber extends Exception{
	public InvalidPhoneNumber() {
		super("*****Invalid Phone Number*****\n Mobile Number should be of length 10 digits\n It should not consists of any special characters or alphabets :)");
	}
}
