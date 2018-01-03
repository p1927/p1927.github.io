package com.cg.airspacetelecomm.bean;

public class UserBean {
	private int SNo;
	private String name;
	private String userName;
	private String pwd;
	private String mobileNo;
	private String bill="750";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String str) {
		this.mobileNo = str;
	}
	public String getBill() {
		return bill;
	}
	public void setBill(String bill) {
		this.bill = bill;
	}
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	
	

}
