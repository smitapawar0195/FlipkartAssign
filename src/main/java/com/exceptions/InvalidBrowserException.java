package com.exceptions;

public class InvalidBrowserException extends Exception{
	private static final long serialVersionUID = 1L;
	private String msg;

	public InvalidBrowserException(String browserName) {
		this.msg = "InvalidBrowserException" + browserName;
	}

	@Override
	public String toString() {
		return msg;
	}
}
