package com.springoverallApplication.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_AppCountry")
public class AppCountry implements Serializable {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int countryId;
	@Column(name="countryName",nullable = true)
	private String countryName;
	@Column(name="isActive",nullable = true)
	private int isActive;
	@OneToMany(mappedBy = "appCountry")
	private List<AppState> steteList;
	@OneToMany(mappedBy = "appCountry")
	private List<AppUser> userList;	
	
	public AppCountry(int countryId) {
		super();
		this.countryId = countryId;
	}
	public AppCountry() {
		super();
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public List<AppState> getSteteList() {
		return steteList;
	}
	public void setSteteList(List<AppState> steteList) {
		this.steteList = steteList;
	}
	public List<AppUser> getUserList() {
		return userList;
	}
	public void setUserList(List<AppUser> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "AppCountry [countryId=" + countryId + ", countryName=" + countryName + ", isActive=" + isActive
				+ ", steteList=" + steteList + ", userList=" + userList + "]";
	}	
}
