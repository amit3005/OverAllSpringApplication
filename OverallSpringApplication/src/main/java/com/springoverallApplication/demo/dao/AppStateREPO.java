package com.springoverallApplication.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springoverallApplication.demo.model.AppCountry;
import com.springoverallApplication.demo.model.AppState;

public interface AppStateREPO extends JpaRepository<AppState, Integer> {
	
	
	@Query(value="select s.stateId,s.stateName from AppState s where s.isActive=1 and s.appCountry.countryId=:countryId",nativeQuery = false)	 
	public List<Object[]> stateData(int countryId);
	//public List<AppState> findAllByAppCountry(AppCountry appCountry);
	
	public List<AppState> findAllByIsActive(int isActive);

}
