package com.pojo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class RealInfo {
	
	@Id
	@GeneratedValue
	private long id ; 
	
	@Column
	private String domain ;
	
	@Column
	private String userID ; 
	
	@Column
	private String password ; 
	
	@Column
	private Date addDate ;
	
	@ManyToOne
	private CredInfo_internal credinfo ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

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

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public CredInfo_internal getCredinfo() {
		return credinfo;
	}

	public void setCredinfo(CredInfo_internal credinfo) {
		this.credinfo = credinfo;
	}
	
	

	public RealInfo() {
		super();
	}

	public RealInfo(String domain, String userID, String password, Date addDate, CredInfo_internal credinfo) {
		super();
		this.domain = domain;
		this.userID = userID;
		this.password = password;
		this.addDate = addDate;
		this.credinfo = credinfo;
	}

	public RealInfo(long id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "[Domain=" + domain + ", userID=" + userID + ", password=" + password + ", addDate=" + addDate
				+ "]\n";
	}
}
