package com.pojo;

import java.util.LinkedList;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CredInfo_internal {
	
	@Id
	private String userID ;
	
	private String password ; 
	
	@Column(name = "yourName")
	private String yourName ;
	
	@Column(name = "yourMob")
	private long yourMob ;
	
	@OneToMany(mappedBy = "credinfo" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RealInfo> realinfo = new LinkedList<>();

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RealInfo> getRealinfo() {
		return realinfo;
	}

	public void setRealinfo(List<RealInfo> realinfo) {
		this.realinfo = realinfo;
	}

	public void setYourName(String yourName) {
		this.yourName = yourName;
	}
	
	public String getYourName() {
		return yourName;
	}

	public long getYourMob() {
		return yourMob;
	}

	public void setYourMob(long yourMob) {
		this.yourMob = yourMob;
	}

	public CredInfo_internal() {
	}
	
	public CredInfo_internal(String userID, String password, String yourName, long yourMob) {
	    this.userID = userID;
	    this.password = password;
	    this.yourName = yourName;
	    this.yourMob = yourMob;
	}


	@Override
	public String toString() {
		return "CredInfo_internal [userID =" + userID + "\nrealinfo=" + realinfo + "]";
	}	
	
}
