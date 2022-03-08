package com.springoverallApplication.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springoverallApplication.demo.model.AppCountry;

public interface AppCountryREPO extends JpaRepository<AppCountry, Integer> {
	
	public List<AppCountry> findAllByIsActive(int isActive);

}
