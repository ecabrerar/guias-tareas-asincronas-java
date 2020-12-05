package com.eudriscabrera.examples.java.contacting;

public class Message {

	private final String msg;
	private final String email1;
	private final String email2;
	private final String mobileNumber;
	private final String whatsAppNumber;


	public Message(String msg, String email1, String email2, String mobileNumber, String whatsAppNumber) {
		this.msg = msg;
		this.email1 = email1;
		this.email2 = email2;
		this.mobileNumber = mobileNumber;
		this.whatsAppNumber = whatsAppNumber;
	}

	public String getMsg() {
		return msg;
	}

	public String getEmail1() {
		return email1;
	}

	public String getEmail2() {
		return email2;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getWhatsAppNumber() {
		return whatsAppNumber;
	}
}
