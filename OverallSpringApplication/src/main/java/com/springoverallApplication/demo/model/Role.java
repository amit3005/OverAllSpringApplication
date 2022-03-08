package com.springoverallApplication.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rid;
	@Column(nullable = false,unique = true)
	private String roleName;
	@ManyToMany(mappedBy = "rolesList")
	private List<AppUser> userList;
	public int getRid() {
		return rid;
	}
	public void setRid(int id) {
		this.rid = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<AppUser> getUserList() {
		return userList;
	}
	public void setUserList(List<AppUser> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "Role [id=" + rid + ", roleName=" + roleName + ", userList=" + userList + "]";
	}	
}
