package com.qa.hubspot.util;

public class Credentials {
	
	String appUsername;    // bu sinifi olumlu test case yani correct username and password icin kurdu
	String appPassword;    //getter lar olumlu test case icin
	                       // setter lar dataproviderdaki invalid username password icin gerekli

	public Credentials(String appUsername , String appPassword){
		this.appUsername = appUsername;
		this.appPassword = appPassword;
	}

	public String getAppUsername() {
		return appUsername;
	}

	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}

	public String getAppPassword() {
		return appPassword;
	}

	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
}
