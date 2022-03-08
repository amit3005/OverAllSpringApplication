package com.springoverallApplication.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springoverallApplication.demo.model.AppUser;

public interface AppUserREPO extends JpaRepository<AppUser, Integer> {
	
	public AppUser findByAppUserEmailId(String emailId);

}
