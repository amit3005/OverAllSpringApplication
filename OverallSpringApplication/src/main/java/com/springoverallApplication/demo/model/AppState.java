package com.springoverallApplication.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_AppState")
public class AppState implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stateId;
	@Column(name="stateName",nullable = false)
	private String stateName;
	@Column(name="isActive",nullable = false)
	private int isActive;
	@ManyToOne
	@JoinColumn(name = "countryId")
	private AppCountry appCountry;
	@OneToMany(mappedBy = "appState")
	private List<AppUser> userList;
	
	public AppState() {
		super();
	}	
	public AppState(int stateId) {
		super();
		this.stateId = stateId;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public AppCountry getAppCountry() {
		return appCountry;
	}
	public void setAppCountry(AppCountry appCountry) {
		this.appCountry = appCountry;
	}
	public List<AppUser> getUserList() {
		return userList;
	}
	public void setUserList(List<AppUser> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "AppState [stateId=" + stateId + ", stateName=" + stateName + ", isActive=" + isActive + ", appCountry="
				+ appCountry + ", userList=" + userList + "]";
	}
}
