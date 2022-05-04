package com.bridgelab.jdbcWS;

public class BankUserData {
	private String name;
	private String ifsccode;
	private int totalamount;
	
	public BankUserData(String name, String ifsccode, int totalamount) {
		this.name = name;
		this.ifsccode = ifsccode;
		this.totalamount = totalamount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public int getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}

	@Override
	public String toString() {
		return "BankUserData [name=" + name + ", ifsccode=" + ifsccode + ", totalamount=" + totalamount + "]";
	}
}
