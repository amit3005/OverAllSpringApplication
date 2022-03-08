package com.springoverallApplication.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_AppUser")
public class AppUser implements Serializable {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int appUserId;
	@Column(name="appUserName",nullable = false)
	private String appUserName;
	@Column(name="appUserEmailId",nullable = false)
	private String appUserEmailId;
	@Column(name="appUserMobileNumber",nullable = false)
	private String appUserMobileNumber;
	@ManyToOne
	@JoinColumn(name = "countryId")
	private AppCountry appCountry;
	@ManyToOne
	@JoinColumn(name = "stateId")
	private AppState appState;
	@Column(name="appUserCity",nullable = false)
	private String appUserCity;
	@Column(name="appUserPassword",nullable = false)
	private String appUserPassword;
	@ManyToMany(cascade = CascadeType.MERGE ,fetch = FetchType.EAGER)
	@JoinTable(
			name="user_role",
			joinColumns = {@JoinColumn(name="app_user_id")},
			inverseJoinColumns = {@JoinColumn(name="role_id")}
			)
	private List<Role> rolesList;
	
	public AppUser() {
		super();
	}
	public AppUser(AppUser user) {
		this.appUserId = user.getAppUserId();
		this.appUserName = user.getAppUserName();
		this.appUserEmailId = user.getAppUserEmailId();
		this.appUserMobileNumber = user.getAppUserMobileNumber();
		this.appCountry = user.getAppCountry();
		this.appState = user.getAppState();
		this.appUserCity = user.getAppUserCity();
		this.appUserPassword = user.getAppUserPassword();
		this.rolesList = user.getRolesList();
	}
	public int getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(int appUserId) {
		this.appUserId = appUserId;
	}
	public String getAppUserName() {
		return appUserName;
	}
	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}
	public String getAppUserEmailId() {
		return appUserEmailId;
	}
	public void setAppUserEmailId(String appUserEmailId) {
		this.appUserEmailId = appUserEmailId;
	}
	public String getAppUserMobileNumber() {
		return appUserMobileNumber;
	}
	public void setAppUserMobileNumber(String appUserMobileNumber) {
		this.appUserMobileNumber = appUserMobileNumber;
	}
	public AppCountry getAppCountry() {
		return appCountry;
	}
	public void setAppCountry(AppCountry appCountry) {
		this.appCountry = appCountry;
	}
	public AppState getAppState() {
		return appState;
	}
	public void setAppState(AppState appState) {
		this.appState = appState;
	}
	public String getAppUserCity() {
		return appUserCity;
	}
	public void setAppUserCity(String appUserCity) {
		this.appUserCity = appUserCity;
	}
	public String getAppUserPassword() {
		return appUserPassword;
	}
	public void setAppUserPassword(String appUserPassword) {
		this.appUserPassword = appUserPassword;
	}	
	public List<Role> getRolesList() {
		return rolesList;
	}
	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}
	@Override
	public String toString() {
		return "AppUser [appUserId=" + appUserId + ", appUserName=" + appUserName + ", appUserEmailId=" + appUserEmailId
				+ ", appUserMobileNumber=" + appUserMobileNumber + ", appCountry=" + appCountry + ", appState="
				+ appState + ", appUserCity=" + appUserCity + ", appUserPassword=" + appUserPassword + ", rolesList="
				+ rolesList + "]";
	}
	
}
