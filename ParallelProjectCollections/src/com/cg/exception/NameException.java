package com.cg.exception;

public class NameException extends Exception{
	public NameException() {
		super("*****Invalid Name declaration***** First letter should be capital remaining should be small letter... :)");
	}
}
